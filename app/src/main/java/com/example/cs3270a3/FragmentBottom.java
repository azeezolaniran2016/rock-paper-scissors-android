package com.example.cs3270a3;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link IFragmentBottom} interface
 * to handle interaction events.
 */
public class FragmentBottom extends Fragment {

    private IFragmentBottom mListener;
    private TextView tvGamesPlayed, tvTies, tvPlayerWins, tvComputerWins;
    private Button btnResetCounts;

    public FragmentBottom() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_buttom, container, false);
        btnResetCounts = view.findViewById(R.id.btn_reset_counts);
        btnResetCounts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onResetCounts();
                tvComputerWins.setText("");
                tvGamesPlayed.setText("");
                tvTies.setText("");
                tvPlayerWins.setText("");
            }
        });
        tvGamesPlayed = view.findViewById(R.id.tv_games_played);
        tvComputerWins = view.findViewById(R.id.tv_phone_wins);
        tvPlayerWins = view.findViewById(R.id.tv_my_wins);
        tvTies = view.findViewById(R.id.tv_tie_games);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof IFragmentBottom) {
            mListener = (IFragmentBottom) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement IFragmentTop");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void updateScores(int player, int computer, int ties) {
        tvTies.setText(String.valueOf(ties));
        tvPlayerWins.setText(String.valueOf(player));
        tvComputerWins.setText(String.valueOf(computer));
        tvGamesPlayed.setText(String.valueOf(player + computer + ties));
    }

    public interface IFragmentBottom {
        void onResetCounts();
    }
}
