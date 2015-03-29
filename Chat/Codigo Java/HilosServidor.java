
import java.net.*;
import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    SocketServer Inicio=new SocketServer(0);
    
    public HilosServidor(Socket lsocket) {
        try {
            socket = lsocket;
        } catch (Exception excepcion) {
            System.out.println(excepcion);
        }
    }
   
    public void run() {
        
        try {
            osalida = socket.getOutputStream();
            dsalida = new DataOutputStream(osalida);
            ientrada = socket.getInputStream();
            dentrada = new DataInputStream(ientrada);            
            
           do {
               if(Inicio.EnviarTexto!="NoHayNada.-."){
                   dsalida.writeUTF("Servidor -> " +Inicio.EnviarTexto);
               }else{
                   if(Inicio.EnviarTexto=="zumbbbb"){
                       dsalida.writeUTF("zumbbbb");
                       MensajeCompleto=Inicio.AreaTextoServer.getText();
                       Inicio.AreaTextoServer.setText(MensajeCompleto+"Has enviado un zumbidooooo"+"\n");
                   }else{
                       dsalida.writeUTF("Servidor -> " +"***.");
                   }            
               }
               
                recibido = dentrada.readUTF();
                
                if(recibido.equals("zumbbbb")){
                    dsalida.writeUTF("Enviaste un zumbidoooo");
                    MensajeCompleto=Inicio.AreaTextoServer.getText();
                    Inicio.AreaTextoServer.setText(MensajeCompleto+"Has recibido un zumbidooooo"+"\n");
                   try {
                       Inicio.Zumbido();
                   } catch (InterruptedException ex) {
                       Logger.getLogger(HilosServidor.class.getName()).log(Level.SEVERE, null, ex);
                   }
                }else{
                    MensajeCompleto=Inicio.AreaTextoServer.getText();
                    Inicio.AreaTextoServer.setText(MensajeCompleto+recibido+"\n");
                    dsalida.writeUTF(recibido);
                }
                
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