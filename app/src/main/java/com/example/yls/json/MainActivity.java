package com.example.yls.json;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button btn01;
    private Button btn02;
    private Button btn03;
    private TextView mText;
    private String jsonStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        btn01 = (Button) findViewById(R.id.btn01);
        btn02 = (Button) findViewById(R.id.btn02);
        btn03 = (Button) findViewById(R.id.btn03);
        mText = (TextView) findViewById(R.id.textview);

        student s1 = new student(001,"张二",20);
        student s2 = new student(002,"张三",30);
        student s3 = new student(002,"张四",40);
        final  student[] stus = {s1, s2, s3};

        btn01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONArray array = new JSONArray();
                for(int i=0; i<stus.length; i++){
                    JSONObject stuObj = getStudentJsonObj(stus[i]);
                    array.put(stuObj);
                }
                JSONObject obj = new JSONObject();
                try{
                    obj.put("stuList",array);
                }catch (JSONException e){
                    Log.i("MainActivity",e.toString());
                }

                jsonStr = obj.toString();
                mText.setText(obj.toString());
            }
        });

        btn02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    JSONObject obj = new JSONObject(jsonStr);
                    JSONArray array = obj.getJSONArray("stuList");

                    ArrayList<student> stuList = new ArrayList<student>();
                    for(int i=0; i<array.length(); i++){
                        JSONObject stuObj = array.getJSONObject(i);
                        int id = stuObj.getInt("id");
                        String name = stuObj.getString("name");
                        int age = stuObj.getInt("age");
                        student s = new student(id, name, age);
                        stuList.add(s);
                    }

                }catch (JSONException e){
                    Log.i("MainActivity", e.toString());
                }
            }
        });
        btn03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String jsonStr = "{\"id\":101,\"name\":\"jack\",\"age\":25}";

            }
        });

    }
    private JSONObject getStudentJsonObj(student s){
        JSONObject obj = new JSONObject();
        try{
            obj.put("id",s.getId());
            obj.put("name",s.getName());
            obj.put("age",s.getAge());
        }catch (JSONException e){
            Log.i("MainActivity",e.toString());
        }
        return obj;
    }
}
