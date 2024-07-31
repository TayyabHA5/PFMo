package com.example.personalfinancemanager.activities

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.personalfinancemanager.R
import com.example.personalfinancemanager.activities.models.ModelIncome
import com.example.personalfinancemanager.activities.sharedPref.MySharedPref
import com.example.personalfinancemanager.databinding.ActivityHomeBinding
import com.google.gson.Gson

class HomeActivity : AppCompatActivity(), IncomeAdapter.OnItemLongClickListener {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var mySharedPref: MySharedPref
    private lateinit var incomeAdapter: IncomeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mySharedPref = MySharedPref(this)
        initRecyclerview()
    }

    private fun initRecyclerview() {
        val incomeList = mySharedPref.getIncomes()
        binding.rvHome.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        incomeAdapter = IncomeAdapter(incomeList, this)
        binding.rvHome.adapter = incomeAdapter
    }

    override fun onItemClickListener(income: ModelIncome) {
        showAlertDialog(income)
        Toast.makeText(this, "On item clicked ${income.title}", Toast.LENGTH_SHORT).show()
    }

    private fun showAlertDialog(income: ModelIncome) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Choose an option")
        builder.setMessage("What do you want to do with this item?")
        builder.setPositiveButton("Update") { dialog, _ ->
            val intent = Intent(this, IncomeActivity::class.java)
            val gson = Gson()
            val incomeJson = gson.toJson(income)
            intent.putExtra("income", incomeJson)
            startActivity(intent)
            dialog.dismiss()
        }
        builder.setNegativeButton("Delete") { dialog, _ ->
            mySharedPref.deleteIncome(income) // Implement this method in MySharedPref
            initRecyclerview() // Refresh the RecyclerView
            Toast.makeText(this, "Item deleted", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.addIncome -> {
                startActivity(Intent(this, IncomeActivity::class.java))
            }
            R.id.Update -> {
                Toast.makeText(this, "Update option is selected", Toast.LENGTH_SHORT).show()
            }
            R.id.delete -> {
                Toast.makeText(this, "Delete option is selected", Toast.LENGTH_SHORT).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
