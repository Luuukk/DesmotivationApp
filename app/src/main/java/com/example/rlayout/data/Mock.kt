package com.example.rlayout.data

import com.example.rlayout.infra.MotivationConstants
import kotlin.random.Random

data class Phrase(val description: String, val categoryId: Int)

class Mock {

    private val infinity = MotivationConstants.FILTER.INFINITY
    private val sad = MotivationConstants.FILTER.SAD
    private val heartb = MotivationConstants.FILTER.HEARTB

    private val mListPhrase = listOf<Phrase>(
        Phrase("Você não pode mudar o seu passado. Mas pode estragar o seu futuro.", sad),
        Phrase("Um dia você perde. No outro você não ganha.", sad),
        Phrase("Só dará errado se você tentar.", sad),
        Phrase("Nunca foi azar, sempre foi incompetência.", sad),
        Phrase("O caminho é longo, mas a derrota é certa.", sad),
        Phrase("Você não é incrível.", sad),
        Phrase("Nada como um dia pior do que o outro.", heartb),
        Phrase("Nunca é tarde para desistir.", heartb),
        Phrase("O não você já tem. Busque a humilhação.", heartb),
        Phrase("A vida é um conto de falhas.", heartb),
        Phrase(
            "Nunca deixe ninguém dizer que você não consegue. Diga você mesmo: Eu não consigo!",
            heartb
        ),
        Phrase("Lute como nunca. Perca como sempre.", heartb),
        Phrase("Acreditar que você pode já é meio caminho errado.", heartb)
    )

    fun getPhrase(value: Int): String {
        val filtered = mListPhrase.filter { it.categoryId == value || value == infinity }
        return filtered[Random.nextInt(filtered.size)].description
    }

}

