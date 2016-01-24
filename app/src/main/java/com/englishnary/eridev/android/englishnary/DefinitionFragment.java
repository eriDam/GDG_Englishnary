package com.englishnary.eridev.android.englishnary;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class DefinitionFragment extends Fragment {
    private static final String LOG_TAG = DefinitionFragment.class.getSimpleName();
    private static final int DEFINITIONS_LOADER = 0;
    ArrayAdapter<String> mDefinitionsAdapter;

    public DefinitionFragment() {
    }

        public void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                // Add this line in order for this fragment to handle menu events.
                        setHasOptionsMenu(true);
        }

    // Inflate the menu; this adds items to the action bar if it is present.
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_definition_fragment, menu);


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //If item action_refresh clik call FetchDefinitionTask to execute
        int id = item.getItemId();
            //Toast.makeText(DefinitionFragment.this, "Awesome you are pushed button ok", Toast.LENGTH_SHORT).show();
            if (id == R.id.action_refresh) {
                FetchDefinitionTask definitionTask = new FetchDefinitionTask();
                definitionTask.execute();
                return true;
        }
        Log.v(LOG_TAG, "Action refresh is selected"+ id);
            return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.fragment_main, container, false);

        //Inflate root fragment
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        // Create data for the ListView.
        String[] wordsArray = {
                "Access", "Account", "Activity", "Administrative", "Advantage",
                "Advertisements", "Animate", "Applications", "Art", "Attachment",
                "Audience", "Admin",
                "Back up", "Bandwidth", "Banner", "Basics", "Benefit", "Blog", "Blue tooth",
                "Bookmarks", "Boot up", "Broadband", "Browser", "Bugs", "Bytes"
        };

        List<String> categoryDefinitions = new ArrayList<String>(Arrays.asList(wordsArray));

        // use it to fill the ListView it's attached to.
        ArrayAdapter mDefinitionsAdapter = new ArrayAdapter<String>(
                //The actual context "this"
                getActivity(),
                R.layout.list_item_definitions, // The name of the layout ID.
                R.id.list_item_definitions_textview, // The ID of the textview to fill.
                categoryDefinitions);

        //Get references to the list view and connect adapter
        ListView listViewDef = (ListView) rootView.findViewById(
                +R.id.listview_definitions);
        listViewDef.setAdapter(mDefinitionsAdapter);
        return rootView;
         }


        /*Step 2:
        Add APPIKEY in gradle.properties and
        These code snippets use an open-source library. http://unirest.io/java
        */


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
                Log.i(LOG_TAG, "Try is below");
                try {
                    // Construct the URL for the Dictionary query
                    // Possible parameters are avaiable at OWM's dictionary API page,
                    // at https://market.mashape.com/montanaflynn/dictionary

                    //I don't use header, here is the mistake
                   // URL url = new URL("https://montanaflynn-dictionary.p.mashape.com/define?word=irony"
//                    String baseUrl ="https://montanaflynn-dictionary.p.mashape.com/define?word=legal/";
//                    String apiKey =  "X-Mashape-Key: j3xDUUtlPBmsh2jTpSk26R6i75F7p1c4r9EjsnqnEs6GGPyV7t/"
//                    + "Accept: application/json";
//                    URL url = new URL(baseUrl.concat(apiKey));

                            String baseUrl = "https://montanaflynn-dictionary.p.mashape.com/define?word=irony";
                            String apiKey = "&APPID=" + BuildConfig.X_MASHAPE_KEY;
                            URL url = new URL(baseUrl.concat(apiKey));
                      

                    // Create the request to Api and open the connection
                    urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("GET");
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
                    Log.v(LOG_TAG, "Forecast Json Str" + definitionsJsonStr);
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

