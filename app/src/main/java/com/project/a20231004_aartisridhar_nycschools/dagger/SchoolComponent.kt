package com.project.a20231004_aartisridhar_nycschools.dagger

import com.project.a20231004_aartisridhar_nycschools.view.SchoolListActivity
import dagger.Component

@Component
interface SchoolComponent {
    fun inject(activity: SchoolListActivity)
}