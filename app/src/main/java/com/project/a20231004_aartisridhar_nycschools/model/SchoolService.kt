package com.project.a20231004_aartisridhar_nycschools.model

import android.util.Log
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SchoolService(private val schoolAPI: SchoolAPI) :SchoolAPI {
    override fun getSchoolList(): Observable<List<SchoolDataModel>> {
        Log.d("schoolList","Inside method")
        return schoolAPI.getSchoolList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext { response ->
                Log.d("Inside observable","inside doOnNextblock")
                if (response.isNotEmpty()) {
                    for(schoolData in response){
                        val schoolName = schoolData.school_name
                        val website = schoolData.website
                        Log.d("API Response", "School Description: $schoolName, Name: $website")
                    }
                } else {
                    Log.e("##No Response", "Empty Response")
                }
            }
            .doOnError{ throwable ->
                Log.e("Error","${throwable.message}")
            }
    }

    override fun getSatScoreDataForSchool(): Observable<List<SatScoreDataModel>> {
        Log.d("SATList","Inside method")
        return schoolAPI.getSatScoreDataForSchool()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext { response ->
                Log.d("Inside observable","inside doOnNextblock")
                if (response.isNotEmpty()) {
                    for(scoreData in response){
                        val mathScore = scoreData.sat_math_avg_score
                        val readingScore = scoreData.sat_critical_reading_avg_score
                        val writingScore = scoreData.sat_writing_avg_score
                        Log.d("API Response", "MathScore: $mathScore, ReadingScore: $readingScore, WritingScore:$writingScore ")
                    }
                } else {
                    Log.e("##No Response", "Empty Response")
                }
            }
            .doOnError{ throwable ->
                Log.e("Error","${throwable.message}")
            }
    }
}