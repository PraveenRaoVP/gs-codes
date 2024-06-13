package android.caged.videoapplicationapp.presentation.home

import android.caged.videoapplicationapp.model.Data

data class HomeUiState(
    val users: List<Pair<String,String>> = emptyList(),
    val callReceiver: String? = null,
    val isCallReceived: Boolean = false,
    val callReceiveText: String? = null
)