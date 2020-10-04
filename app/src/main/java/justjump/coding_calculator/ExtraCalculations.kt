package justjump.coding_calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.SurfaceControl
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.activity_extra_calculations.*

class ExtraCalculations : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_extra_calculations)

        val fragmentAverage: Fragment_Average = Fragment_Average()

        makeCurrentFragment(fragmentAverage)

        // Falta establecer el cambio de fragment cuando se preciona el resto de botones
        //https://www.youtube.com/watch?v=fODp1hZxfng&t=285s
    }

    fun makeCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentLayout, fragment)
            commit()
        }

    }
}