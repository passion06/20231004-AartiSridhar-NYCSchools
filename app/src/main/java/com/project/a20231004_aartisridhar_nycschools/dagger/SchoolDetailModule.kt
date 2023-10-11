package com.project.a20231004_aartisridhar_nycschools.dagger

import com.project.a20231004_aartisridhar_nycschools.listener.SchoolViewListener
import com.project.a20231004_aartisridhar_nycschools.presenter.SchoolDetailListener
import com.project.a20231004_aartisridhar_nycschools.view.SchoolDetailActivity
import dagger.Module
import dagger.Provides

@Module
class SchoolDetailModule(var view:SchoolDetailListener.ShowSchoolDetails) {

    @Provides
    fun provideSchoolDetailView():SchoolDetailListener.ShowSchoolDetails{
        return view
    }

}