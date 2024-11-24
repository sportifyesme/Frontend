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

class FragmentLogin : Fragment() {
    private lateinit var email: EditText
    private lateinit var motDePasse: EditText

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        email = view.findViewById(R.id.email)
        motDePasse = view.findViewById(R.id.motDePasse)

        view.findViewById<Button>(R.id.buttonLogin).setOnClickListener {
            loginUser()
        }
    }

    private fun loginUser() {
        val user = User(
            nom_utilisateur = "",
            email = email.text.toString(),
            mot_de_passe = motDePasse.text.toString(),
            sport = "",
            niveau = ""
        )

        RetrofitClient.api.loginUser(user).enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    Toast.makeText(context, "Connexion réussie!", Toast.LENGTH_SHORT).show()
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