package com.englishnary.eridev.android.englishnary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MenuMain extends AppCompatActivity {
    private ImageButton imgButton2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        iniciarControles();
    }


    //Metodo para recuperar los controles mediante su id, para descargar el onCreate
    private void iniciarControles() {
        //Obtengo los controles mediante su id
        imgButton2 = (ImageButton) findViewById(R.id.imgButton2);


  /*Card Lessons         card_maps.setOnClickListener(new CardView.OnClickListener() {
*/
        imgButton2.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent abreLessons = new Intent(MenuMain.this, MainActivity.class);
                startActivity(abreLessons);

            }
        });
    }
}