package com.project.a20231004_aartisridhar_nycschools.listener

import android.content.Context
import com.project.a20231004_aartisridhar_nycschools.model.SatScoreDataModel
import com.project.a20231004_aartisridhar_nycschools.model.SchoolDataModel
import io.reactivex.Observable

interface SchoolViewListener {
    interface View{
        fun showSchoolList(schoolList:List<SchoolDataModel>)
        fun showError(message: String)
        fun showSchoolDetailsScreen(schoolDetails:SatScoreDataModel)
    }
    interface Presenter{
        fun fetchSchoolList()
        fun fetchSchoolDetailsByDBN(context: Context, dbn:String, overview:String)
    }
    interface Service{
        fun getSchoolList(): Observable<List<SchoolDataModel>>
        fun getSatScoreDataForSchool(): Observable<List<SatScoreDataModel>>
    }
}