package justjump.coding_calculator.data.local

class ListColorsRange {

    private val itemList: Array<Array<String>> = arrayOf(
        arrayOf("Red", "#f44336"),
        arrayOf("Pink", "#e91e63"),
        arrayOf("Purple", "#9c27b0"),
        arrayOf("Deep Purple", "#673ab7"),
        arrayOf("Indigo", "#3f51b5"),
        arrayOf("Blue", "#2196f3"),
        arrayOf("Light Blue", "#03a9f4"),
        arrayOf("Cyan", "#00bcd4"),
        arrayOf("Teal", "#009688"),
        arrayOf("Green", "#4caf50"),
        arrayOf("Light Green", "#8bc34a"),
        arrayOf("Lime", "#cddc39"),
        arrayOf("Yellow", "#ffeb3b"),
        arrayOf("Amber", "#ffc107"),
        arrayOf("Orange", "#ff9800"),
        arrayOf("Deep Orange", "#ff5722"),
        arrayOf("Brown", "#795548"),
        arrayOf("Grey", "#9e9e9e"),
        arrayOf("Blue Grey", "#607d8b")
    )

    private val rangeColors1: Array<Array<String>> = arrayOf(
        arrayOf("50","#ffebee"),
        arrayOf("100","#ffcdd2"),
        arrayOf("200","#ef9a9a"),
        arrayOf("300","#e57373"),
        arrayOf("400","#ef5350"),
        arrayOf("500","#f44336"),
        arrayOf("600","#e53935"),
        arrayOf("700","#d32f2f"),
        arrayOf("800","#c62828"),
        arrayOf("900","#b71c1c"),
        arrayOf("A100","#ff8a80"),
        arrayOf("A200","#ff5252"),
        arrayOf("A400","#ff1744"),
        arrayOf("A700","#d50000")
    )

    private val rangeColors2: Array<Array<String>> = arrayOf(
        arrayOf("50","#fce4ec"),
        arrayOf("100","#f8bbd0"),
        arrayOf("200","#f48fb1"),
        arrayOf("300","#f06292"),
        arrayOf("400","#ec407a"),
        arrayOf("500","#e91e63"),
        arrayOf("600","#d81b60"),
        arrayOf("700","#c2185b"),
        arrayOf("800","#ad1457"),
        arrayOf("900","#880e4f"),
        arrayOf("A100","#ff80ab"),
        arrayOf("A200","#ff4081"),
        arrayOf("A400","#f50057"),
        arrayOf("A700","#c51162")
    )

    fun getListColors(): Array<Array<String>> {
        return itemList
    }

    fun getRangeColors(dataColor: String): Array<Array<String>> {
        when(dataColor){
            "#f44336" -> return rangeColors1
            "#e91e63" -> return rangeColors2
        }
        return emptyArray()
    }
}





