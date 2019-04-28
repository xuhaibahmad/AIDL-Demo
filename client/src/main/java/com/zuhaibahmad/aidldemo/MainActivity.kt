package com.zuhaibahmad.aidldemo

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.btEquals
import kotlinx.android.synthetic.main.activity_main.etNumOne
import kotlinx.android.synthetic.main.activity_main.etNumTwo
import kotlinx.android.synthetic.main.activity_main.tvResult

class MainActivity : AppCompatActivity(), ServiceConnection {

    private var isBound: Boolean = false
    private var iRemoteService: IAddition? = null

    // Called when the connection with the service is established
    override fun onServiceConnected(className: ComponentName, service: IBinder) {
        // Following the example above for an AIDL interface,
        // this gets an instance of the IRemoteInterface, which we can use to call on the service
        Log.e("client", "Service connected!")
        iRemoteService = IAddition.Stub.asInterface(service)
        isBound = true
    }

    // Called when the connection with the service disconnects unexpectedly
    override fun onServiceDisconnected(className: ComponentName) {
        Log.e("client", "Service disconnected!")
        iRemoteService = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bindService()

        btEquals.setOnClickListener {
            onPerformAddition()
        }
    }

    private fun onPerformAddition() {
        val numOne = etNumOne.text.toString().toInt()
        val numTwo = etNumTwo.text.toString().toInt()

        tvResult.text = if (isBound) {
            val result = iRemoteService?.performAddition(numOne, numTwo)
            "$numOne + $numTwo = $result"
        } else {
            "Service not bound!"
        }
    }

    override fun onDestroy() {
        unbindService()
        super.onDestroy()
    }

    private fun bindService() {
        Log.e("client", "Attempting to bind service")
        val serviceIntent = Intent()
        serviceIntent.setClassName(PACKAGE_NAME, SERVICE_NAME)
        serviceIntent.action = ACTION_REMOTE_BIND
        bindService(serviceIntent, this, Context.BIND_AUTO_CREATE)
    }

    private fun unbindService() {
        if (isBound) {
            // Detach our existing connection.
            unbindService(this)
            isBound = false
        }
    }

    companion object {
        @JvmStatic
        val PACKAGE_NAME: String = "com.zuhaibahmad.aidldemo"

        @JvmStatic
        val SERVICE_NAME: String = "com.zuhaibahmad.aidldemo.AdditionService"

        @JvmStatic
        val ACTION_REMOTE_BIND = "$SERVICE_NAME-remote-bind"
    }
}
