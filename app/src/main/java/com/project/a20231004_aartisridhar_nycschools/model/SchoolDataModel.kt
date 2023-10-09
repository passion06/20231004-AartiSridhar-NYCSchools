package com.project.a20231004_aartisridhar_nycschools.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class SchoolDataModel(
    val dbn: String,
    val school_name: String,
    val website:String
    // Add other properties as needed
) : Parcelable