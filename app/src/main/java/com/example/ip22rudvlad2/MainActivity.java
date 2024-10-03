package com.example.ip22rudvlad2;

import android.app.DownloadManager;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.os.StrictMode;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.LinearLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        //showGroups();
        showTeachers();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void showGroups(){
        TextView view = findViewById(R.id.content);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("http://217.71.129.139:4862/schedule").build();
        Call call = client.newCall(request);
        call.enqueue(new Callback(){
            public void onResponse(Call call, Response response) throws IOException{
                try{
                    String json = response.body().string();
                    JSONArray array = new JSONArray(json);
                    ArrayList<String> groups = new ArrayList<String>();
                    Spinner spinner = findViewById(R.id.spinner);
                    for(int i=0; i< array.length(); i++){
                        JSONObject object = array.getJSONObject(i);
                        String text = object.getString("days_name");
                        String text1 = object.getString("times_time");
                        String text2 = object.getString("disciplines_name");
                        String text3 = object.getString("teachers_fio");
                        String text4 = object.getString("rooms_num");
                        String text5 = object.getString("groups_title");
                        groups.add(text);
                        groups.add(text1);
                        groups.add(text2);
                        groups.add(text3);
                        groups.add(text4);
                        groups.add(text5);
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, groups);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    MainActivity.this.runOnUiThread(new Runnable() {
                        public void run() {
                            spinner.setAdapter(adapter);
                        }
                    });
                }
                catch (JSONException e){
                    view.post(new Runnable() {
                        public void run() {
                            view.append(e.getMessage());
                        }
                    });
                }
            }
            public void onFailure(Call call, IOException e){
                view.post(new Runnable() {
                    public void run() {
                        view.append(e.getMessage());
                    }
                });
            }
        });
    }
    public void showTeachers(){
        TextView view = findViewById(R.id.content);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("http://217.71.129.139:4862/schedule").build();
        Call call = client.newCall(request);
        call.enqueue(new Callback(){
            public void onResponse(Call call, Response response) throws IOException{
                try{
                    String json = response.body().string();
                    JSONArray array = new JSONArray(json);
                    ArrayList<String> teachers = new ArrayList<String>();
                    ListView list = findViewById(R.id.list123);
                    for(int i=0; i< array.length(); i++){
                        JSONObject object = array.getJSONObject(i);
                        String text = object.getString("days_name");
                        String text1 = object.getString("times_time");
                        String text2 = object.getString("disciplines_name");
                        String text3 = object.getString("teachers_fio");
                        String text4 = object.getString("rooms_num");
                        String text5 = object.getString("groups_title");
                        teachers.add(text);
                        teachers.add(text1);
                        teachers.add(text2);
                        teachers.add(text3);
                        teachers.add(text4);
                        teachers.add(text5);
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, teachers);
                    adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
                    MainActivity.this.runOnUiThread(new Runnable() {
                        public void run() {
                            list.setAdapter(adapter);
                        }
                    });
                }
                catch (JSONException e){
                    view.post(new Runnable() {
                        public void run() {
                            view.append(e.getMessage());
                        }
                    });
                }
            }
            public void onFailure(Call call, IOException e){
                view.post(new Runnable() {
                    public void run() {
                        view.append(e.getMessage());
                    }
                });
            }
        });
    }
}