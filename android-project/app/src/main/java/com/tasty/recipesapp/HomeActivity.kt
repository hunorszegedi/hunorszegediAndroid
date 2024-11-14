package com.tasty.recipesapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {

    private val TAG = "HomeActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        Log.d(TAG, "onCreate: HomeActivity created.")

        // TODO: Implement any additional setup for the home screen
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: HomeActivity started.")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: HomeActivity resumed.")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: HomeActivity paused.")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: HomeActivity stopped.")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart: HomeActivity restarted.")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: HomeActivity destroyed.")
    }
}
