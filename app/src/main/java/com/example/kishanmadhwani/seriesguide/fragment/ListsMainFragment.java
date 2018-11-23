package com.example.kishanmadhwani.seriesguide.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.kishanmadhwani.seriesguide.R;
import com.example.kishanmadhwani.seriesguide.activity.NavigationDrawerActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListsMainFragment extends Fragment {

    private ActionBarDrawerToggle drawerToggle;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private DrawerLayout mDrawer;
    Toolbar toolbar;

    public ListsMainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("findme","oncreate view list main fragment");
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_lists_main, container, false);
    }

    @Override
    public void onCreateOptionsMenu (Menu menu, MenuInflater inflater) {
        //super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.optionlist, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.filter:
                Toast.makeText(getContext(),"filter",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.search:
                Toast.makeText(getContext(),"search",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.add:
                Toast.makeText(getContext(),"add",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.refresh:
                Toast.makeText(getContext(),"refresh",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.cal:
                Toast.makeText(getContext(),"calendar setting",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.latest:
                Toast.makeText(getContext(),"latest",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.syncupdate:
                Toast.makeText(getContext(),"sync and update",Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("findme","onviewcreated list main fragment");
        viewPager = view.findViewById(R.id.viewpagerlist);
        tabLayout = view.findViewById(R.id.tabslist);
        toolbar = view.findViewById(R.id.toolbar1);
        ((NavigationDrawerActivity)getActivity()).setSupportActionBar(toolbar);
        mDrawer=getActivity().findViewById(R.id.drawer_layout);
        drawerToggle = new ActionBarDrawerToggle( getActivity(),mDrawer ,toolbar,0, 0);
        mDrawer.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        toolbar.setTitle("Lists");
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ListsMainFragment.ViewPagerAdapter adapter = new ListsMainFragment.ViewPagerAdapter(getChildFragmentManager());
        for(int i=0;i<8;i++){
            adapter.addFragment(new ListsSubFragment(), "tab "+i);
        }
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }


}
