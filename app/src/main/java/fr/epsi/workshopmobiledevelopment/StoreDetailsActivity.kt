package fr.epsi.workshopmobiledevelopment

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import org.json.JSONObject

class StoreDetailsActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_details)

        // Récupération de l'objet JSON du magasin sélectionné
        val selectedStoreJson = intent.getStringExtra("selectedStore")
        val selectedStore = JSONObject(selectedStoreJson)

        // Affichage du nom du magasin dans le header
        setHeaderTxt(selectedStore.getString("name"))
        showBack()

        // Affichage de l'image du magasin
        val img_store = findViewById<ImageView>(R.id.img_store)
        Picasso.get().load(selectedStore.getString("pictureStore")).into(img_store)

        // Affichage de l'adresse, code postal - ville et description du magasin
        val txt_address = findViewById<TextView>(R.id.txt_address)
        val txt_zip_city = findViewById<TextView>(R.id.txt_zip_city)
        val txt_description = findViewById<TextView>(R.id.txt_description)
        txt_address.text = selectedStore.getString("address")
        txt_zip_city.text = "${selectedStore.getString("zipcode")} ${selectedStore.getString("city")}"
        txt_description.text ="Description: " + selectedStore.getString("description")
    }
}
