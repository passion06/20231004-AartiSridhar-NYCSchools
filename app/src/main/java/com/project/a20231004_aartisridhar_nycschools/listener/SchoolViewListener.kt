package com.project.a20231004_aartisridhar_nycschools.listener

import com.project.a20231004_aartisridhar_nycschools.model.SchoolDataModel

interface SchoolViewListener {
    interface View{
        fun showSchoolList(schoolList:List<SchoolDataModel>)
        fun showError(message:String)
    }
    interface Presenter{
        fun fetchSchoolList()
    }
}