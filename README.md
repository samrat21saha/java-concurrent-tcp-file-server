# Java Concurrent TCP File Server

A Java-based concurrent TCP socket server built to explore and compare different server concurrency models including Single-Threaded, Multi-Threaded, and Thread Pool architectures using Java Socket Programming.

The project demonstrates how backend servers handle multiple client connections, manage threads, process blocking I/O operations, and scale under concurrent load conditions.

Load and concurrency testing were performed using Apache JMeter by simulating 60,000 client requests.

---

# Features

- Single-Threaded TCP Server
- Multi-Threaded TCP Server
- Thread Pool-based TCP Server
- Concurrent client handling
- File content streaming over TCP sockets
- Java Socket Programming
- Blocking I/O implementation
- Apache JMeter load testing
- Performance comparison between concurrency models

---

# Tech Stack

- Java
- TCP Socket Programming
- Multithreading
- ExecutorService
- Java Concurrency API
- Apache JMeter

---

# Project Structure

```text
java-concurrent-tcp-file-server/
│
├── SingleThreaded/
│   ├── Server.java
│   ├── Client.java
│   └── file.txt
│
├── Multithreaded/
│   ├── Server.java
│   ├── Client.java
│   └── file.txt
│
├── ThreadPool/
│   ├── Server.java
│   └── file.txt
│
└── README.md
```

---

# Server Architectures

## 1. Single-Threaded Server

- Handles one client connection at a time
- Other incoming clients must wait until the current request completes
- Demonstrates sequential request processing

### Flow

```text
Client Request
      ↓
Single Server Thread
      ↓
File Read + Response
      ↓
Next Client
```

---

## 2. Multi-Threaded Server

- Creates a new thread for every client connection
- Multiple clients can be served concurrently
- Improves responsiveness compared to single-threaded architecture

### Flow

```text
Client 1 → Thread 1
Client 2 → Thread 2
Client 3 → Thread 3
```

---

## 3. Thread Pool Server

- Uses Java ExecutorService with a fixed thread pool
- Reuses worker threads instead of creating unlimited threads
- Provides better scalability and controlled resource usage

### Flow

```text
Clients
   ↓
Task Queue
   ↓
Reusable Worker Threads
```

---

# How the Server Works

1. The server listens for incoming TCP connections using ServerSocket.
2. Clients connect to the server using Socket.
3. Each server reads contents from its local `file.txt`.
4. File contents are streamed line-by-line to connected clients.
5. The client reads the incoming data stream and prints the received content.

---

# Blocking I/O

This project uses traditional blocking I/O.

Operations such as:

```java
serverSocket.accept()
readLine()
```

block the executing thread until:
- a client connects
- data becomes available

This behavior helps demonstrate why multithreading and thread pools are important in backend server architectures.

---

# Load Testing with Apache JMeter

Apache JMeter was used to simulate concurrent client requests and evaluate server behavior under load.

## Test Configuration

| Parameter | Value |
|---|---|
| Virtual Users | 60,000 |
| Ramp-Up Period | 60 Seconds |
| Loop Count | 1 |

---

# JMeter Result Screenshots

## Single-Threaded Server Result

Paste screenshot here.

---

## Multi-Threaded Server Result

Paste screenshot here.

---

## Thread Pool Server Result

Paste screenshot here.

---

# Key Concepts Demonstrated

- TCP Client-Server Communication
- Socket Programming
- Blocking I/O
- Java Multithreading
- Thread Lifecycle Management
- ExecutorService
- Thread Pools
- Concurrent Request Handling
- Resource Management
- Load Testing
- Scalability Analysis

---

# Why This Project Was Built

This project was built to understand:

- how backend servers handle concurrent client connections
- differences between concurrency models
- thread management strategies
- scalability limitations of thread-per-request architectures
- practical usage of Java networking and concurrency APIs

The file-serving functionality acts as a workload for analyzing server-side concurrency behavior.

---

# Future Improvements

- Implement HTTP protocol support
- Add Java NIO (Non-blocking I/O)
- Add asynchronous logging
- Add request routing
- Add caching mechanism
- Add performance metrics monitoring
- Build a minimal HTTP web server

---

# How to Run

## Compile

```bash
javac Server.java
javac Client.java
```

## Run Server

```bash
java Server
```

## Run Client

```bash
java Client
```

---

# Learning Outcomes

Through this project, I gained hands-on experience with:

- Java Socket Programming
- Concurrent Server Architectures
- Thread Pools and ExecutorService
- Blocking vs Concurrent Request Handling
- TCP Networking Fundamentals
- Load Testing and Performance Analysis

---

# Author

Samrat Saha
