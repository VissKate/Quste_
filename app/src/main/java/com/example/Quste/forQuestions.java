package com.example.Quste;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class forQuestions extends AppCompatActivity {
    BoxAdapter adapter;
    ListView listview;

    String buttonText;
    Boolean clickEdit = false;
    Button edit;


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.forquestions);
        adapter = new BoxAdapter(this);
        listview = (ListView) findViewById(R.id.questions);
        listview.setAdapter(adapter);
    }

    public void newscr(View v) {
        Intent obj = new Intent(forQuestions.this, MainActivity.class);
        startActivity(obj);
    }


    @Override
    protected void onResume() {
        super.onResume();
        adapter.setQuestions(Quest.getInstance().questions);
        listview.setAdapter(adapter);
    }


    public void save(View view) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        } else {
            saveQuest();
        }
    }

    public void load(View v) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
        } else {
            loadQuest();
        }
    }

    private void loadQuest() {
        Quest.getInstance().questions = Utils.loadQuestions("new.csv");
        adapter.setQuestions(Quest.getInstance().questions);
        listview.setAdapter(adapter);
    }

    private void saveQuest() {
        Utils.saveQuestions("new.csv", Quest.getInstance().questions);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (grantResults.length == 0 || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            return;
        }
        switch (requestCode) {
            case 1:
                saveQuest();
                break;
            case 2:
                loadQuest();
                break;
        }
    }
}

