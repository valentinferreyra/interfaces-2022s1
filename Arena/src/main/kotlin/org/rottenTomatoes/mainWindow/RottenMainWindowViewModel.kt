package org.rottenTomatoes.mainWindow

import com.github.unqUi.model.*
import org.rottenTomatoes.application.RottenTomatoesViewModel
import org.rottenTomatoes.category.CategoryViewModel
import org.rottenTomatoes.movie.MovieViewModel
import org.uqbar.commons.model.annotations.Observable
import org.uqbar.commons.model.exceptions.UserException

@Observable
class RottenMainWindowViewModel(var rottenViewModel: RottenTomatoesViewModel) {
    var categories = rottenViewModel.rottenTomatoesSystem
                                    .categories
                                    .map { CategoryViewModel(it) }

    var movies = rottenViewModel.rottenTomatoesSystem
                                .movies
                                .map { MovieViewModel(it) }
    var enabledEditCat = false
    var enableEditMov = false

    var selectedCategory : CategoryViewModel? = null
        set(value) {
            field = value
            this.enabledEditCat = value != null
        }

    var selectedMovie    : MovieViewModel?    = null
        set(value) {
            field = value
            this.enableEditMov= value != null
        }


    fun addCategory(category: DraftCategory){
        checkCategoryValue(category)
        try {
            this.rottenViewModel.rottenTomatoesSystem.addCategory(category)
        } catch (e : CategoryError) {
            throw UserException(e.message)
        }
    }

    fun editCategory(id: String, category: DraftCategory) {
        checkCategoryValue(category)
        try{
            this.rottenViewModel.rottenTomatoesSystem.editCategory(id, category)
        } catch (e : CategoryError){
            throw UserException(e.message)
        }
    }

    fun checkCategoryValue(category: DraftCategory){
        if (category.name.isBlank()){
            throw UserException("Category name cannot be empty")
        }
        if (this.categories.any { it.name == category.name &&
                    category.name != this.selectedCategory!!.name
                                }
            ){
            throw UserException("There is already a category with that name")
        }
    }

    fun refreshCategories(){
        this.categories = this.rottenViewModel.rottenTomatoesSystem
                                              .categories
                                              .map { CategoryViewModel(it) }
    }

    fun addMovie(movie: DraftMovie) {
        checkMovieValue(movie)
        try{
            this.rottenViewModel.rottenTomatoesSystem.addMovie(movie)
        } catch (e : MovieError) {
            throw UserException(e.message)
        }
    }

    fun editMovie(id: String, movie : DraftMovie) {
        checkMovieValue(movie)
        try {
            this.rottenViewModel.rottenTomatoesSystem.editMovie(id, movie)
        }catch (e : MovieError){
            throw UserException(e.message)
        }
    }

    fun checkMovieValue(movie : DraftMovie){
        if (movie.title.isBlank()){
            throw UserException("Movie title cannot be empty")
        }
        if (movie.description.isBlank()){
            throw UserException("Movie description cannot be empty")
        }
        if (movie.poster.isBlank()){
            throw UserException("Movie poster cannot be empty")
        }
        if (this.categories.any { it.name == movie.title &&
                                  movie.title != this.selectedMovie!!.title
                                }
            ){
            throw UserException("There is already a movie with that name")
        }
    }

    fun refreshMovies() {
        this.movies = this.rottenViewModel.rottenTomatoesSystem
                                          .movies
                                          .map { MovieViewModel(it) }
    }
}