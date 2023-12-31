package com.project.a20231004_aartisridhar_nycschools.presenter

import android.content.Context
import android.util.Log
import androidx.core.content.ContextCompat.getString
import com.project.a20231004_aartisridhar_nycschools.R
import com.project.a20231004_aartisridhar_nycschools.listener.SchoolViewListener
import com.project.a20231004_aartisridhar_nycschools.model.SatScoreDataModel
import com.project.a20231004_aartisridhar_nycschools.model.SchoolDataModel
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class SchoolListPresenter @Inject constructor(
    private val schoolView: SchoolViewListener.View,
    private val schoolService: SchoolViewListener.Service
) : SchoolViewListener.Presenter {
    private lateinit var disposable: Disposable

    /** Retrieves the schoolList by hitting the respective API using schoolService
     * **/
    override fun fetchSchoolList() {
        disposable = schoolService.getSchoolList()
            .map { response ->
                //transforming objects from response to SchoolDataModel type
                response.map { schoolData ->
                    SchoolDataModel(
                        schoolData.dbn,
                        schoolData.school_name,
                        schoolData.website,
                        schoolData.overview_paragraph
                    )
                }
            }
            .subscribe(
                { schoolInfo ->
                    //on successful response let this method handle calling the view
                    handleSchoolInfo(schoolInfo)
                },
                { error ->
                    // Error handling here
                    Log.e("SchoolListPresenter", "Error fetching school list: ${error.message}")
                }
            )
    }

    private fun handleSchoolInfo(schoolInfo: List<SchoolDataModel>) {
        if (schoolInfo.isNotEmpty()) {
            schoolView.showSchoolList(schoolInfo)
        } else {
            schoolView.showError("School Info not available now")
        }
    }

    /** Retrieves the schoolDetails by hitting the respective API using schoolService and also filters
     * the list by dbn to populate only the details for the selected school
     * **/
    override fun fetchSchoolDetailsByDBN(context: Context, dbn: String, overview: String) {
        disposable = schoolService.getSatScoreDataForSchool()
            .map { response ->
                response.filter { selectedSchool -> selectedSchool.dbn == dbn }
            }
            .map { filteredSchool ->
                val selectedSchool = filteredSchool.firstOrNull()
                if (selectedSchool == null) {
                    schoolView.showError(getString(context, R.string.no_school_details))
                    null
                } else {
                    SatScoreDataModel(
                        selectedSchool.school_name,
                        selectedSchool.num_of_sat_test_takers,
                        selectedSchool.sat_writing_avg_score,
                        selectedSchool.sat_math_avg_score,
                        selectedSchool.sat_critical_reading_avg_score,
                        overview
                    )
                }
            }
            .subscribe(
                { schoolDetails ->
                    //on successful response let this method handle calling the view
                    handleSchoolDetails(context, schoolDetails)
                },
                { error ->
                    // Error handling here
                    Log.e("SchoolListPresenter", "Error fetching school details: ${error.message}")
                }

            )
    }

    private fun handleSchoolDetails(context: Context, schoolDetails: SatScoreDataModel?) {
        if (schoolDetails != null) {
            schoolView.showSchoolDetailsScreen(schoolDetails)
        } else {
            schoolView.showError(getString(context, R.string.no_school_details))
        }
    }

    /** This clears the disposable, called from the onDestroy() from the view **/
    fun clearDisposable() {
        disposable.dispose()
    }
}