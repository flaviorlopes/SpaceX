package com.example.testapplication.ui

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.NumberPicker
import android.widget.RadioGroup
import androidx.fragment.app.DialogFragment
import com.example.testapplication.R
import java.util.*
import android.content.Intent

import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.testapplication.TestApplication

/*private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"*/

class FilterDialogFragment : DialogFragment() {
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var btnFilter: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
/*        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }*/
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view: View? = activity?.layoutInflater?.inflate(R.layout.fragment_filter_dialog, null)
        view?.let{
            val pickerYear = it.findViewById<NumberPicker>(R.id.np_year)
            pickerYear.maxValue = Calendar.getInstance().get(Calendar.YEAR)
            pickerYear.minValue = 2006
            pickerYear.wrapSelectorWheel = false;
            btnFilter = it.findViewById(R.id.btnFilterLaunches)
            val rgOrdering = it.findViewById<RadioGroup>(R.id.rgOrder)

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
 /*               arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }*/
            }

        const val TAG = "FilterDialog"
    }

}