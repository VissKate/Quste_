package com.example.Quste;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;

import java.util.ArrayList;

public class forQuestions extends AppCompatActivity implements View.OnClickListener {
    ArrayList<Question> questionsn = new ArrayList<Question>();
    BoxAdapter adapter;
    ListView listview;
    TextView textViewNMQ;

    String buttonText;
    Boolean clickEdit = false;
    Button edit;
    RelativeLayout lay;
    String titleText;
    int nmbSlQ;


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.forquestions);
        adapter = new BoxAdapter(this);
        listview = (ListView) findViewById(R.id.questions);


    }

    public void f(View v) {
        textViewNMQ = (TextView) findViewById(R.id.nameOtQuestion);
        if (clickEdit) {
            int i = 0;
            titleText = textViewNMQ.getText().toString();
            for (Question qstn : Quest.getInstance().questions) {
                if (qstn.title.equals(titleText)) {
                    nmbSlQ = i;
                    break;
                }
                i++;
            }
            // Toast.makeText(getApplicationContext(), nmbSlQ, Toast.LENGTH_SHORT).show();
            System.out.println(nmbSlQ);
        }


    }

    public void newscr(View v) {
        Intent obj = new Intent(forQuestions.this, MainActivity.class);
        startActivity(obj);
    }

    public void namevar(View n) {
        Button button = (Button) n;
        buttonText = button.getText().toString();
        if (clickEdit) {
            Toast.makeText(getApplicationContext(), buttonText, Toast.LENGTH_SHORT).show();
        }
    }

    public void edit(View p) {
        edit = (Button) findViewById(R.id.edit);
        if (!clickEdit) {
            clickEdit = true;
            edit.setText("ok");
            //for(int i=0;i<)
        } else {
            edit.setText("edit");
            clickEdit = false;
        }

    }

    @Override
    public void onClick(View v) {

    }


    @Override
    protected void onResume() {
        super.onResume();
        adapter.setQuestions(Quest.getInstance().questions);
        listview.setAdapter(adapter);
    }
}

