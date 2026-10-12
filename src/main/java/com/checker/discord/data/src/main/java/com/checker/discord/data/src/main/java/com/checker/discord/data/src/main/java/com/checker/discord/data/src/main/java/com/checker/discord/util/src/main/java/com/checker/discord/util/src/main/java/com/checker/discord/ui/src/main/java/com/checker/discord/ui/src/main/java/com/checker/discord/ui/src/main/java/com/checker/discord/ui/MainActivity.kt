package com.checker.discord.ui

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.checker.discord.R
import com.checker.discord.data.AppDatabase
import com.checker.discord.data.CheckerRepository
import com.checker.discord.network.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: ResultsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val database = AppDatabase.getDatabase(this)
        val repository = CheckerRepository(RetrofitClient.api, database.resultDao())
        val factory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]

        val etUsername = findViewById<EditText>(R.id.etUsername)
        val btnCheck = findViewById<Button>(R.id.btnCheck)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        adapter = ResultsAdapter(emptyList())
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        btnCheck.setOnClickListener {
            val username = etUsername.text.toString().trim()
            if (username.isNotEmpty()) {
                viewModel.checkUsername(username) { available ->
                    val msg = if (available) "Username is available!" else "Username is taken."
                    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
                }
            }
        }

        CoroutineScope(Dispatchers.Main).launch {
            viewModel.allResults.collect { results ->
                adapter.updateData(results)
            }
        }
    }
}
