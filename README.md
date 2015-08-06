# Danji
##Daji project proposal: DANJI_proposal.pdf


1. 7/24 Activity dummy code 작성 : Fragment lifecycle에는 dummy code를 넣지는 않았음. Activity에만 dummy code작성. 

    - Activity1: MainActivity.java

        * Fragment1: ContentsViewFragment.java

        * Fragment2: BeforeSearchFragment.java

        * Fragment3: WriteCategoryFragment.java

        * Fragment4: MyPageFragment.java

    - Activity2: WriteDialogQuotation.java

    - Activity3: WriteParagraphQuotation.java

    - Activity4: PhotoEditor.java

2. 8/3 View and Fragment 연결: MainActivity의 toolbar, tab view, navigation drawer 구현 및 Fragment연결.

3. 8/7

    - MainActivity.java, activity_main.xml : ToolBar, ViewPager, TabLayout을 세로로 배치, 

        * ContentsViewFragment.java, contens_view_f.xml: 각 category별 String data만들어서 띄워봄. 하트 버튼 누르면 숫자 증가, contents의 제목을 누르면 네이버 검색페이지로 연결. 

        * WriteCategoryFragment.java, write_category_f.xml: 각 category별 button클릭시 글작성 Activity로 연결 

    - WriteDialogQuotation.java, write_dialog.xml: Movie, Drama 글 작성

    - WriteParagraphQuotation.java, write_paragraph.xml; Book, Poem, Music, Cartoon 글 작성

