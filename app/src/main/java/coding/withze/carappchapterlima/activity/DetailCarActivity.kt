package coding.withze.carappchapterlima.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coding.withze.carappchapterlima.R
import coding.withze.carappchapterlima.databinding.ActivityDetailCarBinding
import coding.withze.carappchapterlima.model.ResponseDataCarItem
import coding.withze.carappchapterlima.viewmodel.ViewModelCar

class DetailCarActivity : AppCompatActivity() {

    lateinit var binding : ActivityDetailCarBinding
    lateinit var dataCar : ResponseDataCarItem
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailCarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var bun = intent.extras
        var idd = bun!!.getInt("okedetail")
        Log.d("idd", idd.toString())
        getDetailCar(idd)

//        var data = intent.getStringExtra("det")
//        Log.d("oke", data.toString())

//        val bundle = intent.extras
//        var getDetail = bundle!!.getString("detail","oke") as ResponseDataCarItem
//        Log.d("idcar", getDetail.toString())
//
//        binding.nameCarDetail.text = getDetail!!.name
//        binding.categoryCarDetail.text = getDetail.category
//        binding.priceCarDetail.text = getDetail.price.toString()
//        binding.statusCarDetail.text = getDetail.status.toString()



    }

    fun getDetailCar(id : Int){
        val viewModel = ViewModelProvider(this).get(ViewModelCar::class.java)
        viewModel.callDetailApicar(id)
        viewModel.getliveDataCar().observe(this, Observer {

        })

    }


}