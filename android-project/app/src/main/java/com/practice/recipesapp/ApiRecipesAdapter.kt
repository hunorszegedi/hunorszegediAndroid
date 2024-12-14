import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.practice.recipesapp.ApiRecipe
import com.practice.recipesapp.databinding.ItemApiRecipeBinding

class ApiRecipesAdapter(
    private val recipes: List<ApiRecipe>,
    private val context: Context
) : RecyclerView.Adapter<ApiRecipesAdapter.ApiRecipesViewHolder>() {

    inner class ApiRecipesViewHolder(val binding: ItemApiRecipeBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApiRecipesViewHolder {
        val binding = ItemApiRecipeBinding.inflate(LayoutInflater.from(context), parent, false)
        return ApiRecipesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ApiRecipesViewHolder, position: Int) {
        val recipe = recipes[position]
        holder.binding.recipeTitle.text = recipe.title
        holder.binding.recipeDescription.text = recipe.description
        Glide.with(context).load(recipe.imageUrl).into(holder.binding.recipeImage)
    }

    override fun getItemCount(): Int {
        return recipes.size
    }
}
