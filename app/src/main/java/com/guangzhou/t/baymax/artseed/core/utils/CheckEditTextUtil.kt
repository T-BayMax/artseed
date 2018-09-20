package com.guangzhou.t.baymax.artseed.core.utils

import android.content.Context
import com.guangzhou.t.baymax.artseed.R
import com.guangzhou.t.baymax.artseed.core.utils.ToastUtils.showToast
import java.util.regex.Pattern

/**
 * 输入验证正确性
 * author：T-Baymax on 2018/4/2.
 * mail：baixianhong_aj@163.com
 * authorization：bjike.com
 */

fun Context.checkPhoneNumber(phone: String): Boolean {
    val regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9])|(199))\\d{8}$"
    if (phone == "") {
        showToast(this,resources.getString(R.string.str_hint_phone))
        return true
    }
    if (phone.length != 11) {
        showToast(this,resources.getString(R.string.str_phone_digit_err))
        return true
    }
    val p = Pattern.compile(regex)
    val m = p.matcher(phone)
    val isMatch = m.matches()
    return if (isMatch) {
        false
    } else {
        showToast(this,resources.getString(R.string.str_phone_format_err))
        true
    }
}

/**
 * 检查邮箱格式是否正确
 */
fun Context.isEmailValid(email: String): Boolean {
    val REGEX_EMAIL = "^^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*\$"
    if (email.isNullOrEmpty()) {
        showToast(this,"邮箱不能为空！")
      return true
    }else{
        return if (Pattern.matches(REGEX_EMAIL, email)) {
            false
        } else {
            showToast(this,"邮箱格式有误！")
            true
        }
    }
}

/**
 * 检查密码输入
 */
fun Context.checkPassword(password: String): Boolean {

    if (password.isNullOrBlank()) {
        showToast(this,resources.getString(R.string.str_user_pwd_hint))
        return true
    }

    if (password.length < 6) {
        showToast(this,resources.getString(R.string.str_pwd_length_err))
        return true
    }

    return false
}

/**
 * 检查两次密码是否相同
 */
fun Context.checkPassword(password: String, confirm_password: String): Boolean {

    if (confirm_password.isNullOrBlank()) {
        showToast(this,resources.getString(R.string.str_confirm_password))
        return true
    }
    if (password != confirm_password) {
        showToast(this,resources.getString(R.string.str_pwd_unlike))
        return true
    }
    return false
}