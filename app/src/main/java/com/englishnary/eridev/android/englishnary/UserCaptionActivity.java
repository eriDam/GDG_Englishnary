package com.englishnary.eridev.android.englishnary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class UserCaptionActivity extends AppCompatActivity {
    private static final String LOG_TAG = UserCaptionActivity.class.getSimpleName();
    //Definimos dos variables que hacen referencia a los controles
    private EditText etxtPalabra;
    public TextView txtResultado;
    private Button btnBuscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_caption);
        //Inicializo los controles
        Inicializar();
    }

    private void Inicializar() {
        etxtPalabra  =(EditText)findViewById(R.id.etxtPalabra);
        txtResultado = (TextView) findViewById(R.id.txtResultado);
        btnBuscar    = (Button) findViewById(R.id.btnBuscar);

        //Evento al btnBuscar para realizar la búsqueda o llamada al API en este caso
        btnBuscar .setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent abreMain = new Intent(UserCaptionActivity.this, MainActivity.class);
//                startActivity(abreMain);
                FetchDefinitionTask definitionTask = new FetchDefinitionTask();
                //definitionTask.execute("book");
                definitionTask.execute();
                txtResultado.setText(definitionTask.toString());
                Log.v(LOG_TAG, "Action find is selected"+ definitionTask);
            }
        });

//        etxtPalabra.getText().toString();
//        String wordToDefinition;
//        wordToDefinition = etxtPalabra.toString();


}
    }
