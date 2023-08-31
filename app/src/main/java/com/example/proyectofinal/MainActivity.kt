package com.example.proyectofinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import com.example.proyectofinal.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel.setearContexto(this)

        mainViewModel.comparador.observe(this){
            binding.resultado.text = it.resultado
            it.resultado
        }

        binding.comparar.setOnClickListener{
            val texto1 = findViewById<TextView>(R.id.texto1)
            val texto2 = findViewById<TextView>(R.id.texto2)
            mainViewModel.comparar(texto1.text.toString(), texto2.text.toString())
        }
    }
}