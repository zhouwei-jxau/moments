package com.example.zhouwei.comments;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.List;

/**
 * Created by zhouwei on 2018/12/4.
 */

public class CommentAdapter extends ArrayAdapter{
    private int resourceId=-1;

    public CommentAdapter(@NonNull Context context, int resource, @NonNull List<?> objects) {
        super(context, resource, objects);
        this.resourceId=resource;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        CommitItem item=(CommitItem) this.getItem(position);
        View view=(View) LayoutInflater.from(getContext()).inflate(resourceId,null);
        //判断图片数量，选择显示图片
        int image_counter=item.getImageUri().length;
        ImageView[] images={(ImageView)view.findViewById(R.id.image1),
                (ImageView)view.findViewById(R.id.image2),
                (ImageView)view.findViewById(R.id.image3),
                (ImageView)view.findViewById(R.id.image4),
                (ImageView)view.findViewById(R.id.image5),
                (ImageView)view.findViewById(R.id.image6),
                (ImageView)view.findViewById(R.id.image7),
                (ImageView)view.findViewById(R.id.image8),
                (ImageView)view.findViewById(R.id.image9)};
        LinearLayout[] layouts={
                view.findViewById(R.id.layout_image_1),
                view.findViewById(R.id.layout_image_2),
                view.findViewById(R.id.layout_image_3)};

        for(int i=image_counter;i<9;i++)
        {
            ViewGroup.LayoutParams layoutParams=images[i].getLayoutParams();
            layoutParams.height=0;
            images[i].setLayoutParams(layoutParams);
            images[i].measure(0,0);
        }
        for(int i=0;i<9;i++)
        {
            ViewGroup.LayoutParams layoutParams=images[i].getLayoutParams();
            System.out.println("view"+i+":"+layoutParams.height);
        }
        layouts[0].measure(0,0);
        layouts[1].measure(0,0);
        layouts[2].measure(0,0);
       System.out.println("layout1:"+layouts[0].getHeight());
        System.out.println("layout2:"+layouts[1].getHeight());
        System.out.println("layout3:"+layouts[2].getHeight());
        return view;
    }
}
