package com.kotlin.pagecurl.domainobject.model

import androidx.annotation.DrawableRes
import androidx.ui.graphics.Color
import androidx.ui.graphics.ImageAsset
import com.kotlin.pagecurl.R

sealed class Result<out R> {

    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
}

val Result<*>.succeeded
    get() = this is Result.Success && data != null

fun <T> Result<T>.successOr(fallback: T): T {
    return (this as? Result.Success<T>)?.data ?: fallback
}

enum class AppScreen(val displayNameString: String) {
    Screen1("ホーム"),
    Screen2("ランキング"),
    Screen3("本棚"),
    Screen4("ストア"),
    Screen5("マイページ"),
    Screen6("ビューワー")
}

fun getScreenBasedOnIndex(index: Int) = when (index) {
    0 -> AppScreen.Screen1
    1 -> AppScreen.Screen2
    2 -> AppScreen.Screen3
    3 -> AppScreen.Screen4
    4 -> AppScreen.Screen5
    5 -> AppScreen.Screen6
    else -> AppScreen.Screen1
}

val colors = listOf(
    Color(0xFFffd7d7.toInt()),
    Color(0xFFffe9d6.toInt()),
    Color(0xFFfffbd0.toInt()),
    Color(0xFFe3ffd9.toInt()),
    Color(0xFFd0fff8.toInt())
)

data class Person(
    val name: MarvelName? = null,
    val age: Int,
    val profilePictureUrl: MarvelUrl? = null
)

enum class MarvelName(val nameString: String) {
    Name0("Iron Man"),
    Name1("Hulk"),
    Name2("Deadpool"),
    Name3("Wolverine"),
    Name4("Black Widow"),
    Name5("Thor"),
    Name6("Rogue"),
    Name7("Groot"),
    Name8("Professor X"),
    Name9("Iron Man Other")
}

enum class MarvelUrl(val urlString: String) {
    Url0("https://i.annihil.us/u/prod/marvel/i/mg/9/c0/527bb7b37ff55.jpg"),
    Url1("https://i.annihil.us/u/prod/marvel/i/mg/5/a0/538615ca33ab0.jpg"),
    Url2("https://i.annihil.us/u/prod/marvel/i/mg/9/90/5261a86cacb99.jpg"),
    Url3("https://i.annihil.us/u/prod/marvel/i/mg/2/60/537bcaef0f6cf.jpg"),
    Url4("https://i.annihil.us/u/prod/marvel/i/mg/f/30/50fecad1f395b.jpg"),
    Url5("https://i.annihil.us/u/prod/marvel/i/mg/d/d0/5269657a74350.jpg"),
    Url6("https://i.annihil.us/u/prod/marvel/i/mg/3/10/5112d84e2166c.jpg"),
    Url7("https://i.annihil.us/u/prod/marvel/i/mg/3/10/526033c8b474a.jpg"),
    Url8("https://i.annihil.us/u/prod/marvel/i/mg/3/e0/528d3378de525.jpg"),
    Url9("https://i.annihil.us/u/prod/marvel/i/mg/6/a0/55b6a25e654e6.jpg")
}

fun getSuperheroList() =
    (0..49).map {
        when (it) {
            0, 10, 20, 30, 40 -> { Person(MarvelName.Name0, 43, MarvelUrl.Url0) }
            1, 11, 21, 31, 41 -> { Person(MarvelName.Name1, 38, MarvelUrl.Url1) }
            2, 12, 22, 32, 42 -> { Person(MarvelName.Name2, 25, MarvelUrl.Url2) }
            3, 13, 23, 33, 43 -> { Person(MarvelName.Name3, 48, MarvelUrl.Url3) }
            4, 14, 24, 34, 44 -> { Person(MarvelName.Name4, 30, MarvelUrl.Url4) }
            5, 15, 25, 35, 45 -> { Person(MarvelName.Name5, 35, MarvelUrl.Url5) }
            6, 16, 26, 36, 46 -> { Person(MarvelName.Name6, 28, MarvelUrl.Url6) }
            7, 17, 27, 37, 47 -> { Person(MarvelName.Name7, 28, MarvelUrl.Url7) }
            8, 18, 28, 38, 48 -> { Person(MarvelName.Name8, 55, MarvelUrl.Url8) }
            9, 19, 29, 39, 49 -> { Person(MarvelName.Name9, 44, MarvelUrl.Url9) }
            else -> { Person(MarvelName.Name9, 44, MarvelUrl.Url9) }
        }
    }

data class Post(
    val id: String,
    val title: String,
    val subtitle: String? = null,
    val url: String,
    val publication: Publication? = null,
    val metadata: Metadata,
    val paragraphs: List<Paragraph> = emptyList(),
    val imageId: Int,
    val imageThumbId: Int,
    val image: ImageAsset? = null,
    val imageThumb: ImageAsset? = null
)

data class Metadata(
    val author: PostAuthor,
    val date: String,
    val readTimeMinutes: Int
)

data class PostAuthor(
    val name: String,
    val url: String? = null
)

data class Publication(
    val name: String,
    val logoUrl: String
)

data class Paragraph(
    val type: ParagraphType,
    val text: String,
    val markups: List<Markup> = emptyList()
)

data class Markup(
    val type: MarkupType,
    val start: Int,
    val end: Int,
    val href: String? = null
)

enum class MarkupType {
    Link,
    Code,
    Italic,
    Bold,
}

enum class ParagraphType {
    Title,
    Caption,
    Header,
    Subhead,
    Text,
    CodeBlock,
    Quote,
    Bullet,
}

data class User(@DrawableRes val avatar: Int, val name: String)

val users = listOf(
    User(R.drawable.avatar_1, "Adam"),
    User(R.drawable.avatar_2, "Andrew"),
    User(R.drawable.avatar_3, "Anna"),
    User(R.drawable.avatar_4, "Boris"),
    User(R.drawable.avatar_5, "Carl"),
    User(R.drawable.avatar_6, "Donna"),
    User(R.drawable.avatar_7, "Emily"),
    User(R.drawable.avatar_8, "Fiona"),
    User(R.drawable.avatar_9, "Grace"),
    User(R.drawable.avatar_10, "Irene"),
    User(R.drawable.avatar_11, "Jack"),
    User(R.drawable.avatar_12, "Jake"),
    User(R.drawable.avatar_13, "Mary"),
    User(R.drawable.avatar_14, "Peter"),
    User(R.drawable.avatar_15, "Rose"),
    User(R.drawable.avatar_16, "Victor")
)
