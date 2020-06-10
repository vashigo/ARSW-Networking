package edu.escuelaing.arsw.servidorWeb.socketsWeb;

import java.io.IOException;
import java.net.Socket;

/**
 * Socket of Client
 * 
 * @author Vashi
 */
public class ClientSocket {

    public ClientSocket() {
    }
    
    /**
     * Create a new Client Socket
     * 
     * @param sc
     * @return 
     */
    public static java.net.Socket getNewClientSocket(java.net.ServerSocket sc) {
        Socket clientSocket = null;
        try {
            System.out.println("\nListo para recibir ...");
            clientSocket = sc.accept();
        } catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        }
        return clientSocket;
    }
}
