package com.example.firebasetodo.fRAGMENTS

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.firebasetodo.R
import com.example.firebasetodo.RecordAdapter
import com.example.firebasetodo.modal
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.*


class LaunchFragment : Fragment() {

    lateinit var ctitle : TextView
    lateinit var cdesc : TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_launch, container, false)

        val add = view.findViewById<Button>(R.id.add)
        val v = view.findViewById<Button>(R.id.vb)
        ctitle = view.findViewById(R.id.ctitleEdit)
        cdesc = view.findViewById(R.id.cdescEdit)


        add.setOnClickListener {
            val action = LaunchFragmentDirections.actionAddnote()
            Navigation.findNavController(it).navigate(action)
        }
        v.setOnClickListener {
            val action = LaunchFragmentDirections.actionShow()
            Navigation.findNavController(it).navigate(action)
        }
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val ref = FirebaseDatabase.getInstance().getReference("TODO")

        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists())
                {
                    for(h in snapshot.children)
                    {
                        val Modal = h.getValue(modal::class.java)
//                        modalList.add(modal!!)
                        ctitle.text = Modal!!.title
                        cdesc.text = Modal.desc
                    }

                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, error.message.toString(), Toast.LENGTH_LONG).show()
            }

        })

    }





}