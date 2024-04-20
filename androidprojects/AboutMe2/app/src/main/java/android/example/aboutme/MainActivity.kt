package android.example.aboutme

import android.content.Context
import android.example.aboutme.databinding.ActivityMainBinding
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import android.example.aboutme.ui.theme.AboutMeTheme
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.DataBindingUtil

class MainActivity : ComponentActivity() {

    private lateinit var binding: ActivityMainBinding;

    private val myName: Myname = Myname("Praveen Rao")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.myName = myName

        binding.doneButton.setOnClickListener() {
            addNickname(it)
        }
    }

    private fun addNickname(view: View) {
        binding.apply {
//            binding.nicknameText.text = "Hi, " + binding.nickNameEdit.text+"!" // update the nickname text
            myName?.nickname = "Hi, " + nickNameEdit.text.toString()+"!" // update the nickname text using data binding -
            // myName is a nullable variable and hence the safe call operator.
            // nickNameEdit is a view and hence the text property is a string, it is present in the EditText class.
            // How it is present in the EditText class? It is present in the TextView class and EditText class extends TextView class. Hence, the text property is available in the EditText class.
            // How are we accessing the text property of the EditText class? We are accessing the text property of the EditText class
            // using the nickNameEdit view.
            // How are we accessing the nickNameEdit view? We are accessing the nickNameEdit view using the binding object.
            invalidateAll() // refresh the UI with data binding - invalidateAll() is a method in the View class.
            binding.nickNameEdit.visibility = View.GONE
            binding.doneButton.visibility = View.GONE
            binding.nicknameText.visibility = View.VISIBLE
        }
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}


/*
Data Binding - The Idea
The big idea about data binding is to create an object that connects/maps/binds two pieces of distant information together at compile time, so that you don't have to look for it at runtime.
The object that surfaces these bindings to you is called the Binding object. It is created by the compiler, and while understanding how it works under the hood is interesting, it is not necessary to know for basic uses of data binding.
Data Binding and findViewById
findViewById is a costly operation because it traverses the view hierarchy every time it is called.
With data binding enabled, the compiler creates references to all views in a <layout> that have an id, and gathers them in a Binding object.
In your code, you create an instance of the binding object, and then reference views through the binding object with no extra overhead.



 */