package nl.hva.fdmci.mad.mad_individual_assignment.ui.task.create

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_create_task.*
import nl.hva.fdmci.mad.mad_individual_assignment.R
import nl.hva.fdmci.mad.mad_individual_assignment.database.Task

class CreateTaskFragment : Fragment() {

    private lateinit var createTaskViewModel: CreateTaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        createTaskViewModel =
            ViewModelProviders.of(this).get(CreateTaskViewModel::class.java)

        createTaskViewModel.task.value = Task("", "")

        createTaskViewModel.task.observe(this, Observer { task ->
            if (task != null) {
                etTitle.setText(task.title)
                etDescription.setText(task.description)
            }
        })

        createTaskViewModel.error.observe(this, Observer { message ->
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        })

        createTaskViewModel.success.observe(this, Observer { success ->
            if (success != null && success) {
                val action =
                    CreateTaskFragmentDirections.actionCreateTaskFragmentToTaskFragment()
                findNavController().navigate(action)
            }
        })

        return inflater.inflate(R.layout.fragment_create_task, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        saveTaskFab.setOnClickListener {
            createTaskViewModel.task.value?.apply {
                title = etTitle?.text.toString()
                description = etDescription?.text.toString()
            }
            createTaskViewModel.insertTask()

        }
    }

}
