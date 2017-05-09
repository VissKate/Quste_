package com.example.Quste;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.content.CursorLoader;
import android.view.View;
import android.view.ViewManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;


public class MainActivity extends Activity {
    int kolvobtn = 0;
    Quest quest;
    Question question;
    Answer answer;
    private ImageView imageView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        quest = Quest.getInstance();
        question = new Question();
        answer = new Answer();


        imageView = (ImageView) findViewById(R.id.edit_vendor_image);
        Button PickImage = (Button) findViewById(R.id.UploadIm);
        PickImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, 1);
            }
        });
        Button buttono = (Button) findViewById(R.id.btnWriteSD);
        buttono.setTextColor(getResources().getColor(R.color.colorPrimary));

        buttono.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                fillQuestion();
                quest.questions.add(question);
                finish();
            }
        });
    }

    private void fillQuestion() {

        EditText editText = (EditText) findViewById(R.id.edit_vendor_title);
        question.title = editText.getText().toString();

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    Uri selectedImageUri = imageReturnedIntent.getData();
                    String imageUri = getPath(selectedImageUri);
                    question.image = imageUri;
                    Drawable drawable = Drawable.createFromPath(imageUri);
                    imageView.setImageDrawable(drawable);
                }
        }
    }

    public String getPath(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = new CursorLoader(this, uri, projection, null, null, null).loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);

    }


    public void newVarAnswer(final View v) {
        if (kolvobtn == 4)
            return;
        EditText editText = (EditText) findViewById(R.id.editForBtn);
        String btnText = editText.getText().toString();

        if (btnText.length() == 0)
            return;
        editText.setText("");

        //TODO: сделать через LayoutInflater как в адаптере
        LinearLayout lin = (LinearLayout) findViewById(R.id.Rellay);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        Button button = new Button(this);
        button.setText(btnText);
        button.setTextColor(getResources().getColor(R.color.colorAccent));
        button.setLayoutParams(layoutParams);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View b) {
                ((ViewManager) b.getParent()).removeView(b);
                kolvobtn--;
                v.setVisibility(View.VISIBLE);
            }
        });
        lin.addView(button);

        answer.title = btnText;
        answer.nextQuest = -1;
        question.answers.add(answer);
        answer = new Answer();

        question.anssiz = ++kolvobtn;

        if (kolvobtn == 4)
            v.setVisibility(View.GONE);
    }

}

