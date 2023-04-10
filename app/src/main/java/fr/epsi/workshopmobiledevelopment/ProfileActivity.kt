package fr.epsi.workshopmobiledevelopment

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.journeyapps.barcodescanner.BarcodeEncoder

class ProfileActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        showLogo()
        showUserIcon()

        val firstName = intent.getStringExtra("firstName")
        val lastName = intent.getStringExtra("lastName")
        val nameTextView = findViewById<TextView>(R.id.nameTextView)
        nameTextView.text = "$lastName $firstName"


        val barcodeImageView = findViewById<ImageView>(R.id.barcodeImageView)
        val barcodeFormat = BarcodeFormat.CODE_128 // Le format du code-barre que vous souhaitez générer
        val barcodeValue = intent.getStringExtra("cardRef") // La valeur du code-barre que vous souhaitez générer

        val writer = MultiFormatWriter()
        val bitMatrix = writer.encode(barcodeValue, barcodeFormat, 200, 100)
        val barcodeBitmap = BarcodeEncoder().createBitmap(bitMatrix)

        barcodeImageView.setImageBitmap(barcodeBitmap)
    }
}