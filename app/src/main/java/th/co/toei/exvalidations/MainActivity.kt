package th.co.toei.exvalidations

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import th.co.toei.exvalidations.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val validator = Validator(binding)

        binding.btnConfirm.setOnClickListener {
            if (validator.isValid()) {
                Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Fail", Toast.LENGTH_SHORT).show()
            }
        }

        binding.etPhoneNumber.setTag(
            R.id.tag_edit_text,
            listOf(Validator.ValidationRule("TOEI", validator = { it.length > 4 }))
        )
    }
}