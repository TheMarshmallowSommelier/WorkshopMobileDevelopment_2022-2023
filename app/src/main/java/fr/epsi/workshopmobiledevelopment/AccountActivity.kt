package fr.epsi.workshopmobiledevelopment

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class AccountActivity : BaseActivity() {

    private lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)
        setHeaderTxt("Mon compte")
        showBack()

        preferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        // Récupération des données des préférences
        val firstName = preferences.getString("firstName", "")
        val lastName = preferences.getString("lastName", "")
        val email = preferences.getString("email", "")
        val address = preferences.getString("address", "")
        val zipCode = preferences.getString("zipcode", "")
        val city = preferences.getString("city", "")

        // Affichage des données dans les champs correspondants
        findViewById<EditText>(R.id.et_first_name).setText(firstName)
        findViewById<EditText>(R.id.et_last_name).setText(lastName)
        findViewById<EditText>(R.id.et_email).setText(email)
        findViewById<EditText>(R.id.et_address).setText(address)
        findViewById<EditText>(R.id.et_zip_code).setText(zipCode)
        findViewById<EditText>(R.id.et_city).setText(city)

        val btnSave = findViewById<Button>(R.id.btn_save)
        btnSave.setOnClickListener { onSaveButtonClicked() }
    }

    private fun onSaveButtonClicked() {
        val firstName = findViewById<EditText>(R.id.et_first_name).text.toString()
        val lastName = findViewById<EditText>(R.id.et_last_name).text.toString()
        val email = findViewById<EditText>(R.id.et_email).text.toString()
        val address = findViewById<EditText>(R.id.et_address).text.toString()
        val zipCode = findViewById<EditText>(R.id.et_zip_code).text.toString()
        val city = findViewById<EditText>(R.id.et_city).text.toString()

        if (firstName.isNotEmpty() && lastName.isNotEmpty() && email.isNotEmpty() && address.isNotEmpty() &&
            zipCode.isNotEmpty() && city.isNotEmpty()) {

            val editor = preferences.edit()
            editor.putString("firstName", firstName)
            editor.putString("lastName", lastName)
            editor.putString("email", email)
            editor.putString("address", address)
            editor.putString("zipcode", zipCode)
            editor.putString("city", city)
            editor.apply()

            Toast.makeText(this, "Modifications enregistrées", Toast.LENGTH_SHORT).show()
            val editIntent = Intent(this, MainActivity::class.java)
            editIntent.putExtra("firstName", firstName)
            editIntent.putExtra("lastName", lastName)
            editIntent.putExtra("email", email)
            editIntent.putExtra("address", address)
            editIntent.putExtra("zipcode", zipCode)
            editIntent.putExtra("city", city)
            startActivity(editIntent)
            finish()
        } else {
            Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show()
        }
    }
}