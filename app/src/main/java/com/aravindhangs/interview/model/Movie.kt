package com.aravindhangs.interview.model

data class Movie(
    val copyright: String? = null,
    val has_more: Boolean? = null,
    val results: List<ResultsItem>? = null,
    val num_results: Int? = null,
    val status: String? = null
)

data class Multimedia(
    val src: String? = null,
    val width: Int? = null,
    val type: String? = null,
    val height: Int? = null
)

data class ResultsItem(
    val multimedia: Multimedia? = null,
    val date_updated: String? = null,
    val display_title: String? = null,
    val link: Link? = null,
    val publication_date: String? = null,
    val summary_short: String? = null,
    val critics_pick: Int? = null,
    val byline: String? = null,
    val headline: String? = null,
    val mpaa_rating: String? = null,
    val opening_date: String? = null
)

data class Link(
    val suggested_link_text: String? = null,
    val type: String? = null,
    val url: String? = null
)
