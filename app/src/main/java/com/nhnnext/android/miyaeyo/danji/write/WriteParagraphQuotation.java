package com.nhnnext.android.miyaeyo.danji.write;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.nhnnext.android.miyaeyo.danji.MyApplication;
import com.nhnnext.android.miyaeyo.danji.R;
import com.nhnnext.android.miyaeyo.danji.data.Danji;
import com.nhnnext.android.miyaeyo.danji.show.DanjiMainActivity;
import com.parse.ParseFile;
import com.parse.ParseUser;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**To do
 * 1. Action bar의 취소 버튼 누르면 작성을 종료 하겠냐는 팝업 창 띄우고 확인 누르면 MainActivity로 돌아가고 취소버튼 누르면 작성창 그대로
 * 2. Action bar의 완료버튼 누르면 DB에 저장하고 MainActivity로 돌아옴
 * 3. 카메라 버튼 누르면 카메라를 연결하고 사진을 찍은 후에는 PhotoEditor로 연결
 * 4. 갤러리 버튼 누르면 갤러리를 연결하고 사진을 선택하면 PhotoEditor로 연결
 */
public class WriteParagraphQuotation extends Activity{
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_IMAGE_ALBUM = 2;
    private static final int REQUEST_IMAGE_CROP = 3;
    private ImageView mImageView;
    private String mCurrentPhotoPath;
    private Uri contentUri;
    private ParseUser currentUser;
    private String category;
    private String userName;
    private ParseFile contentsImage;
    private String bodyText;
    private EditText body;
    private String createrText;
    private EditText creater;
    private String titleText;
    private EditText title;
    private Danji danji;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.write_paragraph);
        //1. Action bar의 취소버튼 누르면 popup window로 연결(popup window의 확인버튼 -> MainActivity로, 취소버튼 -> 작성중이던 창으로)
        //2. Action bar의 완료버튼 누르면 completeWriting() 연결
        //3. 카메라 버튼 누르면 takePhoto() 연결
        //4. 갤러리 버튼 누르면 selectPhoto() 연결
        mCurrentPhotoPath = null;
        mImageView = (ImageView) findViewById(R.id.paragraph_imageview);
        danji = new Danji();
        currentUser = ParseUser.getCurrentUser();
        userName = currentUser.getUsername();
        danji.setUserName(userName);
        category = getIntent().getStringExtra("category");
        danji.setCategory(category);
        body = (EditText)findViewById(R.id.write_paragraph);
        creater = (EditText)findViewById(R.id.write_creator);
        title = (EditText)findViewById(R.id.write_title);
        danji.setLikeCount(0);

    }

    @Override
    protected void onStart() {
        super.onStart();
        // 카메라나 갤러리가 불리고 난 후 돌아왔을 때 작성하고 있던 글은 그대로 남아 있어야 한다.
        // PhotoEditor로 편집한 사진을 받아서 No picture칸에 보여줌
    }

    @Override
    protected void onResume() {
        super.onResume();
        // popup window가 떴다가 다시 돌아왔을 때 작성내용 남아 있어야 한다.
        bodyText = body.getText().toString();
        createrText = creater.getText().toString();
        titleText = title.getText().toString();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //임시저장
    }

    @Override
    protected void onStop() {
        super.onStop();
        //임시저장
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    //Action bar구현부
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    public void buttonClick(View view){

        Intent intent;
        switch (view.getId()){
            case R.id.cancel_button:
                onBackPressed();
                break;
            case R.id.complete_button:
                 danji.setContentsBody(bodyText);
                 danji.setContentsTitle(titleText);
                 danji.setCreator(createrText);
                 Log.d(MyApplication.TAG, "body" + bodyText + "\ntitle" + titleText + "\ncreater" + createrText);
                 danji.saveInBackground();
                 Toast.makeText(getApplicationContext(), R.string.save, Toast.LENGTH_SHORT).show();
                 intent = new Intent(this, DanjiMainActivity.class);
                 startActivity(intent);
                 finish();
                 break;
            case R.id.camera:
                dispatchTakePictureIntent();
                break;
            case R.id.gallery:
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, REQUEST_IMAGE_ALBUM);
                break;
        }
    }
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
                mCurrentPhotoPath = photoFile.getAbsolutePath();

            } catch (IOException e) {
                e.printStackTrace();
            }
            if (photoFile != null) {
                contentUri = Uri.fromFile(photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(photoFile));
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.KOREA).format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        return image;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            switch (requestCode) {
                case REQUEST_IMAGE_ALBUM:
                    contentUri = data.getData();
                case REQUEST_IMAGE_CAPTURE:
                    //rotatePhoto();
                    cropImage(contentUri);
                    break;
                case REQUEST_IMAGE_CROP:
                    contentUri = data.getData();
                    //Bundle extras = data.getExtras();
                    if (contentUri != null) {
                        //Bitmap bitmap = (Bitmap) extras.get("data");
                        try{
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), contentUri);
                            ByteArrayOutputStream stream = new ByteArrayOutputStream();
                            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                            byte[] image = stream.toByteArray();
                            contentsImage = new ParseFile("contentsImage", image);
                            contentsImage.saveInBackground();
                            danji.setContentsImage(contentsImage);
                            mImageView.setImageBitmap(bitmap);
                        } catch (FileNotFoundException e){
                            Log.e(MyApplication.TAG, "FileNotFoundException" + e);
                        } catch (IOException e){
                            Log.e(MyApplication.TAG, "IOException" + e);
                        }

                        if (mCurrentPhotoPath != null) {
                            File f = new File(mCurrentPhotoPath);
                            if (f.exists()) {
                                f.delete();
                            }
                            mCurrentPhotoPath = null;
                        }
                    }
                    break;
            }
        }else if (resultCode == RESULT_CANCELED){
            Toast.makeText(this, R.string.result_canceled,Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, R.string.result_failed,Toast.LENGTH_SHORT).show();
        }
    }
    private void cropImage(Uri contentUri) {
        Intent cropIntent = new Intent("com.android.camera.action.CROP");
        //indicate image type and Uri of image
        cropIntent.setDataAndType(contentUri, "image/*");
        //set crop properties
        cropIntent.putExtra("crop", "true");
        //indicate aspect of desired crop
        cropIntent.putExtra("aspectX", 4);
        cropIntent.putExtra("aspectY", 3);
        //indicate output X and Y
//        cropIntent.putExtra("outputX", 256*4);
//        cropIntent.putExtra("outputY", 256*3);
//        //retrieve data on return
//        cropIntent.putExtra("return-data", true);
        try{
            File cropImage = createImageFile();
            Uri cropImageUri = Uri.fromFile(cropImage);
            cropIntent.putExtra(MediaStore.EXTRA_OUTPUT, cropImageUri);
            cropIntent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        } catch (IOException e){
            Log.e(MyApplication.TAG, "IOException: " + e);
        }
        startActivityForResult(cropIntent, REQUEST_IMAGE_CROP);
    }

}
