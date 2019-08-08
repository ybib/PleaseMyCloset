package org.androidtown.pleasemycloset;

import android.graphics.drawable.Drawable;

public class MyItem {

    public Drawable icon;
    public String name;
    public String contents;

    public MyItem(Drawable _drawable, String _name, String _contents){
        icon = _drawable;
        name = _name;
        contents = _contents;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}