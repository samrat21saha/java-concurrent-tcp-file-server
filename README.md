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

# Load Testing & Scalability Analysis

Apache JMeter was used to simulate concurrent TCP client requests and evaluate server behavior under different load conditions.

Initial testing was performed with **60,000 virtual users**, followed by extreme stress testing with **600,000 virtual users** to analyze scalability limits, concurrency handling, throughput, and server stability under heavy load.

> Current metrics and screenshots are for the **Thread Pool-based TCP Server** implementation.  
> Performance comparisons for the Single-Threaded and Multi-Threaded server implementations will be added later.

---

## Test Configuration

| Parameter | Value |
|---|---|
| Initial Virtual Users | 60,000 |
| Maximum Virtual Users Tested | 600,000 |
| Ramp-Up Period | 60 Seconds |
| Loop Count | 1 |

---

## Observations

- The Thread Pool-based server successfully handled a large number of concurrent TCP client requests under heavy load.
- Under extreme concurrency conditions, increased response times and request failures were observed due to blocking I/O limitations, thread/resource exhaustion, and operating system connection limits.
- The experiment demonstrated practical scalability limitations of thread-based server architectures under massive concurrent workloads.
- Stress testing helped analyze thread management overhead, throughput degradation, concurrent request handling behavior, and server resource bottlenecks.

---

## Stress Test Metrics (Thread Pool Server)

| Metric | Approximate Value |
|---|---|
| Average Response Time | ~42 ms |
| Maximum Response Time | 881 ms |
| Throughput | ~98 requests/sec |
| Error Rate Under Extreme Load | ~60% |

---

# JMeter Result Screenshots

## Thread Group Configuration

(Paste screenshot here)

---

## Thread Pool Server - Graph Results

(Paste screenshot here)

---

## Thread Pool Server - Summary Report

(Paste screenshot here)

---

## Thread Pool Server - Aggregate Report

(Paste screenshot here)

---

## Thread Pool Server - View Results Table

(Paste screenshot here)

---

## Future Updates

- Add Single-Threaded Server benchmark comparison
- Add Multi-Threaded Server benchmark comparison
- Compare scalability and throughput across architectures
- Explore Java NIO / Non-blocking server implementation

---

## JMeter Result Screenshots

### View Results Table

<img width="1918" height="1012" alt="image" src="https://github.com/user-attachments/assets/b274d27d-ed2e-4311-b2e7-18d427bdc8b4" />


---

### Graph Results

<img width="1918" height="1032" alt="Graph Results" src="https://github.com/user-attachments/assets/7f32d911-8f17-4615-99bc-e3b9509d7c0b" />


---

### Summary Report

<img width="1918" height="1007" alt="Summary Report" src="https://github.com/user-attachments/assets/56d954f9-81dc-4047-929a-e36a472abe94" />

---

### Aggregate Report


<img width="1917" height="1017" alt="Aggregate Report" src="https://github.com/user-attachments/assets/9082226a-7789-46d3-9842-518ba1e10ced" />


---

## Conclusion

The stress test highlighted practical limitations of thread-per-request and blocking I/O server architectures under massive concurrent load. The project provided hands-on understanding of concurrency bottlenecks, thread management overhead, socket limitations, and the importance of optimized server architectures such as thread pools and non-blocking I/O systems.
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
