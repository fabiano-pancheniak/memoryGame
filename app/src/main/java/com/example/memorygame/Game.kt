package com.example.memorygame

import android.app.ActionBar
import android.content.ClipData
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.RadioGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.memorygame.models.BoardSize
import com.example.memorygame.models.BoardTheme
import com.example.memorygame.models.MemoryGame
import com.example.memorygame.utils.EXTRA_BOARD_SIZE
import com.example.memorygame.utils.EXTRA_BOARD_THEME
import com.github.jinatonic.confetti.CommonConfetti
import kotlinx.android.synthetic.main.memory_card.*

class Game() : AppCompatActivity() {

    private lateinit var memoryGame: MemoryGame
    private lateinit var rvBoard: RecyclerView
    private lateinit var clRoot: ConstraintLayout
    private lateinit var adapter: MemoryBoardAdapter
    private lateinit var boardSize: BoardSize
    private lateinit var boardTheme: BoardTheme
    private lateinit var gameWonCard: CardView
    private lateinit var btnNewGame: Button
    private lateinit var btnMenu: Button
    //private val TAG = "Game"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        var actionBar = getSupportActionBar()

        // showing the back button in action bar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
        }


        //Recebendo informacao da intent
        boardSize = intent.getSerializableExtra(EXTRA_BOARD_SIZE) as BoardSize
        boardTheme = intent.getSerializableExtra(EXTRA_BOARD_THEME) as BoardTheme
        //Log.i(TAG, boardTheme.chosenTheme)



        clRoot = findViewById(R.id.clRoot)
        rvBoard = findViewById(R.id.rvBoard)
        gameWonCard = findViewById(R.id.gwCardView)
        btnMenu = findViewById(R.id.btnMenu)
        btnNewGame = findViewById(R.id.btnNewGame)


        //val intent = Intent(this , CreateActivity::class.java)
        //intent.putExtra(EXTRA_BOARD_SIZE, BoardSize.MEDIUM)
        //startActivity(intent)

        setupBoard()

        btnMenu.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            gameWonCard.setVisibility(View.GONE)
        }

        btnNewGame.setOnClickListener{
            setupBoard()
            gameWonCard.setVisibility(View.GONE)

        }

    }

    companion object{
        private const val CREATE_REQUEST_CODE = 248
    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (!memoryGame.haveWonGame()) {
            when (item.itemId) {
                R.id.mi_refresh -> {
                    if (!memoryGame.haveWonGame()) {
                        showAlertDialog("Iniciar novo jogo?", null, View.OnClickListener {
                            setupBoard()
                        })
                    }
                    return true
                }
                R.id.mi_new_size -> {
                    showNewSizeDialog()
                    return true
                }
                android.R.id.home -> {
                    finish()
                    return true
                }

            }
        }

        return super.onOptionsItemSelected(item)
    }



    private fun showNewSizeDialog() {
         val boardSizeView = LayoutInflater.from(this).inflate(R.layout.dialog_board_size, null)
         val radioGroupSize = boardSizeView.findViewById<RadioGroup>(R.id.radioGroup)
         when (boardSize){
             BoardSize.EASY -> radioGroupSize.check(R.id.rbEasy)
             BoardSize.MEDIUM -> radioGroupSize.check(R.id.rbMedium)
             BoardSize.HARD -> radioGroupSize.check(R.id.rbHard)
         }
         showAlertDialog("Escolha a dificuldade", boardSizeView, View.OnClickListener {
            boardSize = when(radioGroupSize.checkedRadioButtonId){
                R.id.rbEasy -> BoardSize.EASY
                R.id.rbMedium -> BoardSize.MEDIUM
                else -> BoardSize.HARD
            }
             setupBoard()
        })
    }

    private fun showAlertDialog(title: String, view: View?, positiveClickListener: View.OnClickListener) {
        AlertDialog.Builder(this)
            .setTitle(title)
            .setView(view)
            .setNegativeButton("Cancelar", null)
            .setPositiveButton("OK"){_, _ ->
                positiveClickListener.onClick(null)
            }.show()
    }


    private fun updateGameWithFlip(position: Int) {
       //SISTEMA ANTI LEIGOS
        if(memoryGame.haveWonGame()){
            //Snackbar.make(clRoot, "Você venceu!", Snackbar.LENGTH_LONG ).show()
            return
        }
        if(memoryGame.isCardFaceUp(position)){
            //Snackbar.make(clRoot, "Movimento inválido!", Snackbar.LENGTH_SHORT ).show()
            return
        }

        if (memoryGame.flipCard(position)){
            if(memoryGame.haveWonGame()){
                //Snackbar.make(clRoot, "Você venceu!", Snackbar.LENGTH_LONG ).show()
                CommonConfetti.rainingConfetti(clRoot, intArrayOf(Color.CYAN, Color.BLUE, Color.LTGRAY)).oneShot()
                gameWonCard.setVisibility(View.VISIBLE)
            }
        }

        adapter.notifyDataSetChanged()
    }

    private fun setupBoard() {
        memoryGame = MemoryGame(boardSize, boardTheme)
        adapter = MemoryBoardAdapter(this, boardSize, boardTheme, memoryGame.cards, object: MemoryBoardAdapter.CardClickListener{
            override fun onCardClicked(position: Int) {
                updateGameWithFlip(position)
            }
        })
        rvBoard.adapter = adapter
        rvBoard.setHasFixedSize(true)
        rvBoard.layoutManager = GridLayoutManager(this, boardSize.getWidth())
    }


}