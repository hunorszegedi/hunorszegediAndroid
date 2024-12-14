import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("api/recipes") // A végpont az alap URL-hez viszonyítva
    fun getRecipes(): Call<List<ApiRecipe>>

    //tovabbi végpontok implementálása
}
