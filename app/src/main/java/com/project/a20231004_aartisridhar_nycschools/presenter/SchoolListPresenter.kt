package com.project.a20231004_aartisridhar_nycschools.presenter

import com.project.a20231004_aartisridhar_nycschools.model.SchoolService
import javax.inject.Inject

class SchoolListPresenter @Inject constructor(private val schoolService: SchoolService) {


    interface SchoolListViewListener{
        fun showSchoolList()
        fun loadSchoolList()
    }
}