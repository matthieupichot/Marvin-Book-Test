package fr.test.cyllene.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import fr.test.cyllene.R
import fr.test.cyllene.databinding.ActivityMainBinding
import fr.test.cyllene.utils.SharedPreferences
import fr.test.cyllene.view.Application
import fr.test.cyllene.view.fragments.FavoriteFragment
import fr.test.cyllene.view.fragments.HomeFragment
import fr.test.cyllene.view.fragments.ProfileFragment
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var sharedPreferences : SharedPreferences

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        (application as Application).sharedPreferencesComponent?.inject(this)
        if (savedInstanceState == null) {
            val fragment = HomeFragment()
            loadFragment(fragment)
        }

        binding.bottomNavigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener)
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private val navigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
        when (menuItem.itemId) {
            R.id.navigation_book -> {
                val fragment = HomeFragment()
                loadFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_favorites -> {
                val fragment = FavoriteFragment()
                loadFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_profile -> {
                val fragment = ProfileFragment()
                loadFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

}