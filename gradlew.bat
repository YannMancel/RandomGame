package com.mancel.yann.logan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.mancel.yann.logan.logan.annotations.Meow;
import com.mancel.yann.logan.logan.main.Logan;

public class MainActivity extends AppCompatActivity {

    // FIELDS --------------------------------------------------------------------------------------

    private Button mButton;

    @Meow(message = "Field to save")
    private String mAttibut;

    private static String BUNDLE_STATE_TEXT_BUTTON = "BUNDLE_STATE_TEXT_BUTTON";

    // METHODS -------------------------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // BUTTON
        this.mButton = findViewById(R.id.activity_main_button);
        this.mButton.setOnClickListener((v) -> {this.onClick();});

        // FIELD FOR LOGAN
        this.mAttibut = this.mButton.getText().toString();

        // LOGAN
        System.out.println("[onCreate] ----------------------------------------------------------");
        Logan.onSaveInstanceState(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(BUNDLE_STATE_TEXT_BUTTON, this.mButton.getText().toString());

        // LOGAN
        System.out.println("[onSaveInstanceState] -----------------------------------------------");
        Logan.onSaveInstanceState(this);

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // LOGAN
        System.out.println("[onRestoreInstanceState] --------------------------------------------");
        Logan.onSaveInstanceState(this);

        final String message = savedInstanceState.getString(BUNDLE_STATE_TEXT_BUTTON);

        if (message != null) {
            this.mButton.setText(message);
        }
    }

    // ACTIONS ********************************************************************************