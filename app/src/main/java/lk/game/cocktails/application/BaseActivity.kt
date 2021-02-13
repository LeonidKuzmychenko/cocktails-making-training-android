package lk.game.cocktails.application

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<B : ViewBinding>(val bindingFactory: (LayoutInflater) -> B) : AppCompatActivity() {

//    protected lateinit var TAG: String
    protected lateinit var binding: B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        TAG = "${this.localClassName}TAG"
        binding = bindingFactory(layoutInflater)
        setContentView(binding.root)
    }

}