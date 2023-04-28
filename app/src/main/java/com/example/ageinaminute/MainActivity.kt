package com.example.ageinaminute

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.sql.Types.NULL
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
   private var tvselecteddate : TextView?=null
    private var tvageinminute : TextView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnDatePicker : Button =findViewById(R.id.btndatepicker)
        tvselecteddate=findViewById(R.id.tvselecteddate)
        tvageinminute=findViewById(R.id.tvageinminute)
        btnDatePicker.setOnClickListener {
            clickDatePicker()
        }
    }


    fun clickDatePicker(){
        val mycalendar=Calendar.getInstance()
        val year=mycalendar.get(Calendar.YEAR)
        val month=mycalendar.get(Calendar.MONTH)
        val day=mycalendar.get(Calendar.DAY_OF_MONTH)
        DatePickerDialog(this,
        {view,selectedyear,selectedmonth,selecteddayofmonth ->
            Toast.makeText(this,"Button is pressed",Toast.LENGTH_LONG).show()
            val selectedday="$selecteddayofmonth/${selectedmonth+1}/${selectedyear}"
            tvselecteddate?.text=selectedday

            val sdf=SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val thedate=sdf.parse(selectedday)

            val selecteddateintime=thedate.time /60000
            val currentdate=sdf.parse(sdf.format(System.currentTimeMillis()))

            val currentdateinminute = currentdate.time/60000
            val diffrenceinminute=currentdateinminute-selecteddateintime
            tvageinminute?.text=diffrenceinminute.toString()
        },
            year,month,day
            ).show()
    }
}