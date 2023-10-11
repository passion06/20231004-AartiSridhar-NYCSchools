package com.project.a20231004_aartisridhar_nycschools

import android.os.Bundle
import com.project.a20231004_aartisridhar_nycschools.model.SatScoreDataModel
import com.project.a20231004_aartisridhar_nycschools.presenter.SchoolDetailListener
import com.project.a20231004_aartisridhar_nycschools.presenter.SchoolDetailPresenter
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SchoolDetailPresenterTest:BaseTest() {
    @Mock
    var mockSchoolListView = Mockito.mock(SchoolDetailListener.ShowSchoolDetails::class.java)
    @Mock
    val bundle = Mockito.mock(Bundle::class.java)

    private lateinit var presenter: SchoolDetailPresenter
    private lateinit var mockSatScoreModel:SatScoreDataModel

    override fun setup(){
        super.setup()
        presenter = SchoolDetailPresenter(mockSchoolListView)
        mockSatScoreModel = SatScoreDataModel("school1","100","200","300","400","Overview")
    }

    @Test
    fun test_ProcessIntent() {
        setup()
        `when`(bundle.getParcelable<SatScoreDataModel>("schoolDetails")).thenReturn(mockSatScoreModel)
        presenter.processIntent(bundle)
        verify(mockSchoolListView).showSchoolDetails(mockSatScoreModel)
    }
}