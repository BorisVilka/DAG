package com.advertisment.advertisementboard.domain.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.advertisment.advertisementboard.R

sealed class City(@StringRes val name: Int, @DrawableRes val crest: Int) {
    object Makhachkala : City(name = R.string.makhachkala, crest = R.drawable.makhachkala)
    object Derbent : City(name = R.string.derbent, crest = R.drawable.derbent)
    object Kaspiysk : City(name = R.string.kaspiysk, crest = R.drawable.kaspiysk)
    object Izberbash : City(name = R.string.izberbash, crest = R.drawable.izberbash)
    object DagOgni : City(name = R.string.dagogni, crest = R.drawable.dag)
    object Dubki : City(name = R.string.dubki, crest = R.drawable.dubci)
    object Buynask : City(name = R.string.buynaysk, crest = R.drawable.buynask)
    object Hasavrut : City(name = R.string.khasavyurt, crest = R.drawable.khasavyurt)
    object Kizlar : City(name = R.string.kizlar, crest = R.drawable.kizlya)
    object Kizilurt : City(name = R.string.kizilurt, crest = R.drawable.kiziljurt)
}