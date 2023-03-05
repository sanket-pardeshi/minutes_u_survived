package com.example.minutesusurvived

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var selected : TextView? = null
    private var minage : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDate : Button = findViewById(R.id.btn_date)

        selected = findViewById(R.id.sel_date)
        minage = findViewById(R.id.minutes)

        btnDate.setOnClickListener {
            dateselect()
        }
    }

    private fun dateselect(){

        val mycal = Calendar.getInstance()
        val yr = mycal.get(Calendar.YEAR)
        val month = mycal.get(Calendar.MONTH)
        val date = mycal.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this,
            { _, year, month, dayOfMonth ->

                val seldate = "$dayOfMonth/${month+1}/$year"

                selected?.text = seldate

                val dateform = SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)
                val thedate = dateform.parse(seldate)
                thedate?.let {
                    val dateinmin = thedate.time / 60000

                    val curdate = dateform.parse(dateform.format(System.currentTimeMillis()))

                    curdate?.let {
                        val curdatemin = curdate.time / 60000

                        val diff = curdatemin - dateinmin

                        minage?.text = diff.toString()
                    }

                }

            },yr,month,date
            )
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()

        Toast.makeText(this, "Select Date", Toast.LENGTH_SHORT).show()

    }
}