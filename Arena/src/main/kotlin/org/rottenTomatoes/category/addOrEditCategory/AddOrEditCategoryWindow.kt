package org.rottenTomatoes.addOrEditCategory

import org.rottenTomatoes.category.addOrEditCategory.AddOrEditCategoryViewModel
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.*

class AddOrEditCategoryWindow(owner : WindowOwner, addOrEditCategoryViewModel : AddOrEditCategoryViewModel) : Dialog<AddOrEditCategoryViewModel>(owner, addOrEditCategoryViewModel) {
    override fun addActions(actionsPanel: Panel?) {}

    override fun createFormPanel(mainPanel: Panel?) {
        Label(mainPanel) with {
            text = "Category - Rotten Tomatoes"
        }

        TextBox(mainPanel) with { bindTo("name") }

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

}
