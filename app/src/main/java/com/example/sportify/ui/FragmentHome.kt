package com.example.sportify.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.sportify.R

class FragmentHome : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.buttonLogin).setOnClickListener {
            findNavController().navigate(R.id.fragmentLogin) // Assurez-vous que l'ID correspond
        }

        view.findViewById<Button>(R.id.buttonRegister).setOnClickListener {
            findNavController().navigate(R.id.fragmentRegister) // Assurez-vous que l'ID correspond
        }
    }
}
