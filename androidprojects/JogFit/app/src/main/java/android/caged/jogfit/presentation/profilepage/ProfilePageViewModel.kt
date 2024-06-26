package android.caged.jogfit.presentation.profilepage

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfilePageViewModel @Inject constructor(
    private val auth: FirebaseAuth,
) : ViewModel() {

    private val currentUser = auth.currentUser
    val profileImageUrl = currentUser?.photoUrl.toString()
    val displayName = currentUser?.displayName.toString()
    val email = currentUser?.email.toString()

}