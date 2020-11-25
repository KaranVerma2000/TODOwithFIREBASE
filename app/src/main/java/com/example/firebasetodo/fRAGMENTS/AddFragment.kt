package com.example.firebasetodo.fRAGMENTS

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.firebasetodo.R
import com.example.firebasetodo.modal
import com.google.firebase.database.FirebaseDatabase

class AddFragment : Fragment(){

    lateinit var save : Button
    lateinit var cancel : Button
    lateinit var addtitle : EditText
    lateinit var adddesc : EditText
    lateinit var progress : ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_add, container, false)

            save = view.findViewById<Button>(R.id.save)
            cancel = view.findViewById<Button>(R.id.cancel)
            addtitle = view.findViewById<EditText>(R.id.addtitle)
            adddesc = view.findViewById<EditText>(R.id.adddesc)
            progress = view.findViewById(R.id.progress)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val ref = FirebaseDatabase.getInstance().getReference("TODO")
        val id = ref.push().key
        save.setOnClickListener {
            val title = addtitle.text.toString().trim()
            val desc = adddesc.text.toString().trim()

            if(title.isEmpty())
            {
                addtitle.error = "Title required"
                addtitle.requestFocus()
                return@setOnClickListener
            }
            if(desc.isEmpty())
            {
                adddesc.error = "Description required"
                adddesc.requestFocus()
                return@setOnClickListener
            }
            progress.visibility = View.VISIBLE
            val modal = id?.let { it1 -> modal(it1, title, desc) }
            if (id != null) {
                ref.child(id).setValue(modal).addOnCompleteListener { task->
                    progress.visibility = View.GONE
                    Toast.makeText(context, "DATA INSERTED", Toast.LENGTH_SHORT).show()
                    val action = AddFragmentDirections.actionSAVE()
                    Navigation.findNavController(it).navigate(action)
                }
            }
        }

        cancel.setOnClickListener {
            val action = AddFragmentDirections.actionCancel()
            Navigation.findNavController(it).navigate(action)
        }
    }
}