package com.example.sportify.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.sportify.R
import com.example.sportify.api.ApiResponse
import com.example.sportify.api.RetrofitClient
import com.example.sportify.api.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentRegister : Fragment() {
    private lateinit var nomUtilisateur: EditText
    private lateinit var email: EditText
    private lateinit var motDePasse: EditText
    private lateinit var sport: EditText
    private lateinit var niveau: EditText

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nomUtilisateur = view.findViewById(R.id.nomUtilisateur)
        email = view.findViewById(R.id.email)
        motDePasse = view.findViewById(R.id.motDePasse)
        sport = view.findViewById(R.id.sport)
        niveau = view.findViewById(R.id.niveau)

        view.findViewById<Button>(R.id.buttonRegister).setOnClickListener {
            registerUser()
        }
    }

    private fun registerUser() {
        val user = User(
            nom_utilisateur = nomUtilisateur.text.toString(),
            email = email.text.toString(),
            mot_de_passe = motDePasse.text.toString(),
            sport = sport.text.toString(),
            niveau = niveau.text.toString()
        )

        RetrofitClient.api.registerUser(user).enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    Toast.makeText(context, "Inscription réussie! ${response.body()}", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Erreur: ${response.errorBody()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                Toast.makeText(context, "Erreur: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}