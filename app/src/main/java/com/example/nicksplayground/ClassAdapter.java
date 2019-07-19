package com.example.nicksplayground;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ClassAdapter extends BaseAdapter {

    Context context;
    private final String [] lesson;
    private final String [] timing;
    View view;
    LayoutInflater layoutInflater;

    public ClassAdapter(Context context, String[] lesson, String[] timing) {
        this.context = context;
        this.lesson = lesson;
        this.timing = timing;
    }

    @Override
    public int getCount() {
        return lesson.length;
    }

    // 3
    @Override
    public long getItemId(int position) {
        return 0;
    }

    // 4
    @Override
    public Object getItem(int position) {
        return null;
    }

    // 5
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
       if (convertView == null){
           view = new View(context);
           view = layoutInflater.inflate(R.layout.class_grid,null);
           TextView lessons = view.findViewById(R.id.tvClassName);
           TextView timings = view.findViewById(R.id.tvTiming);
           lessons.setText(lesson[position]);
           timings.setText(timing[position]);
       }
       return view;
    }
}
