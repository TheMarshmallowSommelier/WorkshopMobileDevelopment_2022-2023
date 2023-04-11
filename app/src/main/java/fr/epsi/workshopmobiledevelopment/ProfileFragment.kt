package fr.epsi.workshopmobiledevelopment

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class ProfileFragment : Fragment() {
    private lateinit var firstNameTextView: TextView
    private lateinit var lastNameTextView: TextView
    private lateinit var emailTextView: TextView
    private lateinit var addressTextView: TextView
    private lateinit var zipcodeTextView: TextView
    private lateinit var cityTextView: TextView
    private lateinit var cardRefTextView: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        firstNameTextView = view.findViewById(R.id.first_name_text_view)
        lastNameTextView = view.findViewById(R.id.last_name_text_view)
        emailTextView = view.findViewById(R.id.email_text_view)
        addressTextView = view.findViewById(R.id.address_text_view)
        zipcodeTextView = view.findViewById(R.id.zipcode_text_view)
        cityTextView = view.findViewById(R.id.city_text_view)
        cardRefTextView = view.findViewById(R.id.card_ref_text_view)

        displayProfile()

        return view
    }

    private fun displayProfile() {
        val preferences = context?.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val firstName = preferences?.getString("firstName", null)
        val lastName = preferences?.getString("lastName", null)
        val email = preferences?.getString("email", null)
        val address = preferences?.getString("address", null)
        val zipcode = preferences?.getString("zipcode", null)
        val city = preferences?.getString("city", null)
        val cardRef = preferences?.getString("cardRef", null)

        if (firstName != null && lastName != null && email != null) {
            firstNameTextView.text = firstName
            lastNameTextView.text = lastName
            emailTextView.text = email
            addressTextView.text = address
            zipcodeTextView.text = zipcode
            cityTextView.text = city
            cardRefTextView.text = cardRef
        }
    }
}
