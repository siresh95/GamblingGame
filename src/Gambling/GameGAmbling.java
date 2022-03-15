package Gambling;

import java.util.Scanner;
import java.util.Random;
class GameGAmbling
{
    static int stakeAmount = 100;
    static final int BET_AMOUNT = 1;
    static double percentage = 50.00;
    static final int STOP_IF_WIN = stakeAmount + (int)(( percentage /  100 * stakeAmount ));// 50% profit with additional to initial amount
    static final  int STOP_IF_LOOSES = stakeAmount - (int)(( percentage /  100 * stakeAmount ));// 50% loss with respect to stakeAmount
    static int monthLoss = 0, monthWin = 0;// to store month loss and win
    static int luckyDay = 0, unluckyDay = 0, luckyOrUnluckyAmount = 0;
    static final int DAYS_LIMIT = 30;
    static final String CONTINUE = "YES";
    static String gamblerChoice;
    static boolean choiceResult;
    // Welcome message
    private static void welcomeMsg()
    {
        System.out.println("Gambling will be started with $"+stakeAmount+" and the bet amount will be $"+BET_AMOUNT);
        System.out.println("<----------------------------------------------------------------------------->");
    }
    // Random number generation
    private static int getRandom()
    {
        Random random = new Random();
        return random.nextInt(2);
    }
    // Generate Month report
    private static void monthReport()
    {
        int storeAmount[] = new int[30];
        for( int day = 1; day <= DAYS_LIMIT; day++ )
        {
            stakeAmount = 100;
            // Daily report
            while( stakeAmount > STOP_IF_LOOSES && stakeAmount < STOP_IF_WIN )
            {
                int gameResult = getRandom();
                if ( gameResult > 0 )
                    ++stakeAmount;
                else
                    --stakeAmount;
            }
            // Condition to resign for the day
            if( stakeAmount  == STOP_IF_LOOSES )
            {
                System.out.println("He Lost $"+stakeAmount+ " with the "+day+" day");
                monthLoss += 50;
                storeAmount[day-1] = -50;
                unluckyDay++;
            }
            else
            {
                System.out.println("He Won $"+(stakeAmount - 100)+ " with the "+day+" day");
                monthWin += 50;
                storeAmount[day-1] = 50;
                luckyDay++;
            }
        }
        System.out.println("<------------------------------------------------------->");
        System.out.println("The overall maximum Month Loss amount is $"+monthLoss);
        System.out.println("The overall maximum Month Wins amount is $"+monthWin);
        System.out.println("<------------------------------------------------------->");
        // Condition to print whether gambler made Profit or Loss at the month end.
        if( monthLoss > monthWin )
            System.out.println("The Loss which gambler got at the end $"+(monthLoss - monthWin));
        else if( monthLoss == monthWin )
            System.out.println("The Loss or Win is $0");
        else
            System.out.println("The profit money which gambler made at the end $"+(monthWin - monthLoss));
        System.out.println("<--------------------------------------------------------------------------------------->");
        System.out.println("The total Luckiest days for the gambler in the month is "+luckyDay+" days");
        System.out.println("The total Unluckiest days for the gambler in the month is "+unluckyDay+" days");
        System.out.println("<--------------------------------------------------------------------------------------->");
        // recursion call
        choiceResult = gameContinueChoice();
        if( choiceResult == true )
            monthReport();
    }
    //Listens to Gambler choice
    private static boolean gameContinueChoice()
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Hey! Gambler, Do you Like to Continue your Game If YES Please type YES else Please type NO");
        gamblerChoice = scan.next();
        return gamblerChoice.equals(CONTINUE);
    }

    public static void main(String args[])
    {
        welcomeMsg();
        monthReport();
    }
}