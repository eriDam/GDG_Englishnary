package com.englishnary.eridev.android.englishnary;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
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
public class MainActivityFragment extends Fragment {
    ArrayAdapter<String> mDefinitionsAdapter;
    public MainActivityFragment() {
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
                List<String> weekDefinitions = new ArrayList<String>(Arrays.asList(wordsArray));

                        // use it to fill the ListView it's attached to.
                               ArrayAdapter mDefinitionsAdapter =  new ArrayAdapter<String>(
                                            //The actual context "this"
                                           getActivity(),
                                            R.layout.list_item_definitions, // The name of the layout ID.
                                            R.id.list_item_definitions_textview, // The ID of the textview to fill.
                                            weekDefinitions);

                        //Get references to the list view and connect adapter
                                ListView listView =(ListView) rootView.findViewById(
                +                R.id.listview_definitions);
                listView.setAdapter(mDefinitionsAdapter);


                                return  rootView;
    }
    }

