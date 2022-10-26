package coding.withze.carappchapterlima.network

import coding.withze.carappchapterlima.model.PostCarResponse
import coding.withze.carappchapterlima.model.PostDataCar
import coding.withze.carappchapterlima.model.PutCarResponse
import coding.withze.carappchapterlima.model.ResponseDataCarItem
import retrofit2.Call
import retrofit2.http.*

interface RestfulApi {

    @GET("admin/car")
    fun getAllCar():Call<List<ResponseDataCarItem>>

    @GET("admin/car")
    fun getCar(): List<ResponseDataCarItem>

    @POST("admin/car")
    fun postCar(@Body car : PostDataCar) : Call<PostCarResponse>

    @PUT("admin/car/{id}")
    fun updateCar(@Path("id") id : Int,@Body request : PostDataCar ): Call<List<PutCarResponse>>

    @GET("admin/car/{id}")
    fun getDetailCar(@Path("id") id : Int): Call<List<ResponseDataCarItem>>

    @DELETE("admin/car/{id}")
    fun deleteCar(@Path("id") id : Int) :Call<Int>
}