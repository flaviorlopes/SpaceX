package com.example.testapplication

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.testapplication.extensions.addFragment
import com.example.testapplication.ui.FilterDialogFragment
import com.example.testapplication.ui.LaunchFragment
import android.content.IntentFilter

import androidx.localbroadcastmanager.content.LocalBroadcastManager

import retrofit2.internal.EverythingIsNonNull

import com.example.testapplication.services.RetrofitCallback

import com.example.testapplication.services.CommunicationCenter

import com.example.testapplication.services.RetrofitHelper

import android.content.Intent

import android.content.BroadcastReceiver
import android.content.Context
import android.util.Log
import com.example.testapplication.services.CommunicationCenter.getRequestUrl


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragCharacter = LaunchFragment()

        if (savedInstanceState == null) {
            addFragment(R.id.fragment_container_view, fragCharacter, "CharacterFragment")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.filter_menu -> {
                val dialog = FilterDialogFragment.newInstance()
                dialog.show(supportFragmentManager, FilterDialogFragment.TAG)
            }
        }
        return super.onOptionsItemSelected(item)
    }


}