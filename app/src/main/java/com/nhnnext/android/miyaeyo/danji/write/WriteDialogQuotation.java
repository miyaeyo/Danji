package com.nhnnext.android.miyaeyo.danji.write;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.nhnnext.android.miyaeyo.danji.R;
import com.nhnnext.android.miyaeyo.danji.edit.PhotoEditor;
import com.nhnnext.android.miyaeyo.danji.show.MainActivity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/** To do
 * 1. Action bar의 취소 버튼 누르면 작성을 종료 하겠냐는 팝업 창 띄우고 확인 누르면 MainActivity로 돌아가고 취소버튼 누르면 작성창 그대
 * 2. Action bar의 완로버튼 누르면 DB에 저장하고 MainActivity로 돌아옴
 * 3. 등장인물 명대사 기입 칸 + 버튼 누르면 생성됨
 * 4. 카메라 버튼 누르면 카메라를 연결하고 사진을 찍은 후에는 PhotoEditor로 연결
 * 5. 갤러리 버튼 누르면 갤러리를 연결하고 사진을 선택하면 PhotoEditor로 연결
 */
public class WriteDialogQuotation extends Activity{
    private String mCurrentPhotoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.write_dialog);
        //1. Action bar의 취소버튼 누르면 popup window로 연결(popup window의 확인버튼 -> MainActivity로, 취소버튼 -> 작성중이던 창으로)
        //2. Action bar의 완료버튼 누르면 completeWriting() 연결
        //3. +버튼 addColumn()과 연결
        //4. 카메라 버튼 누르면 takePhoto() 연결
        //5. 갤러리 버튼 누르면 selectPhoto() 연결

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
    }

    @Override
    protected void onPause() {
        super.onPause();
        // 임시저장

    }

    @Override
    protected void onStop() {
        super.onStop();
        // storeTemporarily() 임시저장
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    //Action bar의 메뉴 구현부
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    /*method*/
    // completeWriting(): DB저장하고 MainActivity로 연결
    // addColumn(); 작성 칸 한줄 추가
    // storeTemporarily(); 작성내용 임시저장
    // takePhoto(); 카메라 연결하고 사진찍고나면 PhotoEditor호출
    // selectPhoto(); 갤러리 연결하고 사진선택하면 PhotoEditor호출
    private int addCount = 0;
    private static final int CAPTURE_IMAGE_FROM_CAMERA = 100;
    private static final int LOAD_IMAGE_FROM_GALLERY=200;
    public void buttonClick(View view){

        switch (view.getId()){
            case R.id.cancel_button:
                onBackPressed();
                break;
            case R.id.complete_button:
                Toast.makeText(this, R.string.save, Toast.LENGTH_SHORT).show();
                Intent completeIntent = new Intent(this, MainActivity.class);
                startActivity(completeIntent);
                break;
            case R.id.camera:
               // takePictureIntent();
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if(cameraIntent.resolveActivity(getPackageManager()) != null){
                    startActivityForResult(cameraIntent, CAPTURE_IMAGE_FROM_CAMERA);
                }
                break;
            case R.id.gallery:
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                //galleryIntent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
                //galleryIntent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                galleryIntent.setType("image/*");
                startActivityForResult(Intent.createChooser(galleryIntent, "Select File"), LOAD_IMAGE_FROM_GALLERY);
                break;
            case R.id.add_button:
                addCount++;
                LinearLayout writeForm = (LinearLayout)findViewById(R.id.write_form_add);
                LayoutInflater inflater = (LayoutInflater) this.getSystemService(this.LAYOUT_INFLATER_SERVICE);
                View addForm =inflater.inflate(R.layout.write_dialoge_add, writeForm, false);
                writeForm.addView(addForm);

                break;
        }
    }

//    private void takePictureIntent(){
//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//
//        if (intent.resolveActivity(getPackageManager()) != null){
//            File photoFile;
//            try {
//                photoFile = createImageFile();
//            } catch (IOException e) {
//                e.printStackTrace();
//                photoFile = null;
//                mCurrentPhotoPath = null;
//            }
//            if (photoFile != null) {
//                intent.putExtra(MediaStore.EXTRA_OUTPUT,
//                        Uri.fromFile(photoFile));
//                startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
//            }
//
//        }
//    }
//
//    private File createImageFile() throws IOException {
//        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.KOREA).format(new Date());
//        String imageFileName = "JPEG_" + timeStamp + "_";
//        File storageDir = Environment.getExternalStoragePublicDirectory(
//                Environment.DIRECTORY_PICTURES);
//        File image = File.createTempFile(
//                imageFileName,  /* prefix */
//                ".jpg",         /* suffix */
//                storageDir      /* directory */
//        );
//        // Save a file: path for use with ACTION_VIEW intents
//        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
//        return image;
//    }
//
//    private void galleryAddPic() {
//        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
//        File f = new File(mCurrentPhotoPath);
//        Uri contentUri = Uri.fromFile(f);
//        mediaScanIntent.setData(contentUri);
//        this.sendBroadcast(mediaScanIntent);
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            if(requestCode == CAPTURE_IMAGE_FROM_CAMERA){
                Bitmap photo = (Bitmap) data.getExtras().get("data");
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                try {
                    photo.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
                    File destination = new File(Environment.getExternalStorageDirectory(), System.currentTimeMillis() + ".jpg");
                    FileOutputStream fileOutputStream;
                    destination.createNewFile();
                    fileOutputStream = new FileOutputStream(destination);
                    fileOutputStream.write(bytes.toByteArray());
                    fileOutputStream.close();
                } catch(Exception e){
                    e.printStackTrace();
                }
                Intent intent = new Intent(this, PhotoEditor.class);
                intent.putExtra("photo", photo);
                startActivity(intent);
            } else if(requestCode == LOAD_IMAGE_FROM_GALLERY){
                Uri currentImageUri = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContentResolver().query(currentImageUri, filePathColumn, null, null, null);
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String photoPath = cursor.getString(columnIndex);
                cursor.close();

                Bitmap photo;
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(photoPath.trim(),options);
                final int REQUIRED_SIZE = 200;
                int scale = 1;
                while(options.outHeight / scale / 2 >= REQUIRED_SIZE && options.outWidth / scale / 2 >= REQUIRED_SIZE){
                    scale *= 2;
                }

                options.inSampleSize = scale;
                options.inJustDecodeBounds = false;
                photo = BitmapFactory.decodeFile(photoPath.trim(), options);

                Intent intent = new Intent(this, PhotoEditor.class);
                intent.putExtra("photo", photo);
                startActivity(intent);

            }
        }else if (resultCode == RESULT_CANCELED){
            Toast.makeText(this, R.string.result_canceled,Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, R.string.result_failed,Toast.LENGTH_SHORT).show();
        }
    }


}
