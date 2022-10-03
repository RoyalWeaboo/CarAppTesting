package coding.withze.carappchapterlima.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import coding.withze.carappchapterlima.model.PostCarResponse
import coding.withze.carappchapterlima.model.PostDataCar
import coding.withze.carappchapterlima.model.PutCarResponse
import coding.withze.carappchapterlima.model.ResponseDataCarItem
import coding.withze.carappchapterlima.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class ViewModelCar : ViewModel(){

    lateinit var liveDataCar: MutableLiveData<List<ResponseDataCarItem>>
    lateinit var addLiveDataCar : MutableLiveData<PostCarResponse>
    lateinit var updLDcar : MutableLiveData<List<PutCarResponse>>
    lateinit var delCar : MutableLiveData<Int>

    init {
        liveDataCar = MutableLiveData()
        addLiveDataCar = MutableLiveData()
        updLDcar = MutableLiveData()
        delCar = MutableLiveData()
    }

    fun getliveDataCar() :MutableLiveData<List<ResponseDataCarItem>>{
        return  liveDataCar
    }
    fun postLiveDataCar() : MutableLiveData<PostCarResponse>{
        return addLiveDataCar
    }
    fun updLiveDataCar():MutableLiveData<List<PutCarResponse>>{
        return updLDcar
    }

    fun delLiveDataCar() : MutableLiveData<Int>
    {
        return delCar
    }
    fun callApiCar(){
        RetrofitClient.instance.getAllCar()
            .enqueue(object :  Callback<List<ResponseDataCarItem>>{
                override fun onResponse(
                    call: Call<List<ResponseDataCarItem>>,
                    response: Response<List<ResponseDataCarItem>>
                ) {
                    if (response.isSuccessful){
                        liveDataCar.postValue(response.body())
                    }else{
                        liveDataCar.postValue(null)
                    }
                }

                override fun onFailure(call: Call<List<ResponseDataCarItem>>, t: Throwable) {
                    liveDataCar.postValue(null)
                }

            })
    }

    fun callDetailApicar(id : Int){
        RetrofitClient.instance.getDetailCar(id)
            .enqueue(object : Callback<List<ResponseDataCarItem>>{
                override fun onResponse(
                    call: Call<List<ResponseDataCarItem>>,
                    response: Response<List<ResponseDataCarItem>>
                ) {
                    if (response.isSuccessful){
                        liveDataCar.postValue(response.body())
                    }else{
                        liveDataCar.postValue(null)
                    }
                }

                override fun onFailure(call: Call<List<ResponseDataCarItem>>, t: Throwable) {
                    liveDataCar.postValue(null)
                }

            })
    }

    fun postApiCar(name : String, category : String , status : Boolean, price : Int, image : String){
        RetrofitClient.instance.postCar(PostDataCar(name,category,status, price, image ))
            .enqueue(object : Callback<PostCarResponse>{
                override fun onResponse(
                    call: Call<PostCarResponse>,
                    response: Response<PostCarResponse>
                ) {
                    if (response.isSuccessful){
                        addLiveDataCar.postValue(response.body())
                    }else{
                        addLiveDataCar.postValue(null)
                    }
                }

                override fun onFailure(call: Call<PostCarResponse>, t: Throwable) {
                    addLiveDataCar.postValue(null)
                }

            })
    }

    fun updateApiCar(id : Int, name : String, category : String , status : Boolean, price : Int, image : String){
        RetrofitClient.instance.updateCar(id, PostDataCar(name,category,status, price,image ))
            .enqueue(object : Callback<List<PutCarResponse>>{
                override fun onResponse(
                    call: Call<List<PutCarResponse>>,
                    response: Response<List<PutCarResponse>>
                ) {
                    if (response.isSuccessful){
                        updLDcar.postValue(response.body())
                    }else{
                        updLDcar.postValue(null)
                    }
                }

                override fun onFailure(call: Call<List<PutCarResponse>>, t: Throwable) {
                    updLDcar.postValue(null)
                }

            })
    }

    fun deleteApiCar(id : Int){
        RetrofitClient.instance.deleteCar(id)
            .enqueue(object : Callback<Int>{
                override fun onResponse(call: Call<Int>, response: Response<Int>) {
                    if (response.isSuccessful){
                        delCar.postValue(response.body())
                    }else{
                        delCar.postValue(null)
                    }
                }

                override fun onFailure(call: Call<Int>, t: Throwable) {
                    delCar.postValue(null)
                }

            })
    }
}