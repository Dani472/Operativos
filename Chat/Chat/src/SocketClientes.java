import java.io.*;
import java.net.Socket;
import java.text.*;
import java.util.*;
import java.util.logging.*;
import javax.swing.*;

public class SocketClientes extends javax.swing.JFrame {

    static String User="UsuarioPorDefecto";   
    String MensajeCompleto="";
    
    public SocketClientes() {
        initComponents();
        setTitle(User);
        setResizable(false); 
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TextoAreaCliente = new javax.swing.JTextArea();
        Enviar = new javax.swing.JButton();
        EnviarTextoCliente = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        Salir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        TextoAreaCliente.setEditable(false);
        TextoAreaCliente.setColumns(20);
        TextoAreaCliente.setRows(5);
        jScrollPane1.setViewportView(TextoAreaCliente);

        Enviar.setText("Enviar");
        Enviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EnviarActionPerformed(evt);
            }
        });

        EnviarTextoCliente.setText(" ");

        jLabel1.setText("   ");

        Salir.setText("Salir");
        Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalirActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jScrollPane1)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(622, 622, 622)
                        .add(jLabel2))
                    .add(layout.createSequentialGroup()
                        .addContainerGap()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel1)
                            .add(EnviarTextoCliente, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 545, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(Salir, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(Enviar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 75, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 345, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(EnviarTextoCliente, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(jLabel1))
                    .add(layout.createSequentialGroup()
                        .add(Enviar)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(Salir)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jLabel2))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public SocketClientes(int b){}
    
    private void EnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EnviarActionPerformed
        EnviarMensaje(User+" dice-> "+EnviarTextoCliente.getText());
        EnviarTextoCliente.setText("");
         Date date = new Date();
        DateFormat FechaHora = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        jLabel1.setText("Enviado el "+FechaHora.format(date));
        
    }//GEN-LAST:event_EnviarActionPerformed

    private void SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalirActionPerformed
        EnviarMensaje("Desconectar...");
        JOptionPane.showMessageDialog(null,"Su conexion sera cerrada");
        System.exit(0);
    }//GEN-LAST:event_SalirActionPerformed

    
    public  void EnviarMensaje(String Enviar){
        String recibido;
        OutputStream osalida;
        DataOutputStream dsalida;
        InputStream ientrada;
        DataInputStream dentrada;
        Socket cliente;
        try {
            cliente = new Socket("127.0.0.1", 3000);
            osalida = cliente.getOutputStream();
            dsalida = new DataOutputStream(osalida);
            ientrada = cliente.getInputStream();
            dentrada = new DataInputStream(ientrada);

            recibido = dentrada.readUTF();
            MensajeCompleto=TextoAreaCliente.getText();
            TextoAreaCliente.setText(MensajeCompleto+""+recibido +"\n");
            dsalida.writeUTF(Enviar);
            dsalida.close();

            dentrada.close();
            cliente.close();

        } catch (Exception e) {
            System.err.println("Error: " + e);
        }

    
    }

    
    public static void main(String args[]) {
       SocketClientes Inicio=new SocketClientes();  
       User=JOptionPane.showInputDialog("Ingrese  su nombre de usuario");
       if(User==null || User==" "){
           User="Usuario-"+System.getProperty("os.name");
       }
       try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    new SocketClientes().setVisible(true);
                }
            });
            Inicio.EnviarMensaje(User+" se ha unido...");
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
    private javax.swing.JButton Enviar;
    private javax.swing.JTextField EnviarTextoCliente;
    private javax.swing.JButton Salir;
    private javax.swing.JTextArea TextoAreaCliente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
