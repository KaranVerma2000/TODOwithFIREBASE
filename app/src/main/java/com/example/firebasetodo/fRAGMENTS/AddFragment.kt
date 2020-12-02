package com.example.firebasetodo.fRAGMENTS


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.example.firebasetodo.R
import com.example.firebasetodo.modal
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_show.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class AddFragment : Fragment(){

    lateinit var save : Button
    lateinit var cancel : Button
    lateinit var addtitle : EditText
    lateinit var adddesc : EditText
    lateinit var progress : ProgressBar
    var flag : Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_add, container, false)

            save = view.findViewById(R.id.save)
            cancel = view.findViewById(R.id.cancel)
            addtitle = view.findViewById(R.id.addtitle)
            adddesc = view.findViewById(R.id.addDesc)
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
            Log.d("warn", "onActivityCreated: $flag")
            if(flag == 3)
            {
                warning.visibility = View.INVISIBLE
            }
            progress.visibility = View.VISIBLE

            MainScope().launch {
                context.let { t ->
                    val modal = id?.let { it1 -> modal(it1, title, desc) }
                    if (id != null) {
                        ref.child(id).setValue(modal).addOnCompleteListener { task ->
                            progress.visibility = View.GONE
                            Toast.makeText(context, "DATA INSERTED", Toast.LENGTH_SHORT).show()
                            requireActivity().onBackPressed()
//                            val action = AddFragmentDirections.actionSAVE()
//                            Navigation.findNavController(it).navigate(action)
                        }
                    }
                }
            }
        }

        cancel.setOnClickListener {
            requireActivity().onBackPressed()
//            val action = AddFragmentDirections.actionCancel()
//            Navigation.findNavController(it).navigate(action)
        }
    }
}