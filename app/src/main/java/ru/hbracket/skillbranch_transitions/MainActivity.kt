package ru.hbracket.skillbranch_transitions

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.transition.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var filtersState: FiltersState = FiltersState.Collapsed

    sealed class FiltersState {
        object Collapsed : FiltersState()
        object Expanded : FiltersState()
    }

    private val transition = TransitionSet().apply {
        duration = 180
        addTransition(ChangeBounds())
        addTransition(Fade())
        addTransition(Rotate())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        filters_root.setOnClickListener {
            val result: Unit = TransitionManager.beginDelayedTransition(filters_root, transition)
            when (filtersState) {
                is FiltersState.Collapsed -> {
                    filters_arrow.rotation = 180f
                    filters_list.visibility = View.VISIBLE
                    filtersState = FiltersState.Expanded
                }
                is FiltersState.Expanded -> {
                    filters_arrow.rotation = 0f
                    filters_list.visibility = View.GONE
                    filtersState = FiltersState.Collapsed
                }
            }
        }
    }
}