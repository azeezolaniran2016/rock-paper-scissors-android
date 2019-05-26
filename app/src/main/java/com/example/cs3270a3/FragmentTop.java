package com.example.cs3270a3;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link IFragmentTop} interface
 * to handle interaction events.
 */
public class FragmentTop extends Fragment implements View.OnClickListener {
    private final static int ROCK = 3;
    private final static int SCISSORS = 2;
    private final static int PAPER = 1;
    private static ArrayList<Integer> computerChoices = new ArrayList<Integer>(){
        { this.add(new Integer(PAPER)); }
        { this.add(new Integer(ROCK)); }
        { this.add(new Integer(SCISSORS));}
    };
    private final static Random random = new Random();

    private IFragmentTop mListener;
    private Button btnRock, btnPaper, btnScissors, btnPlayAgain;
    private TextView tvPhonePick, tvWinner;
    private View llResults;
    private boolean canPlay = true;

    public FragmentTop() {
        // Required empty public constructor
    }

    private int randomComputerChoice() {
        Collections.shuffle(computerChoices);
        return computerChoices.get(0);
    }

    private String intToSelectionText(int selection) {
        switch (selection) {
            case PAPER: {
                return getString(R.string.btn_paper);
            }
            case SCISSORS: {
                return getString(R.string.btn_scissors);
            }
            case ROCK: {
                return getString(R.string.btn_rock);
            }
            default: { return ""; }
        }
    }

    private void play(int playerChoice) {
        int computerGame  = randomComputerChoice();
        tvPhonePick.setText(intToSelectionText(computerGame));
        if (computerGame > playerChoice) {
            mListener.onComputerWin();
            tvWinner.setText(getString(R.string.computer_won));
        } else if (playerChoice > computerGame) {
            mListener.onUserWin();
            tvWinner.setText(getString(R.string.player_won));
        } else {
            mListener.onTie();
            tvWinner.setText(getString(R.string.game_tied));
        }
        llResults.setVisibility(View.VISIBLE);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_top, container, false);
        btnPaper = view.findViewById(R.id.btn_paper);
        btnPaper.setOnClickListener(this);
        btnRock = view.findViewById(R.id.btn_rock);
        btnRock.setOnClickListener(this);
        btnScissors = view.findViewById(R.id.btn_scissors);
        btnScissors.setOnClickListener(this);

        btnPlayAgain = view.findViewById(R.id.btn_play_again);
        btnPlayAgain.setOnClickListener(this);

        tvWinner = view.findViewById(R.id.tv_winner);
        tvPhonePick = view.findViewById(R.id.tv_phone_pick);

        llResults = view.findViewById(R.id.ll_results);
        llResults.setVisibility(View.INVISIBLE);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof IFragmentTop) {
            mListener = (IFragmentTop) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement IFragmentTop");
        }
    }

    public void resetViews() {
        Button[] actionBtns = { btnRock, btnPaper, btnScissors};
        for (Button btn : actionBtns) {
            btn.setSelected(false);
        }
        llResults.setVisibility(View.INVISIBLE);
        tvPhonePick.setText("");
        tvWinner.setText("");
        canPlay = true;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.btn_play_again) {
            resetViews();
            return;
        }
        if (!canPlay) {
            return;
        }
        canPlay = false;
        switch(view.getId()) {
            case R.id.btn_paper: {
                btnPaper.setSelected(true);
                play(PAPER);
                break;
            }
            case R.id.btn_scissors: {
                btnScissors.setSelected(true);
                play(SCISSORS);
                break;
            }
            case R.id.btn_rock: {
                btnRock.setSelected(true);
                play(ROCK);
                break;
            }
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface IFragmentTop {
       void  onUserWin();
       void onComputerWin();
       void onTie();
    }
}
