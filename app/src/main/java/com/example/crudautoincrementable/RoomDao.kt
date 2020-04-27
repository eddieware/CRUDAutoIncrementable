package com.example.crudautoincrementable

import androidx.room.*

@Dao
interface studentDao{
    @Query("SELECT * FROM studentTbl")
    fun allStudents():List<Student>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(student:Student)

    @Delete
    fun delete(student: Student)



}