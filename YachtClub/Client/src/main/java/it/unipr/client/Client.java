package it.unipr.client;

import it.unipr.connection.Response;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Inet4Address;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static it.unipr.constants.Constants.*;

/**
 * The {@code Client} class establishes a connection with the server
 * and manage the communication, sending request and receiving back the responses.
 *
 * @author Martina Valenti 308044
 * @version 1.0
 * @since 1.0
 */
public class Client {
    private String action;
    private String param;
    private final String ip;
    private final String serverHost;
    private final int serverPort;


    /**
     * Class constructor
     */
    public Client() {
        this.ip = getIp();
        this.serverHost = SERVER_HOST;
        this.serverPort = SERVER_PORT;
    }

    /**
     * Method to retrieve the {@code ip} IP address of the server
     *
     * @return {@code String} ip address
     */
    private String getIp() {
        try {
            return Inet4Address.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            return "localhost";
        }
    }

    /**
     * Method that manages the connection to the server,
     * sending requests and waiting for a response.
     *
     * @param action {@code String} action the server must perform
     * @param param   {@code String} parameters of the request
     * @return {@code String}  which describe the result of the request
     */
    public List<?> connect(String action, String param) {
        this.action = action;
        this.param = param;
        try {
            Socket socket = new Socket(this.serverHost, this.serverPort);
            ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream is = new ObjectInputStream(socket.getInputStream());

            request(os, action + REQUEST_SEPARATOR + param);
            Response<?> response = receive(is);
            this.close(socket, is, os);

            if (response == null) {
                return Collections.singletonList(FAIL);
            }
            return this.readResponse(response);

        } catch (ConnectException c) {
            return Collections.singletonList(CONN_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.singletonList(FAIL);
        }
    }

    /**
     * This method writes the request to the output stream
     *
     * @param os  ObjectOutputStream to write to
     * @param request request to write
     * @param <T>  Type of the request
     */
    public static <T> void request(ObjectOutputStream os, T request) {
        try {
            os.writeObject(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method receives the server response from the input stream
     *
     * @param is ObjectInputStream to read from
     * @return Response object
     */
    public static Response<?> receive(ObjectInputStream is) {
        try {
            return (Response<?>) is.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<?> readResponse(Response<?> response) {
        return switch ((String) response.getMessage()) {
            case FAIL -> Collections.singletonList(FAIL);
            case DEFAULT -> Collections.singletonList(DEFAULT);
            default -> returnResponse(response);
        };
    }

    private List<?> returnResponse(Response<?> response) {

        if (this.action.equals(MEMBER_LOGIN)) {
            return response.getResponse();
        }
        if (this.action.equals(STAFF_LOGIN)) {
            return response.getResponse();
        }
        if (this.action.equals(CHECK_SIGNUP)) {
            return Collections.singletonList(response.getMessage());
        }
        if (this.action.equals(REGISTRATION)) {
            return Collections.singletonList(response.getMessage());
        }
        if (this.action.equals(GET_ADDRESS)) {
            return response.getResponse();
        }
        if (this.action.equals(GET_BOATS)) {
            return response.getResponse();
        }
        if (this.action.equals(GET_REG_RACES)) {
            return response.getResponse();
        }
        if (this.action.equals(GET_NOT_REG_RACES)) {
            return response.getResponse();
        }
        if (this.action.equals(GET_CARDS)) {
            return response.getResponse();
        }
        if (this.action.equals(REGISTER_TO_RACE)) {
            return Collections.singletonList(response.getMessage());
        }
        if (this.action.equals(INSERT_PAYMENT)) {
            return Collections.singletonList(response.getMessage());
        }
        if (this.action.equals(INSERT_BOAT)) {
            return Collections.singletonList(response.getMessage());
        }
        if (this.action.equals(INSERT_CARD)) {
            return Collections.singletonList(response.getMessage());
        }
        if (this.action.equals(DELETE_CARD)) {
            return Collections.singletonList(response.getMessage());
        }
        if (this.action.equals(DELETE_BOAT)) {
            return Collections.singletonList(response.getMessage());
        }
        if (this.action.equals(GET_NOTIFICATIONS)) {
            return response.getResponse();
        }
        if (this.action.equals(UPDATE_MEMBER_FEE)) {
            return Collections.singletonList(response.getMessage());
        }
        if (this.action.equals(GET_ALL_BOATS)) {
            return response.getResponse();
        }
        if (this.action.equals(GET_ALL_MEMBERS)) {
            return response.getResponse();
        }
        if (this.action.equals(GET_ALL_RACES)) {
            return response.getResponse();
        }
        if (this.action.equals(GET_ALL_PAYMENTS)) {
            return response.getResponse();
        }
        if (this.action.equals(GET_PAST_RACES)) {
            return response.getResponse();
        }
        if (this.action.equals(DELETE_MEMBER)) {
            return Collections.singletonList(response.getMessage());
        }
        if (this.action.equals(ADD_NOTIFICATION)) {
            return Collections.singletonList(response.getMessage());
        }
        if (this.action.equals(ADD_RACE)) {
            return Collections.singletonList(response.getMessage());
        }
        if (this.action.equals(DELETE_RACE)) {
            return Collections.singletonList(response.getMessage());
        }
        if (this.action.equals(DELETE_NOTIFICATION)) {
            return Collections.singletonList(response.getMessage());
        }
        if (this.action.equals(UPDATE_BOAT)) {
            return Collections.singletonList(response.getMessage());
        }
        return new ArrayList<>();
    }

    private void close(Socket s, ObjectInputStream input, ObjectOutputStream outuput) {
        try {
            s.close();
            input.close();
            outuput.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
