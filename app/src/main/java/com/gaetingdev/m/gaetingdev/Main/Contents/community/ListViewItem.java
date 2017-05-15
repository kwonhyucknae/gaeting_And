package com.gaetingdev.m.gaetingdev.Main.Contents.community;

import android.graphics.drawable.Drawable;

/**
 * Created by 권혁내 on 2016-11-25.
 */

public class ListViewItem {
    private Drawable iconDrawable;
    private String nicknameStr;
    private String textStr;
    private int likenum;
    private int commentnum;

    public void setIcon(Drawable icon)
    {
        iconDrawable=icon;
    }
    public void setNicknameStr(String nickname)
    {
        nicknameStr=nickname;
    }
    public void setTextStr(String text)
    {
        textStr=text;
    }
    public void setLikenum(int ln)
    {
        likenum=ln;
    }
    public void setCommentnum(int com)
    {
        commentnum=com;
    }

    public Drawable getIcon()
    {
        return this.iconDrawable;
    }
    public String getNickname()
    {
        return this.nicknameStr;
    }
    public String getText()
    {
        return this.textStr;
    }
    public int getLikenum()
    {
        return this.likenum;
    }
    public int getCommentnum()
    {
        return this.commentnum;
    }
}
