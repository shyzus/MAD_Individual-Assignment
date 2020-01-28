package nl.hva.fdmci.mad.mad_individual_assignment.ui.planner

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_planner.*
import nl.hva.fdmci.mad.mad_individual_assignment.R
import nl.hva.fdmci.mad.mad_individual_assignment.ui.task.create.CreateTaskFragmentDirections
import java.text.SimpleDateFormat
import java.util.*

/**
 * A simple [Fragment] subclass.
 *
 */
class PlannerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_planner, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            val action = PlannerFragmentDirections.actionPlannerFragmentToCreateTaskFragment(year, month, dayOfMonth)
            findNavController().navigate(action)
        }

    }

}
