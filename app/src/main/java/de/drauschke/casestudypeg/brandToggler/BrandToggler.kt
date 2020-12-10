package de.drauschke.casestudypeg.brandToggler

import android.content.Context

/**
 * Helper class to keep track of the toggled state of the active [Brand].
 */
class BrandToggler {
    private var activeBrand: Brand

    constructor(activeBrand: Brand) {
        this.activeBrand = activeBrand
    }

    constructor(brandID: Int) : this(if (Brand.ELITE_PARTNER.nameID == brandID) Brand.ELITE_PARTNER else Brand.PARSHIP)

    /**
     * Toggles between the available brands.
     *
     * @param context the surrounding context to get access to text resources.
     */
    fun toggle(context: Context?) {
        activeBrand =
            when {
                isActiveBrand(context!!, Brand.PARSHIP) -> {
                    Brand.ELITE_PARTNER
                }
                isActiveBrand(context!!, Brand.ELITE_PARTNER) -> {
                    Brand.EHARMONY
                }
                else -> {
                    Brand.PARSHIP
                }
            }
    }

    /**
     * @return the currently active [Brand]
     */
    fun getActiveBrand(): Brand {
        return activeBrand
    }

    private fun isActiveBrand(context: Context, brand: Brand): Boolean {
        return getTextFromResourceID(context, brand.nameID).equals(
            getTextFromResourceID(
                context,
                activeBrand.nameID
            )
        )
    }

    private fun getTextFromResourceID(context: Context, resourceID: Int): String {
        return context.resources.getText(resourceID).toString()
    }
}