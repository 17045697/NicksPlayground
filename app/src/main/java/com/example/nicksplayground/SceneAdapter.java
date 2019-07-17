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

public class SceneAdapter extends ArrayAdapter<Scene> {
    private ArrayList<Scene> scene;
    private Context context;
    private TextView tvName;
    private ImageView ivScene;

    public SceneAdapter(Context context, int resource, ArrayList<Scene> objects) {
        super(context, resource, objects);
        scene = objects;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.scene_row, parent, false);

        tvName = rowView.findViewById(R.id.tvScene);
        ivScene = rowView.findViewById(R.id.ivScene);
        Scene currentScene = scene.get(position);
        tvName.setText(currentScene.getName());
        String url = "https://nicksplaygroundfyp2019.000webhostapp.com/image/" + currentScene.getImgPath();
        if (!url.isEmpty()){
            Picasso.get().load(url).fit().centerInside().into(ivScene);
        }

        return rowView;
    }
}