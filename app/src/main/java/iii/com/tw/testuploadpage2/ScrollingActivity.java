package iii.com.tw.testuploadpage2;

import android.Manifest;
import android.app.AlertDialog;
import android.app.LoaderManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import static android.Manifest.permission.*;
import static android.R.attr.bitmap;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;


import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;

import cz.msebera.android.httpclient.Header;

public class ScrollingActivity extends AppCompatActivity {
    private static final int REQUEST_READ_STORAGE = 3;
    //*
    static final int requestCodeImgBtn1 = 1001;
    static final int requestCodeImgBtn2 = 1002;
    static final int requestCodeImgBtn3 = 1003;
    static final int requestCodeImgBtn4 = 1004;
    static final int requestCodeImgBtn5 = 1005;
    //**
    boolean selectedImgForUpload1= false;
    boolean selectedImgForUpload2= false;
    boolean selectedImgForUpload3= false;
    boolean selectedImgForUpload4= false;
    boolean selectedImgForUpload5= false;
    //**
    Bitmap bitmap1;
    Bitmap bitmap2;
    Bitmap bitmap3;
    Bitmap bitmap4;
    Bitmap bitmap5;
    //**
    Bitmap[] bitmapArray = {bitmap1,bitmap2,bitmap3,bitmap4,bitmap5};
    boolean[] selectedImgForUploadArray = {selectedImgForUpload1,selectedImgForUpload2,selectedImgForUpload3,selectedImgForUpload4,selectedImgForUpload5};
    //**

    private View.OnClickListener imgBtnClick = new View.OnClickListener() {
        int requestCode = 0;


        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.imgBtn1:
                    requestCode = requestCodeImgBtn1;
                    Toast.makeText(ScrollingActivity.this,"String.valueOf(R.id.imgBtn1)",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.imgBtn2:
                    requestCode = requestCodeImgBtn2;
                    Toast.makeText(ScrollingActivity.this,"String.valueOf(R.id.imgBtn2)",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.imgBtn3:
                    requestCode = requestCodeImgBtn3;
                    Toast.makeText(ScrollingActivity.this,"String.valueOf(R.id.imgBtn3)",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.imgBtn4:
                    requestCode = requestCodeImgBtn4;
                    Toast.makeText(ScrollingActivity.this,"String.valueOf(R.id.imgBtn4)",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.imgBtn5:
                    requestCode = requestCodeImgBtn5;
                    Toast.makeText(ScrollingActivity.this,"String.valueOf(R.id.imgBtn5)",Toast.LENGTH_SHORT).show();
                    break;
            }
            Toast.makeText(ScrollingActivity.this,String.valueOf(requestCode),Toast.LENGTH_SHORT).show();

            //**
            Intent intent = new Intent();
            //開啟Pictures畫面Type設定為image
            intent.setType("image/*");
            //使用Intent.ACTION_GET_CONTENT這個Action會開啟選取圖檔視窗讓您選取手機內圖檔
            intent.setAction(Intent.ACTION_GET_CONTENT);
            //取得相片後返回本畫面
            startActivityForResult(intent, requestCode);
            //**
        }
    };

    //****
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        //**

        if (resultCode == RESULT_OK) {

            ///*************

            //取得圖檔的路徑位置
            Uri uri = data.getData();
            //寫log
            // Log.e("uri", uri.toString());
            //抽象資料的接口
            //Toast.makeText(ScrollingActivity.this,"11",Toast.LENGTH_SHORT).show();


            ContentResolver cr = this.getContentResolver();
            try {
                //由抽象資料接口轉換圖檔路徑為Bitmap
                Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
                //取得圖片控制項ImageView
                //ImageButton imageView = (ImageButton) findViewById(R.id.imgBtn1);
                // 將Bitmap設定到ImageView

                //***************

                ImageButton imgBtn = (ImageButton)findViewById(R.id.imgBtn1);
                //**check requestCode to decide show image on which button
                switch (requestCode) {
                    case requestCodeImgBtn1:
                        imgBtn = (ImageButton) findViewById(R.id.imgBtn1);
                        //selectedImgForUpload1 = true;
                        selectedImgForUploadArray[0] = true;
                        bitmapArray[0] = bitmap;
                        //Toast.makeText(ScrollingActivity.this, selectedImgForUpload1==true? "TrueY":"FalseY", Toast.LENGTH_SHORT).show();
                        break;
                    case requestCodeImgBtn2:
                        imgBtn = (ImageButton) findViewById(R.id.imgBtn2);
                        selectedImgForUploadArray[1] = true;
                        bitmapArray[1] = bitmap;
                        //Toast.makeText(ScrollingActivity.this, "String.valueOf(R.id.imgBtn2)", Toast.LENGTH_SHORT).show();
                        break;
                    case requestCodeImgBtn3:
                        imgBtn = (ImageButton) findViewById(R.id.imgBtn3);
                        selectedImgForUploadArray[2] = true;
                        bitmapArray[2] = bitmap;
                        //Toast.makeText(ScrollingActivity.this, "String.valueOf(R.id.imgBtn3)", Toast.LENGTH_SHORT).show();
                        break;
                    case requestCodeImgBtn4:
                        imgBtn = (ImageButton) findViewById(R.id.imgBtn4);
                        selectedImgForUploadArray[3] = true;
                        bitmapArray[3] = bitmap;
                        //Toast.makeText(ScrollingActivity.this, "String.valueOf(R.id.imgBtn4)", Toast.LENGTH_SHORT).show();
                        break;
                    case requestCodeImgBtn5:
                        imgBtn = (ImageButton) findViewById(R.id.imgBtn5);
                        selectedImgForUploadArray[4] = true;
                        bitmapArray[4] = bitmap;
                        //Toast.makeText(ScrollingActivity.this, "String.valueOf(R.id.imgBtn5)", Toast.LENGTH_SHORT).show();
                        break;
                }
                //**
                imgBtn.setImageBitmap(bitmap);
                //**

            } catch (FileNotFoundException e) {
                Log.e("Exception", e.getMessage(),e);
            }
         }
       }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);

        int permission = ActivityCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            //未取得權限，向使用者要求允許權限
            ActivityCompat.requestPermissions( this,
                    new String[]{READ_EXTERNAL_STORAGE},
                    REQUEST_READ_STORAGE );
        }else{
            //已有權限，可進行檔案存取
        }
        init();
    }

    private void init() {

        //**
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               new android.support.v7.app.AlertDialog.Builder(ScrollingActivity.this)
                       .setMessage("是否確定送出資料")
                       .setTitle("送出確認")
                       .setPositiveButton("送出", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialog, int which) {
                               Toast.makeText(ScrollingActivity.this,"使用者按下確認",Toast.LENGTH_SHORT).show();
                               sendFormDataToServer();
                           }
                       })
                       .setNegativeButton("取消",null)
                       .show();

            }
        });
        ///**************************


        //**************
        imgBtn1 = (ImageButton)findViewById(R.id.imgBtn1);
        imgBtn1.setOnClickListener(imgBtnClick);
        //**************
        imgBtn2 = (ImageButton)findViewById(R.id.imgBtn2);
        imgBtn2.setOnClickListener(imgBtnClick);
        //**************
        imgBtn3 = (ImageButton)findViewById(R.id.imgBtn3);
        imgBtn3.setOnClickListener(imgBtnClick);
        //**************
        imgBtn4 = (ImageButton)findViewById(R.id.imgBtn4);
        imgBtn4.setOnClickListener(imgBtnClick);
        //**************
        imgBtn5 = (ImageButton)findViewById(R.id.imgBtn5);
        imgBtn5.setOnClickListener(imgBtnClick);
    }

    private void sendFormDataToServer() {
        for (int i = 0; i < selectedImgForUploadArray.length; i++) {

            if(selectedImgForUploadArray[i] == true){
                Toast.makeText(ScrollingActivity.this, selectedImgForUploadArray[i]==true? "True: "+i:"sFalse : "+i, Toast.LENGTH_SHORT).show();
                String bitmapStream = transBitmapToStream(bitmapArray[i]);
                imgurUpload(bitmapStream);
            }
        }
    }

    private void imgurUpload(final String image){ //插入圖片
        Toast.makeText(ScrollingActivity.this,"in Imgur Upload", Toast.LENGTH_SHORT).show();
        //String urlString = "https://imgur-apiv3.p.mashape.com/3/image/";
        String urlString = "https://imgur-apiv3.p.mashape.com/3/image";
        String mashapeKey = "MaXLzROxvOmshVYRZbRxcLZL3s0ip1bnE2Kjsn8tf3B5bKRyig"; //設定自己的 Mashape Key
        String clientId = "d8371f0a27e5085"; //設定自己的 Clinet ID
        String titleString = "hihi45454545"; //設定圖片的標題

        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("X-Mashape-Key", mashapeKey);
        client.addHeader("Authorization", "Client-ID "+clientId);
        client.addHeader("Content-Type", "application/x-www-form-urlencoded");

        RequestParams params = new RequestParams();
        params.put("title", titleString);
        params.put("image", image);

        client.post(urlString, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                if (!response.optBoolean("success") || !response.has("data")) {
                    Log.d("editor", "response: "+response.toString());
                    Toast.makeText(ScrollingActivity.this,"fail", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(ScrollingActivity.this,"in On Success", Toast.LENGTH_SHORT).show();

                JSONObject data = response.optJSONObject("data");
                String link = data.optString("link","");
                int width = data.optInt("width",0);
                int height = data.optInt("height",0);
                String bbcode = "[img="+width+"x"+height+"]"+link+"[/img]";

                Log.d("editor",data.optString("link"));
                Log.d("editor",bbcode);
                //**
                Toast.makeText(ScrollingActivity.this,data.optString("link"),Toast.LENGTH_LONG).show();
                //**

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable e, JSONObject error) {
                Toast.makeText(ScrollingActivity.this,"Error: " + statusCode + " " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private String transBitmapToStream(Bitmap myBitmap) {
        Bitmap bitmap = myBitmap; //程式寫在後面

        //將 Bitmap 轉為 base64 字串
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
        byte[] bitmapData = bos.toByteArray();
        String imageBase64 = Base64.encodeToString(bitmapData, Base64.DEFAULT);
        return imageBase64;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //*********************
    ImageButton imgBtn1;
    ImageButton imgBtn2;
    ImageButton imgBtn3;
    ImageButton imgBtn4;
    ImageButton imgBtn5;
    ImageButton[] imgBtnArray = {imgBtn1,imgBtn2,imgBtn3,imgBtn4,imgBtn5};
    //*********************
}
