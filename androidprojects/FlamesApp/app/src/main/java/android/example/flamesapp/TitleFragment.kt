package android.example.flamesapp

import android.example.flamesapp.databinding.FragmentTitleBinding
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewPropertyAnimator
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TitleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class TitleFragment : Fragment() {
    private val handler = Handler()
    private lateinit var cardView: CardView
    private var animationRunnable: Runnable? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentTitleBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_title, container, false)

        cardView = binding.titleCardView
        /* repeat the animation every 2 seconds */
        startAnimation()

        binding.titleImage.setOnClickListener {view: View ->
            view.findNavController().navigate(TitleFragmentDirections.actionTitleFragmentToGameFragment())
            val mainActivity = activity as MainActivity
            mainActivity.doubleBackToExitPressedOnce = false
            stopAnimation()
        }

        return binding.root
    }

    private fun startAnimation() {
        animationRunnable = Runnable {
            val animator: ViewPropertyAnimator =
                cardView.animate().scaleX(1.2f).scaleY(1.2f).setDuration(ANIMATION_DELAY * 3 / 2)
            animator.withEndAction {
                cardView.animate().scaleX(1.0f).scaleY(1.0f).setDuration(ANIMATION_DELAY * 3 / 2)

                handler.postDelayed(animationRunnable!!, ANIMATION_DELAY)
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
        private const val ANIMATION_DELAY = 1000L // Animation delay in milliseconds
    }
}