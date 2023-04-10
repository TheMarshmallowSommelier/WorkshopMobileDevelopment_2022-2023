package fr.epsi.workshopmobiledevelopment

import android.os.Bundle
import android.content.Intent
import android.widget.Toast
import com.google.gson.Gson
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult
class QrCodeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr_code)
        setHeaderTxt("Scan du QR Code")
        showBack()
        scanQrCode()
    }

    private fun scanQrCode() {
        val integrator = IntentIntegrator(this)
        integrator.setPrompt("")
        integrator.setOrientationLocked(false)
        integrator.initiateScan()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result: IntentResult? = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null && result.contents != null) {
            try {
                val qrCodeData = Gson().fromJson(result.contents, QrCodeData::class.java)
                val registrationFormIntent = Intent(this, RegistrationFormActivity::class.java)
                registrationFormIntent.putExtra("firstName", qrCodeData.firstName)
                registrationFormIntent.putExtra("lastName", qrCodeData.lastName)
                registrationFormIntent.putExtra("email", qrCodeData.email)
                registrationFormIntent.putExtra("address", qrCodeData.address)
                registrationFormIntent.putExtra("zipcode", qrCodeData.zipcode)
                registrationFormIntent.putExtra("city", qrCodeData.city)
                registrationFormIntent.putExtra("cardRef", qrCodeData.cardRef)
                startActivity(registrationFormIntent)
            } catch (e: Exception) {
                Toast.makeText(this, "QR code invalide", Toast.LENGTH_SHORT).show()
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    data class QrCodeData(
        val firstName: String,
        val lastName: String,
        val email: String,
        val address: String,
        val zipcode: String,
        val city: String,
        val cardRef: String
    )

}