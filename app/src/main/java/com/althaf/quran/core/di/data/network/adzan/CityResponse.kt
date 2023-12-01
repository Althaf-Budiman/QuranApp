package com.althaf.quran.core.di.data.network.adzan

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CityResponse(

    @Json(name="data")
	val listCity: List<CityItem>,

    @Json(name="status")
	val status: Boolean? = null
)

@JsonClass(generateAdapter = true)
data class CityItem(

	@Json(name="lokasi")
	val lokasi: String? = null,

	@Json(name="id")
	val id: String? = null
)
