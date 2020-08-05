package fr.test.cyllene.view.fragments

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import fr.test.cyllene.R
import fr.test.cyllene.utils.Constants
import fr.test.cyllene.view.activities.LoginActivity
import kotlinx.android.synthetic.main.fragment_profile.*


class ProfileFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)

        setupViews()
    }

    private fun setupViews(){

        val preferences: SharedPreferences =
            this.activity!!.getSharedPreferences("ApplicationPreferences", Context.MODE_PRIVATE)

        txt_username.text = preferences.getString(Constants.USERNAME, null)

        btn_logout.setOnClickListener {
            preferences.edit()
                .putBoolean(Constants.LOGIN, false)
                .apply()
            preferences.edit().remove(Constants.USERNAME).apply()
            val intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)
        }
    }

}