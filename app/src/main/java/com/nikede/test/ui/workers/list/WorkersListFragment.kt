package com.nikede.test.ui.workers.list

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.nikede.test.R
import com.nikede.test.TestApplication
import com.nikede.test.adapters.SpecialtiesAdapter
import com.nikede.test.adapters.WorkersAdapter
import com.nikede.test.data.db.entities.Worker
import com.nikede.test.ui.base.ScopedFragment
import kotlinx.android.synthetic.main.workers_list_fragment.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class WorkersListFragment : ScopedFragment() {

    companion object {
        fun newInstance() = WorkersListFragment()
    }

    private lateinit var viewModel: WorkersListViewModel

    private val mAdapter = WorkersAdapter()

    @Inject
    lateinit var viewModelFactory: WorkersListViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.workers_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        TestApplication.appComponent.inject(this)

        val args = WorkersListFragmentArgs.fromBundle(arguments!!)

        if (activity is AppCompatActivity)
        (activity as AppCompatActivity).supportActionBar?.title = args.specialtyName
        viewModelFactory.specialtyId = args.specialtyId

        recycler_workers.adapter = mAdapter
        recycler_workers.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recycler_workers.hasFixedSize()

        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(WorkersListViewModel::class.java)

        bindUI()
    }

    private fun bindUI() {
        launch {
            startLoading()
            val specialties = viewModel.workers.await()
            specialties.observe(this@WorkersListFragment, Observer {
                endLoading()

                if (it == null || it.isEmpty()) {
                    setupEmptyList()
                    return@Observer
                }

                setupSpecialtiesList(ArrayList(it))
            })
        }
    }

    private fun setupEmptyList() {
        recycler_workers.visibility = View.GONE
        txt_workers_no_items.visibility = View.VISIBLE
    }

    private fun setupSpecialtiesList(workersList: ArrayList<Worker>) {
        recycler_workers.visibility = View.VISIBLE
        txt_workers_no_items.visibility = View.GONE

        mAdapter.setupWorkers(context, workersList)
    }

    private fun startLoading() {
        recycler_workers.visibility = View.GONE
        txt_workers_no_items.visibility = View.GONE
        cpv_workers.visibility = View.VISIBLE
    }

    private fun endLoading() {
        cpv_workers.visibility = View.GONE
    }

}
