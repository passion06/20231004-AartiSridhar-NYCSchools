package com.project.a20231004_aartisridhar_nycschools.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SatScoreDataModel (
    val dbn: String,
    val school_name: String,
    val num_of_sat_test_takers: String,
    val sat_critical_reading_avg_score: String,
    val sat_math_avg_score: String,
    val sat_writing_avg_score: String): Parcelable