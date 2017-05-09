package com.example.Quste;


import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class Question implements View.OnClickListener {
    String title;
    int anssiz;
    int a_id = -1;

    ArrayList<Answer> answers;


    String image;

    Question() {
        answers = new ArrayList<>();
    }

    public ArrayList<String> makeRow() {
        ArrayList<String> row = new ArrayList<>();
        row.add(title);
        row.add(image);


        row.add(anssiz + "");
        for (Answer a : answers) {
            row.add(a.title);
            row.add(a.nextQuest + "");
        }
        return row;
    }

    @Override
    public void onClick(View v) {

        Quest.getInstance().curQuestion = this;
        String ans = ((Button) v).getText().toString();
        int i = 0;
        for (Answer a : answers) {

            if (a.title.equals(ans)) {
                a_id = i;
                break;
            }
            i++;
        }
        System.out.println(i);
    }
}
