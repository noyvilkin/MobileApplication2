package com.colman.assignment2.model

class Model private constructor() {
    val data: MutableList<Student> = mutableListOf()

    companion object {
        val shared: Model = Model()
    }

    init {
        // Generating initial 20 students
        for (i in 1..20) {
            data.add(
                Student(
                    name = "Student $i",
                    id = (200000000 + i).toString(),
                    phone = "050000000$i",
                    address = "Street $i",
                    isChecked = false
                )
            )
        }
    }

    // New helper to prevent duplicates
    fun getNextId(): String {
        // If list is empty, start at base. Otherwise, find the highest ID and add 1.
        val lastId = data.mapNotNull { it.id.toLongOrNull() }.maxOrNull() ?: 200000000L
        return (lastId + 1).toString()
    }
}