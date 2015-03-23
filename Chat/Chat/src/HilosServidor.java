
import java.net.*;
import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class HilosServidor implements Runnable {

    String recibido;
    OutputStream osalida;
    DataOutputStream dsalida;
    InputStream ientrada;
    DataInputStream dentrada;
    Socket socket;
    int User=0;
    ArrayList<String> NombreUsers = new ArrayList<String>();
    String EnvioServer;
    String MensajeCompleto="";
    
    public HilosServidor(Socket lsocket) {
        try {
            socket = lsocket;
        } catch (Exception excepcion) {
            System.out.println(excepcion);
        }
    }
   
    public void run() {
        SocketServer Inicio=new SocketServer(0);
        try {
            osalida = socket.getOutputStream();
            dsalida = new DataOutputStream(osalida);
            ientrada = socket.getInputStream();
            dentrada = new DataInputStream(ientrada);
            dsalida.writeUTF(Inicio.EnviarTexto);
           
            
            do {
                recibido = dentrada.readUTF();
                MensajeCompleto=Inicio.AreaTextoServer.getText();
                Inicio.AreaTextoServer.setText(MensajeCompleto+recibido+"\n");
                
            } while (!recibido.equals("Desconectar..."));
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