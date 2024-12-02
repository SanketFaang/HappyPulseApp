package com.jhainusa.happypulse.doctor_appointment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.jhainusa.happypulse.databinding.FragmentAppointmentBinding

class AppointmentFragment : Fragment() {
      private lateinit var binding: FragmentAppointmentBinding
      lateinit var recyclerView: RecyclerView
      private lateinit var adapter: Slideadapter
      private lateinit var doctorlist:MutableList<doctorinfo>
      lateinit var database:DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentAppointmentBinding.inflate(layoutInflater)
        recyclerView=binding.recyclerview
        recyclerView.layoutManager=LinearLayoutManager(requireContext())
        doctorlist= mutableListOf()
        adapter= Slideadapter(doctorlist)
        recyclerView.adapter=adapter
        database = FirebaseDatabase.getInstance().getReference("Appointments")
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for(i in snapshot.children){
                    val doc = i.getValue(doctorinfo::class.java)
                    if(doc!=null){
                        doctorlist.add(doc)
                    }
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError){
                Toast.makeText(requireContext(),"Failed to load data +${error}",Toast.LENGTH_SHORT).show()
            }
        })
        return binding.root
    }
}