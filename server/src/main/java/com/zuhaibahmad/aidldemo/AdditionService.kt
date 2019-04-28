package com.zuhaibahmad.aidldemo

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class AdditionService : Service() {

    override fun onBind(intent: Intent?): IBinder? {
        Log.e("server", "Binding Service")
        return object : IAddition.Stub() {
            override fun performAddition(numOne: Int, numTwo: Int): Result {
                return Result(numOne, numTwo, numOne + numTwo)
            }
        }
    }
}