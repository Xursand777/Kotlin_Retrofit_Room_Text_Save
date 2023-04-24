package com.X7.Kotlin_Retrofit_Room_Text_Save

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.X7.Kotlin_Retrofit_Room_Text_Save.databinding.ActivityMainBinding
import com.X7.Kotlin_Retrofit_Room_Text_Save.retrofit.JsonModel
import com.X7.Kotlin_Retrofit_Room_Text_Save.retrofit.RetrofitInstance
import com.X7.Kotlin_Retrofit_Room_Text_Save.room.Appdatabase
import com.X7.Kotlin_Retrofit_Room_Text_Save.room.Constants.TABLE_NAME
import com.X7.Kotlin_Retrofit_Room_Text_Save.room.UserDao
import com.X7.Kotlin_Retrofit_Room_Text_Save.room.UserModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var livedata=MutableLiveData<Boolean>()
    var arrayList=ArrayList<JsonModel>()
    lateinit var jsonAdapter: JsonAdapter
    lateinit var userDao: UserDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = Room.databaseBuilder(
            applicationContext,
            Appdatabase::class.java,"${TABLE_NAME}"
        ).allowMainThreadQueries().build()
        userDao=db.userdao()

        livedata.observe(this,{
            if (it){
                retrofitrecycler()
            }else{
                binding.recylerview1.layoutManager=LinearLayoutManager(this@MainActivity)
                val roomAdapter=RoomAdapter(this@MainActivity,userDao.getAllUsers() as ArrayList<UserModel>)
                binding.recylerview1.adapter=roomAdapter
            }
        })

        observeinternetConnection()

    }

    fun observeinternetConnection(){

        val networkRequest = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .build()
        val networkCallback = object : ConnectivityManager.NetworkCallback() {
            // network is available for use
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                livedata.postValue(true)
            }

            // lost network connection
            override fun onLost(network: Network) {
                super.onLost(network)
                livedata.postValue(false)
            }
        }
        val connectivityManager = getSystemService(ConnectivityManager::class.java) as ConnectivityManager
        connectivityManager.requestNetwork(networkRequest, networkCallback)

    }

    fun retrofitrecycler(){

        val call:Call<ArrayList<JsonModel>> =RetrofitInstance.api.getallPosts()

        call.enqueue(object :Callback<ArrayList<JsonModel>>{
            override fun onResponse(
                call: Call<ArrayList<JsonModel>>,
                response: Response<ArrayList<JsonModel>>
            ) {
                if (response.isSuccessful){
                    arrayList=response.body()!!

                    binding.recylerview1.layoutManager=LinearLayoutManager(this@MainActivity)
                    jsonAdapter= JsonAdapter(this@MainActivity,arrayList)
                    binding.recylerview1.adapter=jsonAdapter

                    for (i in 0 until arrayList.size){
                        val user=UserModel(id = 0, userId = 0, title = arrayList.get(i).title, body = arrayList.get(i).body)
                        userDao.insertUser(user)
                    }

                }
            }

            override fun onFailure(call: Call<ArrayList<JsonModel>>, t: Throwable) {

            }

        })
    }
}