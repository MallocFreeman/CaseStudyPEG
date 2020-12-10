package de.drauschke.casestudypeg

import android.content.Intent
import android.view.View
import android.widget.TextView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.hamcrest.Description
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class CaseStudyActivityTest {
    @Rule
    @JvmField
    var activityScenarioRule = ActivityScenarioRule<CaseStudyActivity>(
        Intent(
            ApplicationProvider.getApplicationContext(),
            CaseStudyActivity::class.java
        )
    )

    @Test
    fun toggleBrandRepresentationsAreCorrect() {
        verifyParshipDesign()
        clickToggleButton()
        verifyElitepartnerDesign()
    }

    private fun verifyParshipDesign() {
        verifyDesign(PARSHIP, PARSHIP_RED)
    }

    private fun verifyElitepartnerDesign() {
        verifyDesign(ELITEPARTNER, ELITERPARTNER_BLUE)
    }

    private fun verifyDesign(text: String, htmlColor: String) {
        getBrandTextView().check(ViewAssertions.matches(ViewMatchers.withText(text)))
        getBrandTextView().check(ViewAssertions.matches(withHTMLCodedTextColor(htmlColor)))
    }

    private fun getBrandTextView(): ViewInteraction {
        return Espresso.onView(withId(R.id.brand))
    }

    private fun clickToggleButton() {
        Espresso.onView(withId(R.id.toggle_brand)).perform(ViewActions.click())
    }

    companion object {
        private const val PARSHIP = "Parship"
        private const val PARSHIP_RED = "#D70005"
        private const val ELITEPARTNER = "ElitePartner"
        private const val ELITERPARTNER_BLUE = "#016EB3"

        private fun withHTMLCodedTextColor(htmlColor: String): BoundedMatcher<View?, TextView> {

            return object : BoundedMatcher<View?, TextView>(TextView::class.java) {
                override fun matchesSafely(textView: TextView): Boolean {
                    return mapToHTMLCode(textView.currentTextColor) == htmlColor
                }

                override fun describeTo(description: Description) {
                    description.appendText("with HTML coded text color: ")
                    description.appendValue(htmlColor)
                }

                private fun mapToHTMLCode(colorInt: Int): String {
                    return String.format("#%06X", 0xFFFFFF and colorInt)
                }
            }
        }
    }
}