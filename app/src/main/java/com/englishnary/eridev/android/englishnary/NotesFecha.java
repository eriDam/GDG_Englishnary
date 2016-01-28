package com.englishnary.eridev.android.englishnary;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class NotesFecha extends ActionBarActivity {

    //Definimos dos variables que hacen referencia a los EditText donde se cargan la fecha en uno y las notas de dicho día en el otro:
    private EditText et_fecha,et_nota;

//    En el método onCreate obtenemos las referencias de los dos EditText, normalmente lo hago en un
//    método aparte para iniciar pero ahora solo són dos controles :
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_fecha);
        et_fecha=(EditText)findViewById(R.id.et_fecha);
        et_nota=(EditText)findViewById(R.id.et_nota);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    /*El método grabar que se ejecuta cuando presionamos el botón grabar (no olvidar de inicializar la
    propiedad onClick de cada botón con el nombre del método respectivo) tenemos primero que extraer la
    fecha ingresada en el primer EditText y remplazar las barras de la fecha por guiones ya que no se
    puede utilizar este caracter dentro de un nombre de archivo en Android:*/
    public void grabar(View v) {
        String nomarchivo=et_fecha.getText().toString();
        nomarchivo=nomarchivo.replace('/','-');
        try {
            OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput(
                    nomarchivo, Activity.MODE_PRIVATE));
            archivo.write(et_nota.getText().toString());
            archivo.flush();
            archivo.close();
        } catch (IOException e) {
        }
        Toast t = Toast.makeText(this, "La nota se ha guardado, introduce la fecha para recuperarla",
                Toast.LENGTH_SHORT);
        t.show();
        et_fecha.setText("");
        et_nota.setText("");
    }

    public void recuperar(View v) {
        String nomarchivo=et_fecha.getText().toString();
        nomarchivo=nomarchivo.replace('/','-');
        boolean enco=false;
        String[] archivos = fileList();
        for (int f = 0; f < archivos.length; f++)
            if (nomarchivo.equals(archivos[f]))
                enco= true;
        if (enco==true) {
            try {
                InputStreamReader archivo = new InputStreamReader(
                        openFileInput(nomarchivo));
                BufferedReader br = new BufferedReader(archivo);
                String linea = br.readLine();
                String todo = "";
                while (linea != null) {
                    todo = todo + linea + "\n";
                    linea = br.readLine();
                }
                br.close();
                archivo.close();
                et_nota.setText(todo);
            } catch (IOException e) {
            }
        } else
        {
            Toast.makeText(this, "No hay notas grabadas para esa fecha", Toast.LENGTH_LONG).show();
            et_nota.setText("");
        }
    }

}