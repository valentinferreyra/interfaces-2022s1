package org.rottenTomatoesApi.controllers

import com.github.unqUi.model.RottenTomatoesSystem
import io.javalin.http.Context
import io.javalin.http.NotFoundResponse
import org.rottenTomatoesApi.dto.CategoryDTO
import org.rottenTomatoesApi.dto.ResultCategoriesDTO
import org.rottenTomatoesApi.dto.ResultSimpleMoviesDTO
import org.rottenTomatoesApi.dto.SimpleMovieDTO
import org.rottenTomatoesApi.rottenTomatoes

class CategoryController(val rottenTomatoesSystem: RottenTomatoesSystem) {

    fun getCategories(ctx: Context) {
        val allCategoriesDTO : MutableList<CategoryDTO> = mutableListOf()
        val categories = rottenTomatoes.categories.map { CategoryDTO(it.id, it.name) }
        allCategoriesDTO.addAll(categories)
        ctx.json(ResultCategoriesDTO(allCategoriesDTO))
    }

    fun getCategoryById(ctx: Context) {
        val id = ctx.pathParam("id")
        val moviesWithCategory = rottenTomatoes.movies.filter { it -> it.categories.any { it.id == id } }
        if(moviesWithCategory.isEmpty()) throw NotFoundResponse("There is not category with that id")
        val simpleMoviesDTO = moviesWithCategory.map { SimpleMovieDTO(it)}
        ctx.json(ResultSimpleMoviesDTO(simpleMoviesDTO))
    }
}
