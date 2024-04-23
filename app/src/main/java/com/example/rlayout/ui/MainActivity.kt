package com.example.rlayout.ui

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.rlayout.infra.MotivationConstants
import com.example.rlayout.R
import com.example.rlayout.data.Mock
import com.example.rlayout.data.Phrase
import com.example.rlayout.infra.SecurityPreferences
import com.example.rlayout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private var categoryId = MotivationConstants.FILTER.INFINITY

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        //Esconder a barra de navegação
        supportActionBar?.hide()
        handleUserName()
        handleFilter(R.id.image_infinity)
        handleNextPhrase()

        //Eventos
        binding.buttonNewPhrase.setOnClickListener(this)
        binding.imageInfinity.setOnClickListener(this)
        binding.imageSad.setOnClickListener(this)
        binding.imageHeartb.setOnClickListener(this)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onClick(view: View) {
        if (view.id == R.id.button_new_phrase) {
            handleNextPhrase()
        } else if (view.id in listOf(R.id.image_infinity, R.id.image_sad, R.id.image_heartb)) {
            handleFilter(view.id)
        }
    }

    private fun handleNextPhrase() {
        binding.textPhrase.text = Mock().getPhrase(categoryId)
    }

    private fun handleFilter(id: Int) {
        binding.imageInfinity.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))
        binding.imageSad.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))
        binding.imageHeartb.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))

        when (id) {
            R.id.image_infinity -> {
                binding.imageInfinity.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = MotivationConstants.FILTER.INFINITY
            }
            R.id.image_sad -> {
                binding.imageSad.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = MotivationConstants.FILTER.SAD
            }
            R.id.image_heartb -> {
                binding.imageHeartb.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = MotivationConstants.FILTER.HEARTB
            }
        }
    }

    private fun handleUserName() {
        val name = SecurityPreferences(this).getString(MotivationConstants.KEY.USER_NAME)
        binding.textUserName.text = "Olá, $name!"
    }
}