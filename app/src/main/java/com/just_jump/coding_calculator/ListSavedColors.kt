package com.just_jump.coding_calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.just_jump.coding_calculator.data.local.SRDataColors
import kotlinx.android.synthetic.main.activity_list_saved_colors.*

class ListSavedColors() : AppCompatActivity(){

    lateinit var adapter: NewRecycleAdapterSavedColors
    lateinit var data: ArrayList<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_saved_colors)

        data = SRDataColors.customPreference(this).getlist()

        recycleViewFields.layoutManager = LinearLayoutManager(this)
        adapter = NewRecycleAdapterSavedColors(data)
        recycleViewFields.adapter = adapter
        recycleViewFields.setHasFixedSize(true)
    }
}