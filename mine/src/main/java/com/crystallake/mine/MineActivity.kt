package com.crystallake.mine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.crystallake.router.annotation.Route

@Route("/mine/MineActivity")
class MineActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mine)
    }
}