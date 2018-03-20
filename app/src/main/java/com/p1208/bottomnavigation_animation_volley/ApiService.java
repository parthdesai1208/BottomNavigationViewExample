package com.p1208.bottomnavigation_animation_volley;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Administrator on 29/12/2017.
 */

public interface ApiService {
    //update 1st
    @GET("movies_2017.json")
    Call<List<Movie>> getstoredeatils();

}
