package com.example.firebasetodo.fRAGMENTS

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.firebasetodo.R
import com.example.firebasetodo.hideKeyboard
import com.example.firebasetodo.modal
import com.google.android.gms.tasks.Task
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch


class update : Fragment() {

    val args : updateArgs by navArgs()

    private var titleval : String ?= null
    private var descval : String ?= null
    private var id : String ?= null




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_update, container, false)
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        arguments.let {
            titleval = (args.value?.title)
            descval = (args.value?.desc)
            id = args.value?.id
        }


        if(titleval!!.isNotBlank())
        {
            updatetitle.setText(titleval)
        }
        if (descval!!.isNotBlank())
        {
            updateDesc.setText(descval)
        }


        updatesave.setOnClickListener {
            val ref = FirebaseDatabase.getInstance().getReference("TODO")
            val title = updatetitle.text.toString().trim()
            val desc = updateDesc.text.toString().trim()

            if (title.isEmpty()) {
                updatetitle.error = "Title required"
                updatetitle.requestFocus()
                return@setOnClickListener
            }
            if (desc.isEmpty()) {
                updateDesc.error = "Description required"
                updateDesc.requestFocus()
                return@setOnClickListener
            }
            progress.visibility = View.VISIBLE
            val m = id?.let { it1 -> modal(it1, title, desc) }
            if (id != null) {
                ref.child(id!!).setValue(m).addOnCompleteListener { Task ->
                    Toast.makeText(context, "DATA UPDATED", Toast.LENGTH_SHORT).show()
                    progress.visibility = View.GONE
                    requireActivity().onBackPressed()
//                    val action = updateDirections.actionupdated()
//                    Navigation.findNavController(it).navigate(action)
                }
            }
            hideKeyboard()
        }

        updatecancel.setOnClickListener {
            requireActivity().onBackPressed()
//            val action = updateDirections.actionupdated()
//            Navigation.findNavController(it).navigate(action)
        }
    }
}
