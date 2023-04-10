package fr.epsi.workshopmobiledevelopment

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("Perkeret","##### onCreate :"+javaClass.simpleName)
    }

    override fun onStart() {
        super.onStart()
        Log.i("Perkeret","##### onStart :"+javaClass.simpleName)
    }

    override fun onResume() {
        super.onResume()
        Log.i("Perkeret","##### onResume :"+javaClass.simpleName)
    }

    override fun onPause() {
        super.onPause()
        Log.i("Perkeret","##### onPause :"+javaClass.simpleName)
    }

    override fun onStop() {
        super.onStop()
        Log.i("Perkeret","##### onStop :"+javaClass.simpleName)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("Perkeret","##### onDestroy :"+javaClass.simpleName)
    }

    fun setHeaderTxt(txt:String) {
        val textViewTitle = findViewById<TextView>(R.id.textViewTitle)
        textViewTitle.setText(txt)
    }

    fun showBack(){
        val imageViewBack=findViewById<ImageView>(R.id.imageViewBack)
        imageViewBack.visibility= View.VISIBLE
        imageViewBack.setOnClickListener(View.OnClickListener {
            finish()
        })
    }

}