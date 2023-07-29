package com.xenia.practice.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.xenia.practice.adapter.AdapterUser
import com.xenia.practice.databinding.ActivityMainBinding
import com.xenia.practice.interfaceModel.RetrofitServicesInterface
import com.xenia.practice.model.ObjectData
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.FileOutputStream
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

    fun saveStringToJSON(jsonString: String): JSONArray {
        val userJsonObject = JSONObject(jsonString)
        val userArray = userJsonObject.getJSONArray("data")

        for (i in 0 until userArray.length()) {
            val uuid = UUID.randomUUID()
            userArray.getJSONObject(i).put("pass_id", uuid)
        }
        userJsonObject.put("data", userArray)

        var fos: FileOutputStream? = null
        fos = openFileOutput("data.json", MODE_PRIVATE)
        fos.write(userJsonObject.toString().toByteArray())
        fos.close()

        return userArray
    }

    private fun getAllUsersList() {
        val apiInterface = RetrofitServicesInterface.create().getUserList()

        apiInterface.enqueue(object : Callback<ObjectData> {
            override fun onResponse(call: Call<ObjectData>, response: Response<ObjectData>) {
                if (response.body() != null) {
                    val gson = Gson()
                    val jsonString = gson.toJson(response.body())
                    val userList = saveStringToJSON(jsonString)
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
