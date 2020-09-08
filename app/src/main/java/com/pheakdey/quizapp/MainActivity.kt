package com.pheakdey.quizapp

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var first_answer:String
    lateinit var second_answer:String
    var result: Double = 0.0

    var day = 0
    var month: Int = 0
    var year: Int = 0
    var hour: Int = 0
    var minute: Int = 0
    var myDay = 0
    var myMonth: Int = 0
    var myYear: Int = 0
    var myHour: Int = 0
    var myMinute: Int = 0
    lateinit var currentdatetime: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_submit.setOnClickListener {
            Toast.makeText(this, "${result}", Toast.LENGTH_SHORT).show()

            /*
            val c = Calendar.getInstance()
            val mYear = c.get(Calendar.YEAR)
            val  mMonth = c.get(Calendar.MONTH)
            val  mDay = c.get(Calendar.DAY_OF_MONTH)
            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                // Display Selected date in TextView
                tv_result.text = "$dayOfMonth  $monthOfYear  $year"
            }, mYear, mMonth, mDay)
            dpd.show() */

            /*
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                // Selected hour and minutes set into the TextView
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)
                tv_result.text = SimpleDateFormat("HH:mm").format(cal.time)
            }
            TimePickerDialog(this, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show() */

            pickDateTime()

        }

        btn_reset.setOnClickListener {
            Toast.makeText(this, "Reset", Toast.LENGTH_SHORT).show()
            //radio_group_first_question.clearCheck()
            //radio_group_second_question.clearCheck()
        }

       radio_group_first_question.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { group, checkId ->
           var radio: RadioButton = findViewById(checkId)
           Toast.makeText(this, "${radio.text}", Toast.LENGTH_SHORT).show()
           first_answer = radio.text.toString()
           if (first_answer == "Public") {
               result = result + 50
           }
       })

       radio_group_second_question.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { group, checkId ->
           var radio: RadioButton = findViewById(checkId)
           Toast.makeText(this, "${radio.text}", Toast.LENGTH_SHORT).show()
           second_answer = radio.text.toString()
           if (second_answer == "3. Both of 1 and 2") {
               result = result + 50
           }
       })

    }

    private fun pickDateTime() {
        val currentDateTime = Calendar.getInstance()
        val startYear = currentDateTime.get(Calendar.YEAR)
        val startMonth = currentDateTime.get(Calendar.MONTH)
        val startDay = currentDateTime.get(Calendar.DAY_OF_MONTH)
        val startHour = currentDateTime.get(Calendar.HOUR_OF_DAY)
        val startMinute = currentDateTime.get(Calendar.MINUTE)

        DatePickerDialog(this, DatePickerDialog.OnDateSetListener { _, year, month, day ->
            TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { _, hour, minute ->
                val pickedDateTime = Calendar.getInstance()
                pickedDateTime.set(year, month, day, hour, minute)
                currentdatetime = "${month}/${day}/${year}, ${hour}:${minute}"
                tv_result.text = currentdatetime.toString()
                showCorrect(null)
                //doSomethingWith(pickedDateTime)
            }, startHour, startMinute, false).show()
        }, startYear, startMonth, startDay).show()
    }

    fun showCorrect(view: View?){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Quiz Result")
        builder.setMessage("Congratulations ${currentdatetime}, Your achieved ${result.toInt()}%")
        builder.show()
    }
}