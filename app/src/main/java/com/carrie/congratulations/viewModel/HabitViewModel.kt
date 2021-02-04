package com.carrie.congratulations.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.carrie.congratulations.data.dao.Habit
import com.carrie.congratulations.data.dao.HabitRepository
import com.carrie.congratulations.utils.todayDate
import kotlinx.coroutines.launch

/**
 * Created by Carrie on 2021/2/3.
 */
class HabitViewModel (private val habitRepository: HabitRepository):ViewModel() {
    var getUpToday:LiveData<Habit> = liveData<Habit> {
        emitSource(habitRepository.getupPunchToday(todayDate))
    }

    fun insertGetupRecord(getUp: Habit){
        viewModelScope.launch {
            habitRepository.insertGetupRecord(getUp)
        }
    }



}