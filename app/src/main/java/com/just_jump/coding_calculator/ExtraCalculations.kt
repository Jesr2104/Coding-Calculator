package com.just_jump.coding_calculator

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.just_jump.coding_calculator.databinding.ActivityExtraCalculationsBinding
import com.just_jump.coding_calculator.utilities.ReturnMainActivity

class ExtraCalculations : AppCompatActivity(), ReturnMainActivity {

    private lateinit var binding: ActivityExtraCalculationsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExtraCalculationsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragmentAverage = FragmentAverage(this)
        val fragmentExponent = FragmentExponent(this)
        val fragmentPercentage = FragmentPercentage(this)
        val fragmentRuleOfThree = FragmentRuleOfThree(this)
        val fragmentSquareRoot = FragmentSquareRoot(this)

        val colors = intArrayOf(
            Color.rgb(0, 0, 0),// no select
            Color.rgb(150, 150, 150)// Select
        )

        val states = arrayOf(
            intArrayOf(android.R.attr.state_enabled, -android.R.attr.state_checked),
            intArrayOf(android.R.attr.state_enabled, android.R.attr.state_checked)
        )

        binding.navigationBarView.itemTextColor = ColorStateList(states, colors)
        binding.navigationBarView.itemIconTintList = null

        makeCurrentFragment(fragmentAverage)

        binding.navigationBarView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.NavBar_average -> makeCurrentFragment(fragmentAverage)
                R.id.NavBar_exponent -> makeCurrentFragment(fragmentExponent)
                R.id.NavBar_percentage -> makeCurrentFragment(fragmentPercentage)
                R.id.NavBar_ruleOfTree -> makeCurrentFragment(fragmentRuleOfThree)
                R.id.NavBar_squareRoot -> makeCurrentFragment(fragmentSquareRoot)
            }
            true
        }
    }

    private fun makeCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentLayout, fragment)
            commit()
        }
    }

    override fun returnMainActivity() {
        finish()
    }
}