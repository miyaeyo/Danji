package com.nhnnext.android.miyaeyo.danji.show;



import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import com.nhnnext.android.miyaeyo.danji.R;
import com.nhnnext.android.miyaeyo.danji.adapter.ViewPagerAdapter;

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

        final TabLayout mTabLayout = (TabLayout)findViewById(R.id.tab);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.getTabAt(0).setIcon(R.drawable.ic_home_white_24dp);
        mTabLayout.getTabAt(1).setIcon(R.drawable.ic_create_white_24dp);
        mTabLayout.getTabAt(2).setIcon(R.drawable.ic_face_white_24dp);


        final DrawerLayout mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.drawable.ic_menu_white_24dp, R.string.open_drawer, R.string.close_drawer){

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        final NavigationView mNavView = (NavigationView)findViewById(R.id.nav_view);
        mNavView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.drawer_movie:
                        mTabLayout.getTabAt(0);
                        break;
                    case R.id.drawer_drama:
                        mTabLayout.getTabAt(0);
                        break;
                    case R.id.drawer_book:
                        mTabLayout.getTabAt(0);
                        break;
                    case R.id.drawer_poem:
                        mTabLayout.getTabAt(0);
                        break;
                    case R.id.drawer_music:
                        mTabLayout.getTabAt(0);
                        break;
                    case R.id.drawer_cartoon:
                        mTabLayout.getTabAt(0);
                        break;
                    default:
                        mTabLayout.getTabAt(0);
                        break;
                }
                mDrawerLayout.closeDrawer(mNavView);
                return true;
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
        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void setupViewPager(ViewPager viewPager){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new ContentsViewFragment(),"home");
        adapter.addFragment(new WriteCategoryFragment(), "write");
        adapter.addFragment(new MyPageFragment(), "mypage");
        viewPager.setAdapter(adapter);

    }

    /* Swaps fragments in the main content view */
    // selectMenu(): 하단의 Actionbar 의 메뉴에 따른 fragment연결 수행
    // selectCategory(): navigation drawer의 category에 따라 DB에서 해당 category data불러와서 fragment1에 연결
    // searchContents(): 검색버튼이 눌렸을 때 DB에서 해당 검색어를 포함하는 contents를 가져옴
}
