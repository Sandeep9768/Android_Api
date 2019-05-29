package com.example.sandeeptransfinite.api_project;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

import static com.example.sandeeptransfinite.api_project.Constants.GET_URL;

public interface API {

     @GET
     Call<List<Post>> getPosts(
             @Url String url
     );

     @POST("/posts")
     Call<DataAccess> savePost(DataAccess dataAccess,@Url String url);

}
