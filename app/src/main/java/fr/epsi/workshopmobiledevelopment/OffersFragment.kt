package fr.epsi.workshopmobiledevelopment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.squareup.picasso.Picasso
import org.json.JSONException
import org.json.JSONObject

class OffersFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: OffersAdapter
    private lateinit var requestQueue: RequestQueue

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_offers, container, false)

        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = OffersAdapter(emptyList())
        recyclerView.adapter = adapter

        requestQueue = Volley.newRequestQueue(context)
        val url = "https://www.ugarit.online/epsi/offers.json"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            Response.Listener<JSONObject> { response ->
                Log.d(TAG, "Response: $response")
                val offers = parseJson(response)
                adapter.setOffers(offers)
            },
            Response.ErrorListener { error ->
                Log.e(TAG, "Error occurred while making the request: $error")
            }
        )

        requestQueue.add(jsonObjectRequest)

        return view
    }

    private fun parseJson(jsonObject: JSONObject): List<Offer> {
        val offers = mutableListOf<Offer>()

        try {
            val itemsArray = jsonObject.getJSONArray("items")

            for (i in 0 until itemsArray.length()) {
                val itemObject = itemsArray.getJSONObject(i)
                val name = itemObject.getString("name")
                val description = itemObject.getString("description")
                val pictureUrl = itemObject.getString("picture_url")

                offers.add(Offer(name, description, pictureUrl))
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return offers
    }

    override fun onStop() {
        super.onStop()
        requestQueue.cancelAll(this)
    }

    companion object {
        private const val TAG = "OffersFragment"
    }
}

class OffersAdapter(private var offers: List<Offer>) : RecyclerView.Adapter<OffersAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.product_name)
        private val descriptionTextView: TextView = itemView.findViewById(R.id.product_description)
        private val imageView: ImageView = itemView.findViewById(R.id.product_image)

        fun bind(offer: Offer) {
            nameTextView.text = offer.name
            descriptionTextView.text = offer.description
            Picasso.get().load(offer.pictureUrl).into(imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.offer_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(offers[position])
    }

    override fun getItemCount(): Int {
        return offers.size
    }

    fun setOffers(offers: List<Offer>) {
        this.offers = offers
        notifyDataSetChanged()
    }
}

data class Offer(
    val name: String,
    val description: String,
    val pictureUrl: String
)