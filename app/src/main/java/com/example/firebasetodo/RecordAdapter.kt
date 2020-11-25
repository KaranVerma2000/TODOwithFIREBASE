package com.example.firebasetodo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.firebasetodo.fRAGMENTS.AddFragmentDirections
import com.example.firebasetodo.fRAGMENTS.update
import com.example.firebasetodo.fRAGMENTS.updateDirections
import com.google.firebase.database.FirebaseDatabase

class RecordAdapter(val record : List<modal>) : RecyclerView.Adapter<RecordAdapter.RecordViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordViewHolder {
        return RecordViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.note_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecordViewHolder, position: Int) {
        holder.titleEdit.text = record[position].title
        holder.descEdit.text = record[position].desc

//        holder.relativeLayout.setOnClickListener {
//            dialog(record[position])
//        }
    }

    override fun getItemCount() = record.size

    class RecordViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        val titleEdit = view.findViewById<TextView>(R.id.titleEdit)
        val descEdit = view.findViewById<TextView>(R.id.descEdit)
        val relativeLayout: RelativeLayout = view.findViewById(R.id.RelativeLayout)
    }

//    private fun dialog(modal: modal)
//    {
//        val view = LayoutInflater.from(context).inflate(R.layout.fragment_update,null)
//        val updateaddtitle = view.findViewById<EditText>(R.id.updateaddtitle)
//        val updateadddesc = view.findViewById<EditText>(R.id.updateadddesc)
//        val updatesave = view.findViewById<Button>(R.id.updatesave)
//        val updatecancel = view.findViewById<Button>(R.id.updatecancel)
//
//
//        updateaddtitle.setText(modal.title)
//        updateadddesc.setText(modal.desc)
//
//        updatesave.setOnClickListener {
//            val ref = FirebaseDatabase.getInstance().getReference("TODO")
//            val title = updateaddtitle.text.toString().trim()
//            val desc = updateadddesc.text.toString().trim()
//
//            if(title.isEmpty())
//            {
//                updateaddtitle.error = "Title required"
//                updateaddtitle.requestFocus()
//                return@setOnClickListener
//            }
//            if(desc.isEmpty())
//            {
//                updateadddesc.error = "Description required"
//                updateadddesc.requestFocus()
//                return@setOnClickListener
//            }
//            val modal = modal.id.let { it1 -> modal(it1, title, desc) }
//            ref.child(modal.id).setValue(modal).addOnCompleteListener { task->
////                    progress.visibility = View.GONE
//                Toast.makeText(context, "DATA UPDATED", Toast.LENGTH_SHORT).show()
//                val action = updateDirections.actionupdated()
//                Navigation.findNavController(it).navigate(action)
//            }
//        }
//    }

}