package com.example.kishanmadhwani.seriesguide.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.kishanmadhwani.seriesguide.response.CastDetailResponse;
import com.example.kishanmadhwani.seriesguide.response.CrewDetailsResponse;
import com.example.kishanmadhwani.seriesguide.R;
import com.example.kishanmadhwani.seriesguide.adapter.CustomAdapterCastDetail;
import com.example.kishanmadhwani.seriesguide.adapter.CustomAdapterCrewDetail;

import java.util.ArrayList;

public class ShowAllCreditsActivity extends AppCompatActivity {
  ArrayList credits=new ArrayList();
  ListView showallcreditlist;
    ArrayList<CastDetailResponse> castlist;
    ArrayList<CrewDetailsResponse> crewlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_credits);
        showallcreditlist=findViewById(R.id.allcreditlist);
        Resources res =getResources();
        Intent intent = getIntent();
        String credit=intent.getExtras().get("credit").toString();
        if(credit.equals("castDetailResponse")){
           castlist = (ArrayList<CastDetailResponse>) intent.getSerializableExtra("castlist");
            CustomAdapterCastDetail adaptercast= new CustomAdapterCastDetail(ShowAllCreditsActivity.this,castlist,res,castlist.size());
            showallcreditlist.setAdapter(adaptercast);
            showallcreditlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position,
                                        long id) {
                    Intent intent = new Intent(ShowAllCreditsActivity.this, PersonDetailActivity.class);
                    intent.putExtra("id", castlist.get(position).getId());
                    startActivity(intent);
                }
            });

        }
        else {
            crewlist = (ArrayList<CrewDetailsResponse>) intent.getSerializableExtra("crewlist");
            CustomAdapterCrewDetail adaptercrew= new CustomAdapterCrewDetail(ShowAllCreditsActivity.this,crewlist,res,crewlist.size());
            showallcreditlist.setAdapter(adaptercrew);
            showallcreditlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position,
                                        long id) {
                    Intent intent = new Intent(ShowAllCreditsActivity.this, PersonDetailActivity.class);
                    intent.putExtra("id", crewlist.get(position).getId());
                    startActivity(intent);
                }
            });
        }

    }
}
