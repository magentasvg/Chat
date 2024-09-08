import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

class ChatServer {
    ArrayList<Client> clients = new ArrayList<>();
    ServerSocket serverSocket;
    public ChatServer() throws IOException {
        serverSocket = new ServerSocket(1234);
    }
    public void sendAll(String message) {
        for (Client client : clients) {
            client.receive(message);
        }
    }
    public void run() {
        while(true) {
            System.out.println("Waiting");
            try {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected");
                clients.add(new Client(socket, this));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}