package com.carrie.congratulations.data.dao

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Carrie on 2021/2/3.
 */
@Entity
data class Habit (@PrimaryKey val date:String, val time:String, val name:String)