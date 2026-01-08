package com.colman.assignment2.model

/**
 * Represents a single student in the system.
 * As per assignment requirements: name, id, phone, address, and checked status.
 */
data class Student(
    var name: String,
    var id: String,
    var phone: String,
    var address: String,
    var isChecked: Boolean
)