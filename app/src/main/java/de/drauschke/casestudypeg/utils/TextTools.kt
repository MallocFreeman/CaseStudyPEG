package de.drauschke.casestudypeg.utils

import android.content.Context
import android.content.res.Resources.NotFoundException

/**
 * Utility object to find resources via identifiers.
 */
object TextTools {
    /**
     * Returns the text for the passed resource ID.
     *
     * @param context The context for accessing the resources.
     * @param id      the resource ID
     * @return the corresponding String representation of the [CharSequence]. If the resource is
     * not available an empty string will be returned.
     */
    fun getTextFromResourceID(context: Context, id: Int): String {
        return try {
            context.resources.getText(id).toString()
        } catch (exception: NotFoundException) {
            ""
        }
    }
}
