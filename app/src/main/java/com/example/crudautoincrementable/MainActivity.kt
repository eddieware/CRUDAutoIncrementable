package com.example.crudautoincrementable

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {

    private lateinit var mDb:RoomSingleton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize the room database
        mDb = RoomSingleton.getInstance(applicationContext)

        // Make the text view scrollable
        textView.movementMethod = ScrollingMovementMethod()

        // Insert button click listener
        btnInsert.setOnClickListener{
            // Initialize a new student
            val student = Student(id = null,
                    fullName = "Eduardo",
                    result ="Mejia"
            )

            doAsync {
                // Put the student in database
                mDb.studentDao().insert(student)

                uiThread {
                    toast("One record inserted.")
                }
            }
        }


        // Select button click listener
        btnSelect.setOnClickListener{
            doAsync {
                // Get the student list from database
                val list = mDb.studentDao().allStudents()

                uiThread {
                    toast("${list.size} records found.")
                    // Display the students in text view
                    textView.text = ""
                    for (student in list){
                        textView.append("${student.id} : ${student.fullName} : ${student.result}\n")
                    }
                }
            }
        }
    }
}