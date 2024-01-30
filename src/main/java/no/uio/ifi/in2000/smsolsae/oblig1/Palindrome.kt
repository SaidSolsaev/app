package no.uio.ifi.in2000.smsolsae.oblig1


fun isPalindrome(tekst: String) : Boolean{
    var revTekst = tekst.reversed()
    return(revTekst.lowercase() == tekst.lowercase())
}