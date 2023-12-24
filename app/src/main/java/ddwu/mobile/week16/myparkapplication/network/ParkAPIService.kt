package ddwu.mobile.week16.myparkapplication.network

import ParkRoot
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ParkAPIService {
    @GET("756f435353736f6538334f78616348/json/SearchParkInfoService/1/5/")
    fun getParkData(
        @Query("KEY") apiKey: String,
        @Query("TYPE") fileType: String,
        @Query("SERVICE") serviceName: String,
        @Query("START_INDEX") startIndex: Int,
        @Query("END_INDEX") endIndex: Int,
        @Query("P_IDX") pIdx: Int? = null
    ): Call<ParkRoot>
}