package com.englishnary.eridev.android.englishnary;

import android.content.res.Resources;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class DefinitionFragment extends Fragment {
    private static final String LOG_TAG = DefinitionFragment.class.getSimpleName();
    private static final int DEFINITIONS_LOADER = 0;
    private ArrayAdapter<String> mDefinitionsAdapter;

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
                definitionTask.execute("book");
                return true;
        }
        Log.v(LOG_TAG, "Action refresh is selected"+ id);
return super.onOptionsItemSelected(item);
    }
        @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.fragment_main, container, false);

       //Create an ArrayList
                    Resources res = getResources();
                    String[] definitionsArray = res.getStringArray(R.array.items);

        List<String> categoryDefinitions = new ArrayList<String>(Arrays.asList(definitionsArray));
            for (int i = 0; i <= definitionsArray.length; i++) {

                Log.i("Item ", String.valueOf(i));
            }
        // use it to fill the ListView it's attached to.
         mDefinitionsAdapter = new ArrayAdapter<String>(
                //The actual context "this"
                getActivity(),
                R.layout.list_item_definitions, // The name of the layout ID.
                R.id.list_item_definitions_textview, // The ID of the textview to fill.
                categoryDefinitions);

        //Get references to the list view and connect adapter
//        ListView listViewDef = (ListView) rootView.findViewById(
//                +R.id.listview_definitions);
//        listViewDef.setAdapter(mDefinitionsAdapter);
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            //setup Views
            ListView listViewDef = (ListView) rootView.findViewById(R.id.listview_definitions);
            listViewDef.setAdapter(mDefinitionsAdapter);
            // return inflater.inflate(R.layout.fragment_main, container, false);
        Log.v(LOG_TAG, "listViewDef is inflate" + listViewDef.toString());
       return rootView;
         }
}

