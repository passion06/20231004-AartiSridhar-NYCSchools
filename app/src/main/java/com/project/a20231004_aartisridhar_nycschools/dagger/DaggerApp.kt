package com.project.a20231004_aartisridhar_nycschools.dagger

import android.app.Application

class DaggerApp: Application() {
    val daggerComponent = DaggerSchoolComponent.create()
}