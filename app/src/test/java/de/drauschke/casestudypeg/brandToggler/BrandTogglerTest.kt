package de.drauschke.casestudypeg.brandToggler

import android.content.Context
import android.content.res.Resources
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class BrandTogglerTest {
    @Mock
    private lateinit var contextMock: Context

    @Mock
    private lateinit var resourcesMock: Resources

    @Test
    fun toggle_InitialBrandParship_BrandAfterToggleIsCorrect() {
        testExecution(1, Brand.PARSHIP, Brand.ELITE_PARTNER)
    }

    @Test
    fun toggle_TwiceInitialBrandParship_BrandAfterToggleIsCorrect() {
        testExecution(2, Brand.PARSHIP, Brand.PARSHIP)
    }

    @Test
    fun toggle_InitialBrandElitePartner_BrandAfterToggleIsCorrect() {
        testExecution(1, Brand.ELITE_PARTNER, Brand.PARSHIP)
    }

    @Test
    fun toggle_TwiceInitialBrandElitePartner_BrandAfterToggleIsCorrect() {
        testExecution(2, Brand.ELITE_PARTNER, Brand.ELITE_PARTNER)
    }

    private fun testExecution(toggleCount: Int, initialBrand: Brand, expectedBrand: Brand) {
        `when`(contextMock.resources).thenReturn(resourcesMock)
        `when`(resourcesMock.getText(Brand.PARSHIP.nameID)).thenReturn("Parship")
        `when`(resourcesMock.getText(Brand.ELITE_PARTNER.nameID)).thenReturn("Elitepartner")
        val brandToggler = BrandToggler(initialBrand)
        for (i in 0 until toggleCount) {
            brandToggler.toggle(contextMock)
        }
        assertEquals(brandToggler.getActiveBrand(), expectedBrand)
    }
}