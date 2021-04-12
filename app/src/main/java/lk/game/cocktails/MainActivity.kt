package lk.game.cocktails

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import lk.game.cocktails.application.MyApplication
import lk.game.cocktails.retrofit.Api
import lk.game.cocktails.room.AppDatabase
import lk.game.cocktails.room.EmployeeDao
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var db: AppDatabase

    @Inject
    lateinit var api:Api

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (application as MyApplication).getWebComponent().inject(this)

        Log.d(TAG, "db = $db")
        Log.d(TAG, "Api = $api")

        val employeeDao = db.employeeDao()
        Log.d(TAG, "employeeDao = $employeeDao")
        GlobalScope.launch {
            Log.d(TAG, "employeeDao = ${employeeDao?.getAll()}")
        }
    }

}
