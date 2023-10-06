package com.project.a20231004_aartisridhar_nycschools.model

import io.reactivex.Observable
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface SchoolAPI {
    @GET("s3k6-pzi2.json")
    fun getSchoolList(): Observable<List<SchoolDataModel>>
    @GET("f9bf-2cp4.json")
    fun getSatScoreDataForSchool(): Observable<List<SatScoreDataModel>>
    //
    companion object{
        fun create():SchoolAPI{
            val retrofit = Retrofit.Builder()
                .baseUrl("https://data.cityofnewyork.us/resource/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
            return retrofit.create(SchoolAPI::class.java)
        }
    }
}