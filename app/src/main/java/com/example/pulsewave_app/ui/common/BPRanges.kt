package com.example.pulsewave_app.ui.common

import com.example.pulsewave_app.ui.theme.BloodPressureBlue
import com.example.pulsewave_app.ui.theme.BloodPressureCardBlue
import com.example.pulsewave_app.ui.theme.BloodPressureCardGreen
import com.example.pulsewave_app.ui.theme.BloodPressureCardOrange
import com.example.pulsewave_app.ui.theme.BloodPressureCardPurple
import com.example.pulsewave_app.ui.theme.BloodPressureCardRed
import com.example.pulsewave_app.ui.theme.BloodPressureCardYellow
import com.example.pulsewave_app.ui.theme.BloodPressureGlowBlue
import com.example.pulsewave_app.ui.theme.BloodPressureGlowGreen
import com.example.pulsewave_app.ui.theme.BloodPressureGlowOrange
import com.example.pulsewave_app.ui.theme.BloodPressureGlowPurple
import com.example.pulsewave_app.ui.theme.BloodPressureGlowRed
import com.example.pulsewave_app.ui.theme.BloodPressureGlowYellow
import com.example.pulsewave_app.ui.theme.BloodPressureGreen
import com.example.pulsewave_app.ui.theme.BloodPressureOrange
import com.example.pulsewave_app.ui.theme.BloodPressurePurple
import com.example.pulsewave_app.ui.theme.BloodPressureRed
import com.example.pulsewave_app.ui.theme.BloodPressureYellow
import com.example.pulsewave_app.ui.theme.NoConnectionCardGrey
import com.example.pulsewave_app.ui.theme.NoConnectionGlowGrey
import com.example.pulsewave_app.ui.theme.NoConnectionGrey

enum class BPRanges(
    val color: BPColors,
    val title: String,
    val symptoms: Array<String>,
    val care: Array<String>,
    val avoid: Array<String>,
    val alert: Boolean,
    val emergency: Boolean
) {
    HYPOTENSIONALERT(
        BPColors(
            BloodPressurePurple,
            BloodPressureCardPurple,
            BloodPressureGlowPurple
        ),
        "Critical Hypotension",
        arrayOf("Confusion", "Fainting", "Blurry Vision", "Sleepiness"),
        arrayOf(
            "Contact/seek emergency support",
            "Place yourself in a safe position, as you could lose consciousness",
            "Get near others that can support you",
            "Move slowly when changing posture, when standing, sitting, or bending down",
            "Drink water",
        ),
        arrayOf(""),
        true,
        true
    ),
    HYPOTENSION(
        BPColors(
            BloodPressurePurple,
            BloodPressureCardPurple,
            BloodPressureGlowPurple
        ),
        "Hypotensive",
        arrayOf("Nausea", "Dizziness", "Tiredness", "Weakness"),
        arrayOf(""),
        arrayOf(""),
        true,
        false
    ),
    LOW(
        BPColors(
            BloodPressureBlue,
            BloodPressureCardBlue,
            BloodPressureGlowBlue
        ),
        "Low Normal",
        arrayOf(),
        arrayOf(),
        arrayOf(),
        false,
        false
    ),
    NORMAL(
        BPColors(
            BloodPressureGreen,
            BloodPressureCardGreen,
            BloodPressureGlowGreen
        ),
        "Normal",
        arrayOf(),
        arrayOf(),
        arrayOf(),
        false,
        false
    ),
    ELEVATED(
        BPColors(
            BloodPressureYellow,
            BloodPressureCardYellow,
            BloodPressureGlowYellow
        ),
        "High",
        arrayOf(),
        arrayOf(),
        arrayOf(),
        false,
        false
    ),
    HYPERTENSION1(
        BPColors(
            BloodPressureOrange,
            BloodPressureCardOrange,
            BloodPressureGlowOrange
        ),
        "Hypertensive",
        arrayOf("It is uncommon for symptoms to present themselves at this stage, but you are at increased risk of issues resulting from high blood pressure."),
        arrayOf(),
        arrayOf(),
        true,
        false
    ),
    HYPERTENSION2(
        BPColors(
            BloodPressureRed,
            BloodPressureCardRed,
            BloodPressureGlowRed
        ),
        "Very Hypertensive",
        arrayOf("Headaches", "Chest Pain", "Nausea", "Anxiety"),
        arrayOf(""),
        arrayOf(""),
        true,
        false
    ),
    HYPERTENSIONALERT(
        BPColors(
            BloodPressureRed,
            BloodPressureCardRed,
            BloodPressureGlowRed
        ),
        "Critical Hypertension",
        arrayOf(
            "Severe Headaches",
            "Difficulty Breathing",
            "Confusion",
            "Nosebleeds",
            "Dizziness"
        ),
        arrayOf(""),
        arrayOf(""),
        true,
        true
    ),
    NULL(
        BPColors(
            NoConnectionGrey,
            NoConnectionCardGrey,
            NoConnectionGlowGrey,
        ),
        "No Connection",
        arrayOf(""),
        arrayOf(""),
        arrayOf(""),
        true,
        true,
    )
}