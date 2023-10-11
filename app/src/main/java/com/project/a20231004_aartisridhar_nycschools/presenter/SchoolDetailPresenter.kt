package com.project.a20231004_aartisridhar_nycschools.presenter

import android.os.Bundle
import com.project.a20231004_aartisridhar_nycschools.model.SatScoreDataModel
import com.project.a20231004_aartisridhar_nycschools.view.SchoolDetailActivity
import javax.inject.Inject

class SchoolDetailPresenter @Inject constructor(var view:SchoolDetailListener.ShowSchoolDetails):SchoolDetailListener.GetSchoolDetails{
    override fun processIntent(bundle:Bundle?){
        val satScoreModel : SatScoreDataModel
        if (bundle != null) {
            satScoreModel = bundle.getParcelable("schoolDetails")!!
            view.showSchoolDetails(satScoreModel)
        } else {
            throw Throwable("Empty bundle received")
        }
    }
}

interface SchoolDetailListener {
    interface GetSchoolDetails {
        fun processIntent(bundle: Bundle?)
    }

    interface ShowSchoolDetails {
        fun showSchoolDetails(schoolDetails: SatScoreDataModel?)
    }
}
