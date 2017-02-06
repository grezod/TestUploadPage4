/*
package iii.com.tw.testuploadpage2;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class ActSearchShelter extends AppCompatActivity {
    String selectedArea = "";
    String selectedType = "";

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_searchshelter);
        initComponent();

        //縣市下拉選單
        final String[] area = {"全部","臺北市", "新北市", "基隆市", "宜蘭縣",
                "桃園縣", "新竹縣", "新竹市", "苗栗縣", "臺中市", "彰化縣",
                "南投縣", "雲林縣", "嘉義縣", "嘉義市", "臺南市", "高雄市",
                "屏東縣", "花蓮縣", "臺東縣", "澎湖縣", "金門縣", "連江縣"};
        //adapter可以使用自訂layout
        ArrayAdapter<String> areaList = new ArrayAdapter<>(ActSearchShelter.this,
                R.layout.spinnercontent,
                area);
        spinner_area.setDropDownWidth(500);
        spinner_area.setAdapter(areaList);
        spinner_area.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedArea = area[position];
               // Log.d(CDictionary.Debug_TAG,selectedArea);
                //Toast.makeText(MainActivity.this, "你選的是" + lunch[position], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(ActSearchShelter.this);
                dialog.setTitle("請選擇欲查詢的縣市");
                dialog.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                dialog.create().show();
            }
        });

        //類型下拉選單
        final String[] type = {"全部", "狗", "貓"};
        ArrayAdapter<String> typeList = new ArrayAdapter<>(ActSearchShelter.this,
                R.layout.spinnercontent,
                type);
        spinner_type.setDropDownWidth(500);
        spinner_type.setAdapter(typeList);
        spinner_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedType = type[position];
                //Log.d(CDictionary.Debug_TAG,selectedType);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(ActSearchShelter.this);
                dialog.setTitle("請選擇欲查詢的類型");
                dialog.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                dialog.create().show();
            }
        });

    }


    //搜尋按鈕點擊後事件
    View.OnClickListener btnSearch_Click=new View.OnClickListener(){
        public void onClick(View arg0) {
            Bundle bundle = new Bundle();
            bundle.putString(CDictionary.BK_Area, selectedArea);
            bundle.putString(CDictionary.BK_Type, selectedType);
            Intent intent = new Intent(ActSearchShelter.this, ActShelterPetList.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    };


    public void initComponent(){
        btnSearch = (Button)findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(btnSearch_Click);
        spinner_area = (Spinner)findViewById(R.id.spinner_area);
        spinner_type = (Spinner)findViewById(R.id.spinner_type);

    }


    Button btnSearch;
    Spinner spinner_area, spinner_type;
}


*/