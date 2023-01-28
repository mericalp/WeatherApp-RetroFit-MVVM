import com.example.weatherapp.Model.City

data class  Root(
    val cod: String,
    val message: Long,
    val cnt: Long,
    val list: List<com.example.weatherapp.Model.List>,
    val city: City,
): java.io.Serializable

