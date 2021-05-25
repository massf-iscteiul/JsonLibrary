package tests.testobjects

import tests.testobjects.Movie

data class MovieList(val score: Int,
                     val movie: Movie,
                     val liked: Boolean,
                     val related: List<*>)