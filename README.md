# Danji
##Daji project proposal: DANJI_proposal.pdf

*1. Activity List*
> - Login.java : signup and login 
> > * BeforeLoginFragment.java : signup and login form 
> > * WelcomeFragment.java : after login (start danji, logout)

> - DanjiMainActivity.java : with navigation drawer
> > * ContentsViewFragment.java : home, show all contents or selected category's contents
> > * ContnetsSearchFragment.java : search, show contents ranking by like count descending order and change toolbar to SearchView
> > * WriteCategoryFragment.java : write, write category button(movie, drama, cartoon -> WriteDalogQuotation.java, book, poem, music -> WriteParagraphQuotation.java)
> > * MyPageFragment.java : mypage, show the contents written by me and inbox contents

> - SearchWebview.jaca : naver search, search query: contents' title

> - WriteDialogQuotation.java: write dialog quotation. write form added by push + button

> - WriteParagraphQuotation.java: wite paragraph quotation.

*2. Parse Object List*
> - Danji: key "UserName", "Category", "ContentsImage", "ContentsBody", "Creator", "Title", "LikeCount"
> - Inbox: key "UserName", "InboxId"










