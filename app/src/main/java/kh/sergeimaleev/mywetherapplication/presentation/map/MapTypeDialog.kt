package kh.sergeimaleev.mywetherapplication.presentation.map

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog
import kh.sergeimaleev.mywetherapplication.R
import kh.sergeimaleev.mywetherapplication.helpers.getTextArray

class MapTypeDialog(
    context: Context,
    items: HashMap<Int, Int>,
    private val onClickAction: (mapType: Int) -> Unit
) {
    companion object {
        private const val DIALOG_TITLE = R.string.dialog_make_choose
    }

    fun show(): Unit = alert.show()
    fun hide(): Unit = if (alert.isShowing) alert.dismiss() else Unit

    private val alert = AlertDialog.Builder(context)
        .setCancelable(true)
        .setTitle(DIALOG_TITLE)
        .setSingleChoiceItems(context.getTextArray(items.values.toTypedArray()), 0, ::onChoose)
        .create()

    private fun onChoose(dialogInterface: DialogInterface, item: Int) {
        onClickAction.invoke(item)
        this.hide()
    }
}




