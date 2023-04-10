package fr.epsi.workshopmobiledevelopment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import com.google.gson.Gson

class RegistrationFormActivity : BaseActivity() {

    private lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        setHeaderTxt("Création de compte")
        showBack()

        // Récupération des données de l'intent
        val firstName = intent.getStringExtra("firstName")
        val lastName = intent.getStringExtra("lastName")
        val email = intent.getStringExtra("email")
        val address = intent.getStringExtra("address")
        val zipcode = intent.getStringExtra("zipcode")
        val city = intent.getStringExtra("city")
        val cardRef = intent.getStringExtra("cardRef")

        // Affichage des données dans les champs correspondants
        val editTextFirstName = findViewById<EditText>(R.id.edit_text_firstName)
        val editTextLastName = findViewById<EditText>(R.id.edit_text_lastName)
        val editTextEmail = findViewById<EditText>(R.id.edit_text_email)
        val editTextAddress = findViewById<EditText>(R.id.edit_text_address)
        val editTextZipcode = findViewById<EditText>(R.id.edit_text_zipcode)
        val editTextCity = findViewById<EditText>(R.id.edit_text_city)
        val editTextCardRef = findViewById<EditText>(R.id.edit_text_cardRef)

        editTextFirstName.setText(firstName)
        editTextLastName.setText(lastName)
        editTextEmail.setText(email)
        editTextAddress.setText(address)
        editTextZipcode.setText(zipcode)
        editTextCity.setText(city)
        editTextCardRef.setText(cardRef)

        Log.d("RegistrationFormActivity", "firstName: $firstName")
        Log.d("RegistrationFormActivity", "lastName: $lastName")
        Log.d("RegistrationFormActivity", "email: $email")
        Log.d("RegistrationFormActivity", "address: $address")
        Log.d("RegistrationFormActivity", "zipcode: $zipcode")
        Log.d("RegistrationFormActivity", "city: $city")
        Log.d("RegistrationFormActivity", "cardRef: $cardRef")
    }

    fun onCreateAccountButtonClicked(view: android.view.View) {
        val firstName = findViewById<EditText>(R.id.edit_text_firstName).text.toString()
        val lastName = findViewById<EditText>(R.id.edit_text_lastName).text.toString()
        val email = findViewById<EditText>(R.id.edit_text_email).text.toString()
        val address = findViewById<EditText>(R.id.edit_text_address).text.toString()
        val zipcode = findViewById<EditText>(R.id.edit_text_zipcode).text.toString()
        val city = findViewById<EditText>(R.id.edit_text_city).text.toString()
        val cardRef = findViewById<EditText>(R.id.edit_text_cardRef).text.toString()

        if (firstName.isNotEmpty() && lastName.isNotEmpty() && email.isNotEmpty() && address.isNotEmpty() &&
            zipcode.isNotEmpty() && city.isNotEmpty() && cardRef.isNotEmpty()) {

            val editor = preferences.edit()
            editor.putString("firstName", firstName)
            editor.putString("lastName", lastName)
            editor.putString("email", email)
            editor.putString("address", address)
            editor.putString("zipcode", zipcode)
            editor.putString("city", city)
            editor.putString("cardRef", cardRef)
            editor.apply()

            Toast.makeText(this, "Compte créé avec succès", Toast.LENGTH_SHORT).show()
            finish()

        } else {
            Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show()
        }
    }

}