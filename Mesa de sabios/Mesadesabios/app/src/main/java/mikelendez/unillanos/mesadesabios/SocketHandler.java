package mikelendez.unillanos.mesadesabios;

import java.net.Socket;

public class SocketHandler {
    public static Socket socket;

    public static synchronized Socket getSocket(){
        return socket;
    }

    public static synchronized void setSocket(Socket socket){
        SocketHandler.socket = socket;
    }
}