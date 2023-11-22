package ir.miare.androidcodechallenge.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import ir.miare.androidcodechallenge.R
import ir.miare.androidcodechallenge.databinding.ActivityMainBinding
import ir.miare.androidcodechallenge.presentation.ui.ranking.RankingFragment
import ir.miare.androidcodechallenge.presentation.util.SortType

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var rankingFragment: RankingFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        rankingFragment = RankingFragment()

        binding.rbNone.isChecked = true
        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.rb_team_league -> {
                    rankingFragment.sortData(
                        sortType = SortType.LEAGUE_AND_TEAM
                    )
                }

                R.id.rb_most_goals -> {
                    rankingFragment.sortData(
                        sortType = SortType.MOST_GOALS
                    )
                }

                R.id.rb_average_goal -> {
                    rankingFragment.sortData(
                        sortType = SortType.MOST_AVERAGE_GOALS
                    )
                }

                else -> {
                    rankingFragment.sortData(
                        sortType = SortType.NONE
                    )
                }
            }
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, rankingFragment)
            .commit()
    }
}
