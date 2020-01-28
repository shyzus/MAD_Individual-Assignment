package nl.hva.fdmci.mad.mad_individual_assignment.ui.task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.component_task.*
import kotlinx.android.synthetic.main.fragment_task.*
import nl.hva.fdmci.mad.mad_individual_assignment.R
import nl.hva.fdmci.mad.mad_individual_assignment.database.TaskAdapter

/**
 * A simple [Fragment] subclass.
 *
 */
class TasksFragment : Fragment() {

    private lateinit var tasksViewModel: TasksViewModel
    private lateinit var taskAdapter: TaskAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        tasksViewModel =
            ViewModelProviders.of(this).get(TasksViewModel::class.java)
        tasksViewModel.tasks.observe(this.viewLifecycleOwner, Observer {
            it.forEach{ task ->
                tvTitle?.text = task.title
                tvDescription?.text = task.description
            }

            taskAdapter = TaskAdapter(tasksViewModel.tasks.value)

            rvTask.adapter = taskAdapter
            rvTask.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

            taskAdapter.notifyDataSetChanged()
        })


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_task, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tasksViewModel.createItemTouchHelper().attachToRecyclerView(rvTask)

        createTaskFab.setOnClickListener {
            val action =
                TasksFragmentDirections.actionTaskFragmentToCreateTaskFragment()
            findNavController().navigate(action)
        }
    }

}
