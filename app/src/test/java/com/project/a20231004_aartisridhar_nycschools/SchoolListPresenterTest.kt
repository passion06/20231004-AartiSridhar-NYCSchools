package com.project.a20231004_aartisridhar_nycschools

import com.project.a20231004_aartisridhar_nycschools.listener.SchoolViewListener
import com.project.a20231004_aartisridhar_nycschools.model.SchoolDataModel
import com.project.a20231004_aartisridhar_nycschools.model.SchoolService
import com.project.a20231004_aartisridhar_nycschools.presenter.SchoolListPresenter
import com.project.a20231004_aartisridhar_nycschools.view.SchoolListActivity
import io.reactivex.Observable
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.spy
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

class SchoolListPresenterTest: BaseTest() {

    @Mock
    var mockSchoolListView = mock(SchoolViewListener.View::class.java)
    @Mock
    var mockSchoolService = mock(SchoolViewListener.Service::class.java)

    private lateinit var presenter:SchoolListPresenter

    override fun setup(){
        presenter = SchoolListPresenter(mockSchoolListView,mockSchoolService)
    }

    @Test
    fun test_fetchSchoolList_success(){
        setup()
        val mockSchoolList = listOf(SchoolDataModel("dbn1","School1","http://school1.com","School1 Overview"),
            SchoolDataModel("dbn2","School2","https://example2.com","School2 Overview"))
        `when`(mockSchoolService.getSchoolList()).thenReturn(Observable.just(mockSchoolList))
        presenter.fetchSchoolList()
        verify(mockSchoolListView).showSchoolList(mockSchoolList)
    }
    //Similarly more tests can be written for fetchschoolList failure, fetchSATScore success and failure

}