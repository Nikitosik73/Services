package com.example.services

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.services.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.buttonFirstService.setOnClickListener {
            startService(MyService.newIntent(this, 25))
            // остановка сервиса в другом месте программы
            stopService(MyForegroundService.newIntent(this))
        }

        binding.foregroundService.setOnClickListener {
            ContextCompat.startForegroundService(
                this,
                MyForegroundService.newIntent(this)
            )
        }

        binding.intentService.setOnClickListener {
            // первый способ запуска intentService
            startService(MyIntentService.newIntent(this))
            // второй способ запуска intentService
//            ContextCompat.startForegroundService(
//                this,
//                MyIntentService.newIntent(this)
//            )
        }
    }
}