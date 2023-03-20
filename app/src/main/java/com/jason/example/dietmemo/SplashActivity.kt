package com.jason.example.dietmemo

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SplashActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        auth = Firebase.auth

        try {

            Log.d("splash",auth.currentUser!!.uid)

            Handler().postDelayed({
                startActivity(Intent(this,MainActivity::class.java))
                finish()
            },3000)

        }catch (e:Exception){

            Log.d("splash","회원가입 필요")

            auth.signInAnonymously()
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Toast.makeText(this,"비회원 로그인 성공",Toast.LENGTH_SHORT).show()
                        Log.d("splash2","회원가입 성공")

                        Handler().postDelayed({
                            startActivity(Intent(this,MainActivity::class.java))
                            finish()
                        },3000)

                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(this,"비회원 로그인 실패",Toast.LENGTH_SHORT).show()
                        Log.d("splash2","회원가입 실패")
                        Log.w(TAG, "signInAnonymously:failure", task.exception)



                    }
                }

        }

    }
}