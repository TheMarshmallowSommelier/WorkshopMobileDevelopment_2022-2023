package fr.epsi.workshopmobiledevelopment

import android.os.Bundle
import android.widget.TextView

class ProfileActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val firstName = intent.getStringExtra("firstName")
        val lastName = intent.getStringExtra("lastName")
        val email = intent.getStringExtra("email")

        val nameTextView = findViewById<TextView>(R.id.nameTextView)
        nameTextView.text = "$firstName $lastName $email"
    }
}