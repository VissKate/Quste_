package com.example.Quste;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.CursorLoader;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.myapplication.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;


public class MainActivity extends Activity {
    String imageUri;
    int kolvobtn = 0;
    ArrayList<String> var;
    int numberOfAnswer = 0;
    Quest quest;
    Question question;
    Answer answer;
    ArrayList<ArrayList<String>> structure = new ArrayList<>();
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
                ArrayList<String> row = question.makeRow();
                structure.add(row);

                putStructure("new.csv", structure);
                ArrayList<ArrayList<String>> structure = getStructure("new.csv");
                System.out.println(structure);
                Toast toast = Toast.makeText(getApplicationContext(),
                        structure.toString(), Toast.LENGTH_SHORT);
                toast.show();

                finish();

            }
        });


/*
        save_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                putStructure("test.csv",structure);
            }
        });
        */
    }

    private void fillQuestion() {

        EditText editText = (EditText) findViewById(R.id.edit_vendor_title);
        question.title = editText.getText().toString();

    }

    public void putStructure(String filename, ArrayList<ArrayList<String>> structure) {

        File down_folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        String csvFile = down_folder.getAbsolutePath() + "/" + filename;
        BufferedWriter bw = null;
        try {
            File f = new File(csvFile);
            if (!f.exists())
                f.mkdirs();
            bw = new BufferedWriter(new FileWriter(f));
            for (ArrayList<String> row : structure) {
                for (String col : row) {
                    bw.write(col + ";");
                }
                bw.write("\r\n");
                bw.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public ArrayList<ArrayList<String>> getStructure(String filename) {
        ArrayList<ArrayList<String>> ret = new ArrayList<>();
        File down_folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        String csvFile = down_folder.getPath() + "/" + filename;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(csvFile));
            String line = "";
            while ((line = br.readLine()) != null) {
                ArrayList<String> tmp = new ArrayList<>();
                Collections.addAll(tmp, line.split(";"));
                ret.add(tmp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return ret;

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


    public void newVarAnswer(View v) {
        if (kolvobtn < 4) {
            EditText editText = (EditText) findViewById(R.id.editForBtn);
            String btnText = editText.getText().toString();
            //чтобы не было пустого

            editText.setText("");
            LinearLayout lin = (LinearLayout) findViewById(R.id.Rellay);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            Button button = new Button(this);
            button.setText(btnText);

            button.setTextColor(getResources().getColor(R.color.colorAccent));
            button.setBackgroundColor(Color.WHITE);
            layoutParams.gravity = Gravity.CENTER_HORIZONTAL;
            button.setLayoutParams(layoutParams);

            // button.setTextAppearance(this, android.support.v7.appcompat.R.style.Base_TextAppearance_AppCompat_Widget_Button_Borderless_Colored);


            lin.addView(button);
            answer.title = btnText;
            answer.nextQuest = kolvobtn + 1;
            question.answers.add(answer);
            answer = new Answer();


        }
        kolvobtn = kolvobtn + 1;
        question.anssiz = kolvobtn;

        //создание кнопок

    }

}

