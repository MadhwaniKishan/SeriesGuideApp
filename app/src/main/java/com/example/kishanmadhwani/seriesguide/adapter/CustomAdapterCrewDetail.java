package com.example.kishanmadhwani.seriesguide.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.kishanmadhwani.seriesguide.response.CrewDetailsResponse;
import com.example.kishanmadhwani.seriesguide.R;

import java.util.ArrayList;

public class CustomAdapterCrewDetail extends BaseAdapter {
    public Resources res;
    private ArrayList<CrewDetailsResponse> crewlist;
    private static LayoutInflater inflater=null;
    int size=0;
    CrewDetailsResponse temp=null;
    Context mContext;
    String posterUrl,baseUrl="https://image.tmdb.org/t/p/w500";
    public CustomAdapterCrewDetail(Context context, ArrayList<CrewDetailsResponse> data, Resources res, int size) {
        this.crewlist = data;
        this.mContext=context;
        this.res=res;
        this.size=size;
        inflater=(LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


    }

    public int getCount() {

//        if(crewlist.size()<=0)
//            return 1;
        return size;
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }
    public static class ViewHolder{

        public TextView actorname;
        public TextView character;
        public ImageView actorimage;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        View vi = convertView;
        CustomAdapterCrewDetail.ViewHolder holder;

        if(convertView==null) {

            vi = inflater.inflate(R.layout.crew_item, null);

            /****** View Holder Object to contain tabitem.xml file elements ******/

            holder = new CustomAdapterCrewDetail.ViewHolder();
            holder.actorname = (TextView) vi.findViewById(R.id.actorname);
            holder.character = (TextView) vi.findViewById(R.id.character);
            holder.actorimage = (ImageView) vi.findViewById(R.id.actorphoto);

            /************  Set holder with LayoutInflater ************/
            vi.setTag(holder);
        }

        else
            holder=(CustomAdapterCrewDetail.ViewHolder)vi.getTag();


        temp=null;
        temp=crewlist.get(position);
        holder.actorname.setText(temp.getName());
        Log.d("actorname",temp.getName());
        holder.character.setText(temp.getJob());
        posterUrl=baseUrl+temp.getProfile();
        Glide.with(mContext)
                .load(posterUrl)
                //.apply(new RequestOptions().override(150, 200))
                .into(holder.actorimage);
        // Return the completed view to render on screen
        return vi;
    }


}
