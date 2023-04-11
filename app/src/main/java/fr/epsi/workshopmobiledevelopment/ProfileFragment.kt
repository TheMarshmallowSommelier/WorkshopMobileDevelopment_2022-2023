import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.google.zxing.common.BitMatrix
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult
import fr.epsi.workshopmobiledevelopment.R

class ProfileFragment : Fragment() {
    private lateinit var fullNameTextView: TextView
    private lateinit var cardRefTextView: TextView
    private lateinit var cardRefBarcodeImageView: ImageView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        fullNameTextView = view.findViewById(R.id.full_name_text_view)
        cardRefTextView = view.findViewById(R.id.card_ref_text_view)
        cardRefBarcodeImageView = view.findViewById(R.id.card_ref_barcode_image_view)

        displayProfile()

        return view
    }

    private fun displayProfile() {
        val preferences = context?.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val firstName = preferences?.getString("firstName", null)
        val lastName = preferences?.getString("lastName", null)
        val fullName = "$firstName $lastName"
        val cardRef = preferences?.getString("cardRef", null)

        if (fullName != null) {
            fullNameTextView.text = fullName
        }

        if (cardRef != null) {
            cardRefTextView.text = cardRef
            generateBarcode(cardRef)
        }
    }

    private fun generateBarcode(cardRef: String) {
        try {
            val bitMatrix: BitMatrix = MultiFormatWriter().encode(cardRef, BarcodeFormat.CODE_128, 800, 200, null)
            val width = bitMatrix.width
            val height = bitMatrix.height
            val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
            for (x in 0 until width) {
                for (y in 0 until height) {
                    bitmap.setPixel(x, y, if (bitMatrix[x, y]) Color.BLACK else Color.WHITE)
                }
            }
            cardRefBarcodeImageView.setImageBitmap(bitmap)
        } catch (e: WriterException) {
            e.printStackTrace()
        }
    }
}
