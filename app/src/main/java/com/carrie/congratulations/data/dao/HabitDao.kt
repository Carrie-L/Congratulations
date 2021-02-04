package com.carrie.congratulations.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * Created by Carrie on 2021/2/3.
 */
@Dao
interface HabitDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGetupRecord(entity:Habit)

    @Query("select * from Habit where date=:today")
    fun getupPunchToday(today:String):LiveData<Habit>



}