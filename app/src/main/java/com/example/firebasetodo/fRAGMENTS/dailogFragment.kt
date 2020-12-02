package com.example.firebasetodo.fRAGMENTS

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.firebasetodo.R
import com.example.firebasetodo.modal
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_dailog.*
import kotlinx.android.synthetic.main.fragment_dailog.view.*
import kotlin.math.log


class dailogFragment() : DialogFragment() {

    var deleteVal : modal ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("val", "onCreate: called")
        arguments.let{
            Log.d("val", "onCreate: let ")
            deleteVal = it?.getSerializable("value") as modal?
        }
        Log.d("val", "onCreate: ${deleteVal!!.title}")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_dailog, container, false)
        view.delete.setOnClickListener {
            val ref = FirebaseDatabase.getInstance().getReference("TODO").child(deleteVal!!.id)
            ref.removeValue()
            Toast.makeText(it.context, "SUCCESSFULLY DELETED", Toast.LENGTH_SHORT).show()
            dismiss()
            requireActivity().onBackPressed()
        }
        view.cancelbtn.setOnClickListener {
            dismiss()
        }
        return view
    }
}

