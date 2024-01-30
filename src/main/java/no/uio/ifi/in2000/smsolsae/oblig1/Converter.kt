package no.uio.ifi.in2000.smsolsae.oblig1

import kotlin.math.round

enum class ConverterUnits(val liter: Double){
    OUNCE(0.029),
    GALLON(3.785),
    HOGSHEAD(238.480),
    CUP(0.236)
}

fun converter(verdi: Int, enhet: ConverterUnits): Int {

    return (round(enhet.liter * verdi).toInt())

}