package com.example.memorygame


import android.content.Intent
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.example.memorygame.databinding.ActivityMainBinding
import com.example.memorygame.models.BoardSize
import com.example.memorygame.models.BoardTheme
import com.example.memorygame.utils.DEFAULT_ICONS
import com.example.memorygame.utils.EXTRA_BOARD_SIZE
import com.example.memorygame.utils.EXTRA_BOARD_THEME
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object{
        private const val CREATE_REQUEST_CODE = 1705
    }


    lateinit var binding: ActivityMainBinding
    private lateinit var btnPlay: Button
    private lateinit var rgThemes: RadioGroup
    private lateinit var rbDefault: RadioButton
    private lateinit var rbAlphabet: RadioButton
    private lateinit var rgLevel: RadioGroup
    private lateinit var easy: RadioButton
    private lateinit var medium: RadioButton
    private lateinit var hard: RadioButton
    private lateinit var boardSize: BoardSize
    private lateinit var boardTheme: BoardTheme


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        btnPlay = findViewById(R.id.btnPlay)
        rgThemes = findViewById(R.id.themeGroup)
        rbDefault = findViewById(R.id.themeDefault)
        rbAlphabet = findViewById(R.id.themeAlphabet)
        rgLevel = findViewById(R.id.levelGroup)
        easy = findViewById(R.id.easy)
        medium = findViewById(R.id.medium)
        hard = findViewById(R.id.hard)


        btnPlay.setOnClickListener {
            newGame()
        }


    }

    private fun newGame(){
        //val boardSizeView = LayoutInflater.from(this).inflate(R.layout.dialog_board_size, null)
        //val radioGroupSize = boardSizeView.findViewById<RadioGroup>(R.id.radioGroup)
        val desiredBoardSize = when(rgLevel.checkedRadioButtonId){
            R.id.easy ->BoardSize.EASY
            R.id.medium ->BoardSize.MEDIUM
            else -> BoardSize.HARD
        }

        val intent = Intent(this, Game::class.java)
        intent.putExtra(EXTRA_BOARD_SIZE, desiredBoardSize)
        startActivityForResult(intent, CREATE_REQUEST_CODE);

    }



}




