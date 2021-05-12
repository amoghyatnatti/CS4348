# CS4348
Projects from CS 4348 (Operating Systems)

## Project 1
The purpose of the project was to learn and mimic how a CPU processes instructions with the incorporation of two processes: one responsible for memory and another responsible for the CPU. While we are not necessarily creating a CPU or memory, we are mimicking the functions of them through a high-level language. In our mock CPU, we have a PC, SP, IR, AC, X, and Y register. These registers still serve the same purpose as they would in an actual CPU. The PC and SP registers are used to access indexes in memory, the IR holds instruction values, and the AC, X, and Y registers are used to store data. With our mock memory, we are making array with 2000 elements with the first half being dedicated for the user program and the second half being dedicated for the system code. The CPU makes sure we are not accessing system code when we should be accessing the user program. We also have a user stack that is at the end of the user program memory and a system stack that is at the end of the system memory. We also are including mock interrupts as well. There are two types: timer and a system call. The timer interrupts execute at 1000 and the system calls execute at 1500. The interrupts first save the user program’s SP and PC before proceeding. These behaviors that we are tasked with implementing are similar to the behaviors of a real CPU and memory. This project is essentially an application of the low-level concepts we learned. 

## Project 2
The purpose of this project was to apply our knowledge of coordinating threads with semaphores. In this program, we are trying to simulate patients visiting a doctor and we are coordinating their actions. The program will ask 

## Project 3
This project was implemented in Java.
