package org.rottenTomatoes.mainWindow

import com.github.unqUi.model.DraftCategory
import com.github.unqUi.model.DraftMovie
import org.rottenTomatoes.category.addOrEditCategory.AddOrEditCategoryViewModel
import org.rottenTomatoes.addOrEditCategory.AddOrEditCategoryWindow
import org.rottenTomatoes.category.CategoryViewModel
import org.rottenTomatoes.movie.MovieViewModel
import org.rottenTomatoes.movie.addOrEditMovie.AddOrEditMovieViewModel
import org.rottenTomatoes.movie.addOrEditMovie.AddOrEditMovieWindow
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner
import java.awt.Color

class RottenMainWindow(owner: WindowOwner, rottenViewModel: RottenMainWindowViewModel) : SimpleWindow<RottenMainWindowViewModel>(owner, rottenViewModel){
    override fun addActions(actionsPanel: Panel?) {}

    override fun createFormPanel(mainPanel: Panel) {
        title = "Home - Rotten Tomatoes"

        Label(mainPanel) with { text = "Categories" }

        table<CategoryViewModel>(mainPanel) {
            bindItemsTo("categories")
            bindSelectionTo("selectedCategory")
            visibleRows = 5
            column {
                title = "Id"
                weight = 50
                bindContentsTo("id")
            }
            column {
                title = "Name"
                bindContentsTo("name")
            }
        }

        Button(mainPanel) with {
            caption= "Add category"
            width = 50
            onClick {
                var newCategory = DraftCategory("")
                AddOrEditCategoryWindow(thisWindow, AddOrEditCategoryViewModel(newCategory)) with {
                    onAccept {
                        thisWindow.modelObject.updateCategory()
                        this@RottenMainWindow.modelObject.addCategory(newCategory)
                        this@RottenMainWindow.modelObject.refreshCategories()
                    }
                    open()
                }
            }
        }

        Button(mainPanel) with {
            caption = "Edit category"
            width = 50
            bindEnabledTo("enabledEditCat")
            onClick {
                var selected = thisWindow.modelObject.selectedCategory
                var category = DraftCategory(selected!!.name)

                AddOrEditCategoryWindow(thisWindow, AddOrEditCategoryViewModel(category)) with {
                    onAccept {
                        thisWindow.modelObject.updateCategory()
                        this@RottenMainWindow.modelObject.editCategory(selected.id, category)
                        this@RottenMainWindow.modelObject.refreshCategories()
                    }
                    open()
                }
            }
        }

        Label(mainPanel) with { text = separatorText() }

        Label(mainPanel) with { text = "Movies" }

        table<MovieViewModel>(mainPanel) {
            bindItemsTo("movies")
            bindSelectionTo("selectedMovie")
            visibleRows = 5
            column {
                title = "Id"
                weight = 50
                bindContentsTo("id")
            }
            column {
                title = "Title"
                bindContentsTo("title")
            }
            column {
                title = "Poster"
                bindContentsTo("poster")
            }
        }

        Button(mainPanel) with {
            caption= "Add movie"
            width = 50
            onClick {
                var categories = thisWindow.modelObject.categories.toMutableList()
                var relatedContent = thisWindow.modelObject.movies.toMutableList()

                var newDraftMovie = DraftMovie("", "", "")
                AddOrEditMovieWindow(thisWindow, AddOrEditMovieViewModel(newDraftMovie, categories, relatedContent)) with {
                    onAccept {
                        thisWindow.modelObject.updateMovie()
                        this@RottenMainWindow.modelObject.addMovie(newDraftMovie)
                        this@RottenMainWindow.modelObject.refreshMovies()
                    }
                    open()
                }
            }
        }

        Button(mainPanel) with {
            caption = "Edit movie"
            width = 50
            bindEnabledTo("enableEditMov")
            onClick {
                var selected = thisWindow.modelObject.selectedMovie
                var movie = DraftMovie(selected!!.title, selected!!.description, selected!!.poster, selected!!.categories, selected!!.relatedContent)
                var categories = thisWindow.modelObject.categories.toMutableList()
                var relatedContent = thisWindow.modelObject.movies.toMutableList()

                AddOrEditMovieWindow(thisWindow, AddOrEditMovieViewModel(movie, categories, relatedContent)) with {
                    onAccept {
                        thisWindow.modelObject.updateMovie()
                        this@RottenMainWindow.modelObject.editMovie(selected.id, movie)
                        this@RottenMainWindow.modelObject.refreshMovies()
                    }
                    open()
                }
            }
        }

        Panel(mainPanel) with {
            asHorizontal()
            Button(it) with {
                caption = "Exit"
                width = 60
                background = Color.BLUE
                onClick { close() }
            }
        }
    }

    private fun separatorText(): String {
        return "---------------------------------------------"
    }
}