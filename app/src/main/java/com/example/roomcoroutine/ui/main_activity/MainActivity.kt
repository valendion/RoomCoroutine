package com.example.roomcoroutine.ui.main_activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.roomcoroutine.model.User
import com.example.kalvin_iring_allo_172094.room.UserDB
import com.example.roomcoroutine.databinding.ActivityMainBinding
import com.example.roomcoroutine.ui.view_all_activity.ViewAllActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val db by lazy { UserDB(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initialConditions()
        addData()
        viewData()
        deleteData()
        editData()
    }

    private fun clear() {
        binding.apply {
            inputNis.requestFocus()
            inputNis.text?.clear()
            inputName.text?.clear()
            inputPhone.text?.clear()
        }
    }

    private fun addData() {
        binding.apply {

            btnEntry.setOnClickListener {
                if (inputNis.text.toString().isBlank() &&
                    inputName.text.toString().isBlank() &&
                    inputPhone.text.toString().isBlank()
                ) {
                    Toast.makeText(this@MainActivity, "Lengkapi data anda", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    CoroutineScope(Dispatchers.IO).launch {
                        db.userDao().addUser(
                            User(
                                inputNis.text.toString().trim(),
                                inputName.text.toString().trim(),
                                inputPhone.text.toString().trim()
                            )
                        )
                    }
                    Toast.makeText(this@MainActivity, "Data berhsil ditambah", Toast.LENGTH_SHORT)
                        .show()
                    clear()
                }
            }
        }
    }

    private fun viewData() {
        binding.apply {

            btnView.setOnClickListener {
                if (inputNis.text.toString().isBlank()) {
                    Toast.makeText(this@MainActivity, "Lengkapi data anda", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    CoroutineScope(Dispatchers.IO).launch {
                        val data = db.userDao().getUser(
                            inputNis.text.toString().trim()
                        )
                        withContext(Dispatchers.Main) {
                            inputNis.setText(data.nis)
                            inputName.setText(data.name)
                            inputPhone.setText(data.phone)

                            Toast.makeText(
                                this@MainActivity,
                                "Menampilkn data ${inputNis.text.toString().trim()}",
                                Toast.LENGTH_SHORT
                            )
                                .show()

                            btnModify.isEnabled = true
                            btnDelete.isEnabled = true
                            btnEntry.isEnabled = false

                            inputNis.isEnabled = false
                        }

                    }
                }
            }
        }
    }

    private fun deleteData() {
        binding.apply {

            btnDelete.setOnClickListener {
                CoroutineScope(Dispatchers.IO).launch {
                    db.userDao().delete(
                        User(
                            inputNis.text.toString().trim(),
                            inputName.text.toString().trim(),
                            inputPhone.text.toString().trim()
                        )
                    )
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            this@MainActivity,
                            "Data berhasil dihapus",
                            Toast.LENGTH_SHORT
                        )
                            .show()

                        btnModify.isEnabled = false
                        btnDelete.isEnabled = false
                        btnEntry.isEnabled = true

                        inputNis.isEnabled = true
                        clear()
                    }
                }
            }
        }
    }

    private fun editData() {
        binding.apply {

            btnModify.setOnClickListener {
                CoroutineScope(Dispatchers.IO).launch {
                    db.userDao().updateUser(
                        User(
                            inputNis.text.toString().trim(),
                            inputName.text.toString().trim(),
                            inputPhone.text.toString().trim()
                        )
                    )
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            this@MainActivity,
                            "Data berhasil diubah",
                            Toast.LENGTH_SHORT
                        )
                            .show()

                        btnModify.isEnabled = false
                        btnDelete.isEnabled = false
                        btnEntry.isEnabled = true

                        inputNis.isEnabled = true
                        clear()
                    }
                }
            }
        }
    }

    private fun initialConditions() {
        binding.apply {
            btnModify.isEnabled = false
            btnDelete.isEnabled = false

            btnViewAll.setOnClickListener {
                btnModify.isEnabled = false
                btnDelete.isEnabled = false
                btnEntry.isEnabled = true
                inputNis.isEnabled = true

                startActivity(Intent(this@MainActivity, ViewAllActivity::class.java))
            }

            btnClear.setOnClickListener {
                clear()
                btnModify.isEnabled = false
                btnDelete.isEnabled = false
            }

        }
    }
}