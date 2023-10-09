package com.project.a20231004_aartisridhar_nycschools.presenter

import android.util.Log
import com.project.a20231004_aartisridhar_nycschools.listener.SchoolViewListener
import com.project.a20231004_aartisridhar_nycschools.model.SatScoreDataModel
import com.project.a20231004_aartisridhar_nycschools.model.SchoolDataModel
import com.project.a20231004_aartisridhar_nycschools.model.SchoolService
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class SchoolListPresenter @Inject constructor(
    private val schoolView: SchoolViewListener.View,
    private val schoolService: SchoolService
) : SchoolViewListener.Presenter {
    private lateinit var disposable: Disposable
    override fun fetchSchoolList() {
        disposable = schoolService.getSchoolList()
            .map { response ->
            //transforming objects from response to SchoolDataModel type
                response.map { schoolData ->
                    SchoolDataModel(schoolData.dbn,schoolData.school_name,schoolData.website)
                }
            }
            .subscribe(
                { schoolInfo ->
                    Log.d("API Response", schoolInfo.toString())
                    if (schoolInfo.isNotEmpty()) {
                        schoolView.showSchoolList(schoolInfo)
                    } else {
                        schoolView.showError("School Info not available now")
                    }
                },
                { error ->
                    // Error handling here
                    Log.e("###Presentor Error", "${error.message}")
                }
            )
    }

    override fun fetchSchoolDetailsByDBN(dbn:String) {
        disposable = schoolService.getSatScoreDataForSchool()
            .map { response ->
                    response.firstOrNull{ selectedSchool -> selectedSchool.dbn == dbn }
                 }
            .map { selectedSchool ->
                    SatScoreDataModel(
                        selectedSchool.school_name,
                        selectedSchool.num_of_sat_test_takers,
                        selectedSchool.sat_writing_avg_score,
                        selectedSchool.sat_math_avg_score,
                        selectedSchool.sat_critical_reading_avg_score
                    )
                }
            .subscribe(
                { schoolDetails ->
                    Log.d("API Response", schoolDetails.toString())
                    if (schoolDetails!=null) {
                       schoolView.showSchoolDetailsScreen(schoolDetails)
                    } else {
                        schoolView.showError("School Info not available")
                    }
                },
                { error ->
                    // Error handling here
                    Log.e("###Presentor Error", "${error.message}")
                }

            )
    }

    fun clearDisposable() {
        disposable.dispose()
    }
}