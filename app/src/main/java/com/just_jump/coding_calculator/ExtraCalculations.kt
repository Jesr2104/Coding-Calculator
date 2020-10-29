package com.just_jump.coding_calculator

import android.content.pm.ActivityInfo
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_extra_calculations.*

class ExtraCalculations : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_extra_calculations)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        val fragmentAverage = FragmentAverage()
        val fragmentPercentage = FragmentPercentage()
        val fragmentRuleOfThree = FragmentRuleOfThree()

        val colors = intArrayOf(
            Color.rgb(0,0,0),// no seleccionado
            Color.rgb(150,150,150)// Seleccionado
        )

        val states = arrayOf(
            intArrayOf(android.R.attr.state_enabled, -android.R.attr.state_checked),
            intArrayOf(android.R.attr.state_enabled, android.R.attr.state_checked)
        )

        navigationBar_view.itemTextColor = ColorStateList(states, colors)
        navigationBar_view.itemIconTintList = null

        makeCurrentFragment(fragmentAverage)


        navigationBar_view.setOnNavigationItemSelectedListener {
            when (it.itemId){
                R.id.NavBar_average -> makeCurrentFragment(fragmentAverage)
                R.id.NavBar_Percentage -> makeCurrentFragment(fragmentPercentage)
                R.id.NavBar_ruleOfTree -> makeCurrentFragment(fragmentRuleOfThree)
            }
            true
        }
    }

    fun makeCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentLayout, fragment)
            commit()
        }
    }
}