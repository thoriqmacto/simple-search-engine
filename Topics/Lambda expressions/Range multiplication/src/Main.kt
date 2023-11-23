val lambda: (Long, Long) -> Long = { left, right ->
    var result = 1L

    // Ensure 0 <= left border <= right border
    val start = if (left <= right) left else right
    val end = if (left <= right) right else left

    for(num in start..end){
        result *= num
    }

    result
}