package com.tillster.smartquiltkotlin.Retrofit

import com.example.earlylife.Models.Quilt
import io.reactivex.Observable
import retrofit2.http.GET

interface IQuilt
{

    @GET("/")
    fun getShapes(): Observable<Quilt>

    @GET("/")
    fun getLove(): Observable<Quilt>


}