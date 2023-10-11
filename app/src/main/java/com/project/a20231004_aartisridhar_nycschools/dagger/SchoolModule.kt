package com.project.a20231004_aartisridhar_nycschools.dagger

import com.project.a20231004_aartisridhar_nycschools.listener.SchoolViewListener
import com.project.a20231004_aartisridhar_nycschools.model.SchoolAPI
import com.project.a20231004_aartisridhar_nycschools.model.SchoolService
import com.project.a20231004_aartisridhar_nycschools.presenter.SchoolDetailListener
import com.project.a20231004_aartisridhar_nycschools.view.SchoolListActivity
import dagger.Module
import dagger.Provides

@Module
class SchoolModule(private val view: SchoolViewListener.View) {
        @Provides
        fun provideSchoolService(schoolAPI: SchoolAPI): SchoolViewListener.Service {
            return SchoolService(schoolAPI)
        }

        @Provides
        fun provideSchoolAPI(): SchoolAPI {
            return SchoolAPI.create()
        }

        @Provides
        fun provideSchoolView(): SchoolViewListener.View{
            return view
        }

}