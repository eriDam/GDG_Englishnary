package com.englishnary.eridev.android.englishnary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MenuMain extends AppCompatActivity {
    private ImageButton imgButtonBuscar, imgButtonNotas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        iniciarControles();
    }


    //Metodo para recuperar los controles mediante su id, para descargar el onCreate
    private void iniciarControles() {
        //Obtengo los controles mediante su id
        imgButtonBuscar = (ImageButton) findViewById(R.id.imgButtonBuscar);
        imgButtonNotas  = (ImageButton) findViewById(R.id.imgButtonNotas);

        imgButtonBuscar.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent abreUserCaption = new Intent(MenuMain.this, UserCaptionActivity.class);
                startActivity(abreUserCaption );

            }
        });
        imgButtonNotas.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent abreNotas = new Intent(MenuMain.this, NotesFecha.class);
                startActivity(abreNotas );

            }
        });
    }
}