package fr.epsi.workshopmobiledevelopment

import android.os.Bundle

class RegistrationFormActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.`activity_registration`)
        setHeaderTxt("Création de compte")
        showBack()
    }
}