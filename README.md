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

Apache JMeter was used to simulate concurrent TCP client requests and evaluate scalability, concurrency handling, latency, throughput, and stability across different server architectures.

Testing was initially performed with **60,000 virtual users**, followed by extreme stress testing with **600,000 virtual users** to analyze the practical limitations of blocking I/O and thread-based server architectures under massive concurrent load.

---

# Test Configuration

| Parameter | Value |
|---|---|
| Initial Virtual Users | 60,000 |
| Maximum Virtual Users Tested | 600,000 |
| Ramp-Up Period | 60 Seconds |
| Loop Count | 1 |

---

# Architecture Comparison Under Extreme Load

| Architecture | Concurrency Model | Throughput | Error Rate | Key Observation |
|---|---|---|---|---|
| Single-Threaded Server | Sequential Request Processing | ~141 req/sec | ~75% | Sequential execution became a major scalability bottleneck under heavy load |
| Multi-Threaded Server | One Thread per Client | ~192 req/sec | ~79% | Improved concurrency but excessive thread creation increased latency and resource exhaustion |
| Thread Pool Server | Fixed Reusable Worker Threads | ~98 req/sec | ~60% | Better resource management and more controlled concurrent request handling |

---

# Single-Threaded Server Analysis

## Observations

- The server processes one client request at a time using a single execution thread.
- Under extreme concurrent load, incoming client requests accumulated rapidly due to sequential processing.
- High request failure rates demonstrated the scalability limitations of single-threaded architectures in concurrent systems.

## Stress Test Metrics

| Metric | Approximate Value |
|---|---|
| Samples Processed | ~1,380,000 |
| Average Response Time | ~41 ms |
| Maximum Response Time | 881 ms |
| Throughput | ~141 requests/sec |
| Error Rate | ~75% |

---

## Single-Threaded Server Screenshots

### View Results Table
<img width="1918" height="1016" alt="image" src="https://github.com/user-attachments/assets/9c6e4c1a-423f-49e4-9286-da2fb3f62d8e" />

---

### Graph Results

<img width="1918" height="1021" alt="image" src="https://github.com/user-attachments/assets/5ced6925-1bcd-46d8-8398-7ce1f02bedea" />

---

### Summary Report

<img width="1918" height="1017" alt="image" src="https://github.com/user-attachments/assets/879cb0a0-228b-4ec2-8c6e-21c927b7a63f" />

---

### Aggregate Report

<img width="1912" height="1011" alt="image" src="https://github.com/user-attachments/assets/af636ebb-f157-4f87-98f7-7732ed972304" />

---

# Multi-Threaded Server Analysis

## Observations

- The multithreaded architecture improved concurrency by creating a dedicated thread for each client connection.
- Throughput increased significantly compared to the single-threaded server due to concurrent request handling.
- Under massive load, latency and response times increased because of excessive thread creation, context switching overhead, blocking I/O delays, and system resource exhaustion.
- Very high error rates demonstrated the scalability limitations of thread-per-client architectures under extreme concurrency.

## Stress Test Metrics

| Metric | Approximate Value |
|---|---|
| Samples Processed | ~1,980,000 |
| Average Response Time | ~55 ms |
| Maximum Response Time | 6871 ms |
| Throughput | ~192 requests/sec |
| Error Rate | ~79% |

---

## Multi-Threaded Server Screenshots

### View Results Table

<img width="1917" height="1015" alt="image" src="https://github.com/user-attachments/assets/f2cb541f-4596-49b0-b790-72b5bc9ebf20" />

---

### Graph Results

<img width="1918" height="1018" alt="image" src="https://github.com/user-attachments/assets/e522daae-9cd9-498e-aaec-a7091ae6806c" />

---

### Summary Report

<img width="1918" height="1017" alt="image" src="https://github.com/user-attachments/assets/4b9404a6-7cb2-4ea1-99ad-5ae4d1700117" />

---

### Aggregate Report

<img width="1918" height="1017" alt="image" src="https://github.com/user-attachments/assets/055b5811-0ba4-4067-a349-97717dc9ffac" />

---

# Thread Pool Server Analysis

## Observations

- The thread pool architecture used reusable worker threads through Java ExecutorService for controlled concurrent request handling.
- Compared to thread-per-client architecture, resource usage became more stable under high load conditions.
- Request failures and increased response times were still observed under extreme concurrency due to blocking I/O limitations and operating system resource constraints.
- The experiment demonstrated how fixed thread pools improve scalability and thread management compared to uncontrolled thread creation.

## Stress Test Metrics

| Metric | Approximate Value |
|---|---|
| Samples Processed | ~780,000 |
| Average Response Time | ~42 ms |
| Maximum Response Time | 881 ms |
| Throughput | ~98 requests/sec |
| Error Rate | ~60% |

---


## Thread Pool Server Screenshots

---

### View Results Table

<img width="1918" height="1012" alt="image" src="https://github.com/user-attachments/assets/b274d27d-ed2e-4311-b2e7-18d427bdc8b4" />






---

### Graph Result

<img width="1918" height="1032" alt="Graph Results" src="https://github.com/user-attachments/assets/7f32d911-8f17-4615-99bc-e3b9509d7c0b" />






---

### Summary Report

<img width="1918" height="1007" alt="Summary Report" src="https://github.com/user-attachments/assets/56d954f9-81dc-4047-929a-e36a472abe94" />





---

### Aggregate Report
<img width="1917" height="1017" alt="Aggregate Report" src="https://github.com/user-attachments/assets/9082226a-7789-46d3-9842-518ba1e10ced" />



---

# Final Conclusion

The project demonstrated how different concurrency architectures behave under extreme concurrent TCP workloads using Java Socket Programming and blocking I/O.

Key findings from the experiments:

- Single-threaded architecture is simple but scales poorly because requests are processed sequentially.
- Multi-threaded architecture improves concurrency and throughput but suffers from excessive thread creation, context switching overhead, increased latency, and resource exhaustion under massive load.
- Thread Pool architecture provides more controlled resource management and better stability by reusing worker threads instead of creating unlimited threads.

The stress tests highlighted several important backend engineering concepts:

- scalability bottlenecks in blocking I/O systems
- thread lifecycle management
- resource exhaustion under high concurrency
- socket connection limitations
- latency growth under heavy load
- trade-offs between throughput, stability, and concurrency models

The project provided hands-on understanding of practical server-side concurrency challenges and demonstrated why modern production systems often use optimized thread pools, asynchronous processing, and non-blocking architectures for high-scale backend systems.

---

# Key Learning Outcomes

- Java Socket Programming
- TCP Client-Server Communication
- Blocking I/O Behavior
- Java Multithreading
- Thread Lifecycle Management
- ExecutorService and Thread Pools
- Concurrent Request Handling
- Stress Testing using Apache JMeter
- Throughput and Latency Analysis
- Scalability and Resource Bottleneck Analysis

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
