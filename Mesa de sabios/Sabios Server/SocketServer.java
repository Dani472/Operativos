//package SABIOS;

import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;

public class SocketServer {

    ArrayList<SocketServerHilo> LISTA;

    public SocketServer() {
        LISTA = new ArrayList<SocketServerHilo>();

        try {
            ServerSocket servidor = new ServerSocket(3000);

            for (int i = 1; i <= 3; i++) {

                System.out.println("Esperando sabio");
                Socket socketConectado = servidor.accept();

                SocketServerHilo nuevoSocket = new SocketServerHilo(socketConectado, LISTA);
                LISTA.add(nuevoSocket);

                LISTA.get(LISTA.size() - 1).start();

                System.out.println("Sabio " + i + " recibido \n");

                
            }// while (true);

            EMPEZAR();
        } catch (IOException excepcion) {
            System.out.println(excepcion);
        }

    }

    public static void main(String[] args) {
        new SocketServer();
    }

    public void EMPEZAR() {
        
        while(true){ //for (int j = 0; j < veces; j++) {

            for (int i = 0; i < LISTA.size(); i++) {
                    if(check(i) || !LISTA.get(i).OBTENER_ESTADO()){
                        LISTA.get(i).COMER(true);
                    }
            }
        }
    }

    public boolean check(int i) {
        int a, b;
        if ((i - 1) == -1) {
            a = LISTA.size()-1;
        } else {
            a = i - 1;
        }
        
        if ((i + 1) == LISTA.size()) {
            b = 0;
        } else {
            b = (i + 1);
        }

        if (LISTA.get(a).OBTENER_ESTADO() || LISTA.get(b).OBTENER_ESTADO()) {
            return false;
        }
        return true;
    }

}
