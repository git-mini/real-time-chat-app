# Real-Time WebSocket Chat Application

This project is a real-time chat application built with Spring Boot, WebSocket, and Redis. It demonstrates scalable and efficient real-time communication by integrating WebSocket with a Redis-based messaging system.

## Features
- **User Authentication**: Users can enter a username to join the chat.
- **Real-Time Messaging**: Messages are instantly broadcasted to all connected clients using WebSocket.
- **Redis Integration**: Messages are stored and broadcasted via Redis for quick delivery and scalability.
- **Join and Leave Notifications**: Broadcasts notifications when users join or leave the chat.

## Technologies Used

### Backend
- **Spring Boot**: Framework for building scalable Java applications.
- **Spring WebSocket**: Enables WebSocket support for real-time communication.
- **Spring Data Redis**: Integrates Redis for efficient message broadcasting.
- **Spring Messaging**: Handles STOMP messaging protocols.
- **Redis**: In-memory data store used for broadcasting chat messages.

## Key Components
- **WebSocketConfig**: Configures WebSocket endpoints and the message broker for client-server communication.
- **RedisConfig**: Sets up Redis for message publishing and subscribing.
- **ChatController**: Handles user interactions, such as joining the chat and sending messages.
- **RedisMessageSubscriber**: Listens to Redis messages and broadcasts them to all connected WebSocket clients.
- **WebsocketEventListener**: Manages WebSocket connection and disconnection events, broadcasting notifications.
- **ChatMessage**: DTO for transferring chat messages between the client and server.

## How It Works

1. **User Connection**:  
   Users connect to the WebSocket server, which sends a `JOIN` message to notify all connected clients.
   
2. **Message Broadcasting**:  
   - Messages sent by users are broadcasted to all clients in real-time using WebSocket.  
   - Redis acts as the message broker for efficient delivery across multiple clients.

3. **User Disconnection**:  
   When a user disconnects, a `LEAVE` message is broadcasted to notify others.

## Prerequisites
- **Java 21 or higher**
- **Spring Boot 3.0+**
- **Redis** (for message broadcasting)

## Running the Application

1. **Clone the repository**:
    ```bash
    git clone https://github.com/git-mini/chat-app.git
    ```

2. **Navigate to the project directory**:
    ```bash
    cd chat-app
    ```

3. **Start the Redis server** (if not already running).

4. **Run the Spring Boot application**:
    ```bash
    ./mvn spring-boot:run -Dspring-boot.run.profiles=instance1
    ```
    ```bash
    ./mvn spring-boot:run -Dspring-boot.run.profiles=instance2
    ```
    ```bash
    ./mvn spring-boot:run -Dspring-boot.run.profiles=instance3
    ```

5. Open the application at different instances
 [http://localhost:8080](http://localhost:8080)
 [http://localhost:8081](http://localhost:8081)
 [http://localhost:8082](http://localhost:8082)

![Screenshot 2024-11-17 191911](https://github.com/user-attachments/assets/7327bde2-34fa-4ac9-8a75-186046f3ec9c)

## Contributing

Feel free to fork this repository, submit issues, and send pull requests for improvements or bug fixes.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
