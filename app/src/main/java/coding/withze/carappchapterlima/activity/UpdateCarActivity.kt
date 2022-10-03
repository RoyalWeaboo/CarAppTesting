package coding.withze.carappchapterlima.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coding.withze.carappchapterlima.R
import coding.withze.carappchapterlima.databinding.ActivityUpdateCarBinding
import coding.withze.carappchapterlima.viewmodel.ViewModelCar

class UpdateCarActivity : AppCompatActivity() {

    lateinit var binding : ActivityUpdateCarBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateCarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnUpdateCar.setOnClickListener {
            var fetId = intent.getIntExtra("update", 0)
            var name = binding.name.text.toString()
            var cat = binding.category.text.toString()
            var stat = binding.status.text.toString()
            var price = binding.price.text.toString()
            var img = binding.image.text.toString()
            Log.d("infoid",fetId.toString())

            updateDataCar(fetId,name,cat,stat.toBoolean(), price.toInt(), img)
            finish()
        }

    }

    fun updateDataCar(id : Int, name : String, category : String , status : Boolean, price : Int, image : String){
        var viewModel = ViewModelProvider(this).get(ViewModelCar::class.java)
        viewModel.updateApiCar(id, name, category, status, price, image)
        viewModel.updLiveDataCar().observe(this, Observer {
            if (it != null){
                Toast.makeText(this, "Update Data Success", Toast.LENGTH_SHORT).show()
            }
        })
    }
}