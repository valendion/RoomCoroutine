package com.example.roomcoroutine.ui.view_all_activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kalvin_iring_allo_172094.room.UserDB
import com.example.roomcoroutine.databinding.ActivityViewAllBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ViewAllActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewAllBinding
    private val db by lazy { UserDB(this) }
    private val adapterViewAll by lazy { AdapterViewAll() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewAllBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        getDataUser()
    }

    private fun getDataUser(){
        CoroutineScope(Dispatchers.IO).launch {
            val data = db.userDao().getAllUser()
            withContext(Dispatchers.Main){

                binding.rvMain.apply {
                    layoutManager = LinearLayoutManager(this@ViewAllActivity)
                    adapterViewAll.updateUser(data)
                    Log.e("ViewAll", data.toString())
                    adapter = adapterViewAll
                }

            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}