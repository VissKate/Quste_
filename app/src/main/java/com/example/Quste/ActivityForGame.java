package com.example.Quste;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class ActivityForGame extends AppCompatActivity {
    TextView qst;
    Question next;
    Boolean n;
    Boolean b = true;
    int num;
    Question firstQuestion;
    int ids[] = {R.id.var1, R.id.var2, R.id.var3, R.id.var4};
    ArrayList<Question> arrrayQuste = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        loadQuest();

        qst = (TextView) findViewById(R.id.qstn);
        LinearLayout gamelinear = (LinearLayout) findViewById(R.id.gamelinear);
        Question firstQuestion = Quest.getInstance().questions.get(0);

            while (b) {
                n = false;
                qst.setText(firstQuestion.title);
                for (int id : ids) {
                    Button btn = (Button) findViewById(id);
                    btn.setVisibility(View.GONE);
                }
                gamelinear.setBackgroundDrawable(Drawable.createFromPath(firstQuestion.image));
                if (n) {
                    next = arrrayQuste.get(firstQuestion.answers.get(num).nextQuest);
                    firstQuestion = next;
                }
            }


    }


    public void Var(View h) {

        for (int i = 0; i < 4; i++) {
            String stringanswer = ((Button) h).getText().toString();

            if (stringanswer.equals(firstQuestion.answers.get(i).title)) {
                n = true;
                break;

            }
            num = num + 1;

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

    }


}

