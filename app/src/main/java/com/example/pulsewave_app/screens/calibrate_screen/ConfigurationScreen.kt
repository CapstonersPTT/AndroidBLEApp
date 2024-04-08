package com.example.pulsewave_app.screens.calibrate_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pulsewave_app.R
import com.example.pulsewave_app.screens.calibrate_screen.connect.ConnectSection
import com.example.pulsewave_app.ui.components.ScreenContainer
import com.example.pulsewave_app.ui.components.SectionDivider
import com.example.pulsewave_app.ui.theme.NoConnectionCardGrey
import com.example.pulsewave_app.ui.theme.NoConnectionGlowGrey
import com.example.pulsewave_app.ui.theme.OpenSans
import com.example.pulsewave_app.ui.theme.PulseWaveTheme

@Composable
fun ConfigurationScreen(
    setSystolic: (Int) -> Unit,
    setDiastolic: (Int) -> Unit,
    onClose: () -> Unit,
    onConnect: () -> Unit
) {

    Row(
        modifier = Modifier
            .padding(24.dp, 20.dp)) {
        Text(
            text = "Configuration",
            style = TextStyle(
                fontFamily = OpenSans,
                fontWeight = FontWeight(700),
                fontSize = 24.sp
            ),
            color = Color.White
        )
        Spacer(Modifier.weight(1f))
        Box(
            modifier = Modifier.clickable(
                enabled = true,
                onClick = onClose
            )
        ) {
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.close),
                contentDescription = "close",
            )
        }
    }
    SectionDivider()
    Column(
        modifier = Modifier
            .padding(24.dp, 0.dp)
            .height(IntrinsicSize.Max),
    ) {



        //two input fields
        //one for systolic and one for diastolic
        var systolic by remember { mutableStateOf("") }
        var diastolic by remember { mutableStateOf("") }
        Row(modifier = Modifier.padding(0.dp, 16.dp)) {
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "Calibrate",
                style = TextStyle(
                    fontFamily = OpenSans,
                    fontWeight = FontWeight(700),
                    fontSize = 20.sp
                ),
                color = Color.White
            )
        }
        Row {
            Spacer(modifier = Modifier.weight(1f))
            Box(
                modifier = Modifier
                    .background(color = NoConnectionCardGrey, shape = (RoundedCornerShape(16.dp)))
                    .height(IntrinsicSize.Max)
                    .shadow(
                        elevation = 4.dp,
                        shape = RoundedCornerShape(20.dp),
                        spotColor = NoConnectionGlowGrey,
                    )
            ) {
                Column(modifier = Modifier.padding(24.dp, 16.dp, 24.dp, 24.dp)) {
                    Row() {
                        OutlinedTextField(
                            value = systolic,
                            onValueChange = { systolic = it },
                            label = {
                                Text(
                                    text = "Systolic",
                                    style = TextStyle(
                                        fontFamily = OpenSans,
                                        fontWeight = FontWeight(700),
                                        fontSize = 18.sp,
                                    ),
                                )
                            },
                            textStyle = TextStyle(
                                fontFamily = OpenSans,
                                fontWeight = FontWeight(700),
                                fontSize = 18.sp,
                            )
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Row() {
                        OutlinedTextField(
                            value = diastolic,
                            onValueChange = { diastolic = it },
                            label = {
                                Text(
                                    text = "Diastolic",
                                    style = TextStyle(
                                        fontFamily = OpenSans,
                                        fontWeight = FontWeight(700),
                                        fontSize = 18.sp,
                                    ),
                                )
                            }
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.weight(1f))
        }
        Spacer(modifier = Modifier.height(24.dp))
        Row() {
            Spacer(modifier = Modifier.weight(1f))
            //a button to submit the values
            Box(
                modifier = Modifier
                    .clickable {
                        var intSystolic = 0
                        var intDiastolic = 0

                        try {
                            intSystolic = systolic.toInt()
                            intDiastolic = diastolic.toInt()
                            setSystolic(intSystolic)
                            setDiastolic(intDiastolic)
                        } catch (e: NumberFormatException) {
                            //do nothing
                        }
                        onClose()
                    }
                    .background(color = Color.White, shape = (RoundedCornerShape(50)))
                    .padding(64.dp, 8.dp)
            ) {
                Text(
                    text = "S U B M I T",
                    style = TextStyle(
                        fontFamily = OpenSans,
                        fontWeight = FontWeight(700),
                        fontSize = 16.sp,
                    ),
                    color = Color.DarkGray
                )
            }
            Spacer(modifier = Modifier.weight(1f))
        }
        Spacer(modifier = Modifier.height(24.dp))

    }
    SectionDivider()
    Spacer(modifier = Modifier.height(16.dp))
    ConnectSection(onConnect)
    Spacer(modifier = Modifier.height(24.dp))
    SectionDivider()
}

@Preview(showBackground = true)
@Composable
fun ConfigurationScreenPreview() {
    PulseWaveTheme {
        ScreenContainer {
            ConfigurationScreen({}, {}, {}, {})
        }
    }
}