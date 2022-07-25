package org.rottenTomatoesApi.controllers

import com.github.unqUi.model.*
import io.javalin.http.Context
import org.rottenTomatoesApi.dto.*
import org.rottenTomatoesApi.rottenTomatoes

class ContentController(val rottenTomatoesSystem: RottenTomatoesSystem) {

    private val allMovies = rottenTomatoesSystem.movies

    fun getContentLatest(ctx: Context) {
        val lastMovies = allMovies.takeLast(10)
        val moviesToReturn = lastMovies.map { SimpleMovieDTO(it) }.reversed()
        ctx.json(moviesToReturn)
    }

    fun getContentTop(ctx: Context) {
        val highestScoredMovies = allMovies.map { SimpleMovieDTO(it) }
                                           .sortedWith(compareBy { it.score }).reversed()
        ctx.json(highestScoredMovies.take(10))
    }

    fun getContentWithId(ctx: Context) {
        val id = ctx.pathParam("id")
        try {
            val content = rottenTomatoes.getMovieById(id)
            ctx.json(MovieDTO(content))
        } catch (e : MovieError) { ctx.status(404).json(ErrorDTO(e.message!!)) }
    }

    fun addReview(ctx: Context) {
        val loggedUser = ctx.attribute<User>("user")
        var reviewDTO : ReviewDTO? = null

        if (reviewBodyIsNotEmpty(ctx)){
            reviewDTO = ctx.bodyValidator<ReviewDTO>()
            .check( { reviewDTO -> reviewDTO.text.isNotBlank() }, "Text cannot be empty")
            .check( { reviewDTO -> reviewDTO.stars in 0..5 }, "Stars must be between 0 and 5")
            .get()
        } else throwBadRequestException(ctx)

        try {
            val content = rottenTomatoes.getMovieById(ctx.pathParam("id"))
            val newReview = rottenTomatoes.addReview(DraftReview(loggedUser!!.id, content.id, reviewDTO!!.text, reviewDTO.stars))
            ctx.json(SimpleReviewDTO(newReview))
        }
        catch (e: MovieError) { throwNotFoundException(ctx, e.message!!) }
        catch (e: UserError)  { throwNotFoundException(ctx, e.message!!) }
        catch (e: ReviewError) { throwBadRequestException(ctx, e.message!!) }
    }

    private fun throwBadRequestException(ctx: Context, message: String = "Missing information") {
        ctx.status(400).json(ErrorDTO(message))
    }

    private fun throwNotFoundException(ctx: Context, message: String) {
        ctx.status(404).json(ErrorDTO(message))
    }

    private fun reviewBodyIsNotEmpty(ctx: Context) : Boolean{
        return ctx.body().contains("text") && ctx.body().contains("stars")
    }
}