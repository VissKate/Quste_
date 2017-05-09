package com.example.Quste;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;

import com.example.myapplication.R;

public class SecondActivity extends AppCompatActivity {

    Paint p;
    Path path;
    Path pathDst;
    Canvas canvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_second);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.activity_main);
        relativeLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));


    }

    public void onDraw(Canvas canvas) {
        p.setColor(Color.GREEN);
        canvas.drawPath(path, p);
        canvas.translate(200, 0);
        p.setColor(Color.BLUE);
        canvas.drawPath(pathDst, p);
    }

    public void newScreen(View v) {
        Intent obj = new Intent(SecondActivity.this, forQuestions.class);
        startActivity(obj);


    }

    public void startGame(View v) {
        Intent obj = new Intent(SecondActivity.this, ActivityForGame.class);
        startActivity(obj);
    }


}







