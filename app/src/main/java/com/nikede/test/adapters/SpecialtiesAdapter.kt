package com.nikede.test.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.nikede.test.R
import com.nikede.test.data.db.entities.Specialty
import com.nikede.test.ui.specialties.SpecialtiesListFragmentDirections
import kotlinx.android.synthetic.main.cell_specialty.view.*

class SpecialtiesAdapter() : RecyclerView.Adapter<SpecialtiesAdapter.ViewHolder>() {
    private val mSpecialtiesList = ArrayList<Specialty>()

    fun setupSpecialties(specialtiesList: ArrayList<Specialty>) {
        mSpecialtiesList.clear()
        mSpecialtiesList.addAll(specialtiesList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.cell_specialty, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount() = mSpecialtiesList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mSpecialtiesList[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(specialty: Specialty) {
            itemView.specialty_txt_name.text = specialty.name
            itemView.setOnClickListener {
                val navController = Navigation.findNavController(itemView)
                val action = SpecialtiesListFragmentDirections.actionSpecialtiesListFragmentToWorkersListFragment(specialty.specialtyId, specialty.name)
                navController.navigate(action)
            }
        }
    }
}