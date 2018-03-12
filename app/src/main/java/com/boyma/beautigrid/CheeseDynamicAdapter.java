package com.boyma.beautigrid;

/**
 * Author: alex askerov
 * Date: 9/9/13
 * Time: 10:52 PM
 */

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import org.askerov.dynamicgrid.BaseDynamicGridAdapter;
import org.askerov.dynamicgrid.MyObj;

import java.util.List;
import java.util.Random;

/**
 * Author: alex askerov
 * Date: 9/7/13
 * Time: 10:56 PM
 */
public class CheeseDynamicAdapter extends BaseDynamicGridAdapter {
    private TypedArray imgs;

    public CheeseDynamicAdapter(Context context, List<MyObj> items, int columnCount) {
        super(context, items, columnCount);
        this.imgs = imgs;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CheeseViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_grid, null);
            holder = new CheeseViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (CheeseViewHolder) convertView.getTag();
        }
        holder.build(getItem(position).toString(),position);
        return convertView;
    }

    private class CheeseViewHolder {
        private TextView titleText;
        private ImageView image;

        private CheeseViewHolder(View view) {
            titleText = (TextView) view.findViewById(R.id.item_title);
            image = (ImageView) view.findViewById(R.id.item_img);
        }

        void build(String title, int position) {
            titleText.setText(title);
            /*TypedArray imgs = getContext().getResources().obtainTypedArray(R.array.random_imgs);
            if (position<imgs.length()-1){
                image.setImageResource(imgs.getResourceId(position,-1));
            }else {
                image.setImageResource(imgs.getResourceId(2,-1));
            }*/
            image.setImageResource(getContext().getResources().getIdentifier("ic_ms_"+title,"drawable",getContext().getPackageName()));


            Random rnd = new Random();
            int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
            image.setColorFilter(color);
        }
    }
}