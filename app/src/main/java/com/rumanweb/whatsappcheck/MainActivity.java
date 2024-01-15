package com.rumanweb.whatsappcheck;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    EditText whatsappInput;
    Button checkingButton, pasteButton;

    private static final String WHATSAPP_PACKAGE = "com.whatsapp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        whatsappInput = findViewById(R.id.whatsappInput);
        checkingButton = findViewById(R.id.checkingButton);
        pasteButton = findViewById(R.id.paste_button);


        pasteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);

                // Check if clipboard has data
                if (clipboardManager != null && clipboardManager.hasPrimaryClip()) {
                    ClipData.Item item = clipboardManager.getPrimaryClip().getItemAt(0);
                    if (item.getText() != null) {
                        whatsappInput.setText(item.getText().toString());
                    }
                }
            }
        });


        checkingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String validNumber = whatsappInput.getText().toString();


                if (!validNumber.isEmpty()) {
                    if(validNumber.startsWith("+880")){
                    }
                    else {
                        validNumber = "+880" + validNumber;
                    }
                    checkWhatsAppNumber(validNumber);
                } else {
                    Toast.makeText(MainActivity.this, "Please insert the number", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void checkWhatsAppNumber(String validNumber) {
        Uri uri = Uri.parse("https://wa.me/" + validNumber);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);

    }
}