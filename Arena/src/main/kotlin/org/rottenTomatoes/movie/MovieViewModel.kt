package org.rottenTomatoes.movie

import com.github.unqUi.model.Category
import com.github.unqUi.model.Movie
import org.rottenTomatoes.category.CategoryViewModel
import org.uqbar.commons.model.annotations.Observable

@Observable
class MovieViewModel (movie : Movie){
    var id = movie.id
    var title = movie.title
    var description = movie.description
    var poster = movie.poster
    var categories : MutableList<Category> = movie.categories
    var relatedContent : MutableList<Movie> = movie.relatedContent
}