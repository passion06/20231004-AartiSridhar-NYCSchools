package com.project.a20231004_aartisridhar_nycschools.presenter

import android.os.Bundle
import com.project.a20231004_aartisridhar_nycschools.model.SatScoreDataModel
import com.project.a20231004_aartisridhar_nycschools.view.SchoolDetailActivity

class SchoolDetailPresenter(var view:SchoolDetailActivity):GetSchoolDetails{
    override fun processIntent(bundle:Bundle?){
        var satScoreModel : SatScoreDataModel
        if(bundle!=null){
            satScoreModel = bundle.getParcelable("schoolDetails")!!
            view.showSchoolDetails(satScoreModel)
        } else {
            throw Throwable("Empty bundle received")
        }
    }
}

interface GetSchoolDetails {
    fun processIntent(bundle: Bundle?)
}
interface ShowSchoolDetails {
    fun showSchoolDetails(schoolDetails:SatScoreDataModel?)
}
