package com.nikede.test.ui.specialties

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.nikede.test.R
import com.nikede.test.TestApplication
import com.nikede.test.adapters.SpecialtiesAdapter
import com.nikede.test.data.db.TestDatabase
import com.nikede.test.data.db.entities.Specialty
import com.nikede.test.data.network.ConnectivityInterceptorImpl
import com.nikede.test.data.network.WorkersApiService
import com.nikede.test.data.network.WorkersNetworkDataSourceImpl
import com.nikede.test.data.repository.TestRepository
import com.nikede.test.data.repository.TestRepositoryImpl
import com.nikede.test.di.DaggerAppComponent
import com.nikede.test.ui.base.ScopedFragment
import kotlinx.android.synthetic.main.specialties_list_fragment.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class SpecialtiesListFragment : ScopedFragment() {

    private lateinit var viewModel: SpecialtiesListViewModel

    private val mAdapter = SpecialtiesAdapter()

    @Inject
    lateinit var viewModelFactory: SpecialtiesListViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.specialties_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        TestApplication.appComponent.inject(this)

        recycler_specialties.adapter = mAdapter
        recycler_specialties.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recycler_specialties.hasFixedSize()

        swipe_refresh.setOnRefreshListener {
            bindUI()
        }

        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(SpecialtiesListViewModel::class.java)

        bindUI()
    }

    private fun bindUI() {
        launch {
            startLoading()
            val specialties = viewModel.specialties.await()
            specialties.observe(this@SpecialtiesListFragment, Observer {
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
        recycler_specialties.visibility = View.GONE
        txt_specialties_no_items.visibility = View.VISIBLE
    }

    private fun setupSpecialtiesList(specialtiesList: ArrayList<Specialty>) {
        recycler_specialties.visibility = View.VISIBLE
        txt_specialties_no_items.visibility = View.GONE

        mAdapter.setupSpecialties(specialtiesList)
    }

    private fun startLoading() {
        recycler_specialties.visibility = View.GONE
        txt_specialties_no_items.visibility = View.GONE
        cpv_specialties.visibility = View.VISIBLE
    }

    private fun endLoading() {
        swipe_refresh.isRefreshing = false
        cpv_specialties.visibility = View.GONE
    }
}
