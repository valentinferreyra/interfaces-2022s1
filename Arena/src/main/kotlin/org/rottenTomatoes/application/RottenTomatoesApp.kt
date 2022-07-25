package org.rottenTomatoes.application

import org.rottenTomatoes.mainWindow.RottenMainWindowViewModel
import org.rottenTomatoes.mainWindow.RottenMainWindow
import org.uqbar.arena.Application
import org.uqbar.arena.windows.Window

class RottenTomatoesApp : Application() {
    override fun createMainWindow(): Window<*> {
        return RottenMainWindow(this, RottenMainWindowViewModel(RottenTomatoesViewModel()))
    }
}

fun main() = RottenTomatoesApp().start()