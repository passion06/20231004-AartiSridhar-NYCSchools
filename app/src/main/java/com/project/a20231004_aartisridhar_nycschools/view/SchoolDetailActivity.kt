package com.project.a20231004_aartisridhar_nycschools.view

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.project.a20231004_aartisridhar_nycschools.R
import com.project.a20231004_aartisridhar_nycschools.dagger.DaggerSchoolDetailComponent
import com.project.a20231004_aartisridhar_nycschools.dagger.SchoolDetailModule
import com.project.a20231004_aartisridhar_nycschools.databinding.SchoolDetailBinding
import com.project.a20231004_aartisridhar_nycschools.model.SatScoreDataModel
import com.project.a20231004_aartisridhar_nycschools.presenter.SchoolDetailListener
import com.project.a20231004_aartisridhar_nycschools.presenter.SchoolDetailPresenter
import javax.inject.Inject

class SchoolDetailActivity @Inject constructor(): AppCompatActivity(), SchoolDetailListener.ShowSchoolDetails {

    @Inject
    lateinit var schoolDetailPresenter: SchoolDetailPresenter

    private lateinit var schoolDetailBinding:SchoolDetailBinding
    private lateinit var schoolNameText:TextView
    private lateinit var satTakers:TextView
    private lateinit var satMathScore:TextView
    private lateinit var satReadingScore:TextView
    private lateinit var satWritingScore:TextView
    private lateinit var overviewText:TextView

    override fun onCreate(savedInstance: Bundle?){
        super.onCreate(savedInstance)
        schoolDetailBinding = SchoolDetailBinding.inflate(layoutInflater)
        setContentView(schoolDetailBinding.root)
        configureView()
        //Ideally the below dagger code should go in the DaggerApp class in the dagger package
        DaggerSchoolDetailComponent.builder()
            .schoolDetailModule(SchoolDetailModule(this))
            .build()
            .inject(this)
        val bundle = intent.extras
        schoolDetailPresenter.processIntent(bundle)
    }

    private fun configureView(){
        val toolbar = schoolDetailBinding.topAppBar
        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.detail_screen_name)
        schoolDetailBinding.apply {
            schoolNameText = schoolName
            satTakers = SATTakers
            satMathScore = SATMathScore
            satReadingScore = SATReadingScore
            satWritingScore = SATWritingScore
            overviewText = overview
        }
    }

    override fun showSchoolDetails(schoolDetails: SatScoreDataModel?) {
        schoolDetails?.apply {
            schoolNameText.text = school_name ?:"No data available"
            overviewText.text = overview
            satTakers.text = concatLabelValue(getString(R.string.sat_takers_label),num_of_sat_test_takers)
            satMathScore.text = concatLabelValue(getString(R.string.math_average),sat_math_avg_score)
            satReadingScore.text = concatLabelValue(getString(R.string.critical_reading_average),sat_critical_reading_avg_score)
            satWritingScore.text = concatLabelValue(getString(R.string.writing_average),sat_writing_avg_score)
        }
    }

    private fun concatLabelValue(label:String, value:String?) = if (!value.isNullOrBlank()) "$label $value" else "$label No data available"
}