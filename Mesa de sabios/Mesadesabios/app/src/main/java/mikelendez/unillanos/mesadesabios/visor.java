package mikelendez.unillanos.mesadesabios;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.felipecsl.gifimageview.library.GifImageView;



import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;


public class visor extends ActionBarActivity {


    TextView TEXTO;
    WebView WEB;

    DataOutputStream ESCRIBIR;
    DataInputStream ENTRADA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_visor);

        TEXTO=(TextView)findViewById(R.id.TEXTO);
        WEB=(WebView)findViewById(R.id.WEBVIEW);

        //Drawable GIFF=getResources().getDrawable(getResources().getIdentifier("@Drawable/comer.gif", null, getPackageName()));
/*
        try {
            InputStream GIFFF=getBaseContext().getAssets().open("nocomer.png");
            GIF.setBytes(getBytesFromInputStream(GIFFF));
        } catch (IOException e) {
            e.printStackTrace();
        }


        //IMAGEN.setImageDrawable(GIFF);

        GIF.startAnimation();
            */

        try {
            ESCRIBIR=new DataOutputStream(SocketHandler.getSocket().getOutputStream());
            ENTRADA=new DataInputStream(SocketHandler.getSocket().getInputStream());
        } catch (Exception e) {
           TEXTO.setText("ERROR: "+e);
        }



        WEB.loadUrl("file:///android_asset/nocomer.png");
        new RECEPTOR().execute();
    }
    public static byte[] getBytesFromInputStream(InputStream is){
        try (ByteArrayOutputStream os = new ByteArrayOutputStream())
        {
            byte[] buffer = new byte[0xFFFF];

            for (int len; (len = is.read(buffer)) != -1;)
                os.write(buffer, 0, len);

            os.flush();

            return os.toByteArray();
        }
        catch (IOException e)
        {
            return null;
        }
    }

    public void ENVIAR(String MENS){
        try {
            ESCRIBIR.writeUTF(MENS);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class RECEPTOR extends AsyncTask<String,String,String>{
        @Override
        protected String doInBackground(String... params) {
            while (!Thread.currentThread().isInterrupted()){
                try {
                    publishProgress(ENTRADA.readUTF());
                } catch (IOException e) {
                    publishProgress("ERROR: "+e);
                }catch (Exception e){
                    publishProgress("ERROR: "+e);
                }

            }
            return null;
        }

        String comer="<<<ICODEVIL";
        String nocomer="<<<ICOALIEN";

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);

            if(values[0].equals(comer)){
                WEB.loadUrl("file:///android_asset/comer.gif");

            }else if(values[0].equals(nocomer)){
                WEB.loadUrl("file:///android_asset/nocomer.png");
            }else{
                TEXTO.setText(values[0]);
            }

        }
    }

}
