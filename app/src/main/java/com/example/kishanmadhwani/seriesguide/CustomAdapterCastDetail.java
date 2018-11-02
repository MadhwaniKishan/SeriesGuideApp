package com.example.kishanmadhwani.seriesguide;

import android.content.ClipData;
import android.content.Context;
import android.content.res.Resources;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CustomAdapterCastDetail extends BaseAdapter {
        public Resources res;
        int size;
        private ArrayList<Cast> castlist;
        private static LayoutInflater inflater=null;
        Cast temp=null;
        Context mContext;
        String posterUrl,baseUrl="https://image.tmdb.org/t/p/w500";



        public CustomAdapterCastDetail(Context context, ArrayList<Cast> data, Resources res,int size) {
            this.castlist = data;
            this.mContext=context;
            this.res=res;
            this.size=size;
            inflater=(LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

    public int getCount() {

        if(castlist.size()<=0)
            return 1;
        return size;
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    /********* Create a holder Class to contain inflated xml file elements *********/
    public static class ViewHolder{

        public TextView actorname;
        public TextView character;
        public ImageView actorimage;

    }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Get the data item for this position
            View vi = convertView;
            ViewHolder holder;

            if(convertView==null) {

                vi = inflater.inflate(R.layout.crew_item, null);

                /****** View Holder Object to contain tabitem.xml file elements ******/

                holder = new ViewHolder();
                holder.actorname = (TextView) vi.findViewById(R.id.actorname);
                holder.character = (TextView) vi.findViewById(R.id.character);
                holder.actorimage = (ImageView) vi.findViewById(R.id.actorphoto);

                /************  Set holder with LayoutInflater ************/
                vi.setTag(holder);
            }

          else
              holder=(ViewHolder)vi.getTag();


             temp=null;
             temp=castlist.get(position);
             holder.actorname.setText(temp.getName());
             Log.d("actorname",temp.getName());
             holder.character.setText(temp.getCharacter());
             posterUrl=baseUrl+temp.getProfile();
            Glide.with(mContext)
                    .load(posterUrl)
                    //.apply(new RequestOptions().override(150, 200))
                    .into(holder.actorimage);
            // Return the completed view to render on screen
            return vi;
        }
    }
