package ru.sberschool.hystrix

class FallbackSlowlyApi : SlowlyApi {
    override fun getPokemon() = Pokemon(name = "fallback")
}


