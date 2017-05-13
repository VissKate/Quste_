package com.example.Quste;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


public class ActivityForGame extends AppCompatActivity implements View.OnClickListener {
    TextView qst;
    LinearLayout gamelinear;

    Button ansb[] = new Button[4];
    Quest quest;

    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        quest = Quest.getInstance();
        quest.questions = Utils.loadQuestions("new.csv");

        qst = (TextView) findViewById(R.id.qstn);
        gamelinear = (LinearLayout) findViewById(R.id.gamelinear);
        quest.curQuestion = quest.questions.get(0);

        int ansbids[] = {R.id.var1, R.id.var2, R.id.var3, R.id.var4};
        for (int id : ansbids) {
            ansb[i] = (Button) findViewById(id);
            ansb[i].setOnClickListener(this);
            ansb[i++].setVisibility(View.GONE);
        }
        setQuestion();
    }

    private void setQuestion() {
        qst.setText(quest.curQuestion.title);
        gamelinear.setBackgroundDrawable(Drawable.createFromPath(quest.curQuestion.image));
        i = 0;
        for (Button btn : ansb) {
            if (i < quest.curQuestion.answers.size()) {
                btn.setVisibility(View.VISIBLE);
                btn.setText(quest.curQuestion.answers.get(i++).title);
            } else
                btn.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        int ans;
        switch (v.getId()) {
            case R.id.var1:
                ans = 0;
                break;
            case R.id.var2:
                ans = 1;
                break;
            case R.id.var3:
                ans = 2;
                break;
            case R.id.var4:
                ans = 3;
                break;
            default:
                return;
        }
        int nextQuestionId = quest.curQuestion.answers.get(ans).nextQuest;
        if (nextQuestionId == -1)
            return;
        quest.curQuestion = quest.questions.get(nextQuestionId);
        setQuestion();
    }
}
