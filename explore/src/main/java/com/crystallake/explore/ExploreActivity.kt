package com.crystallake.explore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.crystallake.explore.fragment.GalleryFragment

class ExploreActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explore)

        supportFragmentManager.beginTransaction().replace(R.id.container,GalleryFragment()).commit()
    }
}