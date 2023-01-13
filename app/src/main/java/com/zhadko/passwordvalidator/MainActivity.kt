package com.zhadko.passwordvalidator

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zhadko.passwordvalidator.databinding.ActivityMainBinding

const val passwordsFileName = "passwords.txt"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val passwordValidator = PasswordValidator(this)

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        passwordValidator.validateAllFilePasswords(passwordsFileName)
        binding.text.text = "File has ${passwordValidator.getValidPasswordCount()} valid passwords "
    }
}