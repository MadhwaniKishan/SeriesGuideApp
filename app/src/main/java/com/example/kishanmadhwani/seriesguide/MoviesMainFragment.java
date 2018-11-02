package com.example.kishanmadhwani.seriesguide;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
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

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesMainFragment extends Fragment {


    private ActionBarDrawerToggle drawerToggle;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private DrawerLayout mDrawer;
    Toolbar toolbar;
    FragmentManager fmm;
    CollapsingToolbarLayout tool;

    public MoviesMainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_movies_main, container, false);
    }
    @Override
    public void onCreateOptionsMenu (Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.moviemainmenu, menu);
        menu.findItem(R.id.refresh).setVisible(false);
        menu.findItem(R.id.filter).setVisible(false);
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
            case R.id.language:
                Toast.makeText(getContext(),"language",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.refresh:
                Toast.makeText(getContext(),"refresh",Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewPager=view.findViewById(R.id.viewpagermovie);
        tabLayout=view.findViewById(R.id.tabsmovies);
        toolbar=view.findViewById(R.id.toolbar);
        tool=view.findViewById(R.id.collapsing_toolbar);
        ((NavigationDrawer)getActivity()).setSupportActionBar(toolbar);
         tool.setTitleEnabled(false);
        toolbar.setTitle("Movies");
        mDrawer=getActivity().findViewById(R.id.drawer_layout);
        drawerToggle = new ActionBarDrawerToggle( getActivity(),mDrawer ,toolbar,0, 0);
        mDrawer.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
    }
    private void setupViewPager(ViewPager viewPager) {
        MoviesMainFragment.ViewPagerAdapter adapter = new MoviesMainFragment.ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new MoviesDiscoverFragment(), "DISCOVER");
        adapter.addFragment(new MoviesWatchlistFragment(), "WATCHLIST");
        adapter.addFragment(new MoviesCollectionFragment(), "COLLECTION");
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
