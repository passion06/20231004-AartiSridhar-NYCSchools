package com.project.a20231004_aartisridhar_nycschools.model

import android.util.Log
import com.project.a20231004_aartisridhar_nycschools.listener.SchoolViewListener
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SchoolService(private val schoolAPI: SchoolAPI) : SchoolAPI, SchoolViewListener.Service {

    /** Fetches School List by hitting the API **/
    override fun getSchoolList(): Observable<List<SchoolDataModel>> {
        return schoolAPI.getSchoolList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError { throwable ->
                Log.e("Error", "${throwable.message}")
            }
    }

    /** Fetches School Details like SAT Scores by hitting the respective API **/
    override fun getSatScoreDataForSchool(): Observable<List<SatScoreDataModel>> {
        return schoolAPI.getSatScoreDataForSchool()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError { throwable ->
                Log.e("Error", "${throwable.message}")
            }
    }
}