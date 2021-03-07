/* Amogh Yatnatti
 * CS 4348.
 * 02/27/2021
 * Program Description: 
*/

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <string>
#include <iostream>
#include <fstream>
#include <ctime>

// Pipes
int CpuToMem[2];
int MemToCpu[2];

// Global variable when the CPU enters interrupt/kernel mode
bool interrupt = false;

// CPU receives instruction/value/address from memory at index
static int readMem(int index)
{
    char buf[6];
    // Checks if the user is accessing restricted part of memory
    if (index > 999 && !interrupt)
    {
        // Prints error msg and exits program
        printf("Memory violation: accessing system address %d in user mode\n", index);
        snprintf(buf, 6, "q");
        write(CpuToMem[1], buf, 6);
        wait(NULL);
        exit(1);
    }
    // Writes index to child process
    snprintf(buf, 6, "r%d", index);
    write(CpuToMem[1], buf, 6);

    // Reads value at index from child process and returns it
    char val[5];
    read(MemToCpu[0], val, 5);
    return atoi(val);
}

// CPU writes instruction/value/address to memory
static void writeMem(int index, int data)
{
    char buf[6];
    // Checks if the user is accessing restricted part of memory
    if (index > 999 && !interrupt)
    {
        // Prints error msg and exits program
        printf("Memory violation: accessing system address %d in user mode\n", index);
        snprintf(buf, 6, "q");
        write(CpuToMem[1], buf, 6);
        wait(NULL);
        exit(1);
    }

    // Writes index to child process
    snprintf(buf, 6, "w%d", index);
    write(CpuToMem[1], buf, 6);

    // Writes value to be inserted to child process
    char val[5];
    snprintf(val, 5, "%d", data);
    write(CpuToMem[1], val, 5);
}

int main(int argc, char *argv[])
{
    // Command-line argument check
    if (argc != 3)
    {
        printf("2 arguments required for operation: input filename and timer interrupt value\n");
        exit(1);
    }

    pid_t pid;

    std::string filename = argv[1];

    // Pipe check
    if (pipe(CpuToMem) < 0 || pipe(MemToCpu) < 0)
    {
        printf("The pipe failed");
        exit(1);
    }

    std::ifstream input(filename);

    // Invalid filename check
    if (!input.is_open())
    {
        printf("Invalid filename");
        exit(1);
    }

    // Creates fork
    switch (pid = fork())
    {
    case -1:
        printf("The fork failed!");
        exit(1);
    // Memory
    case 0:
    {
        // Load input file into memory
        int mem[2000]; // Mimics memory storage
        int index = 0;

        while (input)
        {
            // Gets each line of input
            std::string line;
            getline(input, line);

            // Gets the integer from every line and stores it into memory
            int temp;
            if (line.length() > 0)
            {
                // Checks if first character is an integer
                if (isdigit(line.at(0)))
                {
                    // Stores each integer into memory
                    temp = std::stoi(line.substr(0));
                    mem[index] = temp;
                    index++;
                }
                // Checks if first character is '.' and jumps index to that address
                else if (line.at(0) == '.')
                {
                    temp = std::stoi(line.substr(1));
                    index = temp;
                }
            }
        }
        // Read/Write operations
        while (true)
        {
            // Reads instruction from CPU
            char buf[6];
            read(CpuToMem[0], buf, 6);

            char command = buf[0];

            char addr[5];
            for (int i = 1; i < 6; i++)
                addr[i - 1] = buf[i];

            int addrInt = atoi(addr);
            // Reads value from memory and sends it back to CPU
            if (command == 'r')
            {
                int read = mem[addrInt];
                char readVal[5];
                snprintf(readVal, 5, "%d", read);
                write(MemToCpu[1], readVal, 5);
            }
            // Writes value to memory
            else if (command == 'w')
            {
                char data[5];
                read(CpuToMem[0], data, 5);
                mem[addrInt] = atoi(data);
            }
            // Quits memory process
            else if (command == 'q')
            {
                _exit(0);
            }
        }
    }
    break;
    // CPU
    default:
    {
        // CPU Registers
        int pc = 0, sp = 999, ir, ac, x, y;

        // Stores PC and SP values from user mode
        int userPC, userSP;

        // Stores timer interrupt value
        int intrTime = std::stoi(argv[2]);
        srand(time(0));

        // Instruction counter
        int inst = 0;

        // Keeps track of the timer interrupts
        int timerIntQueue = 0;

        while (true)
        {
            // If its time for the time interrupt, we add it to the queue
            if (inst && inst % intrTime == 0)
                timerIntQueue++;

            //Timer interrupt
            if (!interrupt && timerIntQueue)
            {
                // Doing interrupt so we remove it from queue
                timerIntQueue--;

                interrupt = true;

                userSP = sp;
                userPC = pc - 1;

                // Updates PC and SP for timer interrupt
                sp = 1999;
                pc = 1000;

                // Writes current SP and PC into stack
                writeMem(sp, userSP);
                sp--;
                writeMem(sp, userPC);
                sp--;
            }

            // Fetches instruction into IR
            ir = readMem(pc);
            inst++;
            switch (ir)
            {
            // Load value
            case 1:
            {
                pc++;
                // Loads value into AC
                ac = readMem(pc);
                break;
            }
            // Load addr
            case 2:
            {
                pc++;
                // Retrieves address
                int addr = readMem(pc);
                // Loads value at address into AC
                ac = readMem(addr);
                break;
            }
            // LoadInd addr
            case 3:
            {
                pc++;
                // Retrieves address
                int addr = readMem(pc);
                // Retreieves address at address
                addr = readMem(addr);
                // Loads value at address into AC
                ac = readMem(addr);
                break;
            }
            // LoadIdxX addr
            case 4:
            {
                pc++;
                // Retrieves address and adds x
                int xAddr = readMem(pc) + x;
                // Loads value at address into AC
                ac = readMem(xAddr);
                break;
            }
            // LoadIdxY addr
            case 5:
            {
                pc++;
                // Retrieves address and adds y
                int yAddr = readMem(pc) + y;
                // Loads value at address into AC
                ac = readMem(yAddr);
                break;
            }
            // LoadSpX
            case 6:
            {
                // Loads value at stack address + x
                ac = readMem(sp + x + 1);
                break;
            }
            // Store addr
            case 7:
            {
                pc++;
                // Retrieves address
                int addr = readMem(pc);
                // Writes AC at address index
                writeMem(addr, ac);
                break;
            }
            // Get
            case 8:
            {
                // Generates a random int from 1-100
                ac = (rand() % 100) + 1;
                break;
            }
            // Put port
            case 9:
            {
                pc++;
                // Prints integer or character depending on port
                int port = readMem(pc);
                if (port == 1)
                {
                    printf("%d", ac);
                }
                else if (port == 2)
                {
                    printf("%c", ac);
                }
                else
                {
                    printf("Error invalid port!");
                    exit(1);
                }
                break;
            }
            // AddX
            case 10:
                ac += x;
                break;
            // AddY
            case 11:
                ac += y;
                break;
            // SubX
            case 12:
                ac -= x;
                break;
            // SubY
            case 13:
                ac -= y;
                break;
            // CopyToX
            case 14:
                x = ac;
                break;
            // CopyFromX
            case 15:
                ac = x;
                break;
            // CopyToY
            case 16:
                y = ac;
                break;
            // CopyFromY
            case 17:
                ac = y;
                break;
            // CopyToSp
            case 18:
                sp = ac;
                break;
            // CopyFromSp
            case 19:
                ac = sp;
                break;
            // Jump addr
            case 20:
            {
                pc++;
                // PC jumps to the address recieved
                pc = readMem(pc) - 1;
                break;
            }
            // JumpIfEqual addr
            case 21:
            {
                if (ac == 0)
                {
                    pc++;
                    pc = readMem(pc) - 1;
                }
                else
                {
                    pc++;
                }
                break;
            }
            // JumpIfNotEqual addr
            case 22:
            {
                if (ac != 0)
                {
                    pc++;
                    pc = readMem(pc) - 1;
                }
                else
                {
                    pc++;
                }
                break;
            }
            // Call addr
            case 23:
            {
                pc++;
                // Gets address
                int addr = readMem(pc);
                // Pushes return address onto stack
                writeMem(sp, pc + 1);
                sp--;
                // Changes PC to address received
                pc = addr - 1;
                break;
            }
            // Ret
            case 24:
                sp++;
                // Pops return address and jumps to that address
                pc = readMem(sp) - 1;
                break;
            // IncX
            case 25:
                x++;
                break;
            // DecX
            case 26:
                x--;
                break;
            // Push
            case 27:
            {
                writeMem(sp, ac);
                sp--;
                break;
            }
            // Pop
            case 28:
            {
                sp++;
                ac = readMem(sp);
                break;
            }
            // Int
            case 29:
                if (!interrupt)
                {
                    interrupt = true;

                    userSP = sp;
                    userPC = pc;

                    // Updates PC and SP for timer interrupt
                    sp = 1999;
                    pc = 1499;

                    // Writes current SP and PC into stack
                    writeMem(sp, userSP);
                    sp--;
                    writeMem(sp, userPC);
                    sp--;
                    break;
                }
            // IRet
            case 30:
            {
                // Fetches current SP and PC from stack and ends interrupt
                sp++;
                pc = readMem(sp);
                sp++;
                sp = readMem(sp);
                interrupt = false;
                break;
            }
            // End
            case 50:
                // Sends instruction to end child process
                char buf[6];
                snprintf(buf, 6, "q");
                write(CpuToMem[1], buf, 6);

                // Closes all pipes
                close(CpuToMem[0]);
                close(CpuToMem[1]);
                close(MemToCpu[0]);
                close(MemToCpu[1]);
                wait(NULL);
                exit(0);
                break;
            default:
                printf("Invalid command: %d\n", ir);
                exit(1);
                break;
            }
            pc++;
        }
    }
    }
}