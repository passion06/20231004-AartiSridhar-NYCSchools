package com.project.a20231004_aartisridhar_nycschools.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project.a20231004_aartisridhar_nycschools.dagger.DaggerApp
import com.project.a20231004_aartisridhar_nycschools.databinding.SchoolListBinding
import com.project.a20231004_aartisridhar_nycschools.listener.SchoolViewListener
import com.project.a20231004_aartisridhar_nycschools.model.SatScoreDataModel
import com.project.a20231004_aartisridhar_nycschools.model.SchoolAPI
import com.project.a20231004_aartisridhar_nycschools.model.SchoolDataModel
import com.project.a20231004_aartisridhar_nycschools.model.SchoolService
import com.project.a20231004_aartisridhar_nycschools.presenter.SchoolListPresenter
import com.project.a20231004_aartisridhar_nycschools.view.adapter.SchoolListActivityAdapter
import javax.inject.Inject

class SchoolListActivity: AppCompatActivity(), SchoolViewListener.View {

    @Inject
    lateinit var schoolListPresenter:SchoolListPresenter
    private lateinit var schoolListBinding:SchoolListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
//        val schoolComponent = (applicationContext as DaggerApp).daggerComponent
//        schoolComponent.inject(this)
        super.onCreate(savedInstanceState)
        schoolListBinding = SchoolListBinding.inflate(layoutInflater)
        setContentView(schoolListBinding.root)
        val schoolService = SchoolService(SchoolAPI.create())
        schoolListPresenter = SchoolListPresenter(this,schoolService)
        schoolListPresenter.fetchSchoolList()
    }

    private val schoolItemClickListener = object: SchoolListActivityAdapter.ItemClickListener {
        override fun onSchoolClick(dbn: String) {
            schoolListPresenter.fetchSchoolDetailsByDBN(dbn)
        }
    }
    override fun showSchoolList(schoolList: List<SchoolDataModel>) {
        Log.d("size","${schoolList.size}")
        Log.d("##Activity method","Show School List")
        val schoolListRecyclerView = schoolListBinding.recyclerViewSchools
        val schoolLayoutManager = LinearLayoutManager(this)
        schoolListRecyclerView.apply {
            layoutManager = schoolLayoutManager
            val schoolAdapter = SchoolListActivityAdapter(schoolList, schoolItemClickListener)
            adapter = schoolAdapter
            addItemDecoration(
                DividerItemDecoration(
                    baseContext,
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }

    override fun showError(message: String) {
        TODO("Not yet implemented")
    }

    override fun showSchoolDetailsScreen(schoolDetails: SatScoreDataModel) {
        val intent = Intent(this,SchoolDetailActivity::class.java)
        intent.putExtra("schoolDetails",schoolDetails)
        startActivity(intent)
    }

    override fun onDestroy(){
        super.onDestroy()
       schoolListPresenter.clearDisposable()
    }

}