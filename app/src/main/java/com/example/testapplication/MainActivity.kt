package com.example.testapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.testapplication.extensions.addFragment
import com.example.testapplication.ui.LaunchFragment


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragCharacter = LaunchFragment()

        if (savedInstanceState == null) {
            addFragment(R.id.fragment_container_view, fragCharacter, "CharacterFragment")
        }
    }
}