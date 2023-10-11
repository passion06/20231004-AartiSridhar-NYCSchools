package com.project.a20231004_aartisridhar_nycschools.dagger

import com.project.a20231004_aartisridhar_nycschools.presenter.SchoolDetailListener
import dagger.Module
import dagger.Provides

@Module
class SchoolDetailModule(var view: SchoolDetailListener.ShowSchoolDetails) {

    //Provides SchoolDetailActivity instance
    @Provides
    fun provideSchoolDetailView(): SchoolDetailListener.ShowSchoolDetails {
        return view
    }

}