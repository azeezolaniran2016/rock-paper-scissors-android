package com.example.cs3270a3;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements FragmentTop.IFragmentTop, FragmentBottom.IFragmentBottom {
    private int computerScore, userScore, ties;
    private FragmentTop fragmentTop;
    private FragmentBottom fragmentBottom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        if (fragment instanceof FragmentBottom) {
            fragmentBottom  = (FragmentBottom) fragment;
        }

        if (fragment instanceof FragmentTop) {
            fragmentTop = (FragmentTop) fragment;
        }
    }

    @Override
    public void onUserWin() {
        userScore++;
        fragmentBottom.updateScores(userScore, computerScore, ties);
    }

    @Override
    public void onComputerWin() {
        computerScore++;
        fragmentBottom.updateScores(userScore, computerScore, ties);
    }

    @Override
    public void onTie() {
        ties ++;
        fragmentBottom.updateScores(userScore, computerScore, ties);
    }

    @Override
    public void onResetCounts() {
        fragmentTop.resetViews();
    }
}
