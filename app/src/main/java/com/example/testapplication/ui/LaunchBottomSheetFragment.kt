package com.example.testapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testapplication.R
import com.example.testapplication.adapters.InfoAdapter
import com.example.testapplication.models.Links
import com.example.testapplication.models.requests.GenericKeyValueItem
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.bottomsheet_launch.*

class LaunchBottomSheetFragment : BottomSheetDialogFragment() {
    private lateinit var adapter: RecyclerView.Adapter<InfoAdapter.ViewHolder>
    private lateinit var rvInfo: RecyclerView
    private lateinit var btnClose: Button
    private var links: Links? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments.let {
            links = it?.getParcelable("links")
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.bottomsheet_launch, container, false)

    companion object {
        const val TAG = "LaunchBottomSheet"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        links.let {
            val infos = generateItensInfo(it)
            rvInfo = view.findViewById(R.id.info_options_recycler)
            btnClose = view.findViewById(R.id.btn_close_info)
            rvInfo.layoutManager = GridLayoutManager(context, 3)
            adapter = InfoAdapter(0, infos)
            rvInfo.adapter = adapter
            setListeners()
        }
    }

    private fun generateItensInfo(links: Links?): List<GenericKeyValueItem> {
        val infos = mutableListOf<GenericKeyValueItem>()
        if (links != null) {
            val info1 = GenericKeyValueItem("Article", links.article_link)
            val info2 = GenericKeyValueItem("Wikipedia", links.wikipedia)
            val info3 = GenericKeyValueItem("Video", links.video_link)
            infos.add(info1)
            infos.add(info2)
            infos.add(info3)
        }

        return infos
    }

    private fun setListeners() {
        btnClose.setOnClickListener() {
            dismiss()
        }
    }
}