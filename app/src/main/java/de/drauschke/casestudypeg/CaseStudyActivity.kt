package de.drauschke.casestudypeg

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import de.drauschke.casestudypeg.brandToggler.Brand
import de.drauschke.casestudypeg.brandToggler.BrandToggler
import de.drauschke.casestudypeg.utils.TextViewUpdater

/**
 * Sample Activity for the PEG-CaseStudy of 2020. Shows a text and toggles the content and its style
 * with a button switching from the style and text from the Parship brand to the ElitePartner brand
 * and vice versa.
 */
class CaseStudyActivity : AppCompatActivity() {
    private val CURRENTBRAND = "currentBrand"
    private lateinit var brandToggler: BrandToggler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideActionBar()
        setContentView(R.layout.activity_casestudy)
        initialize(savedInstanceState)
    }

    fun toggleBrand(view: View?) {
        brandToggler.toggle(this)
        updateTextView()
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        savedInstanceState.putInt(
            CURRENTBRAND,
            brandToggler.getActiveBrand().nameID
        )

        super.onSaveInstanceState(savedInstanceState)
    }

    private fun initialize(savedInstanceState: Bundle?) {
        brandToggler = if (savedInstanceState != null) {
            BrandToggler(savedInstanceState.getInt(CURRENTBRAND))
        } else {
            BrandToggler(Brand.PARSHIP)
        }

        updateTextView()
    }

    private fun hideActionBar() {
        supportActionBar?.hide()
    }

    private fun getBrandTextView(): TextView {
        return findViewById<View>(R.id.brand) as TextView
    }

    private fun updateTextView() {
        TextViewUpdater.update(getBrandTextView(), brandToggler.getActiveBrand(), this)
    }
}