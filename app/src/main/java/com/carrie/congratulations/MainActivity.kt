package com.carrie.congratulations

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.TextAppearanceSpan
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.carrie.congratulations.data.dao.Habit
import com.carrie.congratulations.utils.Injector
import com.carrie.congratulations.utils.LogUtil.i
import com.carrie.congratulations.utils.currentTime
import com.carrie.congratulations.utils.todayDate
import com.carrie.congratulations.viewModel.HabitViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: HabitViewModel
    private lateinit var tvGetUpPunch: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvGetUpPunch = findViewById(R.id.tvGetUpPunch)

        viewModel = ViewModelProvider(
            this,
            Injector.getupViewModelFactory(applicationContext)
        )[HabitViewModel::class.java]

        viewModel.getUpToday.observe(this, {
            if (it != null) {
                i("已完成早起打卡")
                tvGetUpPunch.isClickable = false

                val ss = SpannableString("完成\n${it.name.substring(0, 2)}\n${it.time}")
                i("ss=$ss, length=${ss.length}")
                val span0 = TextAppearanceSpan(
                    "serif",
                    Typeface.NORMAL,
                    25,
                    ColorStateList.valueOf(Color.WHITE),
                    ColorStateList.valueOf(Color.WHITE)
                )
                val span1 = TextAppearanceSpan(
                    "serif",
                    Typeface.NORMAL,
                    20,
                    ColorStateList.valueOf(Color.WHITE),
                    ColorStateList.valueOf(Color.WHITE)
                )
                val span2 = TextAppearanceSpan(
                    "serif",
                    Typeface.BOLD,
                    48,
                    ColorStateList.valueOf(Color.WHITE),
                    ColorStateList.valueOf(Color.WHITE)
                )
                ss.setSpan(span0, 0, 3, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
                ss.setSpan(span2, 3, 5, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
                ss.setSpan(span1, 6, ss.length, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
                tvGetUpPunch.text = ss

            }
        })

        tvGetUpPunch.setOnClickListener {
            i("insertGetupRecord")
            viewModel.insertGetupRecord(
                Habit(
                    todayDate,
                    currentTime,
                    getString(R.string.habit_get_up)
                )
            )
        }


    }
}