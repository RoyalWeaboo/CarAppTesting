package coding.withze.carappchapterlima.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import coding.withze.carappchapterlima.activity.DetailCarActivity
import coding.withze.carappchapterlima.activity.UpdateCarActivity
import coding.withze.carappchapterlima.databinding.ItemCarBinding
import coding.withze.carappchapterlima.model.PutCarResponse
import coding.withze.carappchapterlima.model.ResponseDataCar
import coding.withze.carappchapterlima.model.ResponseDataCarItem
import coding.withze.carappchapterlima.model.ResponseDetailCar
import coding.withze.carappchapterlima.viewmodel.ViewModelCar
import com.bumptech.glide.Glide
import kotlinx.coroutines.withContext

class CarAdapter(var listcar : List<ResponseDataCarItem>):RecyclerView.Adapter<CarAdapter.ViewHolder>() {

    var onDelete : ((ResponseDataCarItem)->Unit)? = null
    var onEdit : ((PutCarResponse)->Unit)? = null
    var onDetail : ((ResponseDataCarItem)->Unit)? = null

//    private var dataCar : List<ResponseDataCarItem>? = null
//    fun setDataCar(car : List<ResponseDataCarItem>){
//        this.dataCar = car
//    }

    class ViewHolder(var binding : ItemCarBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarAdapter.ViewHolder {
        var view = ItemCarBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return  ViewHolder(view)
    }

    override fun onBindViewHolder(holder: CarAdapter.ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        holder.binding.nameCar.text = listcar!![position].name
        holder.binding.categoryCar.text = listcar!![position].category
        holder.binding.priceCar.text = listcar!![position].price.toString()
        Glide.with(holder.itemView.context).load(listcar!![position].image).into(holder.binding.imgCar)

        holder.binding.deleteCar.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                val bun = Bundle()
                bun.putString("id", listcar[position].id.toString())
            }
        })

//        holder.binding.klikDetail.setOnClickListener(object : View.OnClickListener{
//            override fun onClick(v: View?) {
//                val bun = Bundle()
//                bun.putString("detail", listcar[position].toString())
//            }
//
//        })

        holder.binding.klikDetail.setOnClickListener{
            var a = Bundle()
            val inten = Intent(it.context, DetailCarActivity::class.java)
            a.putInt("okedetail", listcar[position].id)
            inten.putExtras(a)
            it.context.startActivity(inten)
        }

        holder.binding.editCar.setOnClickListener {
            var edit = Intent(it.context, UpdateCarActivity::class.java)
            edit.putExtra("update", listcar[position].id)
            it.context.startActivity(edit)
        }
    }

    override fun getItemCount(): Int {

        return listcar.size
//        if (dataCar == null){
//            return 0
//        }else{
//            return  dataCar!!.size
//        }
    }
}