package com.example.Quste;

import android.os.Environment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;


public class Utils {

    public static void saveQuestions(String filename, ArrayList<Question> questions) {
        ArrayList<ArrayList<String>> structure = new ArrayList<>();
        for (Question question : questions) {
            ArrayList<String> row = question.makeRow();
            structure.add(row);
        }
        File down_folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        String csvFile = down_folder.getAbsolutePath() + "/" + filename;
        BufferedWriter bw = null;
        try {
            File f = new File(csvFile);
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

    public static ArrayList<Question> loadQuestions(String filename) {
        ArrayList<Question> ret = new ArrayList<>();
        ArrayList<ArrayList<String>> structure = new ArrayList<>();
        File down_folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        String csvFile = down_folder.getPath() + "/" + filename;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(csvFile));
            String line = "";
            while ((line = br.readLine()) != null) {
                ArrayList<String> tmp = new ArrayList<>();
                Collections.addAll(tmp, line.split(";"));
                structure.add(tmp);
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
            ret.add(q);
        }
        return ret;

    }

}
