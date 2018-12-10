package com.example.zhouwei.comments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.network.zhouwei.http_network.Server;

import java.util.ArrayList;
import java.util.List;

public class comment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        //返回
        ImageView button_back=(ImageView)findViewById(R.id.imageView_back_login);
        ImageView button_write=(ImageView)findViewById(R.id.imageView_write);

        button_back.setOnClickListener(v-> {
            Intent intent=new Intent();
            intent.setClass(this,login.class);
            startActivity(intent);
        });

        button_write.setOnClickListener(v->{
            Intent intent=new Intent();
            intent.setClass(this,write.class);
            startActivity(intent);        });

        TextView display_username=(TextView)findViewById(R.id.comment_display_username);
        ImageView display_headpartrait=(ImageView)findViewById(R.id.comment_display_headpartrait);
        //填充listview数据
        ListView list=(ListView)findViewById(R.id.data_list);

        List<CommitItem> arraylist =new ArrayList<CommitItem>();
        for(int i=0;i<9;i++)
        {
            CommitItem t=new CommitItem();
            t.setUsername(""+i);
            t.setText(""+i);
            t.setImageUri(new String[i]);
            arraylist.add(t);
        }

        CommentAdapter commentAdapter=new CommentAdapter(this,R.layout.comment_item,arraylist);

        list.setAdapter(commentAdapter);

        //计算高度
        int height=0;
        int height_layout_title=((LinearLayout)findViewById(R.id.layout_title)).getLayoutParams().height;
        int height_layout_datalist_marginTop=((LinearLayout.LayoutParams)(((LinearLayout)findViewById(R.id.data_layout)).getLayoutParams())).topMargin;
        for(int i=0;i<commentAdapter.getCount();i++)
        {
            View v=commentAdapter.getView(i,null,list);
            v.measure(0,0);
            int tHeight=v.getMeasuredHeight();
            height+=tHeight;
        }
        ViewGroup.LayoutParams lp=list.getLayoutParams();
        lp.height=height+list.getDividerHeight()*commentAdapter.getCount()+height_layout_title-height_layout_datalist_marginTop;
        list.setLayoutParams(lp);
        //滚动到顶端
        ScrollView scrollView=(ScrollView)findViewById(R.id.scrollView_comment_total);
        scrollView.smoothScrollTo(0,0);

        Bundle data=this.getIntent().getExtras();

        Server server=new Server();
        server.setServer("119.29.60.170");
        String uid=data.getString("uid");
        String username=server.getUserNameByUid(uid);

        display_username.setText(username);;
        //设置头像
        Bitmap headpartrait=server.imageFileDownload(username);
        display_headpartrait.setImageBitmap(headpartrait);
    }
}
