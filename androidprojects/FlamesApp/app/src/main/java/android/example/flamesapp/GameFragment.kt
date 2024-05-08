package android.example.flamesapp

import android.example.flamesapp.databinding.FragmentGameBinding
import android.graphics.Color
import android.graphics.ColorFilter
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.Typeface
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.annotation.ColorInt
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieProperty
import com.airbnb.lottie.model.KeyPath
import com.airbnb.lottie.value.LottieFrameInfo
import com.airbnb.lottie.value.LottieValueCallback
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class GameFragment : Fragment() {

    data class Game(val name1: String, val name2: String)


    private lateinit var binding: FragmentGameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(savedInstanceState != null) {
            binding.name1EditText.setText(savedInstanceState.getString("name1"))
            binding.name2EditText.setText(savedInstanceState.getString("name2"))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_game, container, false)

        // set font for the name fields to jetbrains mono
        val customFont: Typeface? = ResourcesCompat.getFont(requireContext(), R.font.jetbrains_mono_extrabold)

        // Apply the font to the TextInputEditText
        val name1EditText: TextInputEditText = binding.name1EditText
        name1EditText.typeface = customFont


        val shakeAnimation = AnimationUtils.loadAnimation(context, R.anim.shake_animation)

        // Set an animation listener to reset the animation after it finishes
        shakeAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {}

            override fun onAnimationEnd(animation: Animation) {
                // Reset the position of the LottieAnimationView
                binding.heartAnimation.rotation = 0f
            }

            override fun onAnimationRepeat(animation: Animation) {}
        })

        binding.heartAnimation.setOnClickListener {view: View ->
            val name1 = binding.name1EditText.text.toString().trim()
            val name2 = binding.name2EditText.text.toString().trim()

            // if name1 or name2 is empty, show a toast
            if(name1.isEmpty() || name2.isEmpty()) {
                binding.heartAnimation.startAnimation(shakeAnimation)
                if (name1.isEmpty()) {
                    binding.name1EditText.error = "Name 1 is required!"
                    Toast.makeText(context, "Name 1 is required!", Toast.LENGTH_SHORT).show()
                }
                if (name2.isEmpty()) {
                    binding.name2EditText.error = "Name 2 is required!"
                    Toast.makeText(context, "Name 2 is required!", Toast.LENGTH_SHORT).show()
                }
                return@setOnClickListener
            } else if (!name1.matches("^[a-zA-Z.\\s]+$".toRegex()) || !name2.matches("^[a-zA-Z.\\s]+$".toRegex())) {
                binding.heartAnimation.startAnimation(shakeAnimation)
                if (!name1.matches("^[a-zA-Z.\\s]+$".toRegex())) {
                    binding.name1EditText.error = "Please enter a valid name without numbers or special characters!"
                    Toast.makeText(context, "Please enter a valid name without numbers or special characters!", Toast.LENGTH_SHORT).show()
                }
                if (!name2.matches("^[a-zA-Z.\\s]+$".toRegex())) {
                    binding.name2EditText.error = "Please enter a valid name without numbers or special characters!"
                    Toast.makeText(context, "Please enter a valid name without numbers or special characters!", Toast.LENGTH_SHORT).show()
                }
            }

            else {
                repeat(3) {
                    binding.heartAnimation.playAnimation()
                    // Delay between each animation
                    Handler().postDelayed({
                        // If it's the last animation, navigate to the next fragment
                        if (it == 2) {
                            view.findNavController().navigate(GameFragmentDirections.actionGameFragmentToResultFragment(name1, name2))
                        }
                    }, 1000 * it.toLong()) // Delay increases for each animation
                }
            }
        }



        return binding.root
    }

    companion object {


    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("name1", binding.name1EditText.text.toString())
        outState.putString("name2", binding.name2EditText.text.toString())
    }
}