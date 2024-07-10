package campus.tech.kakao.map.retrofit

import com.google.gson.annotations.SerializedName

data class Document(
    @SerializedName("place_name") val name: String,
    @SerializedName("category_name") val category: String,
    @SerializedName("address_name") val address: String,
)

data class MapSearchResponse(
    @SerializedName("documents") val documents: List<Document>
)

