package com.example.testapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.testapplication.R
import com.example.testapplication.adapters.MyAdapter
import com.example.testapplication.models.CompanyInfo
import com.example.testapplication.models.Launch

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
    private lateinit var launchesAdapter: RecyclerView.Adapter<MyAdapter.MyAdapterViewHolder>
    private lateinit var tvCompanyInfo: TextView
    private lateinit var srLaunches: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        setObservers()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.frag_launch, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvLaunches = view.findViewById(R.id.rvLaunches)
        tvCompanyInfo = view.findViewById(R.id.tvCompanyInfo)
        srLaunches = view.findViewById(R.id.srLaunches)
        mViewModel.getInfo()

        srLaunches.setOnRefreshListener {
            RefreshLaunches()
        }

        // Cores da animação
        srLaunches.setColorSchemeResources(
            R.color.refresh_progress_1,
            R.color.refresh_progress_2,
            R.color.refresh_progress_3
        )
    }

    private fun RefreshLaunches() {
        srLaunches.isRefreshing = true
        mViewModel.getLaunches().observe(viewLifecycleOwner, Observer<List<Launch>> { launches ->
            if (launches != null) {
                prepareRecyclerView(launches)
            }
            srLaunches.isRefreshing = false
        })
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
        tvCompanyInfo.text =
            "${info.name} was founded by ${info.founder} in ${info.founded}. It has now ${info.employees} employees, ${info.launch_sites} launch sites, and is valued at USD ${info.valuation / 100}"
    }

    private fun prepareRecyclerView(launches:List<Launch>) {
        launchesAdapter = MyAdapter(launches)
        rvLaunches.adapter = launchesAdapter
        rvLaunches.layoutManager = LinearLayoutManager(activity)
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