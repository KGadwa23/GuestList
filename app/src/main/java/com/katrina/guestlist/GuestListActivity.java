package com.katrina.guestlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class GuestListActivity extends AppCompatActivity {

    EditText mEnterGuestET;
    Button mSaveGuestButton;
    TextView mGuestListTV;

    ArrayList<String> mGuests;

    private final static String GUEST_LIST_KEY = "Guest List Bundle Key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_list);

        if (savedInstanceState !=null) {
            //There's some state data - check to see if there's a guest list array
            mGuests = savedInstanceState.getStringArrayList(GUEST_LIST_KEY);
        }

        if (mGuests == null) {
            mGuests = new ArrayList<String>();
        }

        mEnterGuestET = (EditText) findViewById(R.id.guest_name_edit_text);
        mSaveGuestButton = (Button) findViewById(R.id.save_button);
        mGuestListTV = (TextView) findViewById(R.id.guest_list_text_view);

        updateGuestList();

        mSaveGuestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String newGuestName = mEnterGuestET.getText().toString();
                //Make sure user has entered some text
                if (newGuestName.length() > 0) {
                    mGuests.add(newGuestName);
                    mEnterGuestET.getText().clear(); //Clear contents of Edit Text, ready for new guest
                    GuestListActivity.this.updateGuestList();
                } else {
                    Toast.makeText(GuestListActivity.this, "Please enter a guest name", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outBundle) {
        outBundle.putStringArrayList(GUEST_LIST_KEY, mGuests);
    }

    private void updateGuestList() {
        //Create a String from all of the names in the MGuests list, seperated by newlines
        String displayString = "";
        for (String name : mGuests) {
            displayString = displayString + name + "\n";
        }
        mGuestListTV.setText(displayString);
    }


}

