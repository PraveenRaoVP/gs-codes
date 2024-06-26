package android.caged.videoapplicationapp.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.caged.videoapplicationapp.ui.CloseActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainServiceReceiver : BroadcastReceiver() {

    @Inject lateinit var serviceRepository: MainServiceRepository
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == "ACTION_EXIT"){
            //we want to exit the whole application
            serviceRepository.stopService()
            context?.startActivity(Intent(context, CloseActivity::class.java))

        }

    }
}