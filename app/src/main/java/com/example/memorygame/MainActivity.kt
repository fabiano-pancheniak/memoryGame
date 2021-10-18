package com.example.memorygame


import android.content.Intent
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.RadioGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.example.memorygame.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    lateinit var binding: ActivityMainBinding
    private lateinit var btnPlay: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        btnPlay = findViewById(R.id.btnPlay)


        btnPlay.setOnClickListener {
            val intent = Intent(this, Game::class.java)
            startActivity(intent);
        }

    }



}




