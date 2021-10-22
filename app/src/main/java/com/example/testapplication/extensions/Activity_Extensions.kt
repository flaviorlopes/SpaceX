package com.example.testapplication.extensions

import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.annotation.IdRes

fun AppCompatActivity.addFragment(@IdRes layoutId: Int, fragment: Fragment, tag: String? = null) {
    fragment.arguments = intent.extras
    val ft = supportFragmentManager.beginTransaction()
    ft.add(layoutId, fragment, tag)
    ft.commit()
}