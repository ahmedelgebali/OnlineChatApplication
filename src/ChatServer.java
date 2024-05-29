import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
    private static final int PORT = 12345;
    private static Set<ClientHandler> clientHandlers = new HashSet<>();
    private static int userId = 0;

    public static void main(String[] args) {
        System.out.println("Chat server starting...");
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Chat server started and listening on port " + PORT);
            while (true) {
                Socket socket = serverSocket.accept();
                userId++;
                ClientHandler clientHandler = new ClientHandler(socket, userId);
                clientHandlers.add(clientHandler);
                new Thread(clientHandler).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void broadcastMessage(String message, ClientHandler excludeUser) {
        for (ClientHandler clientHandler : clientHandlers) {
            if (clientHandler != excludeUser) {
                clientHandler.sendMessage(message);
            }
        }
    }

    static class ClientHandler implements Runnable {
        private Socket socket;
        private PrintWriter out;
        private int userId;

        public ClientHandler(Socket socket, int userId) {
            this.socket = socket;
            this.userId = userId;
        }

        @Override
        public void run() {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                out = new PrintWriter(socket.getOutputStream(), true);
                out.println("Welcome! You are user #" + userId);

                String message;
                while ((message = in.readLine()) != null) {
                    System.out.println("User#" + userId + ": " + message);
                    ChatServer.broadcastMessage("User#" + userId + ": " + message, this);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ChatServer.clientHandlers.remove(this);
            }
        }

        public void sendMessage(String message) {
            out.println(message);
        }
    }
}

