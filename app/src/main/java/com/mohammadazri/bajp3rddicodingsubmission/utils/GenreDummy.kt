package com.mohammadazri.bajp3rddicodingsubmission.utils

import com.mohammadazri.bajp3rddicodingsubmission.models.Genre

object GenreDummy {
    val mortalKombatGenres: List<Genre> = listOf(
        Genre(28, "Action"),
        Genre(14, "Fantasy"),
        Genre(12, "Adventure")
    )

    val dilwaleDulhania: List<Genre> = listOf(
        Genre(35, "Comedy"),
        Genre(18, "Drama"),
        Genre(10749, "Romance"),

    )

    val vanquishGenre: List<Genre> = listOf(
        Genre(28, "Action"),
        Genre(80, "Crime"),
        Genre(53, "Thriller")
    )

    val nobodyGenre: List<Genre> = listOf(
        Genre(28, "Action"),
        Genre(53, "Thriller"),
        Genre(80, "Crime")
    )

    val kimetsuNoYaibaGenre: List<Genre> = listOf(
        Genre(16, "Animation"),
        Genre(28, "Action"),
        Genre(12, "Adventure"),
        Genre(14, "Fantasy"),
        Genre(18, "Drama")
    )

    val iAmNotAnimalGenre: List<Genre> = listOf(
        Genre(16, "Animation"),
        Genre(35, "Comedy")
    )

    val promisedNeverlandGenre: List<Genre> = listOf(
        Genre(16, "Animation"),
        Genre(9648, "Mystery"),
        Genre(10765, "Sci-Fi & Fantasy"),
        Genre(10759, "Action & Adventure"),
        Genre(18, "Drama")
    )

    val tateNoYuushaGenre: List<Genre> = listOf(
        Genre(16, "Animation"),
        Genre(10759, "Action & Adventure"),
        Genre(10765, "Sci-Fi & Fantasy"),
        Genre(18, "Drama"),
    )

    val givenGenre: List<Genre> = listOf(
        Genre(16, "Animation"),
        Genre(18, "Drama")
    )

    val starWarsGenre: List<Genre> = listOf(
        Genre(10765, "Sci-Fi & Fantasy"),
        Genre(10759, "Action & Adventure"),
        Genre(16, "Animation")
    )
}