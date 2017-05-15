package com.gaetingdev.m.gaetingdev.Main.Contents.community;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gaetingdev.m.gaetingdev.R;

import java.util.ArrayList;

/**
 * Created by 권혁내 on 2016-11-25.
 */

public class ListViewAdapter extends BaseAdapter{

    private ArrayList<ListViewItem> listviewItemList=new ArrayList<ListViewItem>();

    public ListViewAdapter()
    {

    }
    public int getCount()
    {
        return listviewItemList.size();
    }
    public View getView(int position, View convertView, ViewGroup parent)
    {
        final int pos=position;
        final Context context=parent.getContext();

        if(convertView==null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_item, parent, false);
        }

            ImageView iconImage=(ImageView)convertView.findViewById(R.id.listview_imageview);
            TextView nicknameTV=(TextView)convertView.findViewById(R.id.listview_Nickname);
            TextView textTV=(TextView)convertView.findViewById(R.id.listview_text);
            TextView likeTV=(TextView)convertView.findViewById(R.id.listview_like);
            TextView commentTV=(TextView)convertView.findViewById(R.id.listview_retv);

            ListViewItem listViewItem=listviewItemList.get(position);

            int like=listViewItem.getLikenum();
            int comnum=listViewItem.getCommentnum();
            String likenum=Integer.toString(like);
            String comment=Integer.toString(comnum);

            iconImage.setImageDrawable(listViewItem.getIcon());
            nicknameTV.setText(listViewItem.getNickname());
            textTV.setText(listViewItem.getText());
            likeTV.setText(likenum);
            commentTV.setText(comment);

            return convertView;
        }
    public long getItemId(int position)
    {
        return position;

    }
    public Object getItem(int position)
    {
        return listviewItemList.get(position);
    }
    public void addItem(Drawable icon, String nickname, String text, int like,int comment)
    {
        ListViewItem item=new ListViewItem();

        item.setIcon(icon);
        item.setNicknameStr(nickname);
        item.setTextStr(text);
        item.setLikenum(like);
        item.setCommentnum(comment);

        listviewItemList.add(item);

    }
}
