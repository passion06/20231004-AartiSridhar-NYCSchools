package com.project.a20231004_aartisridhar_nycschools.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.a20231004_aartisridhar_nycschools.R
import com.project.a20231004_aartisridhar_nycschools.databinding.SchoolItemBinding
import com.project.a20231004_aartisridhar_nycschools.model.SchoolDataModel

class SchoolListActivityAdapter(private var schoolList:List<SchoolDataModel>): RecyclerView.Adapter<SchoolListActivityAdapter.SchoolViewHolder>() {
    private lateinit var schoolItemBinding:SchoolItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SchoolViewHolder {
        Log.d("Adapter","inside create method")
        Log.d("school list size","${schoolList.size}")
        schoolItemBinding = SchoolItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return SchoolViewHolder(schoolItemBinding)
    }

    override fun getItemCount(): Int {
        return schoolList.size
    }

    override fun onBindViewHolder(holder: SchoolViewHolder, position: Int) {
        Log.d("Iniside adapter","Bind view method of adapter")
        val schoolItem = schoolList[position]
        //val schoolItem = SchoolDataModel("xx","School Name","rt@gmail.com")
        holder.schoolName.text = schoolItem.school_name
        holder.website.text = schoolItem.website
    }

    inner class SchoolViewHolder(itemBinding: SchoolItemBinding):RecyclerView.ViewHolder(itemBinding.root){
        val schoolName = itemBinding.schoolName
        val website = itemBinding.website
    }

}