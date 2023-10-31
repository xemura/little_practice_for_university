package com.xenia.practice.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xenia.practice.adapter.AdapterUser
import com.xenia.practice.databinding.ActivityMainBinding
import com.xenia.practice.interfaceModel.RetrofitServicesInterface
import com.xenia.practice.model.ObjectData
import com.xenia.practice.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.UUID

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AdapterUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView = binding.recyclerView
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        getAllUsersList()
    }

    private fun getAllUsersList() {
        val apiInterface = RetrofitServicesInterface.create().getUserList()

        apiInterface.enqueue(object : Callback<ObjectData> {
            override fun onResponse(call: Call<ObjectData>, response: Response<ObjectData>) {
                if (response.body() != null) {
                    val userList = response.body()!!.data as ArrayList<User>

                    // для каждого пользователя делаем UUID
                    // по которому потому будет генерироваться qr code
                    for (i in 0 until userList.size) {
                        val uuid = UUID.randomUUID()
                        userList[i].pass_id = uuid.toString()
                    }

                    adapter = AdapterUser(this@MainActivity, userList)
                    recyclerView.adapter = adapter
                }
            }

            override fun onFailure(call: Call<ObjectData>, t: Throwable) {
                Log.d("MyLog", t.message.toString())
            }
        })
    }
}
