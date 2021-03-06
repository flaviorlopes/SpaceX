package com.example.testapplication.ui

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.testapplication.R
import com.example.testapplication.adapters.LaunchAdapter
import com.example.testapplication.models.CompanyInfo
import com.example.testapplication.models.Launch
import com.example.testapplication.models.requests.FilterLaunches
import java.text.NumberFormat

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LaunchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LaunchFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    val mViewModel: LaunchViewModel by viewModels()
    private lateinit var rvLaunches: RecyclerView
    private lateinit var launchesAdapter: RecyclerView.Adapter<LaunchAdapter.MyAdapterViewHolder>
    private lateinit var tvCompanyInfo: TextView
    private lateinit var srLaunches: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        setHasOptionsMenu(true)
        setObservers()
        setReceivers()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.filter_menu -> {
                val dialog = FilterDialogFragment.newInstance(mViewModel.filter)
                activity?.let {
                    dialog.show(it.supportFragmentManager, FilterDialogFragment.TAG)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.frag_launch, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvLaunches = view.findViewById(R.id.rvLaunches)
        tvCompanyInfo = view.findViewById(R.id.tvCompanyInfo)
        srLaunches = view.findViewById(R.id.srLaunches)

        srLaunches.setOnRefreshListener {
            RefreshLaunches()
        }

        // Cores da anima????o
        srLaunches.setColorSchemeResources(
            R.color.refresh_progress_1,
            R.color.refresh_progress_2,
            R.color.refresh_progress_3
        )
    }

    private fun RefreshLaunches() {
        srLaunches.isRefreshing = true
        mViewModel.loadLaunches()
    }

    private fun setObservers() {
        mViewModel.getLaunches().observe(this, Observer<List<Launch>> { launches ->
            if (launches != null) {
                prepareRecyclerView(launches)
            }
            srLaunches.isRefreshing = false
        })

        mViewModel.getInfo().observe(this, Observer<CompanyInfo> { info ->
            if (info != null) {
                setCompanyInfo(info)
            }
        })
    }

    private fun setCompanyInfo(info: CompanyInfo) {
        context?.let {
            val numberFormat = NumberFormat.getNumberInstance()
            val valuation = numberFormat.format(info.valuation)
            tvCompanyInfo.text =
                it.getString(R.string.companyInfo, info.name, info.founder, info.founded, info.employees, info.launch_sites, valuation)
        }
    }

    private fun prepareRecyclerView(launches: List<Launch>) {
        launchesAdapter = LaunchAdapter(launches)
        rvLaunches.adapter = launchesAdapter
        rvLaunches.layoutManager = LinearLayoutManager(activity)
    }

    private fun setReceivers() {
        val brFilter: BroadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent) {
                val action = intent.action
                if (action == "filter") {
                    filterLaunches(intent)
                }
            }
        }

        context?.let {
            LocalBroadcastManager.getInstance(it)
                .registerReceiver(brFilter, IntentFilter("filter"))
        }
    }

    private fun filterLaunches(intent: Intent) {
        mViewModel.filter = null
        intent.extras?.let{
            val year = intent.getIntExtra("year", 0)
            val ordering = intent.getStringExtra("ordering") ?: "asc"
            val success: Boolean = intent.getBooleanExtra("success", false)
            val filter = FilterLaunches(year, ordering, success)
            mViewModel.filter = filter
        }
        RefreshLaunches()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CharacterFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LaunchFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}