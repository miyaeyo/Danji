package com.nhnnext.android.miyaeyo.danji.show;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.nhnnext.android.miyaeyo.danji.R;

/** To do
 *  1. Action bar의 navigation drawer 구현
 *  2. Home, Search, Write, MyPage button을 눌렀을 때 각 fragment로 연결
 *  3. Search button누르면 상단의 action bar가 검색어를 입력받고 DB에서 검색어가 들어간 contents를 가지고와서 ContentsViewFragment에 뿌려줌
 */

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 1. Action bar에서 navigation drawer toggle버튼을 클릭하면 navigation list가 나옴
        //    -> navigation list에 eventlistener 설정
        //    -> selectCategory()
        // 2. 하단에 Action bar로 home, search, write, mypage 버튼 구성 및 eventㅣistener로 각 fragment로 연결
        //    -> selectMenu()
        //    -> home : ContentsViewFragment
        //    -> search: before search; BeforeSearchFragment, after search; ContentsViewFragment, action bar 검색어 입력 모드로 연결
        //    -> write: WriteCategoryFragment
        //    -> mypage: MyPageFragment
        // 3. action bar의 검색 버튼 과 검색 수행 method 연결
        //    -> searchContents()

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

    // action bar의 메뉴 구현부
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    /* Swaps fragments in the main content view */
    // selectMenu(): 하단의 Actionbar 의 메뉴에 따른 fragment연결 수행
    // selectCategory(): navigation drawer의 category에 따라 DB에서 해당 category data불러와서 fragment1에 연결


    // searchContents(): 검색버튼이 눌렸을 때 DB에서 해당 검색어를 포함하는 contents를 가져옴
}
