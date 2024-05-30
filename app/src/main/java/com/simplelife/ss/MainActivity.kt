package com.simplelife.ss

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.simplelife.ss.bean.AppItemBean
import com.simplelife.ss.bean.ScreenItemBean
import com.simplelife.ss.ui.theme.CustomActive
import com.simplelife.ss.ui.theme.CustomBackground
import com.simplelife.ss.ui.theme.CustomButtonPrimary
import com.simplelife.ss.ui.theme.CustomButtonSuccess
import com.simplelife.ss.ui.theme.CustomDisActive
import com.simplelife.ss.ui.theme.CustomMask
import com.simplelife.ss.ui.theme.CustomTextLight
import com.simplelife.ss.ui.theme.CustomTextPrimary
import com.simplelife.ss.ui.theme.SecondScreenTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

//        val resources: Resources = this.resources
//        val config: Configuration = resources.configuration
//        config.setLocale(Locale.JAPAN)
//        resources.updateConfiguration(config, resources.displayMetrics)

        // 启用边缘到边缘

        setContent {
            SecondScreenTheme() {
                Content()
            }
        }
    }

    override fun onResume() {
        super.onResume()

        val areNotificationsEnabled = NotificationUtils.isNotificationEnabled(this)

        if (!areNotificationsEnabled) {
            ToastUtils.showShort(this, this.getString(R.string.s3))
        } else {
            NotificationUtils.createNotificationChannel(this)

            NotificationUtils.showNotification(this, WakeActivity())
        }
    }
}


class StatusBean(
    var packageName: String,
    var appName: String,
    var displayId: Int,
    var displayName: String,
)

@Composable
fun Content() {

    // 创建一个可变状态用于追踪 loading 状态
    val isLoading = remember { mutableStateOf(false) }

    var displayList = remember {
        mutableStateOf(listOf<ScreenItemBean>())
    }
    var appList = remember {
        mutableStateOf(listOf<AppItemBean>())
    }

    var textFieldValue by remember { mutableStateOf(TextFieldValue()) }
    var Status by remember { mutableStateOf(StatusBean("", "", -1, "")) }

    var context = LocalContext.current

    if (isLoading.value) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clickable(enabled = false, onClick = {})
                .background(CustomMask)
                .zIndex(1f)
        ) {
            CircularProgressIndicator(
                color = Color.White, modifier = Modifier.align(Alignment.Center)
            )
        }
    }

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


            var MyText: String =
                "${context.getString(R.string.s4)}（${context.getString(R.string.s5)}）"//"屏幕列表（点击你要投放的屏幕）"


            Text(text = MyText,
                fontSize = 18.sp,
                color = CustomTextLight,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.constrainAs(text1) {

                })


            LaunchedEffect(Unit) {
                isLoading.value = true

                CoroutineScope(Dispatchers.Default).launch {

                    delay(500)
                    displayList.value = loadDisplayListData(context)
                    appList.value = loadAppListData(context, textFieldValue.text)
                    // 耗时操作完成后将 loading 状态设置为 false
                    isLoading.value = false
                    // 按钮事件结束后将按钮点击状态设置为 false
                }
            }


            //屏幕列表
            //屏幕列表
            //屏幕列表
            //屏幕列表
            //屏幕列表
            //屏幕列表
            //屏幕列表
            //屏幕列表
            //屏幕列表
            //屏幕列表
            //屏幕列表
            //屏幕列表
            //屏幕列表
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

                displayList.value.forEach { display ->
                    var isActive: Boolean = false

                    if (Status.displayId != -1) {//已经初始化了
                        if (Status.displayId == display.displayId) {
                            isActive = true
                        }
                    }

                    ScreenItem(isActive = isActive,
                        name = display.name,
                        resolution = display.resolution,
                        displayId = display.displayId,
                        onClick = { displayId, displayName ->

                            GlobalUtils.displayId = displayId

                            Status = StatusBean(
                                Status.packageName, Status.appName, displayId, displayName
                            )
                        })
                }
//                ScreenItem(
//                    isActive = true,
//                    name = "显示器1",
//                    resolution = "1920x1080",
//                    displayId = 1,
//                )

            }


            var MyText2: String =
                "${context.getString(R.string.s6)}（${context.getString(R.string.s7)}）"//"应用列表（点击你要投放的应用）",


            Text(text = MyText2,
                fontSize = 18.sp,
                color = CustomTextLight,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.constrainAs(text2) {
                    top.linkTo(row1.bottom, margin = 16.dp)
                })


            //应用列表
            //应用列表
            //应用列表
            //应用列表
            //应用列表
            //应用列表
            //应用列表
            //应用列表
            //应用列表
            //应用列表
            //应用列表
            //应用列表
            //应用列表
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

                appList.value.forEach { app ->
                    var isActive: Boolean = false

                    if (Status.packageName == app.packageName) {
                        isActive = true
                    }

                    AppItem(isActive = isActive,
                        name = app.name,
                        packageName = app.packageName,
                        onClick = { packageName, appName ->
                            GlobalUtils.packageName = packageName
                            Status = StatusBean(
                                packageName, appName, Status.displayId, Status.displayName
                            )
                        })
                }

//                AppItem(
//                    isActive = false, name = "腾讯QQ", packageName = "com.qq.com"
//                )
            }


            //搜索框
            //搜索框
            //搜索框
            //搜索框
            //搜索框
            //搜索框
            //搜索框
            //搜索框
            //搜索框
            //搜索框
            //搜索框
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

                //放大镜按钮
                IconButton(onClick = {
                    isLoading.value = true
                    CoroutineScope(Dispatchers.Default).launch {
                        delay(500)
                        appList.value = loadAppListData(context, textFieldValue.text)
                        // 耗时操作完成后将 loading 状态设置为 false
                        isLoading.value = false
                        // 按钮事件结束后将按钮点击状态设置为 false
                    }
                }, modifier = Modifier
                    .size(48.dp)
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


            //悬浮的按钮
            //悬浮的按钮
            //悬浮的按钮
            //悬浮的按钮
            //悬浮的按钮
            //悬浮的按钮
            //悬浮的按钮
            //悬浮的按钮
            //悬浮的按钮
            //悬浮的按钮
            //悬浮的按钮
            var (rib1, rib2) = createRefs()


            //刷新按钮
            RoundedIconButton(R.drawable.ic_refresh, CustomButtonPrimary, {
                // 同时将 loading 状态设置为 true
                isLoading.value = true


                CoroutineScope(Dispatchers.Default).launch {

                    delay(500)
                    displayList.value = loadDisplayListData(context)
                    appList.value = loadAppListData(context, textFieldValue.text)
                    // 耗时操作完成后将 loading 状态设置为 false
                    isLoading.value = false
                    // 按钮事件结束后将按钮点击状态设置为 false
                }


            }, modifier = Modifier.constrainAs(rib2) {
                end.linkTo(col1.end, margin = 10.dp)
                bottom.linkTo(rib1.top, margin = 10.dp)
            })

            //投屏按钮
            RoundedIconButton(R.drawable.ic_cast, CustomButtonSuccess, {
                if (Status.packageName != "" && Status.displayId != -1) {
                    AppUtils.launchApp(context, Status.packageName, Status.displayId)
                }

            }, modifier = Modifier.constrainAs(rib1) {
                end.linkTo(col1.end, margin = 10.dp)
                bottom.linkTo(col1.bottom, margin = 10.dp)
            })

        }
    }
}


//读取屏幕列表
//读取屏幕列表
//读取屏幕列表
//读取屏幕列表
//读取屏幕列表
//读取屏幕列表
//读取屏幕列表
//读取屏幕列表
//读取屏幕列表
//读取屏幕列表
//读取屏幕列表
//读取屏幕列表
//读取屏幕列表
//读取屏幕列表
//读取屏幕列表
fun loadDisplayListData(context: Context): List<ScreenItemBean> {

    val displayList = DisplayUtils.getAllDisplays(context)

    return displayList.map { display ->
        val resolution =
            "${display.mode.physicalWidth}x${display.mode.physicalHeight}" // 假设display对象有width和height属性
        ScreenItemBean(
            displayId = display.displayId, name = display.name, resolution = resolution
        )
    }
}


//读取应用列表
//读取应用列表
//读取应用列表
//读取应用列表
//读取应用列表
//读取应用列表
//读取应用列表
//读取应用列表
//读取应用列表
//读取应用列表
//读取应用列表
//读取应用列表
//读取应用列表
//读取应用列表
//读取应用列表
fun loadAppListData(context: Context, keyword: String): List<AppItemBean> {

    val appList = AppUtils.getApps(context)

    return appList.filter { app ->
        app.name.contains(keyword, ignoreCase = true) && app.name.length <= 15
    }.map { app ->
        AppItemBean(app.name, app.packageName)
    }.sortedBy { app -> app.name }
}


//预览
//预览
//预览
//预览
//预览
//预览
//预览
//预览
//预览
//预览
//预览
//预览
//预览
//预览
//预览
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SecondScreenTheme() {
        Content()
    }
}


//圆形按钮
//圆形按钮
//圆形按钮
//圆形按钮
//圆形按钮
//圆形按钮
//圆形按钮
//圆形按钮
//圆形按钮
//圆形按钮
//圆形按钮
//圆形按钮
//圆形按钮
//圆形按钮
//圆形按钮
//圆形按钮
//圆形按钮
//圆形按钮
@Composable
fun RoundedIconButton(
    drawableId: Int, color: Color, onClick: () -> Unit, modifier: Modifier = Modifier
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
                .background(color), contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = drawableId),
                contentDescription = null,
                modifier = Modifier.size(32.dp)
            )
        }
    }
}


//APP项目
//APP项目
//APP项目
//APP项目
//APP项目
//APP项目
//APP项目
//APP项目
//APP项目
//APP项目
//APP项目
@Composable
fun AppItem(
    name: String,
    packageName: String,
    isActive: Boolean,
    onClick: (packageName: String, appName: String) -> Unit = { _, _ -> }
) {
    val bgColor = if (!isActive) CustomDisActive else CustomActive
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
            .clickable(onClick = { onClick(packageName, name) })
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
            maxLines = 1,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = textColor,
            modifier = Modifier.constrainAs(text1) {
                start.linkTo(image1.end, margin = 16.dp)
                top.linkTo(image1.top, margin = 0.dp)
            })


        Text(text = "包名: $packageName",
            maxLines = 1,
            color = textColor,
            fontSize = 12.sp,
            modifier = Modifier.constrainAs(text2) {
                start.linkTo(image1.end, margin = 16.dp)
                bottom.linkTo(image1.bottom)
            })
    }
}


//屏幕项目
//屏幕项目
//屏幕项目
//屏幕项目
//屏幕项目
//屏幕项目
//屏幕项目
//屏幕项目
//屏幕项目
//屏幕项目
//屏幕项目
//屏幕项目
//屏幕项目
//屏幕项目
//屏幕项目
//屏幕项目
//屏幕项目
@Composable
fun ScreenItem(
    name: String,
    resolution: String,
    displayId: Int,
    isActive: Boolean,
    onClick: (displayId: Int, displayName: String) -> Unit = { _, _ -> }
) {
    val bgColor = if (!isActive) CustomDisActive else CustomActive
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
            .clickable(onClick = {
                onClick(displayId, name)
            })
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
            maxLines = 1,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = textColor,
            modifier = Modifier.constrainAs(text1) {
                start.linkTo(image1.end)
                top.linkTo(image1.top, margin = 6.dp)
            })

        Text(text = "分辨率: $resolution",
            maxLines = 1,
            color = textColor,
            fontSize = 12.sp,
            modifier = Modifier.constrainAs(text2) {
                start.linkTo(image1.end)
                top.linkTo(text1.bottom)
                bottom.linkTo(text3.top)
                end.linkTo(parent.end, margin = 10.dp)
            })

        Text(text = "显示ID: $displayId",
            maxLines = 1,
            color = textColor,
            fontSize = 12.sp,
            modifier = Modifier.constrainAs(text3) {

                start.linkTo(image1.end)
                bottom.linkTo(parent.bottom, margin = 6.dp)
            })
    }
}