package com.example.Quste;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.R;

import java.util.ArrayList;

public class BoxAdapter extends BaseAdapter {
    private forQuestions activity;
    private ArrayList<Question> objects;

    BoxAdapter(forQuestions forQuestions) {
        activity = forQuestions;
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        }
        Question it = getQuestion(position);

        ((TextView) view.findViewById(R.id.nameOtQuestion)).setText(it.title);

        int ids[] = {R.id.VarF, R.id.VarS, R.id.VarT, R.id.VarFo};
        for (int i = 0; i < it.answers.size(); i++) {
            Button btn = (Button) view.findViewById(ids[i]);
            if (it.answers.get(i) != null) {
                btn.setVisibility(View.VISIBLE);
                btn.setText(it.answers.get(i).title);
                btn.setOnClickListener(it);
            } else
                btn.setVisibility(View.GONE);
        }

        return view;

    }

    private Question getQuestion(int position) {
        return ((Question) getItem(position));
    }

    void setQuestions(ArrayList<Question> items) {
        objects = items;

    }

}
