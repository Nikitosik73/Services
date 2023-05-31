package com.example.services

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
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

        binding.jobScheduler.setOnClickListener {
            // указываем какой сервис нам нужен
            val componentName = ComponentName(this, MyJobService::class.java)
            // устанавливаем все ограничения
            val jobInfo = JobInfo.Builder(MyJobService.JOB_ID, componentName)
                .setRequiresCharging(true)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                .setPersisted(true)
                .build()
            // запускаем на выполнение
            val jobScheduler = getSystemService(JOB_SCHEDULER_SERVICE) as JobScheduler
            jobScheduler.schedule(jobInfo)
        }
    }
}
