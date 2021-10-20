package com.example.memorygame.models

import com.example.memorygame.R

enum class BoardTheme(val chosenTheme: List<Int>) {
        DEFAULT(listOf(
                R.drawable.ic_face,
                R.drawable.ic_flower,
                R.drawable.ic_gift,
                R.drawable.ic_heart,
                R.drawable.ic_home,
                R.drawable.ic_lightning,
                R.drawable.ic_moon,
                R.drawable.ic_plane,
                R.drawable.ic_school,
                R.drawable.ic_send,
                R.drawable.ic_star,
                R.drawable.ic_work
        )),
        //TODO: Add remaining letters
        ALPHABET(listOf(
                R.drawable.ic_card_a,
                R.drawable.ic_card_b,
                R.drawable.ic_card_c,
                R.drawable.ic_card_d,
                R.drawable.ic_card_e,
                R.drawable.ic_card_f,
                R.drawable.ic_card_g,
                R.drawable.ic_card_h,
                R.drawable.ic_card_i,
                R.drawable.ic_card_j,
                R.drawable.ic_card_k,
                R.drawable.ic_card_l)),
        ANIMALS(listOf(
                R.drawable.ic_card_bear,
                R.drawable.ic_card_camel,
                R.drawable.ic_card_dog,
                R.drawable.ic_card_elephant,
                R.drawable.ic_card_gator,
                R.drawable.ic_card_giraffe,
                R.drawable.ic_card_goose,
                R.drawable.ic_card_krab,
                R.mipmap.ic_card_lion_foreground,
                R.drawable.ic_card_scorpion,
                R.drawable.ic_card_snake,
                R.drawable.ic_card_turtle))

}

