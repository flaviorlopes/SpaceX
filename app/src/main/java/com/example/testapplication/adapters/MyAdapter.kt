package com.example.testapplication.adapters

import android.app.Activity
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testapplication.R
import com.example.testapplication.models.Launch
import com.example.testapplication.ui.LaunchBottomSheetFragment
import com.example.testapplication.utils.DateUtils
import com.squareup.picasso.Picasso
import kotlin.math.abs


class MyAdapter(
    private var launchers: List<Launch>
) : RecyclerView.Adapter<MyAdapter.MyAdapterViewHolder>() {
    private lateinit var launch: Launch
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapterViewHolder {
        // Infla a view no adapter
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.card_launch, parent, false)

        this.context = parent.context
        // Retorna a view holder que contém todas as views
        return MyAdapterViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: MyAdapterViewHolder, position: Int) {
        val context = holder.itemView.context

        holder.itemView.setOnClickListener {
            val activity = context as FragmentActivity
            val fm = activity.supportFragmentManager
            val modalBottomSheet = LaunchBottomSheetFragment()
            modalBottomSheet.show(fm, LaunchBottomSheetFragment.TAG)
        }

        holder.progress.visibility = View.VISIBLE

        launch = launchers[position]

        holder.pacthImage.contentDescription = "Mission patch image of ${launch.mission_name}"

        if (launch.links.mission_patch_small.isEmpty()) {
            holder.progress.visibility = View.GONE
            /*holder.photo.setImageResource(R.drawable.semfoto)*/
        } else {
            Picasso.get()
                .load("${launch.links.mission_patch_small}")
                .fit()
                .into(holder.pacthImage,
                    object : com.squareup.picasso.Callback {
                        override fun onSuccess() {
                            // Download OK
                            holder.progress.visibility = View.GONE
                        }

                        override fun onError(e: Exception?) {
                            // Erro no download
                            holder.progress.visibility = View.GONE
                            /*holder.photo.setImageResource(R.drawable.semfoto)*/
                        }
                    })

        }

        with (holder) {
            val dateLaunchString = java.time.format.DateTimeFormatter.ISO_INSTANT.format(java.time.Instant.ofEpochSecond(launch.launch_date_unix))
            val dateString = DateUtils.getStringDateFromDate(dateLaunchString)
            val dateLaunchDate = DateUtils.getDateObjectFromString(dateLaunchString)
            missionName.text = launch.mission_name
            dateLaunch.text = dateString
            rocketNameType.text = "${launch.rocket.rocket_name}/${launch.rocket.rocket_type}"
            val diffDays = DateUtils.getDiffDays(dateLaunchDate)
            daysLaunch.text = abs(diffDays).toString()
            if (launch.launch_success) {
                launchSuccess.setImageDrawable(context.getDrawable(R.drawable.ic_check_mark))
            } else {
                launchSuccess.setImageDrawable(context.getDrawable(R.drawable.ic_cross_close))
            }

            if (diffDays < 0)
                labelDays.text = context.getString(R.string.days_since_now, context.getString(R.string.since))
            else
                labelDays.text = context.getString(R.string.days_since_now, context.getString(R.string.from))
        }

    }

    override fun getItemCount(): Int {
        return launchers.size
    }

    class MyAdapterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var pacthImage: ImageView
        var missionName: TextView
        var dateLaunch: TextView
        var rocketNameType: TextView
        var daysLaunch: TextView
        var labelDays: TextView
        var launchSuccess: ImageView
        var progress: ProgressBar

        init {
            pacthImage = view.findViewById(R.id.ivPatch)
            missionName = view.findViewById(R.id.tvMissionName)
            dateLaunch = view.findViewById(R.id.tvDateTime)
            rocketNameType = view.findViewById(R.id.tvRocket)
            daysLaunch = view.findViewById(R.id.tvDays)
            labelDays = view.findViewById(R.id.lbDays)
            launchSuccess = view.findViewById(R.id.ivLaunchResult)
            progress = view.findViewById(R.id.progressIvPatch)
        }
    }
}