package fr.test.cyllene.view.fragments

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import fr.test.cyllene.databinding.FragmentProfileBinding
import fr.test.cyllene.utils.Constants
import fr.test.cyllene.view.activities.LoginActivity

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)

        setupViews()
    }

    private fun setupViews(){

        val preferences: SharedPreferences =
            this.requireActivity().getSharedPreferences("ApplicationPreferences", Context.MODE_PRIVATE)

        binding.txtUsername.text = preferences.getString(Constants.USERNAME, null)

        binding.btnLogout.setOnClickListener {
            preferences.edit()
                .putBoolean(Constants.LOGIN, false)
                .apply()
            preferences.edit().remove(Constants.USERNAME).apply()
            val intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}