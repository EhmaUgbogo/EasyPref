package com.ehmaugbogo.easypref

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ehmaugbogo.easypref.databinding.ActivityMainBinding
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
                display(isChecked)
            }

            checkPreviousVisitation()
        }


    }

    private fun ActivityMainBinding.checkPreviousVisitation() {
        val isLoggedIn = EasyPref.getBoolean(HAS_LOGGED_IN_BEFORE)
        hasVisitedBeforeSwitch.isChecked = isLoggedIn
        display(isLoggedIn)
    }

    private fun ActivityMainBinding.display(isLoggedIn: Boolean) {
        readTextView.text = "$isLoggedIn"
    }


    /* To save objects */
    private fun saveDateData(dateData: DateData){
        EasyPref.putObject(DATE_DATA_KEY, dateData)
    }

    private fun getDateData(): DateData?{
        return EasyPref.getObject(DATE_DATA_KEY, DateData::class.java)
    }


}