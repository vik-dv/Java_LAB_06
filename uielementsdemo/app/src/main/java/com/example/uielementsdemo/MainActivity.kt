package com.example.uielementsdemo

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.CalendarView
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private var selectedDate: String = ""

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //===========
        val tvTitle = findViewById<TextView>(R.id.tvTitle)
        val tilName = findViewById<TextInputLayout>(R.id.tilName)
        val etName = findViewById<TextInputEditText>(R.id.etName)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val cbAgree = findViewById<CheckBox>(R.id.cbAgree)
        val calendarView = findViewById<CalendarView>(R.id.calendarView)
        val tvResult = findViewById<TextView>(R.id.tvResult)
        val btnSubmit = findViewById<Button>(R.id.btnSubmit)
        //============
        // Обработчик CalendarView
        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val cal = Calendar.getInstance()
            cal.set(year, month, dayOfMonth)
            val fmt = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
            selectedDate = fmt.format(cal.time)
            Toast.makeText(this, "Дата: $selectedDate", Toast.LENGTH_SHORT).show()
        }
        // Обработчик кнопки
        btnSubmit.setOnClickListener {
            val name = etName.text?.toString()?.trim().orEmpty()
            val email = etEmail.text.toString().trim()
            val agreed = cbAgree.isChecked
            //val notifications = switchNotifications.isChecked

            // Валидация TextInputLayout
            if (name.isEmpty()) {
                tilName.error = "Введите имя"
                return@setOnClickListener
            } else {
                tilName.error = null
            }

            if (!agreed) {
                Toast.makeText(this, "Подтвердите согласие", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val result = JavaHelper.buildSummary(name, email, selectedDate)
            tvResult.text = result
            Toast.makeText(this, "Данные отправлены", Toast.LENGTH_SHORT).show()

        }
    }
}
