package com.zhadko.passwordvalidator

import android.content.Context
import java.io.IOException
import java.io.InputStream

class PasswordValidator(private val context: Context) {

    private var validPasswordCount = 0

    fun getValidPasswordCount() = validPasswordCount

    fun validateAllFilePasswords(passwordsFileName: String) {
        val myOutput: String
        val myInputStream: InputStream

        try {
            //reading text from file
            myInputStream = context.assets.open(passwordsFileName)
            val size: Int = myInputStream.available()
            val buffer = ByteArray(size)
            myInputStream.read(buffer)
            myOutput = String(buffer)

            //getting password rules line by line in list
            val passwordsRulesList = myOutput.split("\n")

            //splitting each line to next values: password, must-have symbol and low/high number of symbols in password
            passwordsRulesList.forEach { passwordInput ->
                val validSymbol = passwordInput.first()
                val password = passwordInput.substringAfter(":")
                val lowPoint =
                    passwordInput.substringBefore(":").removePrefix(validSymbol.toString())
                        .substringBefore("-").trim().toInt()
                val highPoint =
                    passwordInput.substringBefore(":").removePrefix(validSymbol.toString())
                        .substringAfter("-").trim().toInt()

                //counting how many must-have symbols has password
                val pas = password.count { char ->
                    char == validSymbol
                }

                //checking conditions and increment the counter of number valid passwords
                if (pas in lowPoint..highPoint) {
                    validPasswordCount++
                }

            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}