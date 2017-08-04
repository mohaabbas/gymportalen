package com.example.moha.gymportalen;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;


/* ----------------------------------- OVNINGARADAPTER ------------------------------------ */


public class OvningarAdapter extends ArrayAdapter<String> {


    private int[] icons;
    private String[] titles;
    private String[] categorys;
    String[] texts;
    int [] images;
    private int [] ratings;

    Context c;
    LayoutInflater inflater;


    public OvningarAdapter(Context context, int[] icons, String[] titles, String[] categorys, String[] texts, int[] images, int[] ratings) {
        super(context, R.layout.item_ovningar, titles);

        this.c = context;
        this.icons = icons;
        this.titles = titles;
        this.categorys = categorys;
        this.texts = texts;
        this.images = images;
        this.ratings = ratings;

    }

    public class ViewHolder{

        ImageView theIcon;
        TextView theTitle;
        TextView theCategory;
        TextView theText;
        ImageView theImage;
        RatingBar theRating;

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        if(convertView == null){
            inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_ovningar, null);
        }

        final ViewHolder myHolder = new ViewHolder();

        myHolder.theIcon = (ImageView) convertView.findViewById(R.id.ovningImage);
        myHolder.theTitle = (TextView) convertView.findViewById(R.id.ovningTitle);
        myHolder.theCategory = (TextView) convertView.findViewById(R.id.ovningCategory);
        myHolder.theRating = (RatingBar) convertView.findViewById(R.id.ovningRating);


        myHolder.theIcon.setImageResource(icons[position]);
        myHolder.theTitle.setText(titles[position]);
        myHolder.theCategory.setText("Kategori: " + categorys[position]);
        myHolder.theRating.setRating(ratings[position]);


        return convertView;

    }



}
