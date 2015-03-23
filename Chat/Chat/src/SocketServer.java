import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class SocketServer extends javax.swing.JFrame {

    String EnviarTexto="";
    
    public SocketServer() {
        initComponents();
        setTitle("Servidor Corriendo");
        setResizable(false); 
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public SocketServer(int a){}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        AreaTextoServer = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        ExpulsarUser = new javax.swing.JComboBox();
        Limpiar = new javax.swing.JButton();
        EnviarMensajeServer = new javax.swing.JButton();
        TextoServer = new javax.swing.JTextField();
        Vef = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        AreaTextoServer.setEditable(false);
        AreaTextoServer.setColumns(20);
        AreaTextoServer.setForeground(new java.awt.Color(51, 0, 204));
        AreaTextoServer.setLineWrap(true);
        AreaTextoServer.setRows(5);
        jScrollPane1.setViewportView(AreaTextoServer);

        jLabel2.setFont(new java.awt.Font("Ravie", 0, 12)); // NOI18N
        jLabel2.setText("Expulsar Usuario");

        ExpulsarUser.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar", " " }));

        Limpiar.setText("Limpiar");
        Limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LimpiarActionPerformed(evt);
            }
        });

        EnviarMensajeServer.setText("Enviar");
        EnviarMensajeServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EnviarMensajeServerActionPerformed(evt);
            }
        });

        Vef.setText("        ");
        Vef.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addComponent(TextoServer, javax.swing.GroupLayout.PREFERRED_SIZE, 625, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(EnviarMensajeServer)
                .addGap(0, 10, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(ExpulsarUser, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Vef)
                .addGap(134, 134, 134)
                .addComponent(Limpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(ExpulsarUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Limpiar)
                    .addComponent(Vef))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TextoServer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EnviarMensajeServer))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void EnviarMensajeServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EnviarMensajeServerActionPerformed
       EnviarTexto=TextoServer.getText();
       TextoServer.setText("");
       
    }//GEN-LAST:event_EnviarMensajeServerActionPerformed

    private void LimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LimpiarActionPerformed
        AreaTextoServer.setText("");
    }//GEN-LAST:event_LimpiarActionPerformed
    
    
    
    public void InicializarServidor(){
    int user=0;
    ArrayList<Thread> Inicio1 = new ArrayList<Thread>();
        try {
            ServerSocket servidor = new ServerSocket(3000);
            do {
                System.out.println("Escuchando....");
                Socket socketConectado = servidor.accept();
                Runnable nuevoSocket = new HilosServidor(socketConectado);
                Inicio1.add(new Thread(nuevoSocket));
                Inicio1.get(user).start();
                System.out.println("Un usuario ha ingresado");
                user++;        
            } while (true);

        } catch (IOException excepcion) {
            System.out.println(excepcion);
        }
    }

    public static void main(String args[]) {
        SocketServer Inicio=new SocketServer();
       try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    new SocketServer().setVisible(true);
                }
            });
            Inicio.InicializarServidor();
            } catch (ClassNotFoundException ex) {
            Logger.getLogger(SocketServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(SocketServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(SocketServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(SocketServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTextArea AreaTextoServer;
    public static javax.swing.JButton EnviarMensajeServer;
    private javax.swing.JComboBox ExpulsarUser;
    private javax.swing.JButton Limpiar;
    public static javax.swing.JTextField TextoServer;
    public javax.swing.JLabel Vef;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
