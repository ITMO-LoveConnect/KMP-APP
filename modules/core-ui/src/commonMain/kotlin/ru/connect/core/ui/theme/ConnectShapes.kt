package ru.connect.core.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.ui.unit.dp

object ConnectShapes {

    val RoundedCornerShape0 = RoundedCornerShape(0.dp)
    val RoundedCornerShape2 = RoundedCornerShape(2.dp)
    val RoundedCornerShape4 = RoundedCornerShape(4.dp)
    val RoundedCornerShape5 = RoundedCornerShape(5.dp)
    val RoundedCornerShape6 = RoundedCornerShape(6.dp)
    val RoundedCornerShape7 = RoundedCornerShape(7.dp)
    val RoundedCornerShape8 = RoundedCornerShape(8.dp)
    val RoundedCornerShape10 = RoundedCornerShape(10.dp)
    val RoundedCornerShape12 = RoundedCornerShape(12.dp)
    val RoundedCornerShape16 = RoundedCornerShape(16.dp)
    val RoundedCornerShape24 = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
    val RoundedCornerShape50 = RoundedCornerShape(50.dp)
    val RoundedCornerShape50p = RoundedCornerShape(50)
    val TopFullyRoundedCorners = RoundedCornerShape(topStartPercent = 100, topEndPercent = 100)

    val LinkShape = RoundedCornerShape(50)

    val Material = Shapes(
        small = RoundedCornerShape4,
        medium = RoundedCornerShape8,
        large = RoundedCornerShape0,
    )
}
