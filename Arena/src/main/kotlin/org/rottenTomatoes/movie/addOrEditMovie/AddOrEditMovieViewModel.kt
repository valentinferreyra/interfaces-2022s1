package org.rottenTomatoes.movie.addOrEditMovie

import com.github.unqUi.model.Category
import com.github.unqUi.model.DraftMovie
import com.github.unqUi.model.Movie
import org.rottenTomatoes.category.CategoryViewModel
import org.rottenTomatoes.movie.MovieViewModel
import org.uqbar.commons.model.annotations.Observable

@Observable
class AddOrEditMovieViewModel(var movie : DraftMovie,  var allCategories : MutableList<CategoryViewModel>,
                               var allRelatedContent : MutableList<MovieViewModel>  ) {

    var title = movie.title
    var description = movie.description
    var poster = movie.poster
    var actualCategories = movie.categories.map { CategoryViewModel(it) }.toMutableList()
    var actualRelatedContent = movie.relatedContent.map { MovieViewModel(it) }.toMutableList()
    var enableToAddCategory = false
    var enableToRemoveCategory = false
    var enableToAddRelatedContent = false
    var enableToRemoveRelatedContent = false

    var selectedCategory : CategoryViewModel? = null
        set(value) {
            field = value
            enableToAddOrRemoveCategory(value)
        }

    var selectedRelatedContent : MovieViewModel? = null
        set(value) {
            field = value
            enableToAddOrRemoveRelatedContent(value)
        }

    init {
        this.allCategories = this.allCategories
                                 .filter { category -> this.actualCategories.all { it.name != category.name } }
                                 .toMutableList()
        this.allRelatedContent = this.allRelatedContent
                                 .filter { relatedContent -> this.actualRelatedContent.all { it.title != relatedContent.title } }
                                 .toMutableList()
    }

    fun updateMovie() {
        this.movie.title = this.title
        this.movie.description = this.description
        this.movie.poster = this.poster
        this.movie.categories = this
                                .actualCategories
                                .map { Category(it.id, it.name) }
                                .toMutableList()
        this.movie.relatedContent = this
                                    .actualRelatedContent
                                    .map { Movie(it.id, it.title, it.description, it.poster, it.categories, it.relatedContent) }
                                    .toMutableList()
    }

    fun addCategory(category: CategoryViewModel?) {
        if(category != null && (!actualCategories.contains(category))) {
            this.actualCategories.add(category)
            this.allCategories.remove(category)
        }
    }

    fun deleteCategory(category: CategoryViewModel?) {
        if(category != null && (!allCategories.contains(category))) {
            this.allCategories.add(category)
            this.actualCategories.remove(category)
        }
    }

    fun addRelatedContent(relatedContent: MovieViewModel?) {
        if(relatedContent != null && (!actualRelatedContent.contains(relatedContent))) {
            this.actualRelatedContent.add(relatedContent)
            this.allRelatedContent.remove(relatedContent)
        }
    }

    fun deleteRelatedContent(relatedContent: MovieViewModel?) {
        if(relatedContent != null && (!this.allRelatedContent.contains(relatedContent))) {
            this.actualRelatedContent.remove(relatedContent)
            this.allRelatedContent.add(relatedContent)
        }
    }

    private fun enableToAddOrRemoveRelatedContent(relatedContent: MovieViewModel?) {
        if(this.selectedRelatedContent != null && this.allRelatedContent.any { it.id == relatedContent!!.id } ) {
            activateOnlyAddRelatedContentButton()
        } else if (this.selectedRelatedContent == null) {
            disableBothAddAndRemoveRelatedContentButtons()
        } else {
            activateOnlyRemoveRelatedContentButton()
        }
    }

    private fun enableToAddOrRemoveCategory (category: CategoryViewModel?){
        if (this.selectedCategory != null && this.allCategories.any { it.name == category!!.name } ) {
            activateOnlyAddCategoryButton()
        } else if (this.selectedCategory == null) {
            disableBothAddAndRemoveCategoryButtons()
        } else {
            activateOnlyRemoveCategoryButton()
        }
    }

    private fun disableBothAddAndRemoveCategoryButtons() {
        this.enableToAddCategory = false
        this.enableToRemoveCategory = false
    }

    private fun disableBothAddAndRemoveRelatedContentButtons() {
        this.enableToAddRelatedContent = false
        this.enableToRemoveRelatedContent = false
    }

    private fun activateOnlyAddCategoryButton() {
        this.enableToRemoveCategory = false
        this.enableToAddCategory = true
    }

    private fun activateOnlyRemoveCategoryButton() {
        this.enableToAddCategory = false
        this.enableToRemoveCategory = true
    }

    private fun activateOnlyAddRelatedContentButton() {
        this.enableToRemoveRelatedContent = false
        this.enableToAddRelatedContent = true
    }

    private fun activateOnlyRemoveRelatedContentButton() {
        this.enableToAddRelatedContent = false
        this.enableToRemoveRelatedContent = true
    }
}
