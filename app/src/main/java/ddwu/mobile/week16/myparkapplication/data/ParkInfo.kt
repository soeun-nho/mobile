import com.google.gson.annotations.SerializedName


data class ParkRoot(
    @SerializedName("SearchParkInfoService")
    val searchParkInfoService: SearchParkInfoService
)

data class SearchParkInfoService(
    @SerializedName("list_total_count")
    val listTotalCount: Long,
    val RESULT: Result,
    val row: List<Row>
)

data class Result(
    val CODE: String,
    val MESSAGE: String
)

data class Row(
    @SerializedName("P_IDX")
    val pIdx: String,
    @SerializedName("P_PARK")
    val pPark: String,
    @SerializedName("P_LIST_CONTENT")
    val pListContent: String,
    val AREA: String,
    @SerializedName("OPEN_DT")
    val openDt: String,
    @SerializedName("MAIN_EQUIP")
    val mainEquip: String,
    @SerializedName("MAIN_PLANTS")
    val mainPlants: String,
    val GUIDANCE: String,
    @SerializedName("VISIT_ROAD")
    val visitRoad: String,
    @SerializedName("USE_REFER")
    val useRefer: String,
    @SerializedName("P_IMG")
    val pImg: String,
    @SerializedName("P_ZONE")
    val pZone: String,
    @SerializedName("P_ADDR")
    val pAddr: String,
    @SerializedName("P_NAME")
    val pName: String,
    @SerializedName("P_ADMINTEL")
    val pAdmintel: String,
    @SerializedName("G_LONGITUDE")
    val gLongitude: String,
    @SerializedName("G_LATITUDE")
    val gLatitude: String,
    val longitude: Double,
    val latitude: Double,
    @SerializedName("TEMPLATE_URL")
    val templateUrl: String
)


data class ParkInfo(
    val longitude: Double,
    val latitude: Double,
    val pName: String,
    val pZone: String,
    val pAddr: String,
    val pPark: String
)

