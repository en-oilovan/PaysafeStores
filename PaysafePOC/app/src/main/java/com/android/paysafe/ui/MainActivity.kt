package com.android.paysafe.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.paysafe.R
import com.android.paysafe.ui.stores.StoreListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, StoreListFragment.newInstance())
                .commitNow()
        }
    }
}