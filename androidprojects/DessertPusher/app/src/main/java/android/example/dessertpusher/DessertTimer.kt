package android.example.dessertpusher///*
/*
*  * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import android.os.Handler
import android.os.Looper
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import timber.log.Timber

/**
 * This is a class representing a timer that you can start or stop. The secondsCount outputs a count of
 * how many seconds since it started, every one second.
 *
 * -----
 *
 * Handler and Runnable are beyond the scope of this lesson. This is in part because they deal with
 * threading, which is a complex topic that will be covered in a later lesson.
 *
 * If you want to learn more now, you can take a look on the Android Developer documentation on
 * threading:
 *
 * https://developer.android.com/guide/components/processes-and-threads
 *
 */
class DessertTimer(lifecycle: Lifecycle): LifecycleObserver {

    init {
        lifecycle.addObserver(this)
    }

    var secondsCount = 0
    private var handler = Handler(Looper.getMainLooper())

    private lateinit var runnable: Runnable

    fun startTimer() {
        // Create the runnable action
        runnable = object : Runnable {
            override fun run() {
                secondsCount++
                Timber.i("Timer is at : $secondsCount")
                // Post the same runnable again after 1 second (1000ms)
                handler.postDelayed(this, 1000L)
            }
        }

        // Post the runnable to start the timer
        handler.postDelayed(runnable, 1000L)
    }


    fun stopTimer() {
        // Removes all pending posts of runnable from the handler's queue, effectively stopping the
        // timer
        handler.removeCallbacks(runnable)
    }
}