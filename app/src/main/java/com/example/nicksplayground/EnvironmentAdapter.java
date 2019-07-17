package com.example.nicksplayground;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class EnvironmentAdapter extends ArrayAdapter<Environment> {
    private ArrayList<Environment> environment;
    private Context context;
    private TextView tvName;
    private ImageView ivEnvi;

    public EnvironmentAdapter(Context context, int resource, ArrayList<Environment> objects) {
        super(context, resource, objects);
        environment = objects;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.envi_row, parent, false);

        tvName = rowView.findViewById(R.id.tvEnvi);
        ivEnvi = rowView.findViewById(R.id.ivEnvi);
        Environment currentEnvironment = environment.get(position);
        tvName.setText(currentEnvironment.getName());
        String url = "https://nicksplaygroundfyp2019.000webhostapp.com/image/" + currentEnvironment.getImgPath();
        if (!url.isEmpty()){
            Picasso.get().load(url).fit().centerInside().into(ivEnvi);
        }

        return rowView;
    }
}