package com.project.a20231004_aartisridhar_nycschools.dagger

import com.project.a20231004_aartisridhar_nycschools.listener.SchoolViewListener
import com.project.a20231004_aartisridhar_nycschools.model.SchoolAPI
import com.project.a20231004_aartisridhar_nycschools.model.SchoolService
import dagger.Module
import dagger.Provides

@Module
class SchoolModule(private val view: SchoolViewListener.View) {

    //Instantiates SchoolService with SchoolAPI dependency
    @Provides
    fun provideSchoolService(schoolAPI: SchoolAPI): SchoolViewListener.Service {
        return SchoolService(schoolAPI)
    }

    @Provides
    fun provideSchoolAPI(): SchoolAPI {
        return SchoolAPI.create()
    }

    //Provides the SchoolListActivity
    @Provides
    fun provideSchoolView(): SchoolViewListener.View {
        return view
    }

}