package com.example.ravil.miwok;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class WORD1Adapter extends ArrayAdapter<WORD1>
{
    private int resocolor;


    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        View item=convertView;
        if(item==null)
        {
            item=LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }
        WORD1 cword=getItem(position);
        TextView ntext=(TextView)item.findViewById(R.id.default_text_view);
        ntext.setText(cword.getdefault());

        TextView mtext=(TextView)item.findViewById(R.id.miwok_text_view);
        mtext.setText(cword.getmiwok());

        ImageView mimag=(ImageView)item.findViewById(R.id.image_view);
        if(cword.hasimage()) {
            mimag.setImageResource(cword.getimage());
            mimag.setVisibility(View.VISIBLE);
        }
        else
        {
            mimag.setVisibility(View.GONE);
        }
        View textcontainer=item.findViewById(R.id.text_container);
        int color= ContextCompat.getColor(getContext(),resocolor);
        textcontainer.setBackgroundColor(color);
        return item;

    }
    public WORD1Adapter(Context context , ArrayList list,int colour)
    {

        super(context,0,list);
        resocolor=colour;
    }

}
