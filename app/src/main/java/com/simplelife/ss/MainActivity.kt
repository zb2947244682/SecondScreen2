package com.simplelife.ss

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.simplelife.ss.ui.theme.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // 启用边缘到边缘

        setContent {
            SecondScreenTheme() {
                Content()
            }
        }
    }
}

@Composable
fun Content() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = CustomBackground)
//            .background(color = Color.Red)
            .padding(top = 60.dp, start = 12.dp, end = 12.dp)
    ) {
        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ) {

            val (text1, row1, text2, row2) = createRefs()

            Text(
                text = "屏幕列表", fontSize = 18.sp, color = CustomTextPrimary,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.constrainAs(text1) {

                }
            )

            Row(
                modifier = Modifier
                    .constrainAs(row1) {
                        top.linkTo(text1.bottom, margin = 16.dp)
                    }
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(
                        color = Color.White, shape = RoundedCornerShape(8.dp)
                    )
                    .horizontalScroll(rememberScrollState())

            ) {
                ScreenItem(
                    isActive = true,
                    name = "显示器1",
                    resolution = "1920x1080",
                    displayId = 1,
                )
                ScreenItem(
                    isActive = false,
                    name = "显示器1",
                    resolution = "1920x1080",
                    displayId = 1,
                )
                ScreenItem(
                    isActive = false,
                    name = "显示器1",
                    resolution = "1920x1080",
                    displayId = 1,
                )
                ScreenItem(
                    isActive = false,
                    name = "显示器1",
                    resolution = "1920x1080",
                    displayId = 1,
                )
            }

            Text(
                text = "应用列表", fontSize = 18.sp, color = CustomTextPrimary,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.constrainAs(text2) {
                    top.linkTo(row1.bottom, margin = 16.dp)
                }
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = Color.White, shape = RoundedCornerShape(8.dp)
                    )
                    .constrainAs(row2) {
                        top.linkTo(text2.bottom, margin = 16.dp)
                        bottom.linkTo(parent.bottom, margin = 216.dp)
                        height = Dimension.fillToConstraints
                    }
                    .verticalScroll(rememberScrollState())

            ) {
                ScreenItem(
                    isActive = true,
                    name = "显示器1",
                    resolution = "1920x1080",
                    displayId = 1,
                )
                ScreenItem(
                    isActive = false,
                    name = "显示器1",
                    resolution = "1920x1080",
                    displayId = 1,
                )
                ScreenItem(
                    isActive = false,
                    name = "显示器1",
                    resolution = "1920x1080",
                    displayId = 1,
                )
                ScreenItem(
                    isActive = false,
                    name = "显示器1",
                    resolution = "1920x1080",
                    displayId = 1,
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SecondScreenTheme() {
        Content()
    }
}

@Composable
fun ScreenItem(name: String, resolution: String, displayId: Int, isActive: Boolean) {
    val bgColor = if (!isActive) Color.White else CustomBackgroundLight
    var textColor = if (!isActive) CustomTextLight else CustomTextPrimary
    var painterId = if (!isActive) R.drawable.ic_screen_light else R.drawable.ic_screen

    ConstraintLayout(
        modifier = Modifier
            .height(100.dp)
            .wrapContentWidth()
            .padding(10.dp)
            .background(
                color = bgColor, shape = RoundedCornerShape(8.dp)
            )
    ) {
        val (image1, text1, text2, text3) = createRefs()

        Image(painter = painterResource(id = painterId),
            contentDescription = null, // 无需提供描述
            modifier = Modifier
                .size(100.dp)
                .constrainAs(image1) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                })

        Text(
            text = name,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = textColor,
            modifier = Modifier.constrainAs(text1) {
                start.linkTo(image1.end)
                top.linkTo(image1.top, margin = 6.dp)
            })

        Text(text = "分辨率: $resolution",
            color = textColor,
            fontSize = 12.sp, modifier = Modifier.constrainAs(text2) {
                start.linkTo(image1.end)
                top.linkTo(text1.bottom)
                bottom.linkTo(text3.top)
                end.linkTo(parent.end, margin = 10.dp)
            })

        Text(text = "显示ID: $displayId",
            color = textColor,
            fontSize = 12.sp, modifier = Modifier.constrainAs(text3) {

                start.linkTo(image1.end)
                bottom.linkTo(parent.bottom, margin = 6.dp)
            })
    }
}
