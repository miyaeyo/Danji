package com.nhnnext.android.miyaeyo.danji.show;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.internal.app.ToolbarActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import com.nhnnext.android.miyaeyo.danji.R;
import com.nhnnext.android.miyaeyo.danji.adapter.DrawerListAdapter;
import com.nhnnext.android.miyaeyo.danji.adapter.ViewPagerAdapter;
import com.nhnnext.android.miyaeyo.danji.data.DrawerListData;
import com.nhnnext.android.miyaeyo.danji.write.WriteDialogQuotation;
import com.nhnnext.android.miyaeyo.danji.write.WriteParagraphQuotation;

import java.util.ArrayList;

/** 7/24 lifecycle dummy code
 *  To do
 *  1. Action bar의 navigation drawer 구현
 *  2. Home, Search, Write, MyPage button을 눌렀을 때 각 fragment로 연결
 *  3. Search button누르면 상단의 action bar가 검색어를 입력받고 DB에서 검색어가 들어간 contents를 가지고와서 ContentsViewFragment에 뿌려줌
 *
 *  8/1 fragment / view
 *  기획 수정
 *  1. 상단과 하단의 toolbar를 분리하지 않고, Action bar역할을 하는 toolbar와 바로 밑에 tabView를 위치시킴.
 *  2. search tab을 따로 만들지 않고 Action bar의 item으로 위치시켜 searchView 사용.
 *  3. navigation drawer 왼쪽에서 나옴.
 *  To do
 *  1. toggle을 구현했는데 toggle icon이 보이지 않음.
 *  2. search icon 클릭시 BeforeSearchFragment로 전환되어야 하는데 지금 fragment가 setupViewPager를 통해서 전환되어서 search icon이랑은 연결안됨.
 *
 *  8/5 fragment / view
 *  기획 수정
 *  1. 처음 기획과 같이 하단 메뉴바 구성.
 *  2. navigation drawer 오른쪽
 */


public class DanjiMainActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private TabLayout mTabLayout;
    private Toolbar mToolbar;
    private ViewPager mViewPager;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private ArrayList<DrawerListData> drawerListItems = new ArrayList<DrawerListData>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 1. Action bar에서 navigation drawer toggle버튼을 클릭하면 navigation list가 나옴
        //    -> navigation list에 eventlistener 설정
        //    -> selectCategory() -> 8/1 onNavigationItemSelected() override method로 구현 했음
        // 2. 하단에 Action bar로 home, search, write, mypage 버튼 구성 및 eventㅣistener로 각 fragment로 연결
        //    -> selectMenu() -> 8/1 setupViewPager()로 구현했음
        //    -> home : ContentsViewFragment
        //    -> search: before search; ContentsSearchFragment, after search; ContentsViewFragment, action bar 검색어 입력 모드로 연결
        //    -> write: WriteCategoryFragment
        //    -> mypage: MyPageFragment
        // 3. action bar의 검색 버튼 과 검색 수행 method 연결
        //    -> searchContents() ->8/1 OnCreatOptionsMenu()안에서 구현했음

        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);


        mViewPager = (ViewPager)findViewById(R.id.view_pager);
        setupViewPager(mViewPager);

        mTabLayout = (TabLayout)findViewById(R.id.tab);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.getTabAt(0).setIcon(R.drawable.ic_home_white_24dp);
        mTabLayout.getTabAt(1).setIcon(R.drawable.ic_search_white_24dp);
        mTabLayout.getTabAt(2).setIcon(R.drawable.ic_create_white_24dp);
        mTabLayout.getTabAt(3).setIcon(R.drawable.ic_face_white_24dp);

        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mDrawerList = (ListView)findViewById(R.id.right_drawer);
        setDrawerList(drawerListItems);
        mDrawerList.setAdapter(new DrawerListAdapter(this, drawerListItems));
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setHomeButtonEnabled(true);

//        mDrawerToggle = new ActionBarDrawerToggle(
//                this,
//                mDrawerLayout,
//                mToolbar,
//                R.string.open_drawer,
//                R.string.close_drawer
//        ){
//            public void onDrawerOpened(View drawerView){
//                getSupportActionBar().setTitle(mDrawerTitle);
//                super.onDrawerOpened(drawerView);
//            }
//            public void onDrawerClosed(View view){
//                getSupportActionBar().setTitle(mTitle);
//                super.onDrawerClosed(view);
//            }
//
//        };
//        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }


            @Override
            public void onPageSelected(int position) {
                if (position == 1) {
                    SearchManager mSearchManager = (SearchManager)getSystemService(Context.SEARCH_SERVICE);
                    SearchView mSearchView = new SearchView(getApplicationContext());
                    try{
                        getSupportActionBar().setCustomView(mSearchView);
                    }catch (NullPointerException e){
                        Log.e("ERROR", "ERROR: " + e);
                    }

                    getSupportActionBar().setDisplayOptions(ToolbarActionBar.DISPLAY_SHOW_CUSTOM);
                    mSearchView.setSearchableInfo(mSearchManager.getSearchableInfo(getComponentName()));
                    mSearchView.setIconifiedByDefault(false);
                    //mSearchView.setFocusable(true);
                    //mSearchView.requestFocusFromTouch();
                } else {

                    getSupportActionBar().collapseActionView();
                    getSupportActionBar().setDisplayOptions(ToolbarActionBar.DISPLAY_SHOW_TITLE);

                    //getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
                    //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                    //getSupportActionBar().setHomeButtonEnabled(true);

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        //DB연결
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //DB연결 해제

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    // upper tool bar의 메뉴 구현부
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.drawer_button){
            mDrawerLayout.openDrawer(Gravity.RIGHT);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


//    @Override
//    public void onConfigurationChanged(Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
//        mDrawerToggle.onConfigurationChanged(newConfig);
//    }
//
//    @Override
//    protected void onPostCreate(Bundle savedInstanceState) {
//        super.onPostCreate(savedInstanceState);
//        mDrawerToggle.syncState();
//    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }
    private void selectItem(int position){
        // categrory에 해당하는 contents만 home화면에 뿌려줌
        String selectCategory = drawerListItems.get(position).getListTitle();
        mTabLayout.getTabAt(0).select();
        mDrawerLayout.closeDrawer(mDrawerList);
    }


    private void setupViewPager(ViewPager viewPager){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new ContentsViewFragment(), "home");
        adapter.addFragment(new ContentsSearchFragment(), "before search");
        adapter.addFragment(new WriteCategoryFragment(), "write");
        adapter.addFragment(new MyPageFragment(), "mypage");
        viewPager.setAdapter(adapter);
    }

    private void setDrawerList(ArrayList<DrawerListData> drawerListDataArray){
        String[] itemTitle = getResources().getStringArray(R.array.contents_category_title);
        TypedArray itemIcon = getResources().obtainTypedArray(R.array.contents_category_icon);
        for(int i = 0; i< itemTitle.length; i++){
            drawerListDataArray.add(new DrawerListData(itemTitle[i],itemIcon.getResourceId(i,-1)));
        }
        itemIcon.recycle();
    }


}
