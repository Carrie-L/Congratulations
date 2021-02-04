package com.carrie.congratulations.data.dao

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.carrie.congratulations.viewModel.HabitViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by Carrie on 2021/2/3.
 */
class HabitRepository(private val habitDao: HabitDao) {

    companion object {
        @Volatile
        private var instance: HabitRepository? = null

        fun getInstance(habitDao: HabitDao) = instance ?: synchronized(this) {
            instance ?: HabitRepository(habitDao).also { instance = it }
        }
    }

   suspend fun insertGetupRecord(entity:Habit) = withContext(Dispatchers.IO){
        habitDao.insertGetupRecord(entity)
    }

    fun getupPunchToday(today:String) = habitDao.getupPunchToday(today)

}


@kotlin.Suppress("UNCHECKED_CAST")
class HabitViewModelFactory(private val habitRepository: HabitRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HabitViewModel::class.java))
            return HabitViewModel(habitRepository) as T
        throw IllegalArgumentException("Unable to construct viewmodel")
    }


}