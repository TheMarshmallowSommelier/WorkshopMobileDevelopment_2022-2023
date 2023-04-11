package fr.epsi.workshopmobiledevelopment

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton

class HomeActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.`activity_home`)
        setHeaderTxt("Création de compte")
        val registration = findViewById<Button>(R.id.registration)
        val qr_code = findViewById<ImageButton>(R.id.qr_code)

        val preferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        // CLEAR PREFERENCES
        /*val editor = preferences.edit()
        editor.clear()
        editor.apply()*/
        val firstName = preferences.getString("firstName", null)
        val lastName = preferences.getString("lastName", null)
        val email = preferences.getString("email", null)
        val address = preferences.getString("address", null)
        val zipcode = preferences.getString("zipcode", null)
        val city = preferences.getString("city", null)
        val cardRef = preferences.getString("cardRef", null)

        if (firstName != null && lastName != null && email != null) {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("firstName", firstName)
            intent.putExtra("lastName", lastName)
            intent.putExtra("email", email)
            intent.putExtra("address", address)
            intent.putExtra("zipcode", zipcode)
            intent.putExtra("city", city)
            intent.putExtra("cardRef", cardRef)
            startActivity(intent)
            finish()
        }

        registration.setOnClickListener(View.OnClickListener {
            val registrationFormIntent = Intent(this, RegistrationFormActivity::class.java)
            startActivity(registrationFormIntent)
        })

        qr_code.setOnClickListener(View.OnClickListener {
            val intent = Intent(application, QrCodeActivity::class.java)
            startActivity(intent)
        })
    }
}