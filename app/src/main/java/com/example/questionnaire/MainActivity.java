package com.example.questionnaire;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.MessageFormat;

public class MainActivity extends AppCompatActivity {

    String inputName;
    String inputSurname;
    String inputEmail;
    String inputPhoneNumber;
    String inputSocialMedia;
    String inputBiography;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button sendBtn = findViewById(R.id.sendButton);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readUserInput();
                if (allFieldsFulfilled()) {
                    createUserInformationSharingIntent();
                } else {
                    Toast.makeText(getBaseContext(), "Fulfill all the fields, please.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private String getStringFromEditTextById(int editTextId) {
        return ((EditText) findViewById(editTextId)).getText().toString();
    }

    private void readUserInput() {
        inputName = getStringFromEditTextById(R.id.inputNameEditText);
        inputSurname = getStringFromEditTextById(R.id.inputSurnameEditText);
        inputEmail = getStringFromEditTextById(R.id.inputEmailEditText);
        inputPhoneNumber = getStringFromEditTextById(R.id.inputPhoneNumberEditText);
        inputSocialMedia = getStringFromEditTextById(R.id.inputSocialMediaEditText);
        inputBiography = getStringFromEditTextById(R.id.inputBiographyEditText);
    }

    private boolean allFieldsFulfilled() {
        String[] inputALl = new String[]{inputName, inputBiography, inputEmail, inputSurname, inputSocialMedia, inputPhoneNumber};
        for (String inputField :
                inputALl) {
            if (inputField.equals("")) {
                return false;
            }
        }
        return true;
    }

    private void createUserInformationSharingIntent() {
        String textToShare = MessageFormat.format("Name: {0}\nSurname: {1}\nEmail: {2}\nMobile phone number: {3}\nSocial: {4}\nBiography: {5}", inputName, inputSurname, inputEmail, inputPhoneNumber, inputSocialMedia, inputBiography);
        Intent shareUserInformation = new Intent()
                .setAction(Intent.ACTION_SEND)
                .setType("text/plain")
                .putExtra(Intent.EXTRA_TEXT, textToShare);
        startActivity(Intent.createChooser(shareUserInformation, "Share"));
    }
}