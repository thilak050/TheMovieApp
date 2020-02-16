package com.themovie.themoviewapp

import Mymovie
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import android.content.DialogInterface
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.themovie.themoviewapp.adapters.ListAdapter
import com.themovie.themoviewapp.utils.Constants
import org.json.JSONObject


class HomePage : AppCompatActivity() {

    var images: ArrayList<Mymovie> = ArrayList()

    var retirnval: Boolean? = null

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)
        supportActionBar?.hide()

//declare and define views

        val newsRecyclerView = findViewById(R.id.rv_movielist) as RecyclerView

        newsRecyclerView.layoutManager = LinearLayoutManager(this)
        val numberOfColumns = 2
        newsRecyclerView.setLayoutManager(GridLayoutManager(this, numberOfColumns))

        val progressBar = ProgressDialog(this)
        progressBar.setCancelable(true)

        progressBar.setMessage("Please wait ...")
        progressBar.show();



        //call api

        val URL = Constants.apimainaUrl
        val queue = Volley.newRequestQueue(applicationContext)



        retirnval=isOnline(applicationContext)
        if (retirnval==true)
        {

        }else{
            progressBar.dismiss();
            Toast.makeText(applicationContext,"No Internet Connection",5000).show()

        }


        val stringRequest = StringRequest(
            Request.Method.GET, URL+"themovielist.php",
            Response.Listener { response ->

                progressBar.dismiss();

                //response from api


                val jsonObj = JSONObject(response.substring(response.indexOf("{"), response.lastIndexOf("}") + 1))
                val foodJson = jsonObj.getJSONArray("mymovie")


                for (i in 0..foodJson!!.length() - 1) {

                    val categories = Mymovie();

                    val movie_id = foodJson.getJSONObject(i).getInt("movie_id")
                    val movie_name = foodJson.getJSONObject(i).getString("movie_name")
                    val movie_rating = foodJson.getJSONObject(i).getString("movie_rating")
                    val movie_image = foodJson.getJSONObject(i).getString("movie_image")
                    val movie_duration = foodJson.getJSONObject(i).getString("movie_duration")
                    val movie_releasedate = foodJson.getJSONObject(i).getString("movie_releasedate")
                    val movie_language = foodJson.getJSONObject(i).getString("movie_language")
                    val movie_genres = foodJson.getJSONObject(i).getString("movie_genres")
                    val movie_about = foodJson.getJSONObject(i).getString("movie_about")
                    val movie_cast = foodJson.getJSONObject(i).getString("movie_cast")




                    categories.movie_id = movie_id
                    categories.movie_name = movie_name
                    categories.movie_rating = movie_rating
                    categories.movie_image = movie_image
                    categories.movie_duration = movie_duration
                    categories.movie_releasedate = movie_releasedate
                    categories.movie_language = movie_language
                    categories.movie_genres = movie_genres
                    categories.movie_about = movie_about
                    categories.movie_cast = movie_cast

                    //setting data to arraylist

                    images.add(categories)






                }


                //sending to adapter
                newsRecyclerView.adapter = ListAdapter(this.applicationContext,images!!)

            }, Response.ErrorListener {


            })

        queue.add(stringRequest)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitByBackKey()

            return true
        }
        return super.onKeyDown(keyCode, event)
    }


    protected fun exitByBackKey() {
        val alertbox: AlertDialog = AlertDialog.Builder(this)
            .setMessage("Do you want to exit application?")
            .setPositiveButton("Yes", DialogInterface.OnClickListener { arg0, arg1 ->

                finish()
                //close();
            })
            .setNegativeButton("No", // do something when the button is clicked
                DialogInterface.OnClickListener { arg0, arg1 -> })
            .show()
    }


    @SuppressLint("ServiceCast", "MissingPermission")
    fun isOnline(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

}

