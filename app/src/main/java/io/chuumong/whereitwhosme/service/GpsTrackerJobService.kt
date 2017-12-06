package io.chuumong.whereitwhosme.service

import android.app.job.JobInfo
import android.app.job.JobParameters
import android.app.job.JobScheduler
import android.app.job.JobScheduler.RESULT_SUCCESS
import android.app.job.JobService
import android.content.ComponentName
import android.content.Context
import android.os.Build
import android.util.Log
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Created by jonghunlee on 2017. 12. 4..
 */
class GpsTrackerJobService : JobService() {

    companion object {
        private val JOB_ID = 9002

        fun setJobScheduler(context: Context) {
            val TAG = GpsTrackerJobService::class.java.simpleName
            Log.d(TAG, "setJobScheduler")

            val jobScheduler = context.getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
            val jobInfo = JobInfo.Builder(JOB_ID, ComponentName(context, GpsTrackerJobService::class.java))

            // SDK_INT >= API 26 # Interval time is 15m, flex time is 5m
            jobInfo.setPeriodic(10 * 1000)
            jobInfo.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                    .setPersisted(true)

            if (jobScheduler.schedule(jobInfo.build()) == RESULT_SUCCESS) {
                Log.d(TAG, "success schedule job info")
            }
        }
    }

    private val TAG = GpsTrackerJobService::class.java.simpleName

    override fun onStartJob(params: JobParameters?): Boolean {
        Timer().schedule(object : TimerTask() {
            override fun run() {
                Log.d(TAG, "Finish Job Service")
                jobFinished(params, true)
            }
        }, 2000L)

        return true
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        Log.d(TAG, "onStopJob")
        return false
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy")

        super.onDestroy()
    }
}