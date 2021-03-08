package com.example.week_7_exercises2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    TextView text;
    TextView file;
    Context context = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView) findViewById(R.id.textView);
        file = (TextView) findViewById(R.id.fileName);
        context = MainActivity.this;
        System.out.println(context.getFilesDir());
    }

    public void LoadButton(View v) {
        String fileName = "";
        String fileText = "";
        StringBuilder stringBuilder = new StringBuilder();
        try {
            fileName = file.getText().toString();
            InputStream ins = context.openFileInput(fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(ins));
            String line = br.readLine();
            while (line != null) {
                stringBuilder.append(line).append('\n');
                line = br.readLine();
            }
            ins.close();
        } catch (IOException e) {
            Log.e("IOException", "Virhe syötteessä");
        }
        fileText = stringBuilder.toString();
        text.setText(fileText);
    }

    public void SaveButton(View v) {
        String fileName = "";
        String newText = "";
        try {
            fileName = file.getText().toString();
            OutputStreamWriter ows = new OutputStreamWriter(context.openFileOutput(fileName,Context.MODE_PRIVATE));

            newText = text.getText().toString();
            ows.write(newText);
            ows.close();
        } catch (IOException e) {
            Log.e("IOException", "Virhe syötteessä");
        }

    }
}