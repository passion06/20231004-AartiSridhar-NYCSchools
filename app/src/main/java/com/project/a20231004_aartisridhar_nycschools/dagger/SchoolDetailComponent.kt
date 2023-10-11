package com.project.a20231004_aartisridhar_nycschools.dagger

import com.project.a20231004_aartisridhar_nycschools.presenter.SchoolDetailPresenter
import com.project.a20231004_aartisridhar_nycschools.view.SchoolDetailActivity
import dagger.Component

@Component(modules = [SchoolDetailModule::class])
interface SchoolDetailComponent {
    fun inject(schoolDetailActivity: SchoolDetailActivity)
    fun inject(schoolDetailPresenter: SchoolDetailPresenter)
}