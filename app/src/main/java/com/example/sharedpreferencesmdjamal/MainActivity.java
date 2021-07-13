package com.example.sharedpreferencesmdjamal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText t1, t2;
    AppCompatButton savebtn, delbtn;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        t1 = findViewById(R.id.t1);
        t2 = findViewById(R.id.t2);
        tv = findViewById(R.id.tv);
        savebtn = findViewById(R.id.savebtn);
        delbtn = findViewById(R.id.delbtn);

        checkExistenceRecord();
        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp = getSharedPreferences("credentials", MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("username", t1.getText().toString());
                editor.putString("password", t2.getText().toString());
                editor.apply();
                t1.setText("");
                t2.setText("");
                tv.setTextColor(Color.GREEN);
                tv.setText("Inserted Successfully");
            }
        });
        delbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp = getSharedPreferences("credentials", MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.clear();
                editor.apply();
                tv.setTextColor(Color.GREEN);
                tv.setText("Deleted Successfully");
            }
        });
    }

    public void checkExistenceRecord() {
        SharedPreferences sp = getSharedPreferences("credentials", MODE_PRIVATE);
        if (sp.contains("username")) {
            t1.setText(sp.getString("username", ""));
            t2.setText(sp.getString("password", ""));
        } else {
            tv.setTextColor(Color.RED);
            tv.setText("Record not found");
        }
    }

}