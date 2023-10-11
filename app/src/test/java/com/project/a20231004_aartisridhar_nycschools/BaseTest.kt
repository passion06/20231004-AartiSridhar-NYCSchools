package com.project.a20231004_aartisridhar_nycschools

import org.junit.Before
import org.mockito.MockitoAnnotations


open class BaseTest {
    @Before
    open fun setup(){
        MockitoAnnotations.initMocks(this)
    }
}