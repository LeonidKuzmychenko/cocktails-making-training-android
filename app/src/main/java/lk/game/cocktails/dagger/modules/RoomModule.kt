//package lk.game.cocktails.dagger.modules
//
//import android.app.Application
//import androidx.room.Room
//import dagger.Module
//import dagger.Provides
//import lk.game.cocktails.room.AppDatabase
//import lk.game.cocktails.room.GameResultDao
//import javax.inject.Singleton
//
//@Module
//class RoomModule(private val application: Application) {
//
//    @Singleton
//    @Provides
//    fun providesRoomDatabase(): AppDatabase {
//        return Room.databaseBuilder(application, AppDatabase::class.java, "data_base").build()
//    }
//
//    @Singleton
//    @Provides
//    fun providesProductDao(db: AppDatabase): GameResultDao? {
//        return db.employeeDao()
//    }
//    //
//    //    @Singleton
//    //    @Provides
//    //    ProductRepository productRepository(ProductDao productDao) {
//    //        return new ProductDataSource(productDao);
//    //    }
//
//}