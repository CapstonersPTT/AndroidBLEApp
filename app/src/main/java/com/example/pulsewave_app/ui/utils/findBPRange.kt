package com.example.pulsewave_app.ui.utils

/**
 * This function references the following 2 charts:
 * https://www.helpguide.org/articles/healthy-living/blood-pressure-and-your-brain.htm
 * https://pharmeasy.in/blog/low-blood-pressure-precautions-and-ways-to-manage-it/
 * But also, many other charts use 110 as the threshold for diastolic so I'm erring on the side
 * of safety.
 */
fun findBPRange(systolic: Int, diastolic: Int): BPRange {
    return if (systolic < 0 || diastolic < 0) {
        BPRange.NULL
    } else if (systolic > 180 || diastolic > 110) {
        BPRange.HYPERTENSIONALERT
    } else if (systolic > 140 || diastolic > 90) {
        BPRange.HYPERTENSION2
    } else if (systolic < 80 || diastolic < 50) {
        BPRange.HYPOTENSIONALERT
    } else if (systolic < 90 || diastolic < 60) {
        BPRange.HYPOTENSION
    } else if (systolic < 100 || diastolic < 65) {
        BPRange.LOW
    } else if (systolic < 120 && diastolic < 80) {
        BPRange.NORMAL
    } else if (systolic < 130 && diastolic < 80) {
        BPRange.ELEVATED
    } else {
        BPRange.HYPERTENSION1
    }
}