package com.example.testapplication.ui

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.NumberPicker
import android.widget.RadioGroup
import androidx.fragment.app.DialogFragment
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.testapplication.R
import com.example.testapplication.TestApplication
import com.example.testapplication.models.requests.FilterLaunches
import java.util.*

private const val ARG_PARAM1 = "param1"

class FilterDialogFragment : DialogFragment() {
    private var pFilter: FilterLaunches? = null
    private lateinit var btnFilter: Button
    private lateinit var cbSucess: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            pFilter = it.getParcelable(ARG_PARAM1)
        }
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view: View? = activity?.layoutInflater?.inflate(R.layout.fragment_filter_dialog, null)
        view?.let{ it ->
            val pickerYear = it.findViewById<NumberPicker>(R.id.np_year)
            pickerYear.maxValue = Calendar.getInstance().get(Calendar.YEAR)
            pickerYear.minValue = 2006
            pickerYear.wrapSelectorWheel = false;
            btnFilter = it.findViewById(R.id.btnFilterLaunches)
            cbSucess = it.findViewById(R.id.cbSuccess)
            val rgOrdering = it.findViewById<RadioGroup>(R.id.rgOrder)

            pFilter?.let {filter ->
                pickerYear.value = filter.year
                cbSucess.isChecked = filter.success
                when(filter.order) {
                    "asc" -> rgOrdering.check(R.id.rbAsc)
                    "desc" -> rgOrdering.check(R.id.rbDes)
                }

            }

            btnFilter.setOnClickListener() {
                val ordering: String
                when(rgOrdering.checkedRadioButtonId) {
                    R.id.rbAsc -> ordering = "asc"
                    R.id.rbDes -> ordering = "desc"
                    else -> ordering = "Ascending"
                }

                val broadCastManager = LocalBroadcastManager.getInstance(TestApplication.getInstance())
                val intent = Intent()
                intent.action = "filter"
                intent.putExtra("year", pickerYear.value)
                intent.putExtra("ordering", ordering)
                intent.putExtra("success", cbSucess.isChecked)
                broadCastManager.sendBroadcast(intent)
                dismiss()
            }
        }
        val alert = AlertDialog.Builder(context)
        alert.setView(view)
        return alert.create()
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            FilterDialogFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_PARAM1, pFilter)
                }
            }

        const val TAG = "FilterDialog"
    }

}