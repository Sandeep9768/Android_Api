package com.example.sandeeptransfinite.api_project;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    EditText name, email, mobile;
    private List<DataAccess> dataAccesses;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        mobile = findViewById(R.id.mobile);
//        DataAccess dataAccess = new DataAccess();
//        dataAccess.setName(name.getText().toString());
//        dataAccess.setEmail(email.getText().toString());
//        dataAccess.setMobile(mobile.getText().toString());
        //getData(dataAccess);
        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    public void getData(DataAccess dataAccess) {
        String url="posts";
        API api = RetroClient.getRetrofitInstance().create(API.class);
        api.savePost(dataAccess,url).enqueue(new Callback<DataAccess>() {
            @Override
            public void onResponse(Call<DataAccess> call, Response<DataAccess> response) {

            }

            @Override
            public void onFailure(Call<DataAccess> call, Throwable t) {

            }
        });

    }

    public void registration(View view) {
        DataAccess dataAccess = new DataAccess();
        dataAccess.setName(name.getText().toString());
        dataAccess.setEmail(email.getText().toString());
        dataAccess.setMobile(mobile.getText().toString());
        getData(dataAccess);
    }
}
