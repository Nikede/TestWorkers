package com.nikede.test.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.nikede.test.R
import com.nikede.test.data.db.entities.Worker
import com.nikede.test.internal.glide.GlideApp
import com.nikede.test.ui.workers.list.WorkersListFragmentDirections
import kotlinx.android.synthetic.main.cell_worker.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class WorkersAdapter() : RecyclerView.Adapter<WorkersAdapter.ViewHolder>() {
    private val mWorkersList = ArrayList<Worker>()
    private var context: Context? = null

    fun setupWorkers(context: Context?, workersList: ArrayList<Worker>) {
        mWorkersList.clear()
        mWorkersList.addAll(workersList)
        this.context = context
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.cell_worker, parent, false)
        return ViewHolder(itemView, context)
    }

    override fun getItemCount() = mWorkersList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mWorkersList[position])
    }

    class ViewHolder(itemView: View, val context: Context?) : RecyclerView.ViewHolder(itemView) {
        fun bind(worker: Worker) {
            if (worker.avatarUrl != null && worker.avatarUrl != "" && context != null) {
                GlideApp.with(context)
                    .load(worker.avatarUrl)
                    .into(itemView.worker_avatar)
            }
            var name = worker.lName.substring(0, 1).toUpperCase() + worker.lName.substring(1).toLowerCase()
            name += " " + worker.fName.substring(0, 1).toUpperCase() + worker.fName.substring(1).toLowerCase()
            itemView.worker_txt_name.text = name
            var birthday = "-"
            if (worker.birthday != null && worker.birthday != "") {
                val finalFormat = SimpleDateFormat("dd.MM.yyyy г.")
                var date: Date
                var format: SimpleDateFormat
                val temp = worker.birthday
                if (temp != null && temp.indexOf('-') > 3) {
                    format = SimpleDateFormat("yyyy-MM-dd")
                    date = format.parse(worker.birthday)
                } else {
                    format = SimpleDateFormat("dd-MM-yyyy")
                    date = format.parse(worker.birthday)
                }

                birthday = finalFormat.format(date)
            }
            itemView.worker_txt_birthday.text = birthday
            itemView.setOnClickListener {
                val navController = Navigation.findNavController(itemView)
                val action = WorkersListFragmentDirections.actionWorkersListFragmentToWorkerDetailFragment(worker.lName, worker.fName, birthday, worker.avatarUrl ?: "")
                navController.navigate(action)
            }
        }
    }
}