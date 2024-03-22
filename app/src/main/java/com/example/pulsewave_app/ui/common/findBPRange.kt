package com.example.pulsewave_app.ui.common

/**
 * This function references the following 2 charts:
 * https://www.helpguide.org/articles/healthy-living/blood-pressure-and-your-brain.htm
 * https://pharmeasy.in/blog/low-blood-pressure-precautions-and-ways-to-manage-it/
 * But also, many other charts use 110 as the threshold for diastolic so I'm erring on the side
 * of safety.
 */
fun findBPRange(systolic: Int, diastolic: Int): BPRanges {
    return if (systolic < 0 || diastolic < 0) {
        BPRanges.NULL
    } else if (systolic > 180 || diastolic > 110) {
        BPRanges.HYPERTENSIONALERT
    } else if (systolic > 140 || diastolic > 90) {
        BPRanges.HYPERTENSION2
    } else if (systolic < 80 || diastolic < 50) {
        BPRanges.HYPOTENSIONALERT
    } else if (systolic < 90 || diastolic < 60) {
        BPRanges.HYPOTENSION
    } else if (systolic < 100 || diastolic < 65) {
        BPRanges.LOW
    } else if (systolic < 120 && diastolic < 80) {
        BPRanges.NORMAL
    } else if (systolic < 130 && diastolic < 80) {
        BPRanges.ELEVATED
    } else {
        BPRanges.HYPERTENSION1
    }
}