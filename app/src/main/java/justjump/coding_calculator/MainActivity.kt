package justjump.coding_calculator

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        calculator.setOnClickListener()
        {
            val calculator: Intent = Intent(this, Calculator::class.java)
            calculator.putExtra("TAG",0)
            startActivity(calculator)
        }
    }
}