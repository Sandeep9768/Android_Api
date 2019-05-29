package com.example.sandeeptransfinite.api_project;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {


    private ProgressDialog progressDialog;
    private RecyclerView rvList;
    private List<Post> postList;
    private PostsAdapter postsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setViews();


        getData();

    }

    private void setViews() {
        rvList = findViewById(R.id.rvList);
        rvList.setLayoutManager(new LinearLayoutManager(this));

        postList = new ArrayList<>();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
    }

    private void getData() {

        progressDialog.show();

        API api = RetroClient.getRetrofitInstance().create(API.class);
        api.getPosts("/photos").enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                progressDialog.dismiss();

//                postList = gson.fromJson(response.body().toString(), new TypeToken<ArrayList<Post>>(){}.getType());
                postList = response.body();
                postsAdapter = new PostsAdapter(postList);
                rvList.setAdapter(postsAdapter);

                Toast.makeText(HomeActivity.this, "Success", Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Toast.makeText(HomeActivity.this, "Error", Toast.LENGTH_SHORT).show();
                Log.d("Error", t.toString());

            }
        });

    }

    public void next(View view) {
        Intent i=new Intent(this,MainActivity.class);
        startActivity(i);
    }
}
