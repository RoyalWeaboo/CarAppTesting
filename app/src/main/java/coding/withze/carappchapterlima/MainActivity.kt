package coding.withze.carappchapterlima

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coding.withze.carappchapterlima.activity.AddCarActivity
import coding.withze.carappchapterlima.activity.DetailCarActivity
import coding.withze.carappchapterlima.adapter.CarAdapter
import coding.withze.carappchapterlima.databinding.ActivityMainBinding
import coding.withze.carappchapterlima.model.ResponseDataCarItem
import coding.withze.carappchapterlima.viewmodel.ViewModelCar


class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    lateinit var carAdapter : CarAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setVmtoAdapter()

        binding.addButton.setOnClickListener {
            startActivity(Intent(this, AddCarActivity::class.java))
        }
    }

    fun setVmtoAdapter(){
            val viewModel =ViewModelProvider(this).get(ViewModelCar::class.java)
            viewModel.callApiCar()
            viewModel.getliveDataCar().observe(this, Observer {
                carAdapter = CarAdapter(it)
                if ( it != null){
                    binding.rvCar.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                    binding.rvCar.adapter = CarAdapter(it)

                }

                carAdapter.onDetail ={
                    var getData = it
                    var inten = Intent(this, DetailCarActivity::class.java)
                    inten.putExtra("det",getData)
                    startActivity(inten)
                }
            })
    }

    fun oke(){
        carAdapter.onDetail ={
            var getData = it
            var inten = Intent(this, DetailCarActivity::class.java)
            inten.putExtra("det",getData)
            startActivity(inten)
        }

    }





}