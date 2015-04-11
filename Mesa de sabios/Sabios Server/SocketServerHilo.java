//package SABIOS;

import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SocketServerHilo extends Thread {

    String recibido;
    OutputStream osalida;
    DataOutputStream dsalida;

    InputStream ientrada;
    DataInputStream dentrada;

    Socket socket;
    ArrayList<SocketServerHilo> LISTA;
    
    boolean COMIENDO=false;

    public SocketServerHilo(Socket lsocket, ArrayList<SocketServerHilo> LIST) {
        try {
            this.LISTA = LIST;
            socket = lsocket;
        } catch (Exception excepcion) {
            System.out.println(excepcion);
        }

    }
    
    
    
    String comer="<<<ICODEVIL";
    String nocomer="<<<ICOALIEN";
    
    public void COMER(boolean estado){
        if(estado){
            ENVIAR(comer);
        }else{
            ENVIAR(nocomer);
        }
    }
    
    
    public boolean OBTENER_ESTADO(){
        return COMIENDO;
    }

    public void ENVIAR(String T) {
        try {
        if(T.equals(comer)){
            COMIENDO=true;
            dsalida.writeUTF(comer);
            sleep(3000);
            
            COMIENDO=false;
            dsalida.writeUTF(nocomer);
        }else if(T.equals(nocomer)){
            COMIENDO=false;
            dsalida.writeUTF(T);
        }
            
        
        
        } catch (IOException ex) {
        } catch (InterruptedException ex) {
        }
    }

    public void run() {

        try {
            osalida = socket.getOutputStream();
            dsalida = new DataOutputStream(osalida);

            ientrada = socket.getInputStream();
            dentrada = new DataInputStream(ientrada);

            //dsalida.writeUTF("Bienvenido al server");
            do {
                recibido = dentrada.readUTF();
                
                System.out.println("recibido desde el cliente: " + recibido);
            } while (!recibido.equals("bye"));
        } catch (IOException excepcion) {
            System.out.println(excepcion.getMessage());
        }

        try {
            dsalida.close();
            dentrada.close();
            socket.close();
        } catch (IOException excepcion) {
            System.out.println(excepcion);
        }
    }
}
