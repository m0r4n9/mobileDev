package com.sevostyanov.multiactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickNewActivity(View view){
        Intent intent = new Intent(this, SeconActivity.class);
//        intent.putExtra("key", "MIREA - Севостьянов Александр Максимович");
        EditText text = findViewById(R.id.editTextText);
        intent.putExtra("key", String.valueOf(text.getText()));
        startActivity(intent);

    }
}
