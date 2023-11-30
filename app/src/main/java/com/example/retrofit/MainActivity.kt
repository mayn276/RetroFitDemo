package com.example.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import com.example.retrofit.databinding.ActivityMainBinding
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit  var retService : AlbumService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

            retService = RetrofitInstance
            .getRetroFitInstance()
            .create(AlbumService::class.java)
        getRequestWithPathParameters()



        val responseLiveData:LiveData<Response<Albums>> = liveData {
            val response = retService.getSortedAlbum(3)
            emit(response)

        }
        responseLiveData.observe(this, Observer {
            val albumList = it.body()?.listIterator()
            if (albumList!=null){
                while (albumList.hasNext()){
                    val albumsItem = albumList.next()
                    val result = " "+"Album title:${albumsItem.title}"+"\n"+
                                 "Album id:${albumsItem.id}"+"\n"+
                                  "Album userId:${albumsItem.userId}"+"\n\n\n"
                    binding.textView.append(result)

                }
            }
        })

    }
    private fun getRequestWithPathParameters(){
        //path parameter example
        val pathResponse:LiveData<Response<AlbumsItem>> = liveData{
            val response = retService.getAlbums(3)
            emit(response)
        }
        pathResponse.observe(this, Observer {
            val title = it.body()?.title
            Toast.makeText(applicationContext,title,Toast.LENGTH_LONG).show()

        })
    }
}