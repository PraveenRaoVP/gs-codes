package android.example.flamesapp

import android.example.flamesapp.databinding.FragmentResultBinding
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewPropertyAnimator
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import nl.dionsegijn.konfetti.core.Party
import nl.dionsegijn.konfetti.core.Position
import nl.dionsegijn.konfetti.core.emitter.Emitter
import java.util.Locale
import java.util.concurrent.TimeUnit

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ResultFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ResultFragment : Fragment() {
    private val handler = Handler()
    private lateinit var linearLayout: LinearLayout
    private var animationRunnable: Runnable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val binding: FragmentResultBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_result, container, false)

        linearLayout = binding.button

        val viewKonfetti = binding.konfettiView

        startAnimation()

        val party = Party(
            speed = 0f,
            maxSpeed = 30f,
            damping = 0.9f,
            spread = 360,
            colors = listOf(0xfce18a, 0xff726d, 0xf4306d, 0xb48def),
            emitter = Emitter(duration = 100, TimeUnit.MILLISECONDS).max(100),
            position = Position.Relative(0.5, 0.3)
        )
        viewKonfetti.start(party)

        val name1 = arguments.let { it ->
            ResultFragmentArgs.fromBundle(it!!).name1.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(
                Locale.getDefault()
            ) else it.toString()
        } }
        val name2 = arguments.let { it ->
            ResultFragmentArgs.fromBundle(it!!).name2.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(
                Locale.getDefault()
            ) else it.toString()
        } }
        val result = calculateResult2(name1, name2)


        binding.name1Text.text = name1
        binding.name2Text.text = name2
        binding.resultTextView.text = "$result !"

        // change color for each result
        var color: String = "#000000"
        when(result) {
            "Friends" -> color = "#FFC107" // Amber
            "Lovers" -> color = "#FF5722" // Deep Orange
            "Affectionate" -> color = "#FFEB3B" // Yellow
            "Marriage" -> color = "#4CAF50" // Green
            "Enemies" -> color = "#F44336" // Red
            "Siblings" -> color = "#2196F3" // Blue
        }

        binding.resultTextView.setTextColor(android.graphics.Color.parseColor(color))

        binding.button.setOnClickListener { view: View ->
            navigationToTitleFragment(view)
        }

        binding.cardView.setOnClickListener { view: View ->
            navigationToTitleFragment(view)
        }

        binding.navBtnImage.setOnClickListener {view: View ->
            navigationToTitleFragment(view)
        }

        return binding.root
    }

    fun navigationToTitleFragment(view: View) {
        view.findNavController()
            .navigate(ResultFragmentDirections.actionResultFragmentToGameFragment())
        stopAnimation()
    }

    private fun calculateResult2(name1: String, name2: String): String {
        var sc = 0 // Similar characters count
        val flames = "flames".toCharArray() // Possible results: [f, l, a, m, e, s]

        val name1Chars = name1.toCharArray()
        val name2Chars = name2.toCharArray()

        // Count similar characters
        for (i in name1Chars.indices) {
            if(name1Chars[i] == ' ') name1Chars[i] = '-'
            val c = name1Chars[i]
            for (j in name2Chars.indices) {
                if(name2Chars[j] == ' ') name2Chars[j] = '-'
                if (c == name2Chars[j]) {
                    name1Chars[i] = '-'
                    name2Chars[j] = '-' // Mark matched characters with '-'
                    sc += 2
                    break
                }
            }
        }

        // Count remaining characters
        val rc = name1.length + name2.length - sc

        if(rc == 0) {
            return "the same person."
        }

        var fc = 5 // Remaining flames: f, l, a, m, e
        var i = 0
        var l = 1 // Index for removing flames
        while (i >= 0) {
            if (l == rc) {
                for (k in i until flames.size - 1) {
                    flames[k] = flames[k + 1] // Remove character from flames array
                }
                flames[flames.size - 1] = '0'
                fc--
                i--
                l = 0
            }
            if (i == fc) {
                i = -1
            }
            if (fc == 0) {
                break
            }
            l++
            i++
//            Log.i("ResultFragment Flames: ", "${String(flames)}")
        }

        // Print the result
        val result = flames[0]
        return when (result) {
            'e' -> "Enemies"
            'f' -> "Friends"
            'm' -> "Marriage"
            'l' -> "Lovers"
            'a' -> "Affectionate"
            else -> "Siblings"
        }
    }

    private fun startAnimation() {
        animationRunnable = Runnable {
            val animator: ViewPropertyAnimator =
                linearLayout.animate().scaleX(1.2f).scaleY(1.2f).setDuration(ResultFragment.ANIMATION_DELAY * 3 / 2)
            animator.withEndAction {
                linearLayout.animate().scaleX(1.0f).scaleY(1.0f).setDuration(ResultFragment.ANIMATION_DELAY * 3 / 2)

                handler.postDelayed(animationRunnable!!, ResultFragment.ANIMATION_DELAY)
            }
            animator.start()
        }
        handler.post(animationRunnable!!)
    }

    private fun stopAnimation() {
        handler.removeCallbacks(animationRunnable!!)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        stopAnimation()
    }

    companion object {
        const val ANIMATION_DELAY: Long = 1000
    }
}