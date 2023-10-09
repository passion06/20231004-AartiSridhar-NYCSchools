package com.project.a20231004_aartisridhar_nycschools.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Orientation
import com.project.a20231004_aartisridhar_nycschools.R
import com.project.a20231004_aartisridhar_nycschools.dagger.DaggerApp
import com.project.a20231004_aartisridhar_nycschools.databinding.SchoolListBinding
import com.project.a20231004_aartisridhar_nycschools.listener.SchoolViewListener
import com.project.a20231004_aartisridhar_nycschools.model.SchoolAPI
import com.project.a20231004_aartisridhar_nycschools.model.SchoolDataModel
import com.project.a20231004_aartisridhar_nycschools.model.SchoolService
import com.project.a20231004_aartisridhar_nycschools.presenter.SchoolListPresenter
import com.project.a20231004_aartisridhar_nycschools.view.adapter.SchoolListActivityAdapter
import javax.inject.Inject

class SchoolListActivity: AppCompatActivity(), SchoolViewListener.View {

    @Inject
    lateinit var schoolListPresenter:SchoolListPresenter
    //private lateinit var schoolListBinding:SchoolListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
//        val schoolComponent = (applicationContext as DaggerApp).daggerComponent
//        schoolComponent.inject(this)
        super.onCreate(savedInstanceState)
       // schoolListBinding = SchoolListBinding.inflate(layoutInflater)
        setContentView(R.layout.school_list)
        val schoolService = SchoolService(SchoolAPI.create())
        schoolListPresenter = SchoolListPresenter(this,schoolService)
        schoolListPresenter.fetchSchoolList()
       // configureSATScoresView()
    }

    fun configureSATScoresView(){
        Log.d("###Started","Inside SAT Scores Configure View")
        val schoolAPI = SchoolAPI.create()
        val schoolService = SchoolService(schoolAPI)
        val satDisposable = schoolService.getSatScoreDataForSchool()
            .subscribe(
                { response ->
                    val satResponse = response.toString()
                    // School data is available
                    Log.d("API Response",response.toString())
                },
                { error ->
                    // Handle the error here
                    Log.e("###Error","${error.message}")
                }
            )
    }
    override fun showSchoolList(schoolList: List<SchoolDataModel>) {
        Log.d("size","${schoolList.size}")
        Log.d("##Activity method","Show School List")
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_schools)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val schoolAdapter = SchoolListActivityAdapter(schoolList)
        recyclerView.adapter = schoolAdapter
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                baseContext,
                DividerItemDecoration.VERTICAL
            )
        )
        schoolAdapter.notifyDataSetChanged()
    }

    override fun showError(message: String) {
        TODO("Not yet implemented")
    }

    override fun onDestroy(){
        super.onDestroy()
       schoolListPresenter.clearDisposable()
    }

}