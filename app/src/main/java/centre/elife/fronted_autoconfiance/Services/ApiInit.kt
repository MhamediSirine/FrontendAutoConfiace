package isetb.elife.projectorenda.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiInit {
    const val BASE_URL = "http://10.0.2.2:8081"
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}
