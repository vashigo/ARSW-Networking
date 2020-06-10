package edu.escuelaing.arsw.servidorWeb;

import edu.escuelaing.arsw.servidorWeb.reader.ResourceChooser;
import edu.escuelaing.arsw.servidorWeb.reader.ResourceWriter;
import edu.escuelaing.arsw.servidorWeb.socketsWeb.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * main web server interpreter
 *
 * @author vashi
 */
public class EchoServerHttp {

    public static void main(String[] args) throws IOException {
        //ServerSocket
        ServerSocket serverSocket = edu.escuelaing.arsw.servidorWeb.socketsWeb.ServerSocket.getNewServerSocket();
        while (true) {
            Socket clientSocket = null;
            ResourceWriter rw = null;
            try {
                clientSocket = ClientSocket.getNewClientSocket(serverSocket);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String inputLine, path = "";
                while ((inputLine = in.readLine()) != null) {
                    System.out.println("Received: " + inputLine);
                    if (inputLine.contains("GET")) {
                        path = inputLine.split(" ")[1];
                        rw = ResourceChooser.choose(path);
                    }
                    if (!in.ready()) {
                        break;
                    }
                }
                rw.write(path, clientSocket);
                in.close();
                clientSocket.close();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        }
    }
}
