package org.rottenTomatoesApi.helpers

import com.github.unqUi.model.Movie
import com.github.unqUi.model.User
import com.github.unqUi.model.getRottenTomatoesSystem
import org.rottenTomatoesApi.dto.SimpleReviewDTO
import org.rottenTomatoesApi.rottenTomatoes

object RottenTomatoesReviewsCenter {

    fun getReviewsOfMovie(movie: Movie): List<SimpleReviewDTO> {
        val moviesReviews = rottenTomatoes.reviews.filter { it.movie.id == movie.id }
        return moviesReviews.map { SimpleReviewDTO(it) }.toList()
    }

    fun calculateScore(movie: Movie): Int {
        val movieReviews = rottenTomatoes.reviews.filter { it.movie.id == movie.id }
        val stars = movieReviews.map { it.stars }
        return try {
            stars.sum().div(stars.size)
        } catch (e: ArithmeticException) {
            0
        }
    }
}