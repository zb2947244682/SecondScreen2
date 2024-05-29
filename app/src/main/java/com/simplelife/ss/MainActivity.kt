package com.simplelife.ss

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
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
            .padding(top = 60.dp, start = 12.dp, end = 12.dp)
    ) {

        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ) {

            val (text1, row1, text2, col1, cl1) = createRefs()



            Text(text = "屏幕列表（点击你要投放的屏幕）",
                fontSize = 18.sp,
                color = CustomTextLight,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.constrainAs(text1) {

                })

            Row(modifier = Modifier
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

            Text(text = "应用列表（点击你要投放的应用）",
                fontSize = 18.sp,
                color = CustomTextLight,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.constrainAs(text2) {
                    top.linkTo(row1.bottom, margin = 16.dp)
                })

            Column(modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color.White, shape = RoundedCornerShape(8.dp)
                )
                .constrainAs(col1) {
                    top.linkTo(text2.bottom, margin = 16.dp)
                    bottom.linkTo(cl1.top, margin = 16.dp)
                    height = Dimension.fillToConstraints
                }
                .verticalScroll(rememberScrollState())

            ) {
                AppItem(
                    isActive = true, name = "腾讯QQ", packageName = "com.qq.com"
                )
                AppItem(
                    isActive = false, name = "腾讯QQ", packageName = "com.qq.com"
                )
                AppItem(
                    isActive = false, name = "腾讯QQ", packageName = "com.qq.com"
                )
                AppItem(
                    isActive = false, name = "腾讯QQ", packageName = "com.qq.com"
                )
                AppItem(
                    isActive = false, name = "腾讯QQ", packageName = "com.qq.com"
                )
                AppItem(
                    isActive = false, name = "腾讯QQ", packageName = "com.qq.com"
                )
                AppItem(
                    isActive = false, name = "腾讯QQ", packageName = "com.qq.com"
                )
            }

            ConstraintLayout(modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(
                    color = Color.White, shape = RoundedCornerShape(8.dp)
                )
                .constrainAs(cl1) {
                    bottom.linkTo(parent.bottom, margin = 16.dp)
                }) {

                val (input1, btn2) = createRefs()

                var textFieldValue by remember { mutableStateOf(TextFieldValue()) }

                BasicTextField(
                    value = textFieldValue,
                    onValueChange = { it ->
                        if (it.text.length <= 10) {
                            textFieldValue = it
                        }
                    },
                    textStyle = TextStyle(
                        fontSize = 20.sp,
                        color = CustomTextPrimary,
                    ),
                    maxLines = 1,
                    modifier = Modifier
                        .wrapContentHeight(align = Alignment.CenterVertically)
                        .constrainAs(input1) {
                            start.linkTo(parent.start, margin = 16.dp)
                            top.linkTo(parent.top, margin = 10.dp)
                            bottom.linkTo(parent.bottom, margin = 10.dp)
                            end.linkTo(btn2.start, margin = 10.dp)
                            height = Dimension.fillToConstraints
                            width = Dimension.fillToConstraints
                        },
                )

                IconButton(onClick = { /*TODO*/ }, modifier = Modifier
                    .size(48.dp)
//                    .background(Color.Red)
                    .constrainAs(btn2) {
                        end.linkTo(parent.end, margin = 16.dp)
                        centerVerticallyTo(parent)
                    }) {
                    Icon(
                        Icons.Default.Search,
                        tint = CustomActive,
                        modifier = Modifier.size(32.dp),
                        contentDescription = null
                    )
                }
            }


            var (rib1, rib2) = createRefs()

            RoundedIconButton(
                R.drawable.ic_cast,
                CustomButtonSuccess,
                { },
                modifier = Modifier.constrainAs(rib1) {
                    end.linkTo(col1.end, margin = 10.dp)
                    bottom.linkTo(col1.bottom, margin = 10.dp)
                })

            RoundedIconButton(
                R.drawable.ic_refresh,
                CustomButtonPrimary,
                { },
                modifier = Modifier.constrainAs(rib2) {
                    end.linkTo(col1.end, margin = 10.dp)
                    bottom.linkTo(rib1.top, margin = 10.dp)
                })

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
fun RoundedIconButton(
    drawableId: Int,
    color: Color,
    onClick: () -> Unit, modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(64.dp)
            .shadow(elevation = 4.dp, shape = RoundedCornerShape(32.dp), clip = true) // 添加圆角阴影
            .clip(shape = RoundedCornerShape(32.dp))
            .clickable(onClick = onClick), // 留出一些空间以显示背景
        contentAlignment = Alignment.Center,
    ) {
        Box(
            modifier = Modifier
                .size(64.dp)
                .background(color),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = drawableId),
                contentDescription = null,
                modifier = Modifier.size(32.dp)
            )
        }
    }
}


@Composable
fun AppItem(name: String, packageName: String, isActive: Boolean, onClick: () -> Unit = {}) {
    val bgColor = if (!isActive) Color.White else CustomActive
    var textColor = if (!isActive) CustomTextLight else Color.White
    var painterId = if (isActive) R.drawable.ic_app_light else R.drawable.ic_app

    ConstraintLayout(
        modifier = Modifier
            .height(100.dp)
            .fillMaxWidth()
            .padding(10.dp)
            .background(
                color = bgColor, shape = RoundedCornerShape(8.dp)
            )
            .clickable(onClick = onClick)
    ) {
        val (image1, text1, text2, text3) = createRefs()


        Image(painter = painterResource(id = painterId), contentDescription = null, // 无需提供描述
            modifier = Modifier
                .size(48.dp)
                .constrainAs(image1) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start, margin = 16.dp)
                    centerVerticallyTo(parent)
                })

        Text(text = name,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = textColor,
            modifier = Modifier.constrainAs(text1) {
                start.linkTo(image1.end, margin = 16.dp)
                top.linkTo(image1.top, margin = 0.dp)
            })


        Text(text = "分辨率: $packageName",
            color = textColor,
            fontSize = 12.sp,
            modifier = Modifier.constrainAs(text2) {
                start.linkTo(image1.end, margin = 16.dp)
                bottom.linkTo(image1.bottom)
            })
    }
}

@Composable
fun ScreenItem(
    name: String, resolution: String, displayId: Int, isActive: Boolean, onClick: () -> Unit = {}
) {
    val bgColor = if (!isActive) Color.White else CustomActive
    var textColor = if (!isActive) CustomTextLight else Color.White
    var painterId = if (isActive) R.drawable.ic_screen_light else R.drawable.ic_screen

    ConstraintLayout(
        modifier = Modifier
            .height(100.dp)
            .wrapContentWidth()
            .padding(10.dp)
            .background(
                color = bgColor, shape = RoundedCornerShape(8.dp)
            )
            .clickable(onClick = onClick)
    ) {
        val (image1, text1, text2, text3) = createRefs()

        Image(painter = painterResource(id = painterId), contentDescription = null, // 无需提供描述
            modifier = Modifier
                .size(100.dp)
                .constrainAs(image1) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                })

        Text(text = name,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = textColor,
            modifier = Modifier.constrainAs(text1) {
                start.linkTo(image1.end)
                top.linkTo(image1.top, margin = 6.dp)
            })

        Text(text = "分辨率: $resolution",
            color = textColor,
            fontSize = 12.sp,
            modifier = Modifier.constrainAs(text2) {
                start.linkTo(image1.end)
                top.linkTo(text1.bottom)
                bottom.linkTo(text3.top)
                end.linkTo(parent.end, margin = 10.dp)
            })

        Text(text = "显示ID: $displayId",
            color = textColor,
            fontSize = 12.sp,
            modifier = Modifier.constrainAs(text3) {

                start.linkTo(image1.end)
                bottom.linkTo(parent.bottom, margin = 6.dp)
            })
    }
}