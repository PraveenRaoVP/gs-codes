package android.example.flamesapp

import android.example.flamesapp.databinding.FragmentResultBinding
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import java.util.Locale

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
        val name1 = arguments.let { ResultFragmentArgs.fromBundle(it!!).name1.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(
                Locale.getDefault()
            ) else it.toString()
        } }
        val name2 = arguments.let { ResultFragmentArgs.fromBundle(it!!).name2.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(
                Locale.getDefault()
            ) else it.toString()
        } }
        val result = calculateResult(name1, name2)

        binding.resultTextView.text = "$name1 and $name2 are $result"
        return binding.root
    }

    private fun calculateResult(name1: String, name2: String): String {
        // Remove spaces and convert to lowercase
        val name1Cleaned = name1.replace("\\s".toRegex(), "").lowercase()
        val name2Cleaned = name2.replace("\\s".toRegex(), "").lowercase()

        // Eliminate common characters
        val commonChars = name1Cleaned.filter { name2Cleaned.contains(it) }
        val remainingChars = name1Cleaned.length + name2Cleaned.length - 2 * commonChars.length

        if(remainingChars == 0) return "Invalid"
        if(remainingChars == 1) return "Friends"

        // FLAMES acronym
        val flames = "FLAMES"

        // Calculate result
        var index = 0
        var count = 0
        var flamesString = flames
        while (flamesString.length > 1) {
            index = (index + remainingChars - 1) % flamesString.length
            val endIndex = index + 1
            if (index >= 0 && endIndex <= flamesString.length) {
                flamesString = flamesString.removeRange(index, endIndex)
            } else {
                return "Invalid"
            }
            count++
            if (count == flames.length) break
        }

        // Interpret result
        return when (flamesString) {
            "F" -> "Friends"
            "L" -> "Lovers"
            "A" -> "Affectionate"
            "M" -> "Marriage"
            "E" -> "Enemies"
            "S" -> "Siblings"
            else -> "Invalid"
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ResultFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ResultFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}