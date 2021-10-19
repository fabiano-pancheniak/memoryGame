package com.example.memorygame.models

import com.example.memorygame.utils.ALPHABHET_ICONS
import com.example.memorygame.utils.DEFAULT_ICONS

class MemoryGame(private val boardSize: BoardSize){
    val cards: List<MemoryCard>
    private var indexOfSingleSelectedCard: Int? = null
    private var numCardFlips = 0

    var numPairsFound = 0
    init {
        val chosenImages = DEFAULT_ICONS.shuffled().take(boardSize.getNumPairs())
        val randomizedImages = (chosenImages + chosenImages.shuffled())
        cards = randomizedImages.map { MemoryCard(it) }
    }

    fun flipCard(position: Int): Boolean {
        numCardFlips++
        val card = cards[position]
        var foundMatch = false
            if(indexOfSingleSelectedCard == null){
                //0 ou 2 cartas viradas
                restoreCards()
                indexOfSingleSelectedCard = position
            }else{
                //1 carta virada
                foundMatch = checkForMatch(indexOfSingleSelectedCard!!, position)
                indexOfSingleSelectedCard = null
            }
        card.isFaceUp = !card.isFaceUp
        return foundMatch
    }

    private fun checkForMatch(position1: Int, position2: Int): Boolean {
        if (cards[position1].identifier != cards[position2].identifier){
            return false
        }
        cards[position1].isMatched = true
        cards[position2].isMatched = true
        numPairsFound++
        return true

    }

    private fun restoreCards() {
       for (card in cards){
           if(!card.isMatched) {
               card.isFaceUp = false
           }
       }
    }

    fun haveWonGame(): Boolean {
        return numPairsFound == boardSize.getNumPairs()
    }

    fun isCardFaceUp(position: Int): Boolean {
        return cards[position].isFaceUp
    }


}
