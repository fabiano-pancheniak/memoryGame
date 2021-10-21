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
                R.mipmap.ic_card_a_foreground,
                R.mipmap.ic_card_b_foreground,
                R.mipmap.ic_card_c_foreground,
                R.mipmap.ic_card_d_foreground,
                R.mipmap.ic_card_e_foreground,
                R.mipmap.ic_card_f_foreground,
                R.mipmap.ic_card_g_foreground,
                R.mipmap.ic_card_h_foreground,
                R.mipmap.ic_card_i_foreground,
                R.mipmap.ic_card_j_foreground,
                R.mipmap.ic_card_k_foreground,
                R.mipmap.ic_card_l_foreground,
                R.mipmap.ic_card_m_foreground,
                R.mipmap.ic_card_n_foreground,
                R.mipmap.ic_card_o_foreground,
                R.mipmap.ic_card_p_foreground,
                R.mipmap.ic_card_q_foreground,
                R.mipmap.ic_card_r_foreground,
                R.mipmap.ic_card_s_foreground,
                R.mipmap.ic_card_t_foreground,
                R.mipmap.ic_card_u_foreground,
                R.mipmap.ic_card_v_foreground,
                R.mipmap.ic_card_w_foreground,
                R.mipmap.ic_card_x_foreground,
                R.mipmap.ic_card_y_foreground,
                R.mipmap.ic_card_z_foreground
                )),
        ANIMALS(listOf(
                R.mipmap.ic_card_bear_foreground,
                R.mipmap.ic_card_lion_foreground,
                R.mipmap.ic_card_bear_foreground,
                R.mipmap.ic_card_butterfly_foreground,
                R.mipmap.ic_card_cow_foreground,
                R.mipmap.ic_card_dog_foreground,
                R.mipmap.ic_card_fox_foreground,
                R.mipmap.ic_card_hippo_foreground,
                R.mipmap.ic_card_hound_foreground,
                R.mipmap.ic_card_mice_foreground,
                R.mipmap.ic_card_owl_foreground,
                R.mipmap.ic_card_pig_foreground
                ))

}

