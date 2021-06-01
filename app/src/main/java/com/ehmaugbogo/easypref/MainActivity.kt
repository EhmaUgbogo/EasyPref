package com.ehmaugbogo.easypref

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ehmaugbogo.easypref.databinding.ActivityMainBinding
import com.ehmaugbogo.easypref.util.showSnackBar
import com.ehmaugbogo.easypreflibrary.EasyPref

private const val HAS_LOGGED_IN_BEFORE = "HAS_LOGGED_IN_BEFORE"
private const val DATE_DATA_KEY = "DATE_DATA_KEY"

data class DateData(val year: Int, val month: Int, val dayOfMonth: Int)

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        EasyPref.initialize(application)


        binding.apply {
            hasVisitedBeforeSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
                EasyPref.putBoolean(HAS_LOGGED_IN_BEFORE, isChecked)

                if (isChecked) EasyPref.putObject(DATE_DATA_KEY, DateData(2021, 5, 31))
                else EasyPref.putObject(DATE_DATA_KEY, null)
            }

            observePrefs()

        }


    }

    private fun ActivityMainBinding.observePrefs() = EasyPref.apply {
        observeBoolean(HAS_LOGGED_IN_BEFORE).observe(this@MainActivity) {
            hasVisitedBeforeSwitch.isChecked = it
            readTextView.text = "$it Observe"
        }

        observeObject(DATE_DATA_KEY, DateData::class.java).observe(this@MainActivity) {
            it.value?.let { data -> showSnackBar("$data Observed") }
        }
    }

}