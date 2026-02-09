package fastcampus.part1.chapter3

import android.os.Bundle
<<<<<<< HEAD
import android.os.PersistableBundle
import android.util.Log
=======
>>>>>>> d23b6630daa1788562fc6ce449eca4e0a6e923ba
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
<<<<<<< HEAD
import androidx.core.widget.addTextChangedListener
import fastcampus.part1.chapter3.databinding.ActivityMainBinding

private var cmToM: Boolean = true

=======
import fastcampus.part1.chapter3.databinding.ActivityMainBinding

>>>>>>> d23b6630daa1788562fc6ce449eca4e0a6e923ba
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val outputTextView = binding.outputTextView
        val outputUnitTextView = binding.outputUnitTextView
        val inputEditText = binding.inputEditText
        val inputUnitTextView = binding.inputUnitTextView
<<<<<<< HEAD
        val swapImageButton = binding.swapImageButton

        var inputNumber:Int = 0
//        var cmToM = true


        inputEditText.addTextChangedListener { text ->
            inputNumber = if(text.isNullOrEmpty()){
                0
            } else{
                text.toString().toInt()
            }

            var outputNumber = inputNumber.times(0.01)
            if(cmToM){
                outputTextView.text = inputNumber.times(0.01).toString()
            }else{
                outputTextView.text = inputNumber.times(100).toString()
            }

        }
        swapImageButton.setOnClickListener {
            cmToM = cmToM.not()
            if(cmToM){
                inputUnitTextView.text = "cm"
                outputUnitTextView.text = "m"
                outputTextView.text = inputNumber.times(0.01).toString()
            }else{
                inputUnitTextView.text = "m"
                outputUnitTextView.text = "cm"
                outputTextView.text = inputNumber.times(100).toString()
            }
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean("cmToM", cmToM)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        cmToM = savedInstanceState.getBoolean("cmToM")
        Log.d("cmToM", cmToM.toString())
        binding.inputUnitTextView.text = if(cmToM) "cm" else "m"
        binding.outputUnitTextView.text = if(cmToM) "m" else "cm"
        super.onRestoreInstanceState(savedInstanceState)
=======


>>>>>>> d23b6630daa1788562fc6ce449eca4e0a6e923ba
    }
}