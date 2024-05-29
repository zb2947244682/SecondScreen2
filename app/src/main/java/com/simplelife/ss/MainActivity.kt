package com.simplelife.ss

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
            .padding(top = 60.dp, start = 12.dp, end = 12.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                Text(
                    text = "屏幕列表", fontSize = 18.sp, color = CustomTextPrimary,

                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(
                        color = Color.White, shape = RoundedCornerShape(8.dp)
                    )

            ) {
                CustomItem(
                    name = "显示器1",
                    resolution = "1920x1080",
                    displayId = 1,
                    icon = painterResource(id = R.drawable.ic_screen)
                )
                CustomItem(
                    name = "显示器1",
                    resolution = "1920x1080",
                    displayId = 1,
                    icon = painterResource(id = R.drawable.ic_screen)
                )
                CustomItem(
                    name = "显示器1",
                    resolution = "1920x1080",
                    displayId = 1,
                    icon = painterResource(id = R.drawable.ic_screen)
                )
                CustomItem(
                    name = "显示器1",
                    resolution = "1920x1080",
                    displayId = 1,
                    icon = painterResource(id = R.drawable.ic_screen)
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
fun CustomItem(name: String, resolution: String, displayId: Int, icon: Painter) {
    ConstraintLayout(
        modifier = Modifier
            .height(100.dp)
            .wrapContentWidth()
            .padding(10.dp)
            .background(
                color = Color.Cyan, shape = RoundedCornerShape(8.dp)
            )
    ) {
        val (image1, text1, text2, text3) = createRefs()

        Image(
            painter = icon, contentDescription = null, // 无需提供描述
            modifier = Modifier
                .size(48.dp)
                .constrainAs(image1) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
        )

        Text(
            text = name,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .constrainAs(text1) {
                    start.linkTo(image1.end)
                    top.linkTo(image1.top)
                }
        )

        Text(text = "分辨率: $resolution",
            modifier = Modifier
                .constrainAs(text2) {
                    start.linkTo(image1.end)
                    top.linkTo(text1.bottom)
                    end.linkTo(parent.end, margin = 10.dp)
                })

        Text(text = "显示ID: $displayId",
            modifier = Modifier
                .constrainAs(text3) {

                    start.linkTo(image1.end)
                    top.linkTo(text2.bottom)
                })
    }
}
