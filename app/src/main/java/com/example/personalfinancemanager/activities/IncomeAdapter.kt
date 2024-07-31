package com.example.personalfinancemanager.activities

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.personalfinancemanager.R
import com.example.personalfinancemanager.activities.models.ModelIncome

class IncomeAdapter(private var incomeList: List<ModelIncome>, private val listener: OnItemLongClickListener) : RecyclerView.Adapter<IncomeAdapter.ViewHolder>() {

    interface OnItemLongClickListener {
        fun onItemClickListener(income: ModelIncome)

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.tvRvTitle)
        val date: TextView = itemView.findViewById(R.id.tvRvDate)
        val income: TextView = itemView.findViewById(R.id.tvRvIncome)
        val cardL : CardView = itemView.findViewById(R.id.itemCardView)
        val constL: ConstraintLayout = itemView.findViewById(R.id.constL)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IncomeAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_home, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: IncomeAdapter.ViewHolder, position: Int) {
        val currentPosition = incomeList[position]
        holder.title.text = currentPosition.title
        holder.date.text = currentPosition.date
        holder.income.text = currentPosition.income.toString()
        holder.cardL.setOnClickListener() {
            listener.onItemClickListener(currentPosition)
            true
        }
    }

    override fun getItemCount(): Int {
        return incomeList.size
    }
    fun updateData(newIncomeList: List<ModelIncome>) {
        incomeList = newIncomeList
        notifyDataSetChanged()
    }
}
