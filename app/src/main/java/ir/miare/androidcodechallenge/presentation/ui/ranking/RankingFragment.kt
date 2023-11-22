package ir.miare.androidcodechallenge.presentation.ui.ranking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ir.miare.androidcodechallenge.R
import ir.miare.androidcodechallenge.databinding.FragmentRankingBinding
import ir.miare.androidcodechallenge.presentation.model.PlayerUI
import ir.miare.androidcodechallenge.presentation.ui.player_info.PlayerInfoBottomSheet
import ir.miare.androidcodechallenge.presentation.ui.ranking.adapter.RankingAdapter
import ir.miare.androidcodechallenge.presentation.util.SortType

@AndroidEntryPoint
class RankingFragment() : Fragment() {

    private lateinit var viewModel: RankingViewModel

    private lateinit var adapter: RankingAdapter

    private var binding: FragmentRankingBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_ranking, container, false)
        binding = FragmentRankingBinding.bind(view)

        viewModel = ViewModelProvider(this)[RankingViewModel::class.java]
        viewModel.getSportData()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = RankingAdapter {
            openPlayerInfoBottomSheet(it)
        }
        binding?.recyclerRank?.adapter = adapter
        binding?.recyclerRank?.layoutManager = LinearLayoutManager(
            requireContext()
        )

        viewModel.sportList.observe(viewLifecycleOwner) {
            adapter.rankList = it

            // this is not optimized enough, so for this purpose it is ok.
            adapter.notifyDataSetChanged()

            binding?.recyclerRank?.scrollToPosition(0)
        }

        viewModel.progressState.observe(viewLifecycleOwner) {
            if (it) {
                binding?.pbLoading?.show()
            } else {
                binding?.pbLoading?.hide()
            }
        }

        viewModel.message.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let { messageUI ->
                val message = if (messageUI.id != -1) {
                    getString(messageUI.id)
                } else {
                    messageUI.text
                }

                if (message.isNotEmpty()) {
                    Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun openPlayerInfoBottomSheet(playerUI: PlayerUI) {
        val dialog = PlayerInfoBottomSheet()
        val bundle = Bundle()
        bundle.putSerializable("player_ui", playerUI)
        dialog.arguments = bundle
        dialog.show(requireActivity().supportFragmentManager, "")
    }

    fun sortData(sortType: SortType) {
        viewModel.sortSport(sortType)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}