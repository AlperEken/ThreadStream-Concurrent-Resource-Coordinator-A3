# ThreadStream: Concurrent Resource Coordinator

## Key Features

ThreadStream: Concurrent Resource Coordinator is a high-performance simulation engine designed to model and manage the intricate balance of resource flow within a multi-threaded ecosystem. By implementing a sophisticated Producer-Consumer architecture, the system coordinates independent production threads that generate data units and consumption threads that process them, all while maintaining strict synchronization. The platform features an advanced, real-time monitoring dashboard built with Java Swing, utilizing dynamic progress bars and status indicators to visualize buffer saturation, production throughput, and consumer demand. With its scalable control logic, users can adjust thread behavior on-the-fly, ensuring optimal resource allocation without system deadlock or data corruption.

## Technical Implementation

The system’s technical foundation is a thread-safe, bounded-buffer implementation that serves as the primary synchronization point for all concurrent operations. Developed in Java, the engine utilizes monitors and intrinsic locking mechanisms to manage thread signaling, effectively preventing buffer overflow and underflow scenarios through disciplined wait-and-notify protocols. The architecture is governed by a centralized `Controller` that manages the lifecycle of worker threads, providing a robust abstraction layer between the concurrent data model and the graphical event dispatch thread. This modular approach ensures that the production and consumption logic remains decoupled, allowing for individual scaling of producer and consumer counts to test system limits under various load conditions.

## Challenges & Reflection

A significant technical challenge during development was ensuring the precise coordination of thread interruptions and state consistency when pausing or resetting the simulation. Implementing a synchronized storage medium required rigorous attention to avoid race conditions during the simultaneous access of shared indices, necessitating a deep understanding of thread safety and atomic operations. Furthermore, the project provided critical insights into the performance trade-offs of thread signaling, where inefficient notification patterns can lead to increased latency or context-switching overhead. This experience refined the ability to architect concurrent systems that are not only logically correct but also visually transparent, bridging the gap between background processing and user-facing feedback loops.

## Getting Started

To execute the ThreadStream simulations on your local environment, ensure the JDK is installed and follow these terminal commands:

```bash
# Navigate to the source root directory
cd A3-d9a5dd1efac9abfe0ddd5aaa374955e5d1287869/src

# Compile the concurrent engine and GUI components
javac controller/*.java model/*.java view/*.java

# Run the simulation through the main entry point
java controller.Main
```

*Author: Alper Eken Course: Concurrent Programming Semester: Spring 2025*
