import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.practice.recipesapp.ApiRecipeDetailActivity
import com.practice.recipesapp.databinding.ItemApiRecipeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.Unit

class ApiRecipesAdapter(
    private var recipes: ArrayList<ApiRecipe>,
    private val apiService: ApiService, // Retrofit APIService
    private val onDeleteSuccess: () -> Unit // Callback törlés után
) : RecyclerView.Adapter<ApiRecipesAdapter.ApiRecipeViewHolder>() {

    inner class ApiRecipeViewHolder(val binding: ItemApiRecipeBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApiRecipeViewHolder {
        val binding = ItemApiRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ApiRecipeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ApiRecipeViewHolder, position: Int) {
        val recipe = recipes[position]

        holder.binding.recipeTitle.text = recipe.name
        holder.binding.recipeDescription.text = recipe.description

        Glide.with(holder.itemView.context)
            .load(recipe.thumbnailUrl)
            .into(holder.binding.recipeImage)

        // Részletek megtekintése
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, ApiRecipeDetailActivity::class.java)
            intent.putExtra("RECIPE_ID", recipe.recipeID)
            holder.itemView.context.startActivity(intent)
        }

        // Törlés gomb kezelése
        holder.binding.btnDelete.setOnClickListener {
            val currentPosition = holder.adapterPosition
            if (currentPosition != RecyclerView.NO_POSITION) {
                val currentRecipe = recipes[currentPosition]
                deleteRecipe(currentRecipe.recipeID, currentPosition, holder)
            } else {
                Toast.makeText(holder.itemView.context, "Nem sikerült meghatározni a pozíciót.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun getItemCount(): Int = recipes.size

    private fun deleteRecipe(recipeID: Int, position: Int, holder: ApiRecipeViewHolder) {
        // Ha autentikációs token szükséges, itt kell megadni
        val authToken = "Bearer ya29.a0ARW5m74ORTnUNgXHYlFtok8qEHArcuRSi_FXCzg0YKiN0dGOTjoxOypk6MntwrQjiLzcHic65eXP4kXeQsfzlyFA5GMjHCucfJqXHXrId7zQXbkuT7nSmyErctY8OkJ-V8NP-9i1M-GykBeaiVVNJ14llXGXoWI7czV1ROvsaCgYKAVUSARASFQHGX2MiK6PTDBmQOJf_ziOYlh39rg0175"
        apiService.deleteRecipe(authToken, recipeID).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    // Törlés sikeres, frissítjük a listát
                    recipes.removeAt(position)
                    notifyItemRemoved(position)
                    onDeleteSuccess()
                    Toast.makeText(holder.itemView.context, "Recept sikeresen törölve!", Toast.LENGTH_SHORT).show()
                } else {
                    // Hibakezelés, naplózás
                    val errorBody = response.errorBody()?.string()
                    Log.e("DeleteError", "Response Code: ${response.code()}, Message: ${response.message()}, Error Body: $errorBody")
                    Toast.makeText(holder.itemView.context, "Hiba történt a törléskor: $errorBody", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.e("DeleteFailure", t.message.toString())
                Toast.makeText(holder.itemView.context, "Hálózati hiba történt.", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun updateRecipes(newRecipes: List<ApiRecipe>) {
        recipes.clear()
        recipes.addAll(newRecipes)
        notifyDataSetChanged()
    }

    fun removeRecipeById(recipeID: Int) {
        val index = recipes.indexOfFirst { it.recipeID == recipeID }
        if (index != -1) {
            recipes.removeAt(index)
            notifyItemRemoved(index)
        }
    }
}
