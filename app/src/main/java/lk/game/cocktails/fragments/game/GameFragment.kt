package lk.game.cocktails.fragments.game

import android.content.Context
import android.os.Bundle
import android.view.*
import lk.game.cocktails.R
import lk.game.cocktails.application.AppComponent
import lk.game.cocktails.base.BaseFragment
import lk.game.cocktails.dagger.annotation.named.Keys
import lk.game.cocktails.dagger.annotation.named.Qualifier
import lk.game.cocktails.databinding.FragmentGameBinding
import lk.game.cocktails.fragments.game.observers.cocktail.GameCocktailAdapterObserver
import lk.game.cocktails.fragments.game.observers.cocktail.GameCocktailImageObserver
import lk.game.cocktails.fragments.game.observers.cocktail.GameCocktailTitleObserver
import lk.game.cocktails.fragments.game.observers.next.GameFirstRunObserver
import lk.game.cocktails.fragments.game.observers.next.GameResultObserver
import lk.game.cocktails.fragments.game.services.CheckResultService
import lk.game.cocktails.fragments.game.services.CountIngredientsService
import lk.game.cocktails.fragments.game.services.NextCocktailService
import lk.game.cocktails.retrofit.repository.ApiRepository
import lk.game.cocktails.statistics.services.SharedPrefStatisticService
import javax.inject.Inject

class GameFragment : BaseFragment<FragmentGameBinding, GameViewModel>() {

    @Inject
    lateinit var apiRepository: ApiRepository

    @Inject
    @Qualifier(Keys.SERVER_NAME)
    lateinit var serverName: String

    @Inject
    lateinit var sharedPrefStatistic: SharedPrefStatisticService

    private lateinit var checkResult: CheckResultService
    private lateinit var nextCocktail: NextCocktailService
    private lateinit var countIngredientsService: CountIngredientsService

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as AppComponent).getWebComponent().inject(this)
        checkResult = CheckResultService(viewModel, sharedPrefStatistic)
        nextCocktail = NextCocktailService(viewModel, apiRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        countIngredientsService = CountIngredientsService(viewModel, binding)

        viewModel.cocktail.observe(lifecycle(), GameCocktailTitleObserver(baseActivity()))
        viewModel.cocktail.observe(
            lifecycle(),
            GameCocktailAdapterObserver(
                lifecycle(),
                viewModel,
                countIngredientsService,
                binding
            )
        )
        viewModel.cocktail.observe(
            lifecycle(),
            GameCocktailImageObserver(binding.cocktailImage, serverName)
        )
        viewModel.firstRun.observe(
            lifecycle(),
            GameFirstRunObserver(viewModel.firstRun, nextCocktail)
        )
        viewModel.cocktail.observe(
            lifecycle(), { countIngredientsService.checkIngredientsCount() }
        )
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_item_game, menu)
        viewModel.result.observe(lifecycle(), GameResultObserver(requireContext(), menu))
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.nextCocktail -> {
                viewModel.result.value = !viewModel.result.value!!
                if (viewModel.result.value!!) {
                    checkResult.checkResult()
                } else {
                    nextCocktail.nextCocktail()
                }
                true
            }
            else -> false
        }
    }

    override fun inflate(inflater: LayoutInflater, container: ViewGroup?): FragmentGameBinding {
        return FragmentGameBinding.inflate(inflater, container, false)
    }

    override fun getViewModel(): Class<GameViewModel> {
        return GameViewModel::class.java
    }

    override fun clearSubTitle(): Boolean {
        return false
    }
}