package com.mohammadazri.bajp3rddicodingsubmission.utils

import com.mohammadazri.bajp3rddicodingsubmission.data.source.local.entity.MovieEntity
import com.mohammadazri.bajp3rddicodingsubmission.data.source.local.entity.TvShowEntity

object DataDummy {

    fun generateDummyMovies(): List<MovieEntity> {
        val movies = ArrayList<MovieEntity>()

        movies.add(
            MovieEntity(
                337404,
                "Cruella",
                8.8,
                "2021-05-26",
                "In 1970s London amidst the punk rock revolution, a young grifter named Estella is determined to make a name for herself with her designs. She befriends a pair of young thieves who appreciate her appetite for mischief, and together they are able to build a life for themselves on the London streets. One day, Estella’s flair for fashion catches the eye of the Baroness von Hellman, a fashion legend who is devastatingly chic and terrifyingly haute. But their relationship sets in motion a course of events and revelations that will cause Estella to embrace her wicked side and become the raucous, fashionable and revenge-bent Cruella.",
                "https://image.tmdb.org/t/p/w500/hjS9mH8KvRiGHgjk6VUZH7OT0Ng.jpg",
                "Comedy, Crime",
                134,
                false
            )
        )


        movies.add(
            MovieEntity(
                238,
                "The Godfather",
                8.7,
                "1972-03-14",
                "Spanning the years 1945 to 1955, a chronicle of the fictional Italian-American Corleone crime family. When organized crime family patriarch, Vito Corleone barely survives an attempt on his life, his youngest son, Michael steps in to take care of the would-be killers, launching a campaign of bloody revenge.",
                "https://image.tmdb.org/t/p/w500/3bhkrj58Vtu7enYsRolD1fZdja1.jpg",
                "Drama, Crime",
                175,
                false
            )
        )

        movies.add(
            MovieEntity(
                278,
                "The Shawshank Redemption",
                8.7,
                "1994-09-23",
                "Framed in the 1940s for the double murder of his wife and her lover, upstanding banker Andy Dufresne begins a new life at the Shawshank prison, where he puts his accounting skills to work for an amoral warden. During his long stretch in prison, Dufresne comes to be admired by the other inmates -- including an older prisoner named Red -- for his integrity and unquenchable sense of hope.",
                "https://image.tmdb.org/t/p/w500/q6y0Go1tsGEsmtFryDOJo3dEmqu.jpg",
                "Drama, Crime",
                142,
                false
            )
        )

        movies.add(
            MovieEntity(
                19404,
                "Dilwale Dulhania Le Jayenge",
                8.7,
                "1995-10-20",
                "Raj is a rich, carefree, happy-go-lucky second generation NRI. Simran is the daughter of Chaudhary Baldev Singh, who in spite of being an NRI is very strict about adherence to Indian values. Simran has left for India to be married to her childhood fiancé. Raj leaves for India with a mission at his hands, to claim his lady love under the noses of her whole family. Thus begins a saga.",
                "https://image.tmdb.org/t/p/w500/2CAL2433ZeIihfX1Hb2139CX0pW.jpg",
                "Comedy, Drama, Romance",
                190,
                false
            )
        )

        movies.add(
            MovieEntity(
                696374,
                "Gabriel's Inferno",
                8.7,
                "2020-05-29",
                "An intriguing and sinful exploration of seduction, forbidden love, and redemption, Gabriel's Inferno is a captivating and wildly passionate tale of one man's escape from his own personal hell as he tries to earn the impossible--forgiveness and love.",
                "https://image.tmdb.org/t/p/w500/oyG9TL7FcRP4EZ9Vid6uKzwdndz.jpg",
                "Romance",
                122,
                false
            )
        )
        return movies
    }

    fun generateDummyTvShows(): List<TvShowEntity> {
        val tvShows = ArrayList<TvShowEntity>()

        tvShows.add(
            TvShowEntity(
                100,
                "I Am Not an Animal",
                "2004-05-10",
                "I Am Not An Animal is an animated comedy series about the only six talking animals in the world, whose cosseted existence in a vivisection unit is turned upside down when they are liberated by animal rights activists.",
                9.4,
                "https://image.tmdb.org/t/p/w500/qG59J1Q7rpBc1dvku4azbzcqo8h.jpg",
                "Animation, Comedy",
                6,
                1,
                false
            )
        )

        tvShows.add(
            TvShowEntity(
                83095,
                "The Rising of the Shield Hero",
                "2019-01-09",
                "It turns out that Glass, like L'arc, is also a hero from other world. Naofumi barely fights off their combo attacks and then corners Glass in a one-on-one battle, but is then faced with an all-important question: does he truly wish to protect this world?",
                9.1,
                "https://image.tmdb.org/t/p/w500/6cXf5EDwVhsRv8GlBzUTVnWuk8Z.jpg",
                "Animation, Action & Adventure, Sci-Fi & Fantasy, Drama",
                25,
                2,
                false
            )
        )

        tvShows.add(
            TvShowEntity(
                83097,
                "The Promised Neverland",
                "2019-01-11\"",
                "As Emma extends her hand to Peter and says, \\\"Let's be free,\\\" he shows a calm expression, but realizes his true fate. The children finally arrive at the gate leading to the human world, where they hope to pass through.",
                9.1,
                "https://image.tmdb.org/t/p/w500/oBgRCpAbtMpk1v8wfdsIph7lPQE.jpg",
                "Supernatural",
                23,
                2,
                false
            )
        )

        tvShows.add(
            TvShowEntity(
                88040,
                "Given",
                "2019-07-12",
                "Mafuyu tells Ritsuka that he likes him and Ritsuka is ecstatic to find out that they both like each other but at the same time, he remembers himself saying that band members should never bring relationships into the band. However, they both decide to talk to Haruki and Akihiko about it. Haruki gives them a lecture and Akihiko congratulates them. Meanwhile, Haruki, who likes Akihiko...",
                9.1,
                "https://image.tmdb.org/t/p/w500/pdDCcAq8RNSZNk81PXYoHNUPHjn.jpg",
                "Romance",
                11,
                1,
                false
            )
        )

        tvShows.add(
            TvShowEntity(
                61663,
                "Your Lie in April",
                "2014-10-10",
                "Kousei Arima was a genius pianist until his mother's sudden death took away his ability to play. Each day was dull for Kousei. But, then he meets a violinist named Kaori Miyazono who has an eccentric playing style. Can the heartfelt sounds of the girl's violin lead the boy to play the piano again?",
                9.0,
                "https://image.tmdb.org/t/p/w500/IGbeFv5Ji4W0x530JcSHiQpamY.jpg",
                "Animation, Comedy, Drama",
                22,
                1,
                false
            )
        )

        return tvShows
    }

}