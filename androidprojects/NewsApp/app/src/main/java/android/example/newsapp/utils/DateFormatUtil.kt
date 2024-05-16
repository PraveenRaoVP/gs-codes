package android.example.newsapp.utils

class DateFormatUtil {
    companion object {
        fun calculateFormattedDateTime(date: String, time: String): String {
            val dateTimeRegex = Regex("""(\w+), (\d+) (\w+), (\d+). Time: (\d+):(\d+) (\w+)""")
            val matchResult = dateTimeRegex.find("$date. Time: $time") ?: return ""

            val (_, day, month, year, hour, minute, amPm) = matchResult.destructured
            val formattedHour = if (amPm.toLowerCase() == "pm") {
                (hour.toInt() + 12).toString()
            } else {
                hour
            }

            return "$year-$month-$day $formattedHour:$minute:00"
        }
    }
}