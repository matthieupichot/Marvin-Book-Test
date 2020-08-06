package fr.test.cyllene.view.activities

import android.content.Intent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import fr.test.cyllene.R
import fr.test.cyllene.utils.Constants
import fr.test.cyllene.utils.SharedPreferences
import fr.test.cyllene.view.Application
import fr.test.cyllene.viewmodel.loginview.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity : AppCompatActivity() {

    lateinit var viewModel: LoginViewModel

    @Inject
    lateinit var sharedPreferences : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        (application as Application).sharedPreferencesComponent?.inject(this)
        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        setupViews()
    }

    private fun setupViews() {
        observeViewModel()
        btn_sign_in.setOnClickListener {
            viewModel.login(et_email.text.toString().trim(), et_password.text.toString().trim(), sharedPreferences)
        }
    }

    private fun observeViewModel() {
        viewModel.login.observe(this, Observer {
            when (it) {
                Constants.LOGIN_SUCCESSFUL -> {
                    sharedPreferences.saveLoginStatus(true)
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                    finish()
                }
                Constants.LOGIN_FAILED -> Toast.makeText(this, getString(R.string.login_error), Toast.LENGTH_LONG).show()
            }
        })
    }

}