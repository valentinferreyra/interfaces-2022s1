package org.rottenTomatoesApi.dto

import com.github.unqUi.model.*
import org.rottenTomatoesApi.helpers.RottenTomatoesReviewsCenter

data class ReviewDTO(val text: String, val stars: Int)

data class UserLoginDTO(val email : String, val password : String)

data class UserRegisterDTO(val email : String, val password: String,
                           val image : String, val name : String)

class UserWithReviewsDTO(user : User){
    val id = user.id
    val name = user.name
    val image = user.image
    val email = user.email
    val reviews = user.reviews.map { SimpleReviewDTO(it) }
}

class SimpleReviewDTO(review : Review){
    val id = review.id
    val user = UserDTO(review.user)
    val movie = SimpleMovieDTO(review.movie)
    val texts = review.text
    val stars = review.stars
}
class UserDTO(user: User){
    val id    = user.id
    val name  = user.name
    val image = user.image
    val email = user.email
}

data class ResultCategoriesDTO(val result : MutableList<CategoryDTO>)

data class CategoryDTO(val id: String, val name: String)

data class ResultSimpleMoviesDTO(val result : List<SimpleMovieDTO>)

class SimpleMovieDTO(movie : Movie){
    val id = movie.id
    val title= movie.title
    val description = movie.description
    val poster = movie.poster
    val categories = movie.categories.map { CategoryDTO(it.id, it.name) }
    var score = RottenTomatoesReviewsCenter.calculateScore(movie)
}

data class ErrorDTO(val message: String)

class MovieDTO(movie: Movie) {
    val id = movie.id
    val title = movie.title
    val description = movie.description
    val poster = movie.poster
    val categories = movie.categories.map { CategoryDTO(it.id, it.name) }
    val relatedContent = movie.relatedContent.map { SimpleMovieDTO(it) }
    val score = RottenTomatoesReviewsCenter.calculateScore(movie)
    val reviews = RottenTomatoesReviewsCenter.getReviewsOfMovie(movie)
}




