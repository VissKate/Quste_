package com.example.Quste;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import java.util.ArrayList;

public class BoxAdapter extends BaseAdapter {
    private forQuestions activity;
    private ArrayList<Question> objects = new ArrayList<>();

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
    public View getView(final int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        }
        Question it = getQuestion(position);

        ((Button) view.findViewById(R.id.nameOtQuestion)).setText(it.title);
        view.findViewById(R.id.nameOtQuestion).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Question question = Quest.getInstance().curQuestion;
                if (question == null)
                    return;
                question.answers.get(question.a_id).nextQuest = position;
                Quest.getInstance().curQuestion = null;
            }
        });
        int ids[] = {R.id.VarF, R.id.VarS, R.id.VarT, R.id.VarFo};
        for (int id : ids) {
            Button btn = (Button) view.findViewById(id);
            btn.setVisibility(View.GONE);
        }
        int i = 0;
        for (Answer a : it.answers) {
            Button btn = (Button) view.findViewById(ids[i]);
            btn.setVisibility(View.VISIBLE);
            btn.setText(a.title);
            btn.setOnClickListener(it);
            i++;
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
