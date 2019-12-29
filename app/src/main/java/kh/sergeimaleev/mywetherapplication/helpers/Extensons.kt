package kh.sergeimaleev.mywetherapplication.helpers

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import kh.sergeimaleev.mywetherapplication.R
import kh.sergeimaleev.mywetherapplication.common.BetterViewAnimator
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

const val RECEIVED_TIME_FORMAT_PATTERN = "yyyy-MM-dd HH:mm:ss"

fun <C : Context?> C.showToastLong(message: String) {
    if (this == null) return
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun CharSequence.fromCharSequence() =
    this.toString().parseToDate(RECEIVED_TIME_FORMAT_PATTERN)


fun <C : Context?> C.showToastShort(message: String) {
    if (this == null) return
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

@Suppress("DEPRECATION")
fun Context.getColorFromRes(intRes: Int): Int {
    return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
        resources.getColor(intRes)
    } else {
        getColor(intRes)
    }
}

fun String.parseToDate(formatPattern: String): Date {
    val sdf = SimpleDateFormat(formatPattern, Locale.getDefault())
    sdf.timeZone = TimeZone.getTimeZone("UTC")
    return sdf.parse(this)
}

fun Date.parseToString(formatPattern: String): String {
    val sdf = SimpleDateFormat(formatPattern, Locale.getDefault())
    return sdf.format(this)
}

fun String.convertDateStringToFormat(inStringPattern: String, outStringPattern: String): String {
    val date = this.parseToDate(inStringPattern)
    return date.parseToString(outStringPattern)
}

fun Fragment.showLoading(progressBarId: Int = R.id.progress_loader) {
    this.requireActivity().findViewById<BetterViewAnimator>(R.id.main_activity_loader)
        .visibleChildId = progressBarId
}

fun Fragment.hideLoading(contentContainerId: Int = R.id.nav_host_fragment) {
    requireActivity().findViewById<BetterViewAnimator>(R.id.main_activity_loader).visibleChildId =
        contentContainerId
}

//visibility
fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

@Suppress("DEPRECATION")
fun <C : Context> C.getFormatterByPattern(pattern: String): SimpleDateFormat {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        SimpleDateFormat(pattern, resources.configuration.locales.get(0))
    } else {
        SimpleDateFormat(pattern, resources.configuration.locale)
    }
}

fun Int?.ifNullSetZero(): Int {
    return this ?: 0
}

fun Double?.ifNullSetZero(): Double {
    return this ?: 0.0
}

/**
 * Do action if this [Any]? value is not null or blank string.
 **/
fun Any?.ifNotNullOrBlankDoAction(action: () -> Unit) {
    when {
        this == null -> return
        this.toString().isBlank() -> return
        else -> action.invoke()
    }
}

fun Context.getTextArray(@StringRes intResItems: Array<Int>): Array<CharSequence> {
    val arrayList = ArrayList<CharSequence>(intResItems.size)
    intResItems.forEach {
        arrayList.add(this.getText(it))
    }
    return arrayList.toTypedArray()
}

fun Fragment.navigateTo(
    to: Int,
    popTo: Int? = null,
    inclusive: Boolean = false,
    bundle: Bundle? = null
) {
    val options = NavOptions.Builder()
        .setEnterAnim(R.anim.nav_default_enter_anim)
        .setExitAnim(R.anim.nav_default_exit_anim)
        .setPopEnterAnim(R.anim.nav_default_pop_enter_anim)
        .setPopExitAnim(R.anim.nav_default_pop_exit_anim)

    popTo?.let { options.setPopUpTo(it, inclusive) }

    findNavController().navigate(to, bundle, options.build())
}

fun handlerPostDelayed(delay: Long, function: () -> Unit) {
    Handler().postDelayed({
        function.invoke()
    }, delay)
}
