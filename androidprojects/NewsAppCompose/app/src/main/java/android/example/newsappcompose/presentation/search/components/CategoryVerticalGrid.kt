package android.example.newsappcompose.presentation.search.components

import android.example.newsappcompose.ui.theme.NewsAppComposeTheme
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CategoryVerticalGrid(
    onClick: (String) -> Unit
) {

    LazyVerticalGrid(
        columns = GridCells.Adaptive(100.dp),
        contentPadding = PaddingValues(10.dp)
        ) {
        items(categoryList.size) { index ->
            CategoryChip(
                category = categoryList[index].categoryName,
                imageUrl = categoryList[index].imageUrl,
                onClick = { onClick(categoryList[index].categoryName) },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CategoryVerticalGridPreview() {
    NewsAppComposeTheme {
        CategoryVerticalGrid(onClick = {})
    }
}

data class CategoryDetails(
    val categoryName: String,
    val imageUrl: String
)

val categoryList = listOf(
    CategoryDetails(
        categoryName = "Sports",
        imageUrl = "https://cdn.vox-cdn.com/thumbor/o2eZ-UI17WzzB0FGKz23I7FMzUE=/0x0:2040x1360/1200x628/filters:focal(1020x680:1021x681)/cdn.vox-cdn.com/uploads/chorus_asset/file/24128002/226361_Apple_iPad_10.9_10th_gen_DSeifert_0001.jpg"
    ),
    CategoryDetails(
        categoryName = "Business",
        imageUrl = "https://cdn.vox-cdn.com/thumbor/o2eZ-UI17WzzB0FGKz23I7FMzUE=/0x0:2040x1360/1200x628/filters:focal(1020x680:1021x681)/cdn.vox-cdn.com/uploads/chorus_asset/file/24128002/226361_Apple_iPad_10.9_10th_gen_DSeifert_0001.jpg"
    ),
    CategoryDetails(
        categoryName = "National",
        imageUrl = "https://cdn.vox-cdn.com/thumbor/o2eZ-UI17WzzB0FGKz23I7FMzUE=/0x0:2040x1360/1200x628/filters:focal(1020x680:1021x681)/cdn.vox-cdn.com/uploads/chorus_asset/file/24128002/226361_Apple_iPad_10.9_10th_gen_DSeifert_0001.jpg"
    ),
    CategoryDetails(
        categoryName = "World",
        imageUrl = "https://cdn.vox-cdn.com/thumbor/o2eZ-UI17WzzB0FGKz23I7FMzUE=/0x0:2040x1360/1200x628/filters:focal(1020x680:1021x681)/cdn.vox-cdn.com/uploads/chorus_asset/file/24128002/226361_Apple_iPad_10.9_10th_gen_DSeifert_0001.jpg"
    ),
    CategoryDetails(
        categoryName = "Technology",
        imageUrl = "https://cdn.vox-cdn.com/thumbor/o2eZ-UI17WzzB0FGKz23I7FMzUE=/0x0:2040x1360/1200x628/filters:focal(1020x680:1021x681)/cdn.vox-cdn.com/uploads/chorus_asset/file/24128002/226361_Apple_iPad_10.9_10th_gen_DSeifert_0001.jpg"
    ),
    CategoryDetails(
        categoryName = "Entertainment",
        imageUrl = "https://cdn.vox-cdn.com/thumbor/o2eZ-UI17WzzB0FGKz23I7FMzUE=/0x0:2040x1360/1200x628/filters:focal(1020x680:1021x681)/cdn.vox-cdn.com/uploads/chorus_asset/file/24128002/226361_Apple_iPad_10.9_10th_gen_DSeifert_0001.jpg"
    ),
    CategoryDetails(
        categoryName = "Politics",
        imageUrl = "https://cdn.vox-cdn.com/thumbor/o2eZ-UI17WzzB0FGKz23I7FMzUE=/0x0:2040x1360/1200x628/filters:focal(1020x680:1021x681)/cdn.vox-cdn.com/uploads/chorus_asset/file/24128002/226361_Apple_iPad_10.9_10th_gen_DSeifert_0001.jpg"
    ),
    CategoryDetails(
        categoryName = "Science",
        imageUrl = "https://cdn.vox-cdn.com/thumbor/o2eZ-UI17WzzB0FGKz23I7FMzUE=/0x0:2040x1360/1200x628/filters:focal(1020x680:1021x681)/cdn.vox-cdn.com/uploads/chorus_asset/file/24128002/226361_Apple_iPad_10.9_10th_gen_DSeifert_0001.jpg"
    )
)