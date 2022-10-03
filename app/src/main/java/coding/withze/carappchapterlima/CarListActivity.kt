package coding.withze.carappchapterlima

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import coding.withze.carappchapterlima.adapter.CarAdapter
import coding.withze.carappchapterlima.databinding.ActivityCarListBinding
import coding.withze.carappchapterlima.model.ResponseDataCarItem
import coding.withze.carappchapterlima.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CarListActivity : AppCompatActivity() {

    lateinit var binding : ActivityCarListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCarListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getDataCar()

    }

    fun getDataCar(){
        RetrofitClient.instance.getAllCar()
            .enqueue(object : Callback<List<ResponseDataCarItem>>{
                override fun onResponse(
                    call: Call<List<ResponseDataCarItem>>,
                    response: Response<List<ResponseDataCarItem>>
                ) {
                    if (response.isSuccessful){
                        var dataCar = response.body()
                        binding.rvCar.layoutManager = LinearLayoutManager(this@CarListActivity, LinearLayoutManager.VERTICAL, false)
                        binding.rvCar.adapter = CarAdapter(dataCar!!)
                    }else{
                        Toast.makeText(this@CarListActivity, "Failed to Load Data", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<List<ResponseDataCarItem>>, t: Throwable) {
                    Toast.makeText(this@CarListActivity, "Something Wrong", Toast.LENGTH_SHORT).show()
                }

            })
    }
}