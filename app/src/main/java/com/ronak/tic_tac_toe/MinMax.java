package com.ronak.tic_tac_toe;

import android.util.Log;

public class MinMax {


    private static final String TAG = "Minmax";

    public int getBestMove(int[] array)
    {
        //Log.d(TAG, "winner : "+array[0]+array[1]+array[2]+array[3]+array[4]+array[5]+array[6]+array[7]+array[8]+array[9]);

        int bestscore=-100000;
        int bestmove = 0;
        for(int i=1;i<=9;++i)
        {
            if(array[i] == -1)
            {
                array[i] = 1;
                int score = minmax_fun(array,0,0);
                Log.d(TAG, "getBestMove: "+i+" "+score);
                if(bestscore<score)
                {
                    bestscore = score;
                    bestmove = i;
                }
                array[i] = -1;
            }
        }
        return bestmove;
    }

    public int minmax_fun(int[] array, int depth, int chance)
    {
        int winner = chk_winner(array);
        if(winner != -1)
        {
            if(winner == 1)
            {
                //Log.d(TAG, "winner 11: "+array[0]+array[1]+array[2]+array[3]+array[4]+array[5]+array[6]+array[7]+array[8]+array[9]+" "+(10-depth));
                return 10-depth;
            }
            else
            {
                //Log.d(TAG, "winner00 : "+array[0]+array[1]+array[2]+array[3]+array[4]+array[5]+array[6]+array[7]+array[8]+array[9] +" "+(depth-10));
                return depth-10;
            }
        }

        if(chance == 1)
        {
            int min = -1000000;
            int bestScore = min;
            for (int i = 1; i <= 9; ++i)
            {
                if (array[i] == -1)
                {
                    array[i] = chance;
                    int score = minmax_fun(array, depth + 1, 1 - chance);
                    bestScore = bestScore>score?bestScore:score;
                    array[i] = -1;
                }
            }
            return bestScore==min?0:bestScore;
        }
        else
        {
            int max = 1000000;
            int bestScore = max;
            for (int i = 1; i <= 9; ++i)
            {
                if (array[i] == -1)
                {
                    array[i] = chance;
                    int score = minmax_fun(array, depth + 1, 1 - chance);
                    bestScore = bestScore<score?bestScore:score;
                    array[i] = -1;
                }
            }
            return bestScore==max?0:bestScore;
        }
    }


    public int chk_winner(int[] array)
    {
        final String TAG ="chk_winner" ;

        int winner=-1;

        for (int j = 1; j < 10; j += 3) {
            if (array[j] != (-1) && array[j] == array[j + 1] && array[j] == array[j + 2]) {
                //Log.d(TAG, "onClick: winner1");
                winner = array[j];
                break;
            }
        }
        if (winner == -1)
            for (int j = 1; j < 4; ++j)
                if (array[j] != (-1) && array[j] == array[j + 3] && array[j] == array[j + 6]) {
                    //Log.d(TAG, "onClick: winner2");
                    winner = array[j];
                    break;
                }

        if (winner == -1) {
            if (array[1] != (-1) && array[1] == array[5] && array[1] == array[9]) {
                //Log.d(TAG, "onClick: winner3");
                winner = array[1];
            }

            if (array[3] != (-1) && array[3] == array[5] && array[3] == array[7]) {
                //Log.d(TAG, "onClick: winner4");
                winner = array[3];
            }
        }

        //Log.d(TAG, "winner : "+array[0]+array[1]+array[2]+array[3]+array[4]+array[5]+array[6]+array[7]+array[8]+array[9]+" "+winner);
        return winner;
    }
}
