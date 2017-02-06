package iii.com.tw.testuploadpage2;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import static android.Manifest.permission.*;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;
//import static iii.com.tw.testuploadpage2.R.id.edTxt_animalData_animalTypeID;


import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import cz.msebera.android.httpclient.Header;
//***********
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
//************
import  com.google.gson.Gson;
import com.loopj.android.http.SyncHttpClient;

//*******

public class ScrollingActivity extends AppCompatActivity {
    //*
    object_ConditionOfAdoptPet iv_object_conditionOfAdoptPet_a;
    //**
    public static ScrollingActivity scrollingActivity;
    //***
    OkHttpClient Iv_OkHttp_client = new OkHttpClient();
    public static final MediaType Iv_MTyp_JSON = MediaType.parse("application/json; charset=utf-8");
    //**
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
    //***
    object_ConditionOfAdoptPet object_conditionOfAdoptPet;
    //**
    Gson iv_gson;
    //**
    AlertDialog iv_ADialog_a;
    AlertDialog iv_ADialog_b;
    //**
    Bitmap[] bitmapArray = {bitmap1,bitmap2,bitmap3,bitmap4,bitmap5};
    boolean[] selectedImgForUploadArray = {selectedImgForUpload1,selectedImgForUpload2,selectedImgForUpload3,selectedImgForUpload4,selectedImgForUpload5};
    ArrayList<object_OfPictureImgurSite> iv_ArrayList_object_OfPictureImgurSite;
    ArrayList<object_ConditionOfAdoptPet> iv_ArrayList_object_ConditionOfAdoptPet;
    final String[] area = {"全部","臺北市", "新北市", "基隆市", "宜蘭縣",
            "桃園縣", "新竹縣", "新竹市", "苗栗縣", "臺中市", "彰化縣",
            "南投縣", "雲林縣", "嘉義縣", "嘉義市", "臺南市", "高雄市",
            "屏東縣", "花蓮縣", "臺東縣", "澎湖縣", "金門縣", "連江縣"};


    //**

    private View.OnClickListener btn_click = new View.OnClickListener() {
        int IntentRCodeOfOpenAlbum = 0;


        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.imgBtn1:
                    IntentRCodeOfOpenAlbum = requestCodeImgBtn1;
                    //Toast.makeText(ScrollingActivity.this,"String.valueOf(R.id.imgBtn1)",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.imgBtn2:
                    IntentRCodeOfOpenAlbum = requestCodeImgBtn2;
                    //Toast.makeText(ScrollingActivity.this,"String.valueOf(R.id.imgBtn2)",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.imgBtn3:
                    IntentRCodeOfOpenAlbum = requestCodeImgBtn3;
                    //Toast.makeText(ScrollingActivity.this,"String.valueOf(R.id.imgBtn3)",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.imgBtn4:
                    IntentRCodeOfOpenAlbum = requestCodeImgBtn4;
                    //Toast.makeText(ScrollingActivity.this,"String.valueOf(R.id.imgBtn4)",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.imgBtn5:
                    IntentRCodeOfOpenAlbum = requestCodeImgBtn5;
                    //Toast.makeText(ScrollingActivity.this,"String.valueOf(R.id.imgBtn5)",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btnAdoptCondition:
                    Log.d("123","btnAdoptCondition");
                    Intent intent = new Intent(ScrollingActivity.this,actDialogOfPetAdoptCondition.class);
                    intent.putExtra("l_object_ConditionOfAdoptPet_objA",iv_object_conditionOfAdoptPet_a);
                    startActivityForResult(intent,CDictionary.IntentRqCodeOfPetAdoptCondition);
                    break;
            }

            if(v.getId()!=R.id.btnAdoptCondition){
                //Toast.makeText(ScrollingActivity.this,String.valueOf(IntentRCodeOfOpenAlbum),Toast.LENGTH_SHORT).show();

                //**
                Intent intent = new Intent();
                //開啟Pictures畫面Type設定為image
                intent.setType("image/*");
                //使用Intent.ACTION_GET_CONTENT這個Action會開啟選取圖檔視窗讓您選取手機內圖檔
                intent.setAction(Intent.ACTION_GET_CONTENT);
                //取得相片後返回本畫面
                startActivityForResult(intent, IntentRCodeOfOpenAlbum);
                //**
            }

        }
    };
    private int iv_int_countHowManyPicNeedUpload;

    //****
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //******如果是彈跳視窗的回應********************************
        if(resultCode == CDictionary.IntentRqCodeOfPetAdoptCondition){
             iv_object_conditionOfAdoptPet_a =
                    (object_ConditionOfAdoptPet)data.getSerializableExtra("l_object_ConditionOfAdoptPet_objA");

            //**
            Log.d("test",iv_object_conditionOfAdoptPet_a.toString());
            //*


        }


        //***********如果是圖片按鈕的回應************************

        if (resultCode == RESULT_OK) {
            //****
            Bitmap mScaleBitmap=null;

            ///*************
            Log.d("OK","OK");

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
                //*****************



                float mScale = 1 ;

                //如果圖片寬度大於手機寬度則進行縮放，否則直接將圖片放入ImageView內
                if(bitmap.getWidth() >= 480 ) {
                    //判斷縮放比例
                    mScale = (float)480/bitmap.getWidth();
                }

                    Matrix mMat = new Matrix() ;
                    mMat.setScale(mScale, mScale);

                     mScaleBitmap = Bitmap.createBitmap(bitmap,
                            0,
                            0,
                            bitmap.getWidth(),
                            bitmap.getHeight(),
                            mMat,
                            false);



                //***************

                ImageButton imgBtn = (ImageButton)findViewById(R.id.imgBtn1);
                //**check requestCode to decide show image on which button
                switch (requestCode) {
                    case requestCodeImgBtn1:
                        imgBtn = (ImageButton) findViewById(R.id.imgBtn1);
                        //selectedImgForUpload1 = true;
                        selectedImgForUploadArray[0] = true;
                        bitmapArray[0] = mScaleBitmap;
                        //Toast.makeText(ScrollingActivity.this, selectedImgForUpload1==true? "TrueY":"FalseY", Toast.LENGTH_SHORT).show();
                        break;
                    case requestCodeImgBtn2:
                        imgBtn = (ImageButton) findViewById(R.id.imgBtn2);
                        selectedImgForUploadArray[1] = true;
                        bitmapArray[1] = mScaleBitmap;
                        //Toast.makeText(ScrollingActivity.this, "String.valueOf(R.id.imgBtn2)", Toast.LENGTH_SHORT).show();
                        break;
                    case requestCodeImgBtn3:
                        imgBtn = (ImageButton) findViewById(R.id.imgBtn3);
                        selectedImgForUploadArray[2] = true;
                        bitmapArray[2] = mScaleBitmap;
                        //Toast.makeText(ScrollingActivity.this, "String.valueOf(R.id.imgBtn3)", Toast.LENGTH_SHORT).show();
                        break;
                    case requestCodeImgBtn4:
                        imgBtn = (ImageButton) findViewById(R.id.imgBtn4);
                        selectedImgForUploadArray[3] = true;
                        bitmapArray[3] = mScaleBitmap;
                        //Toast.makeText(ScrollingActivity.this, "String.valueOf(R.id.imgBtn4)", Toast.LENGTH_SHORT).show();
                        break;
                    case requestCodeImgBtn5:
                        imgBtn = (ImageButton) findViewById(R.id.imgBtn5);
                        selectedImgForUploadArray[4] = true;
                        bitmapArray[4] = mScaleBitmap;
                        //Toast.makeText(ScrollingActivity.this, "String.valueOf(R.id.imgBtn5)", Toast.LENGTH_SHORT).show();
                        break;
                }
                //**

                imgBtn.setImageBitmap(mScaleBitmap);
                Log.d("mScaleBitmapSize",""+mScaleBitmap.getWidth()+"  and "+mScaleBitmap.getHeight());
                Log.d("bitmapSize",""+bitmap.getWidth()+"  and "+bitmap.getHeight());
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
        iv_int_countHowManyPicNeedUpload =0;
        iv_ArrayList_object_ConditionOfAdoptPet = new ArrayList<>();
        iv_ArrayList_object_OfPictureImgurSite = new ArrayList<>();
        iv_gson= new Gson();
        setViewComponent();

        //****************************
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 iv_ADialog_a=new AlertDialog.Builder(ScrollingActivity.this)
                       .setMessage("是否確定送出資料")
                       .setTitle("送出確認")
                       .setPositiveButton("送出", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialog, int which) {


                               Toast.makeText(ScrollingActivity.this,"正在上傳您的資料,請稍後...",Toast.LENGTH_LONG).show();

                               try {
                                   Log.d("test","進入TRY");
                                   uploadImageAndGetSiteBack();
                               } catch (Exception e) {
                                   e.printStackTrace();
                               }
                               addAllDataToDBServer();

                           }
                       })
                       .setNegativeButton("取消",null)
                       .show();

            }
        });
        ///**************************

    }



    private void setViewComponent() {
        edTxt_animalKind = (EditText)findViewById(R.id.edTxt_animalKind);
        edTxt_animalType = (EditText)findViewById(R.id.edTxt_animalType);
        edTxt_animalAddress = (EditText)findViewById(R.id.edTxt_animalAddress);
        edTxt_animalAge = (EditText)findViewById(R.id.edTxt_animalAge);
        edTxt_animalBirth = (EditText)findViewById(R.id.edTxt_animalBirth);
        edTxt_animalChip = (EditText)findViewById(R.id.edTxt_animalChip);
        edTxt_animalColor = (EditText)findViewById(R.id.edTxt_animalColor);
        edTxt_animalDate = (EditText)findViewById(R.id.edTxt_animalDate);
        edTxt_animalDisease_Other = (EditText)findViewById(R.id.edTxt_animalDisease_Other);
        edTxt_animalGender = (EditText)findViewById(R.id.edTxt_animalGender);
        edTxt_animalHealthy = (EditText)findViewById(R.id.edTxt_animalHealthy);
        edTxt_animalName = (EditText)findViewById(R.id.edTxt_animalName);
        edTxt_animalNote = (EditText)findViewById(R.id.edTxt_animalNote);
        edTxt_animalReason = (EditText)findViewById(R.id.edTxt_animalReason);
        //***
        scrollingActivity = this;
        //**************
        imgBtn1 = (ImageButton)findViewById(R.id.imgBtn1);
        imgBtn1.setOnClickListener(btn_click);
        //**************
        imgBtn2 = (ImageButton)findViewById(R.id.imgBtn2);
        imgBtn2.setOnClickListener(btn_click);
        //**************
        imgBtn3 = (ImageButton)findViewById(R.id.imgBtn3);
        imgBtn3.setOnClickListener(btn_click);
        //**************
        imgBtn4 = (ImageButton)findViewById(R.id.imgBtn4);
        imgBtn4.setOnClickListener(btn_click);
        //**************
        imgBtn5 = (ImageButton)findViewById(R.id.imgBtn5);
        imgBtn5.setOnClickListener(btn_click);
        //**************
        btnAdoptCondition = (Button) findViewById(R.id.btnAdoptCondition);
        btnAdoptCondition.setOnClickListener(btn_click);
    }

    private void addAllDataToDBServer() {
        //******判斷使用者是否填寫領養條件

        if(iv_object_conditionOfAdoptPet_a == null){
            iv_object_conditionOfAdoptPet_a = new  object_ConditionOfAdoptPet();
            iv_object_conditionOfAdoptPet_a.createAdefault_object_ConditionOfAdoptPet();
        }

            iv_ArrayList_object_ConditionOfAdoptPet.add(iv_object_conditionOfAdoptPet_a);


        //************
        object_petDataForSelfDB l_PetData_PetObj = new object_petDataForSelfDB();
        l_PetData_PetObj.setAnimalAddress(edTxt_animalAddress.getText().toString());
        l_PetData_PetObj.setAnimalAge(edTxt_animalAge.getText().toString());
        l_PetData_PetObj.setAnimalKind(edTxt_animalKind.getText().toString());
        l_PetData_PetObj.setAnimalType((edTxt_animalType.getText().toString()));
        l_PetData_PetObj.setAnimalBirth(edTxt_animalBirth.getText().toString());
        l_PetData_PetObj.setAnimalChip(edTxt_animalChip.getText().toString());
        l_PetData_PetObj.setAnimalColor(edTxt_animalColor.getText().toString());
        l_PetData_PetObj.setAnimalDate(edTxt_animalDate.getText().toString());
        l_PetData_PetObj.setAnimalDisease_Other(edTxt_animalDisease_Other.getText().toString());
        l_PetData_PetObj.setAnimalGender(edTxt_animalGender.getText().toString());
        l_PetData_PetObj.setAnimalHealthy(edTxt_animalHealthy.getText().toString());
        l_PetData_PetObj.setAnimalName(edTxt_animalName.getText().toString());
        l_PetData_PetObj.setAnimalNote(edTxt_animalNote.getText().toString());
        l_PetData_PetObj.setAnimalReason(edTxt_animalReason.getText().toString());
        l_PetData_PetObj.setAnimalData_Condition(iv_ArrayList_object_ConditionOfAdoptPet);
        l_PetData_PetObj.setAnimalData_Pic(iv_ArrayList_object_OfPictureImgurSite);
        //****************
        Gson l_gsn_gson = new Gson();
        String l_strPetDataObjToJSONString = l_gsn_gson.toJson(l_PetData_PetObj);
        //***********
        RequestBody requestBody =  RequestBody.create(Iv_MTyp_JSON,l_strPetDataObjToJSONString);

        //***************
        Request request = new Request.Builder()
                .url("http://twpetanimal.ddns.net:9487/api/v1/AnimalDatas")
                .addHeader("Content-Type","raw")
                .post(requestBody)
                .build();

        Call call = Iv_OkHttp_client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("http","fail");

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String json = response.body().string();
                Log.d("http",json);
                //textView.setText(json);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        try {
                            JSONObject jObj = new JSONObject(json);
                            String id = jObj.getString("animalID");
                            Toast.makeText(ScrollingActivity.this,"上傳成功!(測試用_此次新增資料的id: "+id+")",Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                });

                //parseJson(json);
            }
        });


        //*******************



    }

    private void uploadImageAndGetSiteBack() throws Exception {

        //***************

        //**********
        iv_int_countHowManyPicNeedUpload = 0;

        iv_ADialog_a.dismiss();
        {
            for (int i = 0; i < selectedImgForUploadArray.length; i++) {

                if (selectedImgForUploadArray[i] == true) {
                    iv_int_countHowManyPicNeedUpload += 1;
                    Toast.makeText(ScrollingActivity.this,"資料上傳中 請稍後....",Toast.LENGTH_LONG).show();

                }
            }

        }

        //********

        CountDownLatch latch=new CountDownLatch(iv_int_countHowManyPicNeedUpload);//N个工人的协作

        Log.d("","進入uploadImageAndGetSiteBack");


        for (int i = 0; i < selectedImgForUploadArray.length; i++) {

            if(selectedImgForUploadArray[i] == true){

               // Toast.makeText(ScrollingActivity.this, selectedImgForUploadArray[i]==true? "True: "+i:"sFalse : "+i, Toast.LENGTH_SHORT).show();
                String bitmapStream = transBitmapToStream(bitmapArray[i]);
                //imgurUpload(bitmapStream);
                Log.d(" 進入迴圈",String.valueOf(selectedImgForUploadArray.length));
                uploadImgByCallable l_uploadImgByCallable = new uploadImgByCallable(bitmapStream,latch);
                l_uploadImgByCallable.start();
            }

        }

        latch.await();
        Log.d(" await完畢"," ");

    }

    private void imgurUpload(final String image){ //插入圖片
        SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
        Date date = new Date();
        String strDate = sdFormat.format(date);
        //String urlString = "https://imgur-apiv3.p.mashape.com/3/image/";
        String urlString = "https://imgur-apiv3.p.mashape.com/3/image";
        String mashapeKey = ""; //設定自己的 Mashape Key
        String clientId = ""; //設定自己的 Clinet ID
        String titleString = "GetPet"+strDate; //設定圖片的標題


        AsyncHttpClient client0 = new AsyncHttpClient();
        client0.addHeader("X-Mashape-Key", mashapeKey);
        client0.addHeader("Authorization", "Client-ID "+clientId);
        client0.addHeader("Content-Type", "application/x-www-form-urlencoded");

        RequestParams params = new RequestParams();
        params.put("title", titleString);
        params.put("image", image);

        client0.post(urlString, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                if (!response.optBoolean("success") || !response.has("data")) {
                    Log.d("editor", "response: "+response.toString());
                    Toast.makeText(ScrollingActivity.this,"fail", Toast.LENGTH_SHORT).show();
                    return;
                }


                JSONObject data = response.optJSONObject("data");
                String link = data.optString("link","");
                int width = data.optInt("width",0);
                int height = data.optInt("height",0);
                String bbcode = "[img="+width+"x"+height+"]"+link+"[/img]";

               // Log.d("editor",data.optString("link"));
               // Log.d("editor",bbcode);
                //**

                Log.d("imgSite",link);
                //**
                object_OfPictureImgurSite l_object_OfPictureImgurSite = new object_OfPictureImgurSite(data.optString("link"));


                iv_ArrayList_object_OfPictureImgurSite.add(l_object_OfPictureImgurSite);

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable e, JSONObject error) {

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


    class uploadImgByCallable extends Thread {
        String image;
        CountDownLatch latch;
        public uploadImgByCallable(String p_image,CountDownLatch p_latch){
            this.image = p_image;
            this.latch = p_latch;
        }

        @Override
        public void run() {
            Log.d(" 進入線程"," 進入線程");


            imgurUploadInClass(image);

        }


        private void imgurUploadInClass(final String image){ //插入圖片
            Log.d(" 進入imgurUpload"," 進入imgurUpload");
            SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
            Date date = new Date();
            String strDate = sdFormat.format(date);
            Log.d(" 1進入imgurUpload"," 進入imgurUpload");

            //String urlString = "https://imgur-apiv3.p.mashape.com/3/image/";
            String urlString = "https://imgur-apiv3.p.mashape.com/3/image";
            String mashapeKey = "MaXLzROxvOmshVYRZbRxcLZL3s0ip1bnE2Kjsn8tf3B5bKRyig"; //設定自己的 Mashape Key
            String clientId = "d8371f0a27e5085"; //設定自己的 Clinet ID
            String titleString = "GetPet"+strDate; //設定圖片的標題


            SyncHttpClient client0 = new SyncHttpClient();
            client0.addHeader("X-Mashape-Key", mashapeKey);
            client0.addHeader("Authorization", "Client-ID "+clientId);
            client0.addHeader("Content-Type", "application/x-www-form-urlencoded");

            RequestParams params = new RequestParams();
            params.put("title", titleString);
            params.put("image", image);
            Log.d(" 準備POST"," ");

            client0.post(urlString, params, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                    if (!response.optBoolean("success") || !response.has("data")) {
                        Log.d("editor", "response: "+response.toString());

                        return;
                    }


                    JSONObject data = response.optJSONObject("data");
                    String link = data.optString("link","");
                    int width = data.optInt("width",0);
                    int height = data.optInt("height",0);
                    String bbcode = "[img="+width+"x"+height+"]"+link+"[/img]";

                    // Log.d("editor",data.optString("link"));
                    // Log.d("editor",bbcode);
                    //**

                    Log.d("imgSite",link);
                    //**
                    object_OfPictureImgurSite l_object_OfPictureImgurSite = new object_OfPictureImgurSite(data.optString("link"));


                    iv_ArrayList_object_OfPictureImgurSite.add(l_object_OfPictureImgurSite);
                    latch.countDown();

                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable e, JSONObject error) {

                    Log.d("上傳圖片失敗","");
                }
            });

            Log.d("到底?","");

        }
    }



    //*********************
    ImageButton imgBtn1;
    ImageButton imgBtn2;
    ImageButton imgBtn3;
    ImageButton imgBtn4;
    ImageButton imgBtn5;
    Button btnAdoptCondition;
    ImageButton[] imgBtnArray = {imgBtn1,imgBtn2,imgBtn3,imgBtn4,imgBtn5};
    //*********************
    EditText edTxt_animalID;
    EditText edTxt_animalKind;
    EditText edTxt_animalType;
    EditText edTxt_animalName;
    EditText edTxt_animalAddress;
    EditText edTxt_animalDate;
    EditText edTxt_animalGender;
    EditText edTxt_animalAge;
    EditText edTxt_animalColor;
    EditText edTxt_animalBirth;
    EditText edTxt_animalChip;
    EditText edTxt_animalHealthy;
    EditText edTxt_animalDisease_Other;
    EditText edTxt_animalOwner_userID;
    EditText edTxt_animalReason;
    EditText edTxt_animalGetter_userID;
    EditText edTxt_animalAdopted;
    EditText edTxt_animalAdoptedDate;
    EditText edTxt_animalNote;
    //***********************
    Spinner spinner_animalArea=(Spinner)findViewById(R.id.spinner_animalArea);
    Spinner spinner_animalKind=(Spinner)findViewById(R.id.spinner_animalKind);
    Spinner spinner_animalType=(Spinner)findViewById(R.id.spinner_animalType);
    //*******



}

/*
    //***********
    ProgressDialog progressBar;
//宣告
progressBar = new ProgressDialog(ScrollingActivity.this);
        progressBar.setCancelable(true);
        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressBar.setMessage("載入中");

//顯示
        progressBar.show();
//**********
*/