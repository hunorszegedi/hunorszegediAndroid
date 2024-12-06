package com.practice.recipesapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.practice.recipesapp.databinding.FragmentNewRecipeBinding

class NewRecipeFragment : Fragment() {

    private var _binding: FragmentNewRecipeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewRecipeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSaveRecipe.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val description = binding.etDescription.text.toString()
            val ingredients = binding.etIngredients.text.toString()
            val instructions = binding.etInstructions.text.toString()

            if (title.isNotEmpty() && description.isNotEmpty()) {
                // Mentés Room Database-be
                Toast.makeText(requireContext(), "Recept mentve!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Töltsd ki a kötelező mezőket!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
