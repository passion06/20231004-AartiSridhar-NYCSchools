package com.project.a20231004_aartisridhar_nycschools.view

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.project.a20231004_aartisridhar_nycschools.databinding.SchoolDetailBinding
import com.project.a20231004_aartisridhar_nycschools.model.SatScoreDataModel
import com.project.a20231004_aartisridhar_nycschools.presenter.SchoolDetailPresenter
import com.project.a20231004_aartisridhar_nycschools.presenter.ShowSchoolDetails

class SchoolDetailActivity: AppCompatActivity(), ShowSchoolDetails {

    private lateinit var schoolDetailBinding:SchoolDetailBinding
    private lateinit var schoolDetailPresenter:SchoolDetailPresenter
    private lateinit var schoolNameText:TextView
    private lateinit var satTakers:TextView
    private lateinit var satMathScore:TextView
    private lateinit var satReadingScore:TextView
    private lateinit var satWritingScore:TextView

    override fun onCreate(savedInstance: Bundle?){
        super.onCreate(savedInstance)
        schoolDetailBinding = SchoolDetailBinding.inflate(layoutInflater)
        setContentView(schoolDetailBinding.root)
        configureView()
        val bundle = intent.extras
        schoolDetailPresenter = SchoolDetailPresenter(this)
        schoolDetailPresenter.processIntent(bundle)
    }

    private fun configureView(){
        schoolDetailBinding.apply {
            schoolNameText = schoolName
            satTakers = SATTakers
            satMathScore = SATMathScore
            satReadingScore = SATReadingScore
            satWritingScore = SATWritingScore
        }
    }

    override fun showSchoolDetails(schoolDetails: SatScoreDataModel?) {
        schoolDetails?.apply {
            schoolNameText.text = school_name ?:"No data available"
            satTakers.text = num_of_sat_test_takers ?:"No data available"
            satMathScore.text = sat_math_avg_score ?:"No data available"
            satReadingScore.text = sat_critical_reading_avg_score ?:"No data available"
            satWritingScore.text = sat_writing_avg_score ?:"No data available"
        }

    }
}