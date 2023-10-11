package com.project.a20231004_aartisridhar_nycschools.dagger

import com.project.a20231004_aartisridhar_nycschools.presenter.SchoolDetailPresenter
import com.project.a20231004_aartisridhar_nycschools.presenter.SchoolListPresenter
import com.project.a20231004_aartisridhar_nycschools.view.SchoolDetailActivity
import com.project.a20231004_aartisridhar_nycschools.view.SchoolListActivity
import dagger.Component
import javax.inject.Singleton

@Component(modules = [SchoolModule::class])
@Singleton
interface SchoolComponent {
    fun inject(schoolListActivity: SchoolListActivity)
    fun inject(schoolListPresenter: SchoolListPresenter)
    fun inject(schoolDetailPresenter: SchoolDetailPresenter)
}