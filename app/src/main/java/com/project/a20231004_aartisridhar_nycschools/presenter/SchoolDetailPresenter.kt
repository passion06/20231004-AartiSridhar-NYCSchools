package com.project.a20231004_aartisridhar_nycschools.presenter

import android.os.Bundle
import com.project.a20231004_aartisridhar_nycschools.model.SatScoreDataModel
import javax.inject.Inject

class SchoolDetailPresenter @Inject constructor(var view: SchoolDetailListener.ShowSchoolDetails) :
    SchoolDetailListener.GetSchoolDetails {

    /** Processes the provided bundle to extract school details and render them through view **/
    override fun processIntent(bundle: Bundle?) {
        val satScoreModel: SatScoreDataModel
        if (bundle != null) {
            satScoreModel = bundle.getParcelable("schoolDetails")!!
            view.showSchoolDetails(satScoreModel)
        } else {
            //if bundle is null
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
