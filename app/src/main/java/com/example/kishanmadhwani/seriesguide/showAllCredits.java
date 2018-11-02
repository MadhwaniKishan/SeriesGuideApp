package com.example.kishanmadhwani.seriesguide;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class showAllCredits extends AppCompatActivity {
  ArrayList credits=new ArrayList();
  ListView showallcreditlist;
    ArrayList<Cast> castlist;
    ArrayList<Crew> crewlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_credits);
        showallcreditlist=findViewById(R.id.allcreditlist);
        Resources res =getResources();
        Intent intent = getIntent();
        String credit=intent.getExtras().get("credit").toString();
        if(credit.equals("cast")){
           castlist = (ArrayList<Cast>) intent.getSerializableExtra("castlist");
            CustomAdapterCastDetail adaptercast= new CustomAdapterCastDetail(showAllCredits.this,castlist,res,castlist.size());
            showallcreditlist.setAdapter(adaptercast);
            showallcreditlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position,
                                        long id) {
                    Intent intent = new Intent(showAllCredits.this, PersonDetail.class);
                    intent.putExtra("id", castlist.get(position).getId());
                    startActivity(intent);
                }
            });

        }
        else {
            crewlist = (ArrayList<Crew>) intent.getSerializableExtra("crewlist");
            CustomAdapterCrewDetail adaptercrew= new CustomAdapterCrewDetail(showAllCredits.this,crewlist,res,crewlist.size());
            showallcreditlist.setAdapter(adaptercrew);
            showallcreditlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position,
                                        long id) {
                    Intent intent = new Intent(showAllCredits.this, PersonDetail.class);
                    intent.putExtra("id", crewlist.get(position).getId());
                    startActivity(intent);
                }
            });
        }

    }
}
