package com.example.memorygame

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.memorygame.models.BoardSize
import com.example.memorygame.models.MemoryCard
import com.example.memorygame.models.MemoryGame
import com.example.memorygame.utils.DEFAULT_ICONS
import com.example.memorygame.utils.EXTRA_BOARD_SIZE
import com.github.jinatonic.confetti.CommonConfetti
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener
import com.google.android.material.snackbar.Snackbar

class Game : AppCompatActivity() {

    private lateinit var memoryGame: MemoryGame
    private lateinit var rvBoard: RecyclerView
    private lateinit var clRoot: ConstraintLayout
    private lateinit var adapter: MemoryBoardAdapter
    //private lateinit var tvNumMoves: TextView


    private var  boardSize: BoardSize = BoardSize.EASY

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        clRoot = findViewById(R.id.clRoot)
        rvBoard = findViewById(R.id.rvBoard)

        //val intent = Intent(this , CreateActivity::class.java)
        //intent.putExtra(EXTRA_BOARD_SIZE, BoardSize.MEDIUM)
        //startActivity(intent)

        setupBoard()

    }

    companion object{
        private const val CREATE_REQUEST_CODE = 248
    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.mi_refresh -> {
                if (!memoryGame.haveWonGame()) {
                    showAlertDialog("Iniciar novo jogo?", null, View.OnClickListener {
                        setupBoard()
                    })
                } else {
                    setupBoard()
                }
                return true
            }
            R.id.mi_new_size -> {
               showNewSizeDialog()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showCreationDialog() {
        val boardSizeView = LayoutInflater.from(this).inflate(R.layout.dialog_board_size, null)
        val radioGroupSize = boardSizeView.findViewById<RadioGroup>(R.id.radioGroup)
        showAlertDialog("Crie seu jogo", boardSizeView, View.OnClickListener {
            val desiredBoardSize = when(radioGroupSize.checkedRadioButtonId){
                R.id.rbEasy -> BoardSize.EASY
                R.id.rbMedium -> BoardSize.MEDIUM
                else -> BoardSize.HARD
            }
        })
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
            Snackbar.make(clRoot, "Você venceu!", Snackbar.LENGTH_LONG ).show()
            return
        }
        if(memoryGame.isCardFaceUp(position)){
            Snackbar.make(clRoot, "Movimento inválido!", Snackbar.LENGTH_SHORT ).show()
            return
        }

        if (memoryGame.flipCard(position)){
            if(memoryGame.haveWonGame()){
                Snackbar.make(clRoot, "Você venceu!", Snackbar.LENGTH_LONG ).show()
                CommonConfetti.rainingConfetti(clRoot, intArrayOf(Color.CYAN, Color.BLUE, Color.DKGRAY)).oneShot()
            }
        }
        //tvNumMoves.text = "Moves: ${memoryGame.getNumMoves()}"
        adapter.notifyDataSetChanged()
    }
    private fun setupBoard() {
        memoryGame = MemoryGame(boardSize)
        adapter = MemoryBoardAdapter(this, boardSize, memoryGame.cards, object: MemoryBoardAdapter.CardClickListener{
            override fun onCardClicked(position: Int) {
                updateGameWithFlip(position)
            }
        })

        rvBoard.adapter = adapter
        rvBoard.setHasFixedSize(true)
        rvBoard.layoutManager = GridLayoutManager(this, boardSize.getWidth())
    }
}