package ir.miare.androidcodechallenge.presentation.ui.player_info

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ir.miare.androidcodechallenge.R
import ir.miare.androidcodechallenge.databinding.BottomSheetPlayerInfoBinding
import ir.miare.androidcodechallenge.presentation.model.PlayerUI

class PlayerInfoBottomSheet() : BottomSheetDialogFragment() {

    private var binding: BottomSheetPlayerInfoBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.bottom_sheet_player_info, container, false)
        binding = BottomSheetPlayerInfoBinding.bind(view)

        val playerUI = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getSerializable("player_ui", PlayerUI::class.java)
        } else {
            arguments?.getSerializable("player_ui") as PlayerUI
        }

        playerUI?.let { data ->
            binding?.run {
                tvPlayerName.text = data.playerName
                tvTeamName.text = data.teamName
                tvTotalGoals.text = data.totalGoal.toString()

                btnBack.setOnClickListener {
                    dismiss()
                }
            }
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
