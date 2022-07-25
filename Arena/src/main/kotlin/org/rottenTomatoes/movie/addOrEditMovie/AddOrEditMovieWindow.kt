package org.rottenTomatoes.movie.addOrEditMovie

import org.rottenTomatoes.category.CategoryViewModel
import org.rottenTomatoes.movie.MovieViewModel
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.*


class AddOrEditMovieWindow(owner: WindowOwner, addOrEditMovieViewModel: AddOrEditMovieViewModel) :
           Dialog<AddOrEditMovieViewModel>(owner, addOrEditMovieViewModel) {
    override fun addActions(actionsPanel: Panel?) {}

    override fun createFormPanel(mainPanel : Panel?) {
        title = "Movie - Rotten Tomatoes"

        addPanelWithTextAndBindTo(mainPanel, "Title", "title")
        addPanelWithTextAndBindTo(mainPanel, "Description", "description")
        addPanelWithTextAndBindTo(mainPanel, "Poster", "poster")

        Label(mainPanel) with { text = "Categories" }

        Panel(mainPanel) with {
            asHorizontal()
            List<CategoryViewModel>(it) with {
                width = 300
                height = 75
                bindItemsTo("allCategories").adaptWithProp<CategoryViewModel>("name")
                bindSelectedTo("selectedCategory")
            }

            Panel(it) with {
                asVertical()
                Button(it) with {
                    caption = ">"
                    bindEnabledTo("enableToAddCategory")
                    onClick { addCategory() }
                }
                Button(it) with {
                    caption = "<"
                    bindEnabledTo("enableToRemoveCategory")
                    onClick { deleteCategory() }
                }
            }

            List<CategoryViewModel>(it) with {
                width = 300
                height = 75
                bindItemsTo("actualCategories").adaptWithProp<CategoryViewModel>("name")
                bindSelectedTo("selectedCategory")
            }
        }

        Label(mainPanel) with { text = "Related Content" }

        Panel(mainPanel) with {
            asHorizontal()
            List<MovieViewModel>(it) with {
                width = 302
                height = 75
                bindItemsTo("allRelatedContent").adaptWithProp<MovieViewModel>("title")
                bindSelectedTo("selectedRelatedContent")
            }

            Panel(it) with {
                asVertical()
                Button(it) with {
                    caption = ">"
                    bindEnabledTo("enableToAddRelatedContent")
                    onClick { addRelatedContent() }
                }
                Button(it) with {
                    caption = "<"
                    bindEnabledTo("enableToRemoveRelatedContent")
                    onClick { deleteRelatedContent() }
                }
            }

            List<MovieViewModel>(it) with {
                width = 300
                height = 75
                bindItemsTo("actualRelatedContent").adaptWithProp<MovieViewModel>("title")
                bindSelectedTo("selectedRelatedContent")
            }
        }

        Panel(mainPanel) with {
            asHorizontal()
            Button(it) with {
                caption = "Accept"
                onClick { accept() }
            }
            Button(it) with {
                caption = "Cancel"
                onClick { cancel() }
            }
        }
    }

    private fun addPanelWithTextAndBindTo(mainPanel: Panel?, labelText: String, bindTo: String) {
        Panel(mainPanel) with {
            asHorizontal()
            Label(it) with {
                width = 90
                text = labelText
            }
            TextBox(it) with {
                width = 200
                bindTo(bindTo)
            }
        }
    }

    private fun addRelatedContent() = modelObject.addRelatedContent(modelObject.selectedRelatedContent)

    private fun deleteRelatedContent() = modelObject.deleteRelatedContent(modelObject.selectedRelatedContent)

    private fun deleteCategory() = modelObject.deleteCategory(modelObject.selectedCategory)

    private fun addCategory() = modelObject.addCategory(modelObject.selectedCategory)
}
