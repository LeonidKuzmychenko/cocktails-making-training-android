package lk.game.cocktails.dagger

import dagger.Component
import lk.game.cocktails.MainActivity
import lk.game.cocktails.dagger.modules.AppModule
import lk.game.cocktails.dagger.modules.RoomModule
import lk.game.cocktails.dagger.modules.WebModule
import lk.game.cocktails.room.EmployeeDao
import javax.inject.Singleton

@Singleton
//@Component(modules = [WebModule::class])
//@Component(modules = [WebModule::class, AppModule::class, RoomModule::class])
@Component(modules = [WebModule::class, RoomModule::class])
interface WebComponent {

    fun inject(activity: MainActivity)

//    fun inject(): EmployeeDao
}