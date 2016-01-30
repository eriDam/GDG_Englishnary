package com.englishnary.eridev.android.englishnary;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class UserCaptionActivity extends AppCompatActivity {
    //Definimos dos variables que hacen referencia a los controles
    private EditText etxtPalabra;
    private TextView txtResultado;
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
                 definitionTask.execute();
                txtResultado.setText(definitionTask.toString());
            }
        });



}
    class FetchDefinitionTask extends AsyncTask<Void, Void, Void> {

        private final String LOG_TAG = FetchDefinitionTask.class.getSimpleName();

        @Override
        protected Void doInBackground(Void... params) {

            // These two need to be declared outside the try/catch
            // so that they can be closed in the finally block.
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            // Will contain the raw JSON response as a string.
            String definitionsJsonStr = null;
            Log.v(LOG_TAG, "Try is below");
            try {
                // Construct the URL for the Dictionary query
                // Possible parameters are avaiable at OWM's dictionary API page,
                // at https://market.mashape.com/montanaflynn/dictionary

                String baseUrl = "https://montanaflynn-dictionary.p.mashape.com/define?word=irony";
                String apiKey = "&APPID=" + BuildConfig.X_MASHAPE_KEY;
                URL url = new URL(baseUrl.concat(apiKey));


                // Create the request to Api and open the connection
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                //Antes de hacer el conect hay que usar el setRequestProperty para el header
                urlConnection.setRequestProperty("X-Mashape-Key", "5WNRUjRRXgmshGBMXphs9Jjn9RK0p1hBRh0jsnoyP9sv5cJl4H");
                urlConnection.connect();

                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                    // But it does make debugging a *lot* easier if you print out the completed
                    // buffer for debugging.
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    return null;
                }
                definitionsJsonStr = buffer.toString();
                Log.v(LOG_TAG, "definitions Json Str" + definitionsJsonStr);
                Log.i(LOG_TAG, definitionsJsonStr);
            } catch (IOException e) {
                Log.e("PlaceholderFragment", "Error ", e);
                // If the code didn't successfully get the weather data, there's no point in attemping
                // to parse it.
                return null;
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e("PlaceholderFragment", "Error closing stream", e);
                    }
                }
            }

            return null;
        }


    }
}
