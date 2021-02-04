package com.carrie.congratulations.utils

import android.content.Context
import androidx.annotation.VisibleForTesting
import com.carrie.congratulations.data.dao.AppDatabase
import com.carrie.congratulations.data.dao.HabitRepository
import com.carrie.congratulations.data.dao.HabitViewModelFactory

/**
 * Created by Carrie on 2020/12/16.
 */
interface ViewModelFactoryProvider {

    fun getupViewModelFactory(context: Context): HabitViewModelFactory


}


val Injector: ViewModelFactoryProvider
    get() = currentInjector

private object DefaultViewModelProvider : ViewModelFactoryProvider {
    override fun getupViewModelFactory(context: Context): HabitViewModelFactory {
        return HabitViewModelFactory(
            HabitRepository.getInstance(
                AppDatabase.getInstance(context).habitDao()
            )
        )
    }


}

private object Lock

@Volatile
private var currentInjector: ViewModelFactoryProvider = DefaultViewModelProvider


@VisibleForTesting
private fun setInjectorForTesting(injector: ViewModelFactoryProvider?) {
    synchronized(Lock) {
        currentInjector = injector ?: DefaultViewModelProvider
    }
}

@VisibleForTesting
private fun resetInjector() = setInjectorForTesting(null)