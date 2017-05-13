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
    Button var1;
    Button var2;
    Button var3;
    Button var4;
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
        String csvFile = "C:\\Users\\Надя.Катя\\Desktop\\Quste\\test.csv";


        qst = (TextView) findViewById(R.id.qstn);
        var1 = (Button) findViewById(R.id.var1);
        var2 = (Button) findViewById(R.id.var2);
        var3 = (Button) findViewById(R.id.var3);
        var4 = (Button) findViewById(R.id.var4);
        LinearLayout gamelinear = (LinearLayout) findViewById(R.id.gamelinear);


        for (Question firstQuestion : arrrayQuste) {//как вытащить первый вопрос и на этом остановиться?
            while (b) {
                n = false;
                qst.setText(firstQuestion.title);
                for (int id : ids) {

                    Button btn = (Button) view.findViewById(id);
                    btn.setVisibility(View.GONE);

                }
                gamelinear.setBackgroundDrawable(Drawable.createFromPath(firstQuestion.image));
                if (n) {
                    next = arrrayQuste.get(firstQuestion.answers.get(num).nextQuest);
                    firstQuestion = next;
                }
            }
            break;
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


}

