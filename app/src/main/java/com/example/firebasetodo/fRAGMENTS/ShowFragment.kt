package com.example.firebasetodo.fRAGMENTS

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.firebasetodo.R
import com.example.firebasetodo.RecordAdapter
import com.example.firebasetodo.modal
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_show.*
import java.util.*
import java.util.Collections.reverse


class ShowFragment : Fragment() {

    lateinit var recycle : RecyclerView
    lateinit var modalList : MutableList<modal>
    var flag = 0



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_show, container, false)
        modalList = mutableListOf()
        recycle = view.findViewById(R.id.recycle)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val ref = FirebaseDatabase.getInstance().getReference("TODO")

        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot!!.exists()) {
                    modalList.clear()
                    for (h in snapshot.children) {
                        val modal = h.getValue(modal::class.java)
                        modalList.add(modal!!)
                    }
                    Collections.reverse(modalList)
                    recycle.adapter = RecordAdapter(modalList)
                    flag = RecordAdapter(modalList).add()
                    if(flag >= 3) {
                        warning?.visibility = View.GONE
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, error.message.toString(), Toast.LENGTH_LONG).show()
            }

        })
        recycle.setHasFixedSize(true)
        recycle.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
    }

}