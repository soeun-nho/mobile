package ddwu.mobile.week16.myparkapplication

import ParkAdapter
import ParkInfo
import ParkRoot
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ddwu.mobile.week16.myparkapplication.network.ParkAPIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ParkAdapter
    private lateinit var service: ParkAPIService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.rvParks)
        adapter = ParkAdapter(emptyList())

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val retrofit = Retrofit.Builder()
            .baseUrl(getString(R.string.seoul_api_url))
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(ParkAPIService::class.java)

        val btnFetchParks: Button = findViewById(R.id.btnFetchParks)

        btnFetchParks.setOnClickListener {
            fetchData()
        }
    }

    private fun fetchData() {
        val apiCall: Call<ParkRoot> = service.getParkData(
            getString(R.string.api_key),
            "json", // Change to 'json' assuming the response is in JSON format
            "SearchParkInfoService",
            1,
            5,
            null
        )

        apiCall.enqueue(object : Callback<ParkRoot> {
            override fun onResponse(call: Call<ParkRoot>, response: Response<ParkRoot>) {
                if (response.isSuccessful) {
                    val root: ParkRoot? = response.body()
                    if (root != null && root.searchParkInfoService.row.isNotEmpty()) {
                        val parkInfos = root.searchParkInfoService.row.map { row ->
                            ParkInfo(
                                row.longitude,
                                row.latitude,
                                row.pName,
                                row.pZone,
                                row.pAddr,
                                row.pPark
                            )
                        }
                        adapter.updateData(parkInfos)
                    } else {
                        Toast.makeText(
                            this@MainActivity,
                            "No park data available",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        "Failed to fetch park data",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<ParkRoot>, t: Throwable) {
                Toast.makeText(
                    this@MainActivity,
                    "Network request failed: ${t.message}",
                    Toast.LENGTH_SHORT
                ).show()
                Log.e("API_FAILURE", "Network request failed: ${t.message}", t)
            }
        })
    }
}
