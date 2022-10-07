package com.example.limasmart.retfrofitUtil;

import com.example.limasmart.model.registeredDocs;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("getUsers.php")
    Call<List<registeredDocs> > getUser ( @Query("key") String keyword );
            //( @Query("key") String keyword );
}
