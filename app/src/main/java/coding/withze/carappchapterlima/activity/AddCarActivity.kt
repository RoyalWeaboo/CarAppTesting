package coding.withze.carappchapterlima.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coding.withze.carappchapterlima.databinding.ActivityAddCarBinding
import coding.withze.carappchapterlima.viewmodel.ViewModelCar

class AddCarActivity : AppCompatActivity() {
    lateinit var binding : ActivityAddCarBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddCarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAddCar.setOnClickListener {
            postCar()
        }
    }

    fun postCar(){
        var name = binding.name.text.toString()
        var category = binding.name.text.toString()
        var status = binding.status.text.toString()
        var price = binding.price.text.toString()
        var image = binding.image.text.toString()

        val viewModel = ViewModelProvider(this).get(ViewModelCar::class.java)
        viewModel.postApiCar(name, category, status.toBoolean(), price.toInt(),image)
        viewModel.postLiveDataCar().observe(this, Observer {
            if (it != null){
                Toast.makeText(this, "Add Data Successfull", Toast.LENGTH_SHORT).show()
                Log.d("addcar", it.toString())
            }
        })
    }
}