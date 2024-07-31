package com.example.personalfinancemanager.activities.sharedPref

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.example.personalfinancemanager.activities.models.ModelIncome
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MySharedPref(context: Context) {
    private val mySharedPref: SharedPreferences = context.getSharedPreferences("myPref", Context.MODE_PRIVATE)
    private val gson = Gson()

    fun addIncome(income: ModelIncome) {
        val incomeList = getIncomes().toMutableList()
        incomeList.add(income)
        saveIncomes(incomeList)
    }

    fun updateIncome(updatedIncome: ModelIncome) {
        val incomeList = getIncomes().toMutableList()
        val index = incomeList.indexOfFirst { it.id == updatedIncome.id } // Use the unique identifier here
        if (index != -1) {
            incomeList[index] = updatedIncome
            saveIncomes(incomeList)
        }
    }

    fun getIncomes(): List<ModelIncome> {
        val json = mySharedPref.getString("incomes", null)
        Log.d("MySharedPref", "getIncomes JSON: $json")
        if (json == null) {
            return emptyList()
        }
        val type = object : TypeToken<List<ModelIncome>>() {}.type
        return gson.fromJson(json, type)
    }

    private fun saveIncomes(incomeList: List<ModelIncome>) {
        val json = gson.toJson(incomeList)
        val editor = mySharedPref.edit()
        editor.putString("incomes", json)
        editor.apply()
    }

    fun clearIncomes() {
        val editor = mySharedPref.edit()
        editor.remove("incomes")
        editor.apply()
    }

    fun deleteIncome(income: ModelIncome) {
        val incomeList = getIncomes().toMutableList()
        val index = incomeList.indexOfFirst { it.id == income.id }
        if (index != -1) {
            incomeList.removeAt(index)
            saveIncomes(incomeList)
        }
    }

    // Other methods...

    fun addOrUpdate(key: String, value: String) {
        val editor = mySharedPref.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun deletePref(key: String) {
        val editor = mySharedPref.edit()
        editor.remove(key)
        editor.apply()
    }

    fun getPref(key: String): String? {
        return mySharedPref.getString(key, null)
    }

    fun setLoggedIn(isLoggedIn: Boolean) {
        val editor = mySharedPref.edit()
        editor.putBoolean("isLogged", isLoggedIn)
        editor.apply()
    }

    fun isLoggedIn(): Boolean {
        return mySharedPref.getBoolean("isLogged", false)
    }
}
