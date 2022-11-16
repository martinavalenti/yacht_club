package it.unipr.server;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import static it.unipr.constants.Constants.*;

/**
 * {@code Server} class manages a concurrent server, which generates
 * a new child process for each incoming client connection.
 *
 * @author Martina Valenti 308044
 * @version 1.0
 * @since 1.0
 */
public class Server {
    private final String IPaddress;
    private final ServerSocket serverSocket;
    private Thread threadAccept;

    /**
     * Creates a new thread {@code ServerThread} for each incoming request.
     *
     * @throws Exception if server socket can't be open
     * @see ServerThread
     */
    public Server() throws Exception {
        this.IPaddress = findIPadress();
        serverSocket = new ServerSocket(SERVER_PORT);
        this.serverConnection();
    }

    private String findIPadress() {
        try {
            return Inet4Address.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            return "localhost";
        }
    }

    /**
     * Returns server IPadress
     *
     * @return {@code String} which is the ip address of the server
     */
    public String getIPadress() {
        return this.IPaddress;
    }


    private void serverConnection() {
        threadAccept = new Thread() {
            public void run() {
                while (CURRENT_CONNECTIONS < MAX_CONNECTIONS) {
                    try {
                        Socket accept = serverSocket.accept();
                        CURRENT_CONNECTIONS++;
                        new ServerThread(accept);
                    } catch (IOException e) {
                    }
                }
                try {
                    serverSocket.close();
                } catch (IOException e) {
                }

                this.interrupt();
            }
        };
        threadAccept.start();
    }

    /**
     * Close the server socket
     *
     * @throws Exception if server socket can't be close
     * @see ServerSocket
     */
    public void close() throws Exception {
        this.serverSocket.close();
        threadAccept.interrupt();
    }
}
