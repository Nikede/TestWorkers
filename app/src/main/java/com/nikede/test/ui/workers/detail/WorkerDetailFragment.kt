package com.nikede.test.ui.workers.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity

import com.nikede.test.R
import com.nikede.test.internal.glide.GlideApp
import kotlinx.android.synthetic.main.worker_detail_fragment.*
import java.text.SimpleDateFormat
import java.util.*

class WorkerDetailFragment : Fragment() {

    companion object {
        fun newInstance() = WorkerDetailFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.worker_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val args = WorkerDetailFragmentArgs.fromBundle(arguments!!)

        var name = args.surname.substring(0, 1).toUpperCase() + args.surname.substring(1).toLowerCase()
        name += " " + args.name.substring(0, 1).toUpperCase() + args.name.substring(1).toLowerCase()

        if (activity is AppCompatActivity)
            (activity as AppCompatActivity).supportActionBar?.title = name

        if (args.avatarUrl != "") {
            GlideApp.with(context!!)
                .load(args.avatarUrl)
                .into(worker_avatar_detail)
        }

        worker_surname.text = args.surname.substring(0, 1).toUpperCase() + args.surname.substring(1).toLowerCase()
        worker_name.text = args.name.substring(0, 1).toUpperCase() + args.name.substring(1).toLowerCase()
        var birthday = "-"
        if (args.birthday != "") {
            birthday = args.birthday
        }
        worker_birthday.text = birthday
    }

}
