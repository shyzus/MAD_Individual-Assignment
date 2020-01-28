package nl.hva.fdmci.mad.mad_individual_assignment.database

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.component_task.view.*
import nl.hva.fdmci.mad.mad_individual_assignment.R
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class TaskAdapter(private val tasks: List<Task>?) :
    RecyclerView.Adapter<TaskAdapter.ViewHolder>(){

    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.component_task, parent, false)
        )
    }
    override fun getItemCount(): Int {
        return tasks?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(tasks?.get(position))
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(task : Task?) {
            itemView.tvTitle.text = task?.title
            itemView.tvDescription.text = task?.description
            val sdf= SimpleDateFormat("d LLLL YYYY", Locale.ENGLISH)
            itemView.tvDate.text = sdf.format(task?.date)

        }
    }
}