package com.just_jump.coding_calculator.utilities

import android.text.InputFilter

private const val blockCharacterSet = "~#^|\$%*!@/()-':;,?{}=!\$^';,?×÷<>{}€£¥₩%~`¤♡♥_|《》¡¿°•○●□■◇◆♧♣▲▼▶◀↑↓←→☆★▪:-);-):-D:-(:'(:O ghijklmnñopqrstuvwxyzGHIJKLMNÑOPQRSTUVWXYZ"

val myFilter = InputFilter { source, start, end, dest, dstart, dend ->
    if (source != null && blockCharacterSet.contains("" + source)) {
        println(source)
        ""
    } else null
}