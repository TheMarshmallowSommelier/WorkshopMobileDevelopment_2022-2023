package fr.epsi.workshopmobiledevelopment

import ProfileFragment
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showLogo()

        val showUserIcon = findViewById<ImageView>(R.id.imageUserIcon)
        showUserIcon.visibility= View.VISIBLE
        showUserIcon.setOnClickListener {
            val intent = Intent(this, AccountActivity::class.java)
            startActivity(intent)
        }

        // Récupération du ViewPager et du TabLayout
        val viewPager = findViewById<ViewPager>(R.id.view_pager)
        val tabLayout = findViewById<TabLayout>(R.id.tab_layout)


        // Couleurs du texte pour les onglets actifs et inactifs
        val tabTextInactiveColor = resources.getColor(R.color.red)
        val tabTextActiveColor = resources.getColor(android.R.color.white)

        // Appliquer les couleurs aux onglets
        tabLayout.setTabTextColors(tabTextInactiveColor, tabTextActiveColor)

        // Création de l'adapter pour les fragments
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(ProfileFragment(), "Carte")
        adapter.addFragment(OffersFragment(), "Offres")
        adapter.addFragment(StoreMapsFragment(), "Magasins")

        // Liaison de l'adapter au ViewPager et du TabLayout
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)

    }

    // Adapter pour les fragments
    internal class ViewPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {

        private val fragmentList: MutableList<Fragment> = ArrayList()
        private val fragmentTitleList: MutableList<String> = ArrayList()

        override fun getItem(position: Int): Fragment {
            return fragmentList[position]
        }

        override fun getCount(): Int {
            return fragmentList.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return fragmentTitleList[position]
        }

        fun addFragment(fragment: Fragment, title: String) {
            fragmentList.add(fragment)
            fragmentTitleList.add(title)
        }
    }
}
