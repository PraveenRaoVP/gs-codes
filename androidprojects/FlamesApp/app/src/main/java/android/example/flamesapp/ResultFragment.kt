package android.example.flamesapp

import android.example.flamesapp.databinding.FragmentResultBinding
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val binding: FragmentResultBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_result, container, false)

        val viewKonfetti = binding.konfettiView

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

        binding.resultTextView.text = "$name1 and $name2 are $result"

        binding.button.setOnClickListener { view: View ->
            view.findNavController()
                .navigate(ResultFragmentDirections.actionResultFragmentToTitleFragment())
        }

        return binding.root
    }

//    private fun calculateResult(name1: String, name2: String): String {
//        // Remove spaces and convert to lowercase
//        val name1Cleaned = name1.replace("\\s".toRegex(), "").lowercase()
//        val name2Cleaned = name2.replace("\\s".toRegex(), "").lowercase()
//
//        // Eliminate common characters
//        val commonChars = name1Cleaned.filter { name2Cleaned.contains(it) }
//        val remainingChars = name1Cleaned.length + name2Cleaned.length - 2 * commonChars.length
//
//        if(remainingChars == 0) {
//            val finalAns = listOf("Friends", "Lovers", "Affectionate", "Marriage", "Enemies", "Siblings").random()
//            return finalAns
//        }
//
//        // FLAMES acronym
//        val flames = "FLAMES"
//
//        // Calculate result
//        var index = 0
//        var count = 0
//        var flamesString = flames
//        while (flamesString.length > 1) {
//            index = (index + remainingChars - 1) % flamesString.length
//            val endIndex = index + 1
//            if (index >= 0 && endIndex <= flamesString.length) {
//                flamesString = flamesString.removeRange(index, endIndex)
//            } else {
//                val finalAns = listOf("Friends", "Lovers", "Affectionate", "Marriage", "Enemies", "Siblings").random()
//                return finalAns
//            }
//            count++
//            if (count == flames.length) break
//        }
//
//        // Interpret result
//        return when (flamesString) {
//            "F" -> "Friends"
//            "L" -> "Lovers"
//            "A" -> "Affectionate"
//            "M" -> "Marriage"
//            "E" -> "Enemies"
//            "S" -> "Siblings"
//            else -> "Invalid"
//        }
//    }

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

//        Log.i("ResultFragment Name 1: ", "${String(name1Chars)}")
//        Log.i("ResultFragment Name 2: ", "${String(name2Chars)}")

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

    companion object {

    }
}