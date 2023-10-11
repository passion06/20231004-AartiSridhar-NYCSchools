package com.project.a20231004_aartisridhar_nycschools.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.a20231004_aartisridhar_nycschools.R
import com.project.a20231004_aartisridhar_nycschools.dagger.DaggerSchoolComponent
import com.project.a20231004_aartisridhar_nycschools.dagger.SchoolModule
import com.project.a20231004_aartisridhar_nycschools.databinding.SchoolListBinding
import com.project.a20231004_aartisridhar_nycschools.listener.SchoolViewListener
import com.project.a20231004_aartisridhar_nycschools.model.SatScoreDataModel
import com.project.a20231004_aartisridhar_nycschools.model.SchoolDataModel
import com.project.a20231004_aartisridhar_nycschools.presenter.SchoolListPresenter
import com.project.a20231004_aartisridhar_nycschools.reusableUI.AlertDialog
import com.project.a20231004_aartisridhar_nycschools.view.adapter.SchoolListActivityAdapter
import javax.inject.Inject

class SchoolListActivity : AppCompatActivity(), SchoolViewListener.View {

    @Inject
    lateinit var schoolListPresenter: SchoolListPresenter
    private lateinit var schoolListBinding: SchoolListBinding
    private val alertDialog: AlertDialog by lazy{
        AlertDialog(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        schoolListBinding = SchoolListBinding.inflate(layoutInflater)
        setContentView(schoolListBinding.root)
        //TODO Ideally the below dagger code should go in the DaggerApp class in the dagger package
        DaggerSchoolComponent.builder()
            .schoolModule(SchoolModule(this))
            .build()
            .inject(this)
        configureView()
    }

    //TODO Hardcoding some strings for now, but ideally should come from string resource file
    private fun configureView() {
        val toolbar = schoolListBinding.topAppBar
        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.app_name)
        schoolListPresenter.fetchSchoolList()
    }

    //Callback implemented when a school row is selected from the adapter
    private val schoolItemClickListener = object : SchoolListActivityAdapter.ItemClickListener {
        override fun onSchoolClick(dbn: String, overview: String?) {
            //Handle case where no overview is available or is null for a selected school
            if (overview.isNullOrBlank()) {
                schoolListPresenter.fetchSchoolDetailsByDBN(
                    this@SchoolListActivity,
                    dbn,
                    "School Overview not available"
                )
            } else {
                schoolListPresenter.fetchSchoolDetailsByDBN(this@SchoolListActivity, dbn, overview)
            }
        }
        //Handle error case- if dbn field is null
        override fun onError(message: String) {
            alertDialog.onError(message)
        }
    }

    //Handles rendering schoolList in the adapter
    override fun showSchoolList(schoolList: List<SchoolDataModel>) {
        val schoolListRecyclerView = schoolListBinding.recyclerViewSchools
        val schoolLayoutManager = LinearLayoutManager(this)
        schoolListRecyclerView.apply {
            layoutManager = schoolLayoutManager
            val schoolAdapter =
                SchoolListActivityAdapter(schoolList, schoolItemClickListener, context)
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
        alertDialog.onError(message)
    }

    //Passes intent with the school details to SchoolDetailsActivity
    override fun showSchoolDetailsScreen(schoolDetails: SatScoreDataModel) {
        val intent = Intent(this, SchoolDetailActivity::class.java)
        intent.putExtra("schoolDetails", schoolDetails)
        startActivity(intent)
    }

    //handles clearing disposables onDestroy of activity
    override fun onDestroy() {
        super.onDestroy()
        schoolListPresenter.clearDisposable()
    }

}