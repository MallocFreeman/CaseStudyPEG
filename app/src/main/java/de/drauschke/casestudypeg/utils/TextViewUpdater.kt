package de.drauschke.casestudypeg.utils

import android.content.Context
import android.widget.TextView
import androidx.core.widget.TextViewCompat
import de.drauschke.casestudypeg.brandToggler.Brand

/**
 * Utility object for attribute manipulation on a {@link TextView}
 */
object TextViewUpdater {
    /**
     * Updates the text and the text-appearance of the given [TextView]
     *
     * @param textView the [TextView] to change the values on.
     * @param brand    the [Brand] to change the style and text to.
     * @param context  the [Context] to grant access to resources.
     */
    fun update(textView: TextView, brand: Brand, context: Context?) {
        TextViewCompat.setTextAppearance(textView, brand.appearanceID)
        textView.text = TextTools.getTextFromResourceID(context!!, brand.nameID)
    }
}
