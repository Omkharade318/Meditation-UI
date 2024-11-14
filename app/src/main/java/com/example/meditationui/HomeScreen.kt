package com.example.meditationui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.meditationui.Path.standardQuadTo
import com.example.meditationui.ui.theme.*


@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier
            .background(DeepBlue)
            .fillMaxSize()
            .padding(top = 16.dp)
    ){
        Column {
            GreetingSection()
            ChipSection(chips = listOf("Sweet Sleep", "Insomnia", "Depression"))
            CurrentMeditation()
            FeatureSection( features =
               listOf(
                   Feature(
                    title = "Sleep meditation",
                    iconId = R.drawable.ic_headphone,
                    lightColor = BlueViolet1,
                    mediumColor = BlueViolet2,
                    darkColor = BlueViolet3
                    ),

                   Feature(
                       title = "Tips for sleeping",
                       iconId = R.drawable.ic_videocam,
                       lightColor = LightGreen1,
                       mediumColor = LightGreen2,
                       darkColor = LightGreen3
                   ),

                   Feature(
                       title = "Night     island",
                       iconId = R.drawable.ic_moon,
                       lightColor = OrangeYellow3,
                       mediumColor = OrangeYellow2,
                       darkColor = OrangeYellow1
                   ),

                   Feature(
                       title = "Calming sounds",
                       iconId = R.drawable.ic_music,
                       lightColor = Beige1,
                       mediumColor = Beige2,
                       darkColor = Beige3
                   )

               )
            )
        }
        BottomMenu(items = listOf(
            BottomMenuContent(title = "Home", iconId =  R.drawable.ic_home),
            BottomMenuContent(title = "Meditate", iconId =  R.drawable.ic_bubble),
            BottomMenuContent(title = "Sleep", iconId =  R.drawable.ic_moon),
            BottomMenuContent(title = "Music", iconId =  R.drawable.ic_music),
            BottomMenuContent(title = "Profile", iconId =  R.drawable.ic_profile),
          ),
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Composable
fun GreetingSection(
    name: String = "Schrodinger"
){
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ){
        Column(
            verticalArrangement = Arrangement.Center

        ) {
            Text(
                text = "Good Morning, $name",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                style = MaterialTheme.typography.headlineSmall,
                color = TextWhite
            )

            Text(
                text = "we wish you have a good day!",
                fontSize = 16.sp,
                color = Grey
            )
        }
        Icon(
            painter = painterResource(id = R.drawable.ic_search),
            contentDescription = "search icon",
            tint = Color.White,
            modifier = Modifier.size(24.dp)
            )
    }
}

@Composable
fun ChipSection(
    chips : List<String>
){
    var selectedChipIndex by remember {
        mutableStateOf(0)
    }

    LazyRow {
        items(chips.size){

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(start = 15.dp, top = 15.dp, bottom = 15.dp)
                    .clickable {
                        selectedChipIndex = it
                    }
                    .clip(RoundedCornerShape(10.dp))
                    .background(
                        if (selectedChipIndex == it) ButtonBlue else DarkerButtonBlue
                    )
                    .padding(15.dp)
            ){
                Text(text = chips[it], color = TextWhite)
            }
        }
    }
}

@Composable
fun CurrentMeditation(
    color : Color = LightRed
){
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(color = color)
            .padding(horizontal = 15.dp, vertical = 20.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center

        ) {
            Text(
                text = "Daily Thought",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineSmall,
                color = TextWhite
            )

            Text(
                text = "Meditation . 3-10 min",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                style = MaterialTheme.typography.displaySmall,
                color = TextWhite
            )
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(ButtonBlue)
                .padding(10.dp)
        ){
                    Icon(
                        painter = painterResource(id = R.drawable.ic_play),
                        contentDescription = "play icon",
                        tint = Color.White,
                        modifier = Modifier.size(16.dp)
                    )

                }
    }
}

@Composable
fun FeatureSection(features : List<Feature>){
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Features",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(16.dp),
            color = TextWhite,
           fontWeight = FontWeight.Bold
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(start = 7.5.dp, end = 7.5.dp, bottom = 100.dp),
            modifier = Modifier.fillMaxHeight(),
        ) {
            items(features.size){
                FeatureItem(feature = features[it])
            }
        }
    }
}

@Composable
fun FeatureItem(
    feature: Feature
){
    BoxWithConstraints(
        modifier = Modifier
            .padding(7.5.dp)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(10.dp))
            .background(feature.darkColor)
    ){
        this
        val width = constraints.maxWidth
        val height = constraints.maxHeight

        // Medium colored path
        val mediumColouredPoint1 = Offset(x=0f,y= height * 0.3f)
        val mediumColouredPoint2 = Offset(x=width * 0.1f,y= height * 0.35f)
        val mediumColouredPoint3 = Offset(x=width * 0.4f,y= height * 0.05f)
        val mediumColouredPoint4 = Offset(x=width * 0.75f,y= height * 0.7f)
        val mediumColouredPoint5 = Offset(x=width * 1.4f,y= -height.toFloat())

        val mediumColoredPath = Path().apply {

            moveTo(mediumColouredPoint1.x, mediumColouredPoint1.y)

            standardQuadTo(mediumColouredPoint1, mediumColouredPoint2)
            standardQuadTo(mediumColouredPoint2, mediumColouredPoint3)
            standardQuadTo(mediumColouredPoint3, mediumColouredPoint4)
            standardQuadTo(mediumColouredPoint4, mediumColouredPoint5)

            lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
            lineTo(-100f, height.toFloat() + 100f)
            close()

        }

        // Light colored path
        val lightPoint1 = Offset(x=0f,y= height * 0.35f)
        val lightPoint2 = Offset(x=width * 0.1f,y= height * 0.4f)
        val lightPoint3 = Offset(x=width * 0.3f,y= height * 0.35f)
        val lightPoint4 = Offset(x=width * 0.65f,y= height.toFloat())
        val lightPoint5 = Offset(x=width * 1.4f,y= -height.toFloat() / 3f)

        val lightColouredPath = Path().apply {

            moveTo(lightPoint1.x, lightPoint1.y)

            standardQuadTo(lightPoint1, lightPoint2)
            standardQuadTo(lightPoint2, lightPoint3)
            standardQuadTo(lightPoint3, lightPoint4)
            standardQuadTo(lightPoint4, lightPoint5)

            lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
            lineTo(-100f, height.toFloat() + 100f)
            close()
        }
        Canvas(
            modifier = Modifier
                .fillMaxSize()
        ) {
            drawPath(
                path = mediumColoredPath,
                color = feature.mediumColor
            )
            drawPath(
                path = lightColouredPath,
                color = feature.lightColor
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
        ){
            Text(
                text = feature.title,
                style = MaterialTheme.typography.headlineSmall,
                lineHeight = 26.sp,
                modifier = Modifier.align(Alignment.TopStart),
                color = TextWhite,
                fontWeight = FontWeight.Bold
            )

            Icon(
                painter = painterResource(id = feature.iconId),
                contentDescription = feature.title,
                tint = Color.White,
                modifier = Modifier.align(Alignment.BottomStart)
            )

            Text(
                text = "Start",
                color = TextWhite,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .clickable {

                    }
                    .align(Alignment.BottomEnd)
                    .clip(RoundedCornerShape(10.dp))
                    .background(ButtonBlue)
                    .padding(vertical = 6.dp, horizontal = 15.dp)
            )
        }

    }
}

@Composable
fun BottomMenu(
    items : List<BottomMenuContent>,
    modifier: Modifier = Modifier,
    activeHighlightColor : Color = ButtonBlue,
    activeTextColor: Color = TextWhite,
    inactiveTextColor : Color = AquaBlue,
    initialSelectedItemIndex : Int = 0
) {
    var selectedItemIndex by remember {
        mutableStateOf(initialSelectedItemIndex)
    }

    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .background(DeepBlue)
            .padding(15.dp)
    ) {
        items.forEachIndexed { index , item ->
            BottomMenuItem(
                item = item,
                isSelected = index == selectedItemIndex,
                activeHighlightColor = activeHighlightColor,
                activeTextColor = activeTextColor,
                inactiveTextColor = inactiveTextColor
            ){
                selectedItemIndex = index
            }
        }
    }
}

@Composable
fun BottomMenuItem(
    item: BottomMenuContent,
    isSelected: Boolean = false,
    activeHighlightColor: Color = ButtonBlue,
    activeTextColor: Color = TextWhite,
    inactiveTextColor: Color = AquaBlue,
    onItemClick: () -> Unit
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.clickable {
            onItemClick()
        }

    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(if(isSelected) activeHighlightColor else Color.Transparent)
                .padding(10.dp)
        ){
            Icon(
                painter = painterResource(
                    id = item.iconId
                ),
                contentDescription = item.title,
                tint = if (isSelected) activeTextColor else inactiveTextColor,
                modifier = Modifier.size(20.dp)
            )
        }
        Text(
            text = item.title,
            color = if (isSelected) activeTextColor else inactiveTextColor
        )

    }
}
@Preview
@Composable
fun HomeScreenPreview(){
    HomeScreen()
}