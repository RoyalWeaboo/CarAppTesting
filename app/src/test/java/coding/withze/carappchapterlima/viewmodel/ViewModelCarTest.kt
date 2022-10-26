package coding.withze.carappchapterlima.viewmodel

import coding.withze.carappchapterlima.model.ResponseDataCarItem
import coding.withze.carappchapterlima.network.RestfulApi
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class ViewModelCarTest {
    lateinit var servis: RestfulApi

    @Before
    fun setUp() {
        servis = mockk()
    }

    @Test
    fun getCarTest(): Unit = runBlocking {
//        mocking GIVEN
        val respAllCar = mockk<List<ResponseDataCarItem>>()

        every {
            runBlocking {
                servis.getCar()
            }
        }returns respAllCar

//        System Under Test (WHEN)
        val result = servis.getCar()

        verify {
            runBlocking { servis.getCar() }
        }
        assertEquals(result, respAllCar)

    }

//    @Test
//    fun testGetCar(){
//        val respAllCar = mockk<List<ResponseDataCarItem>>()
//        every {
//            servis.getCar()
//
//        }returns respAllCar
//
//        //        System Under Test (WHEN)
//        val result = servis.getCar()
//
//        verify {
//            servis.getCar()
//        }
//        assertEquals(result, respAllCar)
//    }

}