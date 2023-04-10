package fr.epsi.workshopmobiledevelopment

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