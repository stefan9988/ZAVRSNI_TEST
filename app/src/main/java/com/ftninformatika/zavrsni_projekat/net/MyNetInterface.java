package com.ftninformatika.zavrsni_projekat.net;

import com.ftninformatika.zavrsni_projekat.net.Response;
import com.ftninformatika.zavrsni_projekat.net.Film;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MyNetInterface {
    @GET("/")
    Call<Film>getFilmById(@Query("apikey")String Key, @Query("i")String id);
    @GET("/")
    Call<Response>getFilms(@Query("apikey")String Key, @Query("s")String search);
}
