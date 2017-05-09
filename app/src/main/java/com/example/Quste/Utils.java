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

    public static void putStructure(String filename, ArrayList<ArrayList<String>> structure) {
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

    public static ArrayList<ArrayList<String>> getStructure(String filename) {
        ArrayList<ArrayList<String>> ret = new ArrayList<>();
        File down_folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
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

}
