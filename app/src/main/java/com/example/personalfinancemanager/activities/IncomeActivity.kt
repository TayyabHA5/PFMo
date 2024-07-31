package com.example.personalfinancemanager.activities

import android.app.DatePickerDialog
import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.personalfinancemanager.activities.models.ModelIncome
import com.example.personalfinancemanager.activities.sharedPref.MySharedPref
import com.example.personalfinancemanager.databinding.ActivityIncomeBinding
import com.google.gson.Gson
import java.util.UUID


class IncomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityIncomeBinding
    private lateinit var mySharedPref: MySharedPref
    private var income: ModelIncome? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIncomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mySharedPref = MySharedPref(this)

        // Retrieve the income data if available
        val incomeJson = intent.getStringExtra("income")
        incomeJson?.let {
            val gson = Gson()
            income = gson.fromJson(it, ModelIncome::class.java)
            populateFields(income)
        }

        binding.btnSave.setOnClickListener {
            if (binding.etTitle.text.toString().isEmpty() || binding.etIncome.text.toString().isEmpty() || binding.etDate.text.toString().isEmpty()) {
                Toast.makeText(this@IncomeActivity, "Fill all fields", Toast.LENGTH_SHORT).show()
            } else {
                val newIncome = ModelIncome(
                    income?.id ?: UUID.randomUUID().toString(), // Use existing id or generate a new one
                    binding.etTitle.text.toString(),
                    binding.etDate.text.toString(),
                    binding.etIncome.text.toString()
                )

                if (income == null) {
                    mySharedPref.addIncome(newIncome)
                } else {
                    mySharedPref.updateIncome(newIncome)
                }

                binding.etTitle.text?.clear()
                binding.etIncome.text?.clear()
                binding.etDate.text?.clear()
                Toast.makeText(this@IncomeActivity, "Income Saved", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, HomeActivity::class.java))
            }
        }

        binding.etDate.setOnClickListener {
            showDatePickerDialog()
        }
    }

    private fun showDatePickerDialog() {
        val calender = Calendar.getInstance()
        val year = calender.get(Calendar.YEAR)
        val month = calender.get(Calendar.MONTH)
        val day = calender.get(Calendar.DAY_OF_MONTH)
        val dateDialogPicker = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
            val selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
            binding.etDate.setText(selectedDate)
        }, year, month, day)
        dateDialogPicker.show()
    }

    private fun populateFields(income: ModelIncome?) {
        income?.let {
            binding.etTitle.setText(it.title)
            binding.etDate.setText(it.date)
            binding.etIncome.setText(it.income.toString())
        }
    }
}
