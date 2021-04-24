package lk.game.cocktails.dagger.annotation.named

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Qualifier(val value: Keys)