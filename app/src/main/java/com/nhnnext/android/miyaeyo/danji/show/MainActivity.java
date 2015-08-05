package com.nhnnext.android.miyaeyo.danji.show;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.nhnnext.android.miyaeyo.danji.R;
import com.nhnnext.android.miyaeyo.danji.adapter.DrawerListAdapter;
import com.nhnnext.android.miyaeyo.danji.adapter.ViewPagerAdapter;
import com.nhnnext.android.miyaeyo.danji.data.DrawerListData;

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


public class MainActivity extends ActionBarActivity {

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
        //    -> search: before search; BeforeSearchFragment, after search; ContentsViewFragment, action bar 검색어 입력 모드로 연결
        //    -> write: WriteCategoryFragment
        //    -> mypage: MyPageFragment
        // 3. action bar의 검색 버튼 과 검색 수행 method 연결
        //    -> searchContents() ->8/1 OnCreatOptionsMenu()안에서 구현했음
        Toolbar mToolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        ViewPager mViewPager = (ViewPager)findViewById(R.id.view_pager);
        setupViewPager(mViewPager);

        TabLayout mTabLayout = (TabLayout)findViewById(R.id.tab);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.getTabAt(0).setIcon(R.drawable.ic_home_white_24dp);
        mTabLayout.getTabAt(1).setIcon(R.drawable.ic_search_white_24dp);
        mTabLayout.getTabAt(2).setIcon(R.drawable.ic_create_white_24dp);
        mTabLayout.getTabAt(3).setIcon(R.drawable.ic_face_white_24dp);

        ArrayList<DrawerListData> drawerListDataArray = new ArrayList<DrawerListData>();
        setDrawerList(drawerListDataArray);
        DrawerLayout mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        ListView mDrawerList = (ListView)findViewById(R.id.right_drawer);
        DrawerListAdapter drawerListAdapter = new DrawerListAdapter(this, R.layout.drawer_list, drawerListDataArray);
        mDrawerList.setAdapter(drawerListAdapter);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());


        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setHomeButtonEnabled(true);

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
        MenuItem drawerNavi = menu.findItem(R.id.drawer_navi);
        drawerNavi.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        }
    }

    private void setupViewPager(ViewPager viewPager){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new ContentsViewFragment(), "home");
        adapter.addFragment(new BeforeSearchFragment(), "before search");
        adapter.addFragment(new WriteCategoryFragment(), "write");
        adapter.addFragment(new MyPageFragment(), "mypage");
        viewPager.setAdapter(adapter);
    }

    private void setDrawerList(ArrayList<DrawerListData> drawerListDataArray){
        String[] itemTitle = getResources().getStringArray(R.array.contents_category_title);
        int[] itemImage = getResources().getIntArray(R.array.contents_category_icon);
        for(int i = 0; i< itemTitle.length; i++){
            drawerListDataArray.add(new DrawerListData(itemTitle[i],itemImage[i]));
        }
    }

    /* Swaps fragments in the main content view */
    // selectMenu(): 하단의 Actionbar 의 메뉴에 따른 fragment연결 수행
    // selectCategory(): navigation drawer의 category에 따라 DB에서 해당 category data불러와서 fragment1에 연결
    // searchContents(): 검색버튼이 눌렸을 때 DB에서 해당 검색어를 포함하는 contents를 가져옴
}
