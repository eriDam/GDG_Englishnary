package com.englishnary.eridev.android.englishnary;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by eridev on 30/01/16.
 */

class FetchDefinitionTask extends AsyncTask<String, Void, String[]> {

    private final String LOG_TAG = FetchDefinitionTask.class.getSimpleName();
    private EditText etxtPalabra;
/**
 +         * Take the String representing the complete forecast in JSON Format and
 +         * pull out the data we need to construct the Strings needed for the wireframes.
 +         *
 +         * Fortunately parsing is easy:  constructor takes the JSON string and converts it
 +         * into an Object hierarchy for us.
 +         */
            private String[] getWordsrDataFromJson(String textJsonStr, String atribution)
            throws JSONException

    {

     // These are the names of the JSON objects that need to be extracted.
                    final String OWM_LIST = "definitions";
                    final String OWM_TEXT = "text";
                    final String OWM_ATRIBUTIOND = "atribution";


                    JSONObject defsJson = new JSONObject(textJsonStr);
                    JSONArray defsArray = defsJson.getJSONArray(OWM_LIST);

                    String[] resultStrs = new String[wordD];
                    for(int i = 0; i < defsArray.length(); i++) {

                       String text;
                       String atributionDef;

                       // Get the JSON object representing the wordDef
                       JSONObject wordDef = defsArray.getJSONObject(i);

                        // text is in a child array called "definitions"
                        JSONObject definitionsTObject = defsArray.getJSONArray(OWM_LIST).getJSONObject(0);
                        text = definitionsTObject.getString(OWM_TEXT);
                        atributionDef = definitionsTObject.getString(OWM_ATRIBUTIOND);

                        resultStrs[i] = text + " - " + atributionDef + " - ";
                    }

                        for (String s : resultStrs) {
                        Log.v(LOG_TAG, "DEFS entry: " + s);
                    }
                    return resultStrs;

                        }

    @Override
    protected String[] doInBackground(String... params) {

        // If there's no WORD, there's nothing to look up.  Verify size of params.
        if (params.length == 0) {
        return null;
        }
        // These two need to be declared outside the try/catch
        // so that they can be closed in the finally block.
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        // Will contain the raw JSON response as a string.
        String definitionsJsonStr = null;
        String wordToDefinition = null;

        Log.v(LOG_TAG, "Try is below");
        try {
            // Construct the URL for the Dictionary query
            // Possible parameters are available at OWM's dictionary API page,
            // at https://market.mashape.com/montanaflynn/dictionary

            //String baseUrl = "https://montanaflynn-dictionary.p.mashape.com/define?word"+wordToDefinition+"=";
            String baseUrl = "https://montanaflynn-dictionary.p.mashape.com/define?word=book";
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

            // Log.i(LOG_TAG, definitionsJsonStr);
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
        try {
                            return getWordsrDataFromJson(textJsonStr,atribution);
        } catch (JSONException e) {
                            Log.e(LOG_TAG, e.getMessage(), e);
            e.printStackTrace();
                        }
        return null;
    }


}