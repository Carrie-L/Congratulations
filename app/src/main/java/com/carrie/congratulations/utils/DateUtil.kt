package com.carrie.congratulations.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Carrie on 2021/2/3.
 */


/**
 * 获取今天的日期
 * @return String yyyy-MM-dd
 */
@SuppressLint("ConstantLocale")
val todayDate: String = run {
    val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    format.format(Calendar.getInstance().time)
}

@SuppressLint("ConstantLocale")
val currentTime: String = run {
    val format = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
    format.format(Calendar.getInstance().time)
}
