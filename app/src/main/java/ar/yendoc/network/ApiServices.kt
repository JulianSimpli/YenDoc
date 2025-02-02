package ar.yendoc.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

interface ApiServices {

    //Profesionales
    @GET("profesionales")
    fun getAllProfesionales() : Call<List<Profesional>>

    @GET("profesionales/getProfesionalByName/{name}")
    fun getProfesionalByName(@Path("name") name : String) : Call<Profesional>

    @GET("profesionales/{profesional_id}")
    fun getProfesionalById(@Query("profesional_id") profesional_id : Int) : Call<Profesional>
    /*
    @GET("profesionales/{id}/proximasVisitas")
    fun getProximasVisitasByProfesionalId(@Query("profesional_id") profesional_id : Int) : Call<List<Visita>>
*/
    @GET("profesionales/{id}/proximasVisitas")
    fun getVisitasByProfesionalId(@Path("id") profesional_id : Int) : Call<List<VisitaAdapt>>

    companion object {

        var BASE_URL = "http://10.0.2.2:3001/"

        fun create(): ApiServices {

            val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()

            val retrofit = Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiServices::class.java)
        }
    }
}