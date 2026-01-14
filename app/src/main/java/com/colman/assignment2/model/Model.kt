package com.colman.assignment2.model

class Model private constructor() {
    val data: MutableList<Student> = mutableListOf()

    companion object {
        val instance: Model = Model()
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

    fun getStudentById(id: String?): Student? {
        return data.find { it.id == id }
    }

    fun updateStudent(oldId: String, newStudent: Student) {
        val studentToUpdate = data.find { it.id == oldId }
        studentToUpdate?.apply {
            name = newStudent.name
            id = newStudent.id
            phone = newStudent.phone
            address = newStudent.address
            isChecked = newStudent.isChecked
        }
    }

    fun deleteStudent(id: String) {
        data.removeAll { it.id == id }
    }
}