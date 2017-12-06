package io.chuumong.whereitwhosme

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import io.chuumong.whereitwhosme.service.GpsTrackerJobService

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GpsTrackerJobService.setJobScheduler(this)
    }
}
