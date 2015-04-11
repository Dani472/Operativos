package mikelendez.unillanos.mesadesabios;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;


public class principal extends ActionBarActivity {

    TextView TEXTO;
    EditText IP,PUERTO;
    Button BOTON;

    Socket SOCKET;


    ProgressBar CIRC;
    RelativeLayout LAYUT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        LAYUT=(RelativeLayout)findViewById(R.id.LAAYOUT_START);
        CIRC = new ProgressBar(this);

        TEXTO=(TextView)findViewById(R.id.START_TEXTO);
        IP=(EditText)findViewById(R.id.IP);
        PUERTO=(EditText)findViewById(R.id.PUERTO);
        BOTON=(Button)findViewById(R.id.BOTON);
    }



    public void conectar(View v){
       new CONECTAR().execute();
    }

    protected void CONEXION_ESTABLECIDA(){
        TEXTO.setText("CONEXION HECHA");
        SocketHandler.setSocket(SOCKET);
        startActivity(new Intent(this,visor.class));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    class CONECTAR extends AsyncTask<String,String,String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            BOTON.setEnabled(false);

            RelativeLayout.LayoutParams PARAMETROS = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            PARAMETROS.addRule(RelativeLayout.BELOW, R.id.TEXTO);
            PARAMETROS.addRule(RelativeLayout.CENTER_HORIZONTAL);

            CIRC.setPadding(0, 50, 0, 0);
            CIRC.setLayoutParams(PARAMETROS);
            LAYUT.addView(CIRC);
        }






        @Override
        protected String doInBackground(String... params) {

            try {
                int PORT = Integer.parseInt(PUERTO.getText().toString());
                SOCKET = new Socket(IP.getText().toString(), PORT);

            }catch (UnknownHostException e) {
                return ("Error "+e);

            } catch (IOException e) {
                return ("Error "+e);

            }catch (Exception e){
                return ("Error "+e);
            }

            return "OK";
        }

        @Override
        protected void onPostExecute(String s) {
            if (s.equals("OK")){

                CONEXION_ESTABLECIDA();
            }else {
                TEXTO.setText(s);
                BOTON.setEnabled(true);
            }
            ((ViewManager)CIRC.getParent()).removeView(CIRC);
            super.onPostExecute(s);
        }
    }
}
