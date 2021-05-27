package lk.game.cocktails.fragments.game.listeners

import android.content.Context
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.widget.Toast
import lk.game.cocktails.TAG
import kotlin.math.abs

class OnSwipeListener(private val context: Context) : OnTouchListener {

    private val MIN_DISTANCE = 200f
    private var x1 = 0f

    override fun onTouch(view: View, event: MotionEvent): Boolean {
        return when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                x1 = event.x
                Log.d(TAG, "ACTION_CLICK $x1")
                true
            }
            MotionEvent.ACTION_UP -> {
                val x2 = event.x
                Log.d(TAG, "ACTION_UNCLICK $x2")
                if (abs(x2 - x1) > MIN_DISTANCE && x2 < x1) {
                    Toast.makeText(context, "Свайп влево", Toast.LENGTH_SHORT).show()
                    return true
                }
                false
            }
            else -> false
        }
    }
}