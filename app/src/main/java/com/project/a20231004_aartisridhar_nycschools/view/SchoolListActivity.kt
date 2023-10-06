package com.project.a20231004_aartisridhar_nycschools.view

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import com.project.a20231004_aartisridhar_nycschools.dagger.DaggerApp
import com.project.a20231004_aartisridhar_nycschools.dagger.SchoolComponent
import com.project.a20231004_aartisridhar_nycschools.databinding.SchoolListBinding
import com.project.a20231004_aartisridhar_nycschools.model.SchoolAPI
import com.project.a20231004_aartisridhar_nycschools.model.SchoolService

class SchoolListActivity: ComponentActivity() {
    lateinit var schoolComponent: SchoolComponent
    override fun onCreate(savedInstanceState: Bundle?) {
        schoolComponent = (applicationContext as DaggerApp).daggerComponent
        schoolComponent.inject(this)
        super.onCreate(savedInstanceState)
        val binding = SchoolListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configureView()
        configureSATScoresView()
    }

    fun configureView(){
        Log.d("###Started","Inside Configure View")
        val schoolAPI = SchoolAPI.create()
        val schoolService = SchoolService(schoolAPI)

        val schoolDisposable = schoolService.getSchoolList()
            .subscribe(
            { response ->
                val weather = response.toString()
                // School data is available
                Log.d("API Response",response.toString())

                // Handle the response here
            },
            { error ->
                // Handle the error here
                Log.e("###Error","${error.message}")
            }
        )
    }

    fun configureSATScoresView(){
        Log.d("###Started","Inside SAT Scores Configure View")
        val schoolAPI = SchoolAPI.create()
        val schoolService = SchoolService(schoolAPI)
        val satDisposable = schoolService.getSatScoreDataForSchool()
            .subscribe(
                { response ->
                    val weather = response.toString()
                    // School data is available
                    Log.d("API Response",response.toString())
                },
                { error ->
                    // Handle the error here
                    Log.e("###Error","${error.message}")
                }
            )
    }
}