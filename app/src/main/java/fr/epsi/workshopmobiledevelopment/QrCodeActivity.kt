package fr.epsi.workshopmobiledevelopment

import android.os.Bundle

class QrCodeActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr_code)
        setHeaderTxt("Scan du QR Code")
        showBack()
    }
}