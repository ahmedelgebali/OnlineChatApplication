# Online Chat Application

## Description
This is a simple online chat application implemented in Java. It allows multiple users to connect to a central server, send messages, and receive messages from other users.

## Requirements
- Java JDK 8 or higher

## How to Run

### Server
1. Compile the server code:
    ```sh
    javac ChatServer.java
    ```
2. Run the server:
    ```sh
    java ChatServer
    ```

### Client
1. Compile the client code:
    ```sh
    javac ChatClient.java
    ```
2. Run the client:
    ```sh
    java ChatClient
    ```

## Implementation Details
- The server listens for incoming connections and assigns a unique user ID to each connected client.
- Clients can send messages to the server, which broadcasts the messages to all other connected clients.
- The user interface is text-based, allowing users to input and receive messages via the console.

## Screenshots
![image](https://github.com/ahmedelgebali/OnlineChatApplication/assets/123560796/783e4e40-994b-4218-8c01-4f03da88f660)

![image](https://github.com/ahmedelgebali/OnlineChatApplication/assets/123560796/6d874912-02ad-46aa-aed1-41e94c887813)

