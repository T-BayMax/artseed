package com.bjike.t.baymax.artseed.core.utils

/**
 * Created by Min on 2016/12/7.
 */

import android.annotation.SuppressLint

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.GregorianCalendar


object DataUtils {
    val yyyyMMddHHmm = "yyyy-MM-dd HH:mm"
    val yyyyMMdd = "yyyy-MM-dd"

    var weekName = arrayOf("星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六")

    val year: Int
        get() = Calendar.getInstance().get(Calendar.YEAR)

    val month: Int
        get() = Calendar.getInstance().get(Calendar.MONTH) + 1

    val currentMonthDay: Int
        get() = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)

    val weekDay: Int
        get() = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)

    val hour: Int
        get() = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)

    val minute: Int
        get() = Calendar.getInstance().get(Calendar.MINUTE)

    var lastClickTime: Long = 0

    val isFastDoubleClick: Boolean
        get() {
            val time = System.currentTimeMillis()
            val timeD = time - lastClickTime
            lastClickTime = time
            return timeD <= 300
        }

    fun currentTime(): String {
        val date = Date()
        val sdf = SimpleDateFormat(yyyyMMdd)// 格式化对象
        val calendar = Calendar.getInstance()
        calendar.time = date
        return sdf.format(calendar.time)
    }

    fun formateStringH(dateStr: String, pattren: String): String {
        val date = parseDate(dateStr, pattren)
        try {
            return dateToString(date, yyyyMMddHHmm)
        } catch (e: Exception) {
            e.printStackTrace()
            return dateStr
        }

    }

    fun formateStringBirthday(dateStr: String, pattren: String): String {
        val date = parseDate(dateStr, pattren)
        try {
            return dateToString(date, yyyyMMdd)
        } catch (e: Exception) {
            e.printStackTrace()
            return dateStr
        }

    }

    fun parseCalendar(dateStr: String): Calendar? {
        val df = SimpleDateFormat(yyyyMMdd)
        val calendar = Calendar.getInstance()
        var date: Date? = null
        try {
            date = df.parse(dateStr)

            calendar.time = date
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return calendar

    }

    fun parseStrCalendar(dateStr: String): Calendar? {
        val df = SimpleDateFormat(yyyyMMddHHmm)
        val calendar = Calendar.getInstance()
        var date: Date? = null
        try {
            date = df.parse(dateStr)

            calendar.time = date
        } catch (e: ParseException) {
            e.printStackTrace()
            return null
        }

        return calendar

    }

    fun parseDate(dateStr: String, type: String): Date? {
        val df = SimpleDateFormat(type)
        var date: Date? = null
        try {
            date = df.parse(dateStr)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return date

    }

    @Throws(Exception::class)
    fun dateToString(date: Date?, pattern: String): String {
        return SimpleDateFormat(pattern).format(date)
    }

    @Throws(Exception::class)
    fun stringToDate(dateStr: String, pattern: String): Date {
        return SimpleDateFormat(pattern).parse(dateStr)
    }

    fun currentTimeDeatil(date: Date): String {

        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm")// 格式化对象
        val calendar = Calendar.getInstance()
        calendar.time = date
        return sdf.format(calendar.time)

    }

    fun leadDate(startDateStr: String, endDateStr: String): Int {
        val startDate = parseDate(startDateStr, yyyyMMdd)
        val endDate = parseDate(endDateStr, yyyyMMdd)
        return ((endDate!!.time - startDate!!.time) / (1000 * 3600 * 24)).toInt()
    }

    fun currentMonth(): String {
        val date = Date()
        val sdf = SimpleDateFormat("yyyy-MM")// 格式化对象
        val calendar = Calendar.getInstance()
        calendar.time = date
        return sdf.format(calendar.time)

    }

    fun lastMonth(date: String): String {

        val sdf = SimpleDateFormat("yyyy-MM")
        val d: Date
        try {
            d = sdf.parse(date)
            val calendar = Calendar.getInstance()
            calendar.time = d
            calendar.add(Calendar.MONTH, -1)
            return sdf.format(calendar.time)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return date
    }

    fun nextMonth(date: String): String {

        val sdf = SimpleDateFormat("yyyy-MM")
        val d: Date
        try {
            d = sdf.parse(date)
            val calendar = Calendar.getInstance()
            calendar.time = d
            calendar.add(Calendar.MONTH, +1)
            return sdf.format(calendar.time)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return date

    }

    fun lastDay(): String {
        val sdf = SimpleDateFormat("yyyy.MM.dd")// 格式化对象
        var date = Date(System.currentTimeMillis())
        val calendar = Calendar.getInstance()
        calendar.time = date
        calendar.add(Calendar.DAY_OF_MONTH, -1)
        date = calendar.time
        return sdf.format(date)
    }

    // 前7天数据
    fun lastSevenDay(): String {
        val sdf = SimpleDateFormat("yyyy.MM.dd")// 格式化对象
        var date = Date(System.currentTimeMillis())
        val calendar = Calendar.getInstance()
        calendar.time = date
        calendar.add(Calendar.DAY_OF_MONTH, -7)
        date = calendar.time
        return sdf.format(date)
    }

    // 前14天数据
    fun lastFourteenDay(): String {
        val sdf = SimpleDateFormat("yyyy.MM.dd")// 格式化对象
        var date = Date(System.currentTimeMillis())
        val calendar = Calendar.getInstance()
        calendar.time = date
        calendar.add(Calendar.DAY_OF_MONTH, -14)
        date = calendar.time
        return sdf.format(date)
    }

    // 本月第一天数据
    fun currentFDay(): String {
        val sdf = SimpleDateFormat("yyyy.MM.dd")// 格式化对象
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.DAY_OF_MONTH,
                calendar.getActualMinimum(Calendar.DAY_OF_MONTH))
        sdf.format(calendar.time)
        return sdf.format(calendar.time)
    }

    // 本月最后天数据
    fun currentLDay(): String {
        val sdf = SimpleDateFormat("yyyy.MM.dd")// 格式化对象
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.DAY_OF_MONTH,
                calendar.getActualMaximum(Calendar.DAY_OF_MONTH))
        sdf.format(calendar.time)
        return sdf.format(calendar.time)
    }

    fun currentLDaySchedule(): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd")// 格式化对象
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.DAY_OF_MONTH,
                calendar.getActualMaximum(Calendar.DAY_OF_MONTH))
        sdf.format(calendar.time)
        return sdf.format(calendar.time)
    }

    // 上个月第一天数据
    fun currentFFday(): String {
        val df = SimpleDateFormat("yyyy.MM.dd")
        val gcLast = Calendar.getInstance() as GregorianCalendar
        val calendar = Calendar.getInstance()
        calendar.time = Date()
        calendar.add(Calendar.MONTH, -1)
        val theDate = calendar.time
        gcLast.time = theDate
        gcLast.set(Calendar.DAY_OF_MONTH, 1)
        return df.format(gcLast.time)

    }

    // 上个月最后一天数据
    fun currentLLday(): String {
        val df = SimpleDateFormat("yyyy.MM.dd")// 格式化对象
        val calendar = Calendar.getInstance()// 此时打印它获取的是系统当前时间
        calendar.set(Calendar.DAY_OF_MONTH,
                calendar.getActualMinimum(Calendar.DAY_OF_MONTH) - 1)
        val theDate = calendar.time
        return df.format(theDate)
    }

    // 上个月最后一天数据
    fun currentLLdaySchedule(date: String): String {
        try {
            val aa = "$date-01"
            val df = SimpleDateFormat("yyyy-MM-dd")// 格式化对象
            val calendar = Calendar.getInstance()// 此时打印它获取的是系统当前时间
            calendar.time = df.parse(aa)
            calendar.set(Calendar.DAY_OF_MONTH,
                    calendar.getActualMaximum(Calendar.DAY_OF_MONTH))
            val theDate = calendar.time
            return df.format(theDate)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return date
    }

    // 下一个月最后一天数据
    fun lastLLdaySchedule(date: String): String {
        try {
            val aa = "$date-01"
            val df = SimpleDateFormat("yyyy-MM-dd")// 格式化对象
            val calendar = Calendar.getInstance()// 此时打印它获取的是系统当前时间
            calendar.time = df.parse(aa)
            calendar.set(Calendar.DAY_OF_MONTH,
                    calendar.getActualMaximum(Calendar.DAY_OF_MONTH))
            val theDate = calendar.time
            return df.format(theDate)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return date
    }
    // 获取最近三十天的数据

    fun lastThrDay(): String {
        val df = SimpleDateFormat("yyyy.MM.dd")// 格式化对象
        val calendar = Calendar.getInstance()// 此时打印它获取的是系统当前时间\

        // calendar.add(Calendar.MONTH, -1); //

        // if (calendar.get(calendar.DAY_OF_MONTH)==1) {
        //
        // }
        calendar.add(Calendar.DATE, -30)

        val theDate = calendar.time
        return df.format(theDate)
    }

    fun getMonthDays(year: Int, month: Int): Int {
        var year = year
        var month = month
        if (month > 12) {
            month = 1
            year += 1
        } else if (month < 1) {
            month = 12
            year -= 1
        }
        val arr = intArrayOf(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
        var days = 0

        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
            arr[1] = 29
        }

        try {
            days = arr[month - 1]
        } catch (e: Exception) {
            e.stackTrace
        }

        return days
    }

    fun getWeekSunday(year: Int, month: Int, day: Int, pervious: Int): IntArray {
        val time = IntArray(3)
        val c = Calendar.getInstance()
        c.set(Calendar.YEAR, year)
        c.set(Calendar.MONTH, month)
        c.set(Calendar.DAY_OF_MONTH, day)
        c.add(Calendar.DAY_OF_MONTH, pervious)
        time[0] = c.get(Calendar.YEAR)
        time[1] = c.get(Calendar.MONTH) + 1
        time[2] = c.get(Calendar.DAY_OF_MONTH)
        return time

    }

    fun getWeekDayFromDate(year: Int, month: Int): Int {
        val cal = Calendar.getInstance()
        cal.time = getDateFromString(year, month)
        var week_index = cal.get(Calendar.DAY_OF_WEEK) - 1
        if (week_index < 0) {
            week_index = 0
        }
        return week_index
    }

    @SuppressLint("SimpleDateFormat")
    fun getDateFromString(year: Int, month: Int): Date? {
        val dateString = (year.toString() + "-" + (if (month > 9) month else "0$month")
                + "-01")
        var date: Date? = null
        try {
            val sdf = SimpleDateFormat("yyyy-MM-dd")
            date = sdf.parse(dateString)
        } catch (e: ParseException) {
            println(e.message)
        }

        return date
    }


    fun getMonthend(year: Int, month: Int): String {

        val calendar = Calendar.getInstance()
        // 设置时间,当前时间不用设置
        // calendar.setTime(new Date());
        // 设置日期为本月最大日期
        calendar.set(Calendar.MONTH, month - 1)
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE))

        // 打印
        val format = SimpleDateFormat("yyyy-MM-dd")
        println(format.format(calendar.time))
        return format.format(calendar.time)
    }

    fun getMonthStart(year: Int, month: Int): String {

        val calendar = Calendar.getInstance()
        // 设置时间,当前时间不用设置
        // calendar.setTime(new Date());
        // 设置日期为本月最大日期
        calendar.set(Calendar.MONTH, month - 1)
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.DATE, 1)

        // 打印
        val format = SimpleDateFormat("yyyy-MM-dd")
        println(format.format(calendar.time))
        return format.format(calendar.time)
    }


    // 昨天
    fun currentYesterday(): String {
        val date = Date()
        val sdf = SimpleDateFormat("yyyy-MM-dd")// 格式化对象
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DATE, -1)
        calendar.time = date
        return sdf.format(calendar.time)
    }

    // 本周的第一天
    fun currentWeekone(): String {
        val date = Date()
        val sdf = SimpleDateFormat("yyyy-MM-dd")// 格式化对象
        val calendar = Calendar.getInstance()
        calendar.time = date
        if (calendar.get(Calendar.DAY_OF_WEEK) == 0) {
            calendar.add(Calendar.DATE, -6)
        } else {

            calendar.add(Calendar.DATE, 2 - calendar.get(Calendar.DAY_OF_WEEK))
        }

        return sdf.format(calendar.time)
    }


    // 获取当前的时分
    fun currentHourMinute(): String {
        val date = Date()
        val sdf = SimpleDateFormat("HH:mm")// 格式化对象

        return sdf.format(date)
    }

}
