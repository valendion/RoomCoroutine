package com.example.roomcoroutine.ui.view_all_activity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.roomcoroutine.R
import com.example.roomcoroutine.databinding.ItemViewAllBinding
import com.example.roomcoroutine.model.User


class AdapterViewAll : RecyclerView.Adapter<AdapterViewAll.ViewAllHolder>() {

    var dataUser: ArrayList<User?> = arrayListOf()

    fun updateUser(modelUser: List<User?>) {
        modelUser.forEach {
            if (it != null) {
                add(it)
            }
        }
    }

    private fun add(modelUser: User) {
        dataUser.add(modelUser)
        notifyItemInserted(dataUser.size)
    }


    inner class ViewAllHolder(private val itemBinding: ItemViewAllBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        private val context = itemBinding.root

        fun bind(modelUser: User) {
            itemBinding.apply {
                textDataNis.text = context.resources.getString(R.string.nis, modelUser.nis)
                textDataName.text = context.resources.getString(R.string.name, modelUser.name)
                textDataPhone.text = context.resources.getString(R.string.phone, modelUser.phone)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewAllHolder {
        val itemBinding =
            ItemViewAllBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewAllHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewAllHolder, position: Int) {
        dataUser[position]?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int = dataUser.size
}