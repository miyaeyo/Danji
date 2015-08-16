package com.nhnnext.android.miyaeyo.danji.edit;

        import android.app.Activity;
        import android.content.Intent;
        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.ImageView;
        import android.widget.Toast;

        import com.nhnnext.android.miyaeyo.danji.R;

        import java.io.IOException;
        import java.io.InputStream;

/**To do
 * 1. 상단 Action bar 이전 버튼 누르면 수정을 종료 하겠냐는 팝업 창 띄우고 확인 누르면 직전 activity로 돌아감
 * 2. 상단 Action bar 완료 버튼 누르면 작성중이던 Activity로 편집된 사진 보내고 돌아감
 * 3. 하단 Action bar 자르기 버튼 누르면 사진 crop
 * 4. 하단 Action bar 회전 버튼 누르면 사진 회전
 */
public class PhotoEditor extends Activity {
    private String mCurrentPhotoPath;
    ImageView mImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.edit_photo);
        //1. Action bar 이전 버튼 popup window로 연결 (popup window의 확인 버튼 -> 이전 작성 창, 취소 버튼 -> 사진 편집창)
        //2. Action bar 완료 버튼 completeEdit() 연결
        //3. Action bar 자르기 버튼 cropPhoto() 연결
        //4. Action bar 회전 버튼 rotatePhoto()
        mImageView = (ImageView)findViewById(R.id.photo_load_location);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle != null){
//            mCurrentPhotoPath = (String) bundle.get("photo_path");
//            setPic();
            Bitmap photo = (Bitmap)bundle.get("photo");
            mImageView.setImageBitmap(photo);

        } else {
            Toast.makeText(this, R.string.photo_load_fail,Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void buttonClick(View view){
        switch (view.getId()){
            case R.id.photo_edit_cancel_button:
                onBackPressed();
                break;
            case R.id.photo_edit_complete_button:
                // 작성중이던 화면으로 돌아가기
                break;
            case R.id.crop_button:
                //crop 함수랑 연결
                break;
        }
    }

//    private void setPic() {
//        // Get the dimensions of the View
//        mImageView.measure(View.MeasureSpec.EXACTLY, View.MeasureSpec.EXACTLY);
//        int targetW = mImageView.getMeasuredWidth();
//        int targetH = mImageView.getMeasuredHeight();
//        Log.d("EEE", "target H"+targetH+" W "+targetW);
//
//        // Get the dimensions of the bitmap
//        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
//        bmOptions.inJustDecodeBounds = true;
//        BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
//
//        int photoW = bmOptions.outWidth;
//        int photoH = bmOptions.outHeight;
//        Log.d("EEE", "photo H"+photoH+" w "+photoW);
//
//
//        // Determine how much to scale down the image
//        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);
//        Log.d("EEE", "scaleFactor"+scaleFactor);
//
//        // Decode the image file into a Bitmap sized to fill the View
//        bmOptions.inJustDecodeBounds = false;
//        bmOptions.inSampleSize = scaleFactor;
//        bmOptions.inPurgeable = true;
//
//        Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
//        mImageView.setImageBitmap(bitmap);
//    }


    /*method*/
    //cropPhoto(); 사진 cropping하는 기능
    //rotatePhoto(); 사진 회전 시키는 기능
    //completeEdit(); 수정완료된 사진을 이전 작성중인 Activity로 보내주고, 그 화면으로 돌아감
}
