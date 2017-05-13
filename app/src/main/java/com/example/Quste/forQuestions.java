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

import java.util.ArrayList;

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
        Quest.getInstance().questions = new ArrayList<>();
        ArrayList<ArrayList<String>> structure = Utils.getStructure("new.csv");
        for (ArrayList<String> row : structure) {
            Question q = new Question();
            q.title = row.get(0);
            q.image = row.get(1);
            int n = Integer.parseInt(row.get(2));
            for (int i = 0; i < n; i++) {
                Answer a = new Answer();
                a.title = row.get(3 + i * 2);
                a.nextQuest = Integer.parseInt(row.get(3 + i * 2 + 1));
                q.answers.add(a);
            }
            Quest.getInstance().questions.add(q);
        }
        adapter.setQuestions(Quest.getInstance().questions);
        listview.setAdapter(adapter);
    }

    private void saveQuest() {
        ArrayList<ArrayList<String>> structure = new ArrayList<>();
        for (Question question : Quest.getInstance().questions) {
            ArrayList<String> row = question.makeRow();
            structure.add(row);
        }
        Utils.putStructure("new.csv", structure);
        Toast.makeText(this, "Successfully written to SD", Toast.LENGTH_SHORT).show();
        //TODO: Написать нормальный тост (Во имя Тзинча!)
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

