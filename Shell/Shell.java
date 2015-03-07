//Daniel Fernando Muñoz M. 160003148
import java.io.BufferedInputStream;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Shell extends javax.swing.JFrame {
     JTextField TxtComando;
     JButton jButton1;
     JLabel jLabel1;
     JScrollPane jScrollPane1;
     JTextArea tResultado;

//Constructor organiza la localizacion de la ventana 
    public Shell() {
        initComponents();
        setTitle("Consola en "+System.getProperty("os.name"));
        setResizable(false); 
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @SuppressWarnings("unchecked")

//Componentes del entorno grafico
    private void initComponents() {

        TxtComando = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tResultado = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);


        tResultado.setBackground(new java.awt.Color(0, 0, 0));
        tResultado.setColumns(20);
        tResultado.setForeground(new java.awt.Color(0, 204, 0));
        tResultado.setRows(5);
        jScrollPane1.setViewportView(tResultado);

        jLabel1.setFont(new java.awt.Font("Ravie", 0, 14)); 
        jLabel1.setText("Comando:");

        jButton1.setText("Ejecutar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(TxtComando, javax.swing.GroupLayout.DEFAULT_SIZE, 499, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(TxtComando, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE))
        );

        pack();
    }
//Metodo para la accion del boton
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        tResultado.setText("");	
       
        if(System.getProperty("os.name").charAt(0)=='W'){
            try {                
                WindowsConsola();
            } catch (IOException ex) {
                Logger.getLogger(Shell.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            ShellLinux();                
        }
         TxtComando.setText("");
    }
//Consola en windows
   public void WindowsConsola() throws IOException{
       BufferedReader stdOutErr = null;
       Process        p  = null;
       ProcessBuilder pb = null;
       ArrayList <String> orden = new ArrayList <String> ();
       String linea="",MostrarTodoElResultado="";
       orden.add (TxtComando.getText());
       pb = new ProcessBuilder (orden);
       pb.redirectErrorStream (true);
       try{
           p = pb.start ();
       }catch (java.io.IOException ie){
           ie.printStackTrace ();
       }try{
           stdOutErr = new BufferedReader (new InputStreamReader (p.getInputStream (), "utf-8"));
       }catch (UnsupportedEncodingException uee){
           uee.printStackTrace ();
       }try{
            while ((linea = stdOutErr.readLine ()) != null){
                tResultado.setText(tResultado.getText()+linea+"\n");
            }
       }catch (IOException ie){
           ie.printStackTrace ();
       }
        System.out.println(MostrarTodoElResultado);   
   }
//Consola en linux y mac
   public void ShellLinux(){
       Process proc; 
       InputStream is_in;
       String s_aux;
       BufferedReader br;
       try{
           proc = Runtime.getRuntime().exec(TxtComando.getText());
	   is_in=proc.getInputStream();
	   br=new BufferedReader (new InputStreamReader (is_in));
	   s_aux = br.readLine();
           while (s_aux!=null){
               tResultado.setText(tResultado.getText()+s_aux+"\n");
               s_aux = br.readLine();
           } 
       }catch(Exception e){
           e.getMessage();
       }   
   }
    
//Contiene el inicio y el tema de la ventana
  public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
             java.awt.EventQueue.invokeLater(new Runnable() {
                 public void run() {
                     new Shell().setVisible(true);
                 }
             });
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Shell.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Shell.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Shell.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Shell.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
 }
