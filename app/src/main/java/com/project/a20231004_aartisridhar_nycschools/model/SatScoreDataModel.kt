package com.project.a20231004_aartisridhar_nycschools.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SatScoreDataModel(
    val dbn: String,
    val school_name: String,
    val num_of_sat_test_takers: String,
    val sat_critical_reading_avg_score: String,
    val sat_math_avg_score: String,
    val sat_writing_avg_score: String,
    val overview: String
) : Parcelable {
    constructor(
        school_name: String,
        num_of_sat_test_takers: String,
        sat_writing_avg_score: String,
        sat_math_avg_score: String,
        sat_critical_reading_avg_score: String,
        overview: String
    )
            : this(
        "",
        school_name,
        num_of_sat_test_takers,
        sat_critical_reading_avg_score,
        sat_writing_avg_score,
        sat_math_avg_score,
        overview
    )
}