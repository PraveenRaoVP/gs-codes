package android.caged.jogfit.presentation.auth.signup

import android.caged.jogfit.ext.isMatchingPassword
import android.caged.jogfit.ext.isValidEmail
import android.caged.jogfit.ext.isValidPassword
import android.caged.jogfit.ext.isValidUsername
import android.caged.jogfit.presentation.navigation.Routes
import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : ViewModel() {
    var uiState = mutableStateOf(SignUpUiState())
        private set

    private val username
        get() = uiState.value.username
    private val password
        get() = uiState.value.password
    private val repeatPassword
        get() = uiState.value.repeatPassword
    private val email
        get() = uiState.value.email


    fun onUsernameChange(newValue: String) {
        uiState.value = uiState.value.copy(username = newValue)
    }

    fun onEmailChange(newValue: String) {
        uiState.value = uiState.value.copy(email = newValue)
    }

    fun onPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(password = newValue)
    }

    fun onRepeatPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(repeatPassword = newValue)
    }

    fun clearAllErrors() {
        uiState.value = uiState.value.copy(
            isUsernameError = false,
            isEmailError = false,
            isPasswordError = false,
            isRepeatPasswordError = false,
            usernameError = "",
            emailError = "",
            passwordError = "",
            repeatPasswordError = ""
        )
    }

    fun onEvent(event: SignUpEvent, context: Context, navigatePopUp: (String, String) -> Unit) {
        when(event) {
            is SignUpEvent.OnSignUpClick -> {
                onSignUpClick(navigatePopUp = navigatePopUp, context = context)
            }
            is SignUpEvent.EmailChanged -> {
                onEmailChange(event.email)
            }
            is SignUpEvent.UsernameChanged -> {
                onUsernameChange(event.username)
            }
            is SignUpEvent.PasswordChanged -> {
                onPasswordChange(event.password)
            }
            is SignUpEvent.ConfirmPasswordChanged -> {
                onRepeatPasswordChange(event.confirmPassword)
            }
            is SignUpEvent.ClearAllErrors -> {
                clearAllErrors()
            }
        }
    }

    fun onSignUpClick(navigatePopUp: (String, String) -> Unit, context: Context) {
        val proceedToSignUp = mutableStateOf(true)
        if (!uiState.value.username.isValidUsername()) {
            proceedToSignUp.value = false
            uiState.value =
                uiState.value.copy(isUsernameError = true, usernameError = "Invalid Username")
        }
        if (!uiState.value.email.isValidEmail()) {
            proceedToSignUp.value = false
            uiState.value = uiState.value.copy(isEmailError = true, emailError = "Invalid Email")
        }
        if (!uiState.value.password.isValidPassword()) {
            proceedToSignUp.value = false
            uiState.value = uiState.value.copy(
                isPasswordError = true,
                passwordError = "Password must be at least 8 characters long, contain atleast one uppercase letter, and one number."
            )
        }
        if (!uiState.value.password.isMatchingPassword(repeatPassword) && uiState.value.password.isValidPassword()) {
            proceedToSignUp.value = false
            uiState.value = uiState.value.copy(
                isRepeatPasswordError = true,
                repeatPasswordError = "Passwords do not match"
            )
            return
        }
        if (proceedToSignUp.value)
            createNewUser(navigatePopUp, context)
    }

    private fun createNewUser(navigatePopUp: (String, String) -> Unit, context: Context) {
        // TODO: Implement user creation login in firebase, create a new user pojo and save it to the firebase.
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    val userId = user?.uid ?: ""
                    val userMap = hashMapOf(
                        "username" to username,
                        "email" to email
                    )
                    firestore.collection("users").document(userId)
                        .set(userMap)
                        .addOnSuccessListener {
                            uiState.value = uiState.value.copy(isLoading = false)
                            Toast.makeText(context, "Sign Up Completed Successfully.", Toast.LENGTH_SHORT).show()
                            navigatePopUp(
                                Routes.LoginWithEmailPasswordRoute.route,
                                Routes.SignUpRoute.route
                            )
                        }
                        .addOnFailureListener { e ->
                            uiState.value = uiState.value.copy(isLoading = false)
                            Toast.makeText(
                                context,
                                "Failed to save user data: ${e.message}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                } else {
                    uiState.value = uiState.value.copy(isLoading = false)
                    Toast.makeText(
                        context,
                        "Failed to create user: ${task.exception?.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

}