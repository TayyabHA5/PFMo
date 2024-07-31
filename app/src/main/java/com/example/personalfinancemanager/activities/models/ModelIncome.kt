package com.example.personalfinancemanager.activities.models

import java.util.UUID

data class ModelIncome(
    val id: String = UUID.randomUUID().toString(), // Default value for new entries
    var title: String,
    var date: String,
    var income: String
)

