package com.project.a20231004_aartisridhar_nycschools.dagger

import com.project.a20231004_aartisridhar_nycschools.listener.SchoolViewListener
import com.project.a20231004_aartisridhar_nycschools.model.SchoolAPI
import com.project.a20231004_aartisridhar_nycschools.model.SchoolService
import com.project.a20231004_aartisridhar_nycschools.presenter.SchoolListPresenter
import com.project.a20231004_aartisridhar_nycschools.view.SchoolListActivity
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class SchoolModule {
    /** Created dependencies needed for Presenter to be instantiated **/

        @Provides
        fun provideSchoolListPresenter(
            schoolView: SchoolViewListener.View,
            schoolService: SchoolService
        ): SchoolListPresenter {
            return SchoolListPresenter(schoolView, schoolService)
        }

        @Provides
        fun provideSchoolView(): SchoolViewListener.View {
            return SchoolListActivity()
        }

        @Provides
        @Singleton
        fun provideSchoolService(schoolAPI: SchoolAPI): SchoolService {
            return SchoolService(schoolAPI)
        }

        @Provides
        @Singleton
        fun provideSchoolAPI(): SchoolAPI {
            return SchoolAPI.create()
        }

}