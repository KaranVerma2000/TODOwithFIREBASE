package com.example.firebasetodo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.firebasetodo.fRAGMENTS.ShowFragmentDirections
import com.example.firebasetodo.fRAGMENTS.dailogFragment
import kotlinx.android.synthetic.main.note_layout.view.*


class RecordAdapter(private val record : List<modal>) : RecyclerView.Adapter<RecordAdapter.RecordViewHolder>() {

    var flag : Int ? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordViewHolder {
        return RecordViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.note_layout, parent, false)
        )

    }

    override fun onBindViewHolder(holder: RecordViewHolder, position: Int) {
        holder.titleEdit.text = record[position].title
        holder.descEdit.text = record[position].desc

        if(flag != null)
        {
            if (flag == position)
            {
                holder.visibleSetup.visibility = View.VISIBLE
            }
            else
            {
                holder.visibleSetup.visibility = View.GONE
            }
        }
        else
        {
            holder.visibleSetup.visibility = View.GONE
        }

        holder.relativeLayout.setOnClickListener { view ->

            if (flag == position)
            {
                flag = null
            }
            else
            {
                flag = position
            }
            notifyDataSetChanged()

            holder.view.reUpdate.setOnClickListener {
                val action = ShowFragmentDirections.actionUpdate(record[position])

                Navigation.findNavController(view).navigate(action)

//                Toast.makeText(view.context, "clicked", Toast.LENGTH_SHORT).show()
            }

            holder.view.reDelete.setOnClickListener {

                val dailog = dailogFragment()
                val arg = Bundle()
                arg.putSerializable("value", record[position])
                dailog.arguments = arg
                dailog.show((it.context as AppCompatActivity).supportFragmentManager, "customDailog")
            }
        }
    }

    override fun getItemCount() = record.size

    fun add() : Int
    {
        return itemCount
    }

    class RecordViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        val titleEdit: TextView = view.findViewById(R.id.titleEdit)
        val descEdit: TextView = view.findViewById(R.id.descEdit)
        val relativeLayout: RelativeLayout = view.findViewById(R.id.RelativeLayout)
        val visibleSetup : RelativeLayout = view.findViewById(R.id.visibleSetup)


    }
}
