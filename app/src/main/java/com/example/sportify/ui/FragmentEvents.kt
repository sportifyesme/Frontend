package com.example.sportify.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.sportify.R
import com.example.sportify.api.RetrofitClient
import com.example.sportify.api.Event
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentEvents : Fragment() {
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_events, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recyclerViewEvents)
        getEvents()
    }

    private fun getEvents() {
        RetrofitClient.api.getEvents().enqueue(object : Callback<List<Event>> {
            override fun onResponse(call: Call<List<Event>>, response: Response<List<Event>>) {
                if (response.isSuccessful) {
                    // Mettre à jour votre RecyclerView ici
                } else {
                    Toast.makeText(context, "Erreur: ${response.errorBody()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Event>>, t: Throwable) {
                Toast.makeText(context, "Erreur: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}