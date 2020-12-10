package de.drauschke.casestudypeg.brandToggler

import de.drauschke.casestudypeg.R

/**
 * Enum to bind the resources of a brand to an static context.
 */
enum class Brand(val nameID: Int, val appearanceID: Int) {
    PARSHIP(R.string.brand_parship, R.style.TextView_Parship),
    ELITE_PARTNER(R.string.brand_elitepartner, R.style.TextView_ElitePartner)
}