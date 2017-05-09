package com.example.Quste;


import java.util.ArrayList;

public class Quest {
    private static Quest instance = null;
    ArrayList<Question> questions;
    Question curQuestion = null;


    private Quest() {
        questions = new ArrayList<>();
    }

    public static Quest getInstance() {
        if (instance == null)
            instance = new Quest();
        return instance;
    }
}
