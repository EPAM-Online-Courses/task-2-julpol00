package efs.task.syntax;

import java.nio.channels.ScatteringByteChannel;
import java.util.Scanner;
import java.util.Random;

public class GuessNumberGame {

    int M;

    //Do not modify main method
    public static void main(String[] args) {
        try {
            GuessNumberGame game = new GuessNumberGame(args.length > 0 ? args[0] : "");
            game.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public GuessNumberGame(String argument) {
        try {
            M = Integer.parseInt(argument);
            if (M > UsefulConstants.MAX_UPPER_BOUND || M < 1) {
                System.out.println(UsefulConstants.WRONG_ARGUMENT);
                throw new IllegalArgumentException();
            }

        } catch (NumberFormatException e) {
            System.out.println(UsefulConstants.WRONG_ARGUMENT);
            throw new IllegalArgumentException();
        }
    }

    public void play() {
        System.out.println("<1," + M + ">");

        Random rand = new Random();
        int numberToGuees = rand.nextInt(M) + 1;
        double numberOfAttempts = (int)(Math.abs(Math.floor(Math.log(M)/Math.log(2)) + 1));
        int numberOfUsedAttempts = 1;
        Scanner scannerGuessNumber = new Scanner(System.in);
        boolean numberHasBeenGuessed = false;

        for (int i =0; i < numberOfAttempts; i++) {
            System.out.print("[");
            for (int j =0; j < numberOfUsedAttempts; j++) {
                System.out.print("*");
            }
            for (int j =0; j < numberOfAttempts - numberOfUsedAttempts; j++) {
                System.out.print(".");
            }
            System.out.println("]");
            System.out.println(UsefulConstants.GIVE_ME);

            String guessNumberString = scannerGuessNumber.nextLine();
            int guessNumberInt;

            try{
                guessNumberInt = Integer.parseInt(guessNumberString);
            }
            catch (NumberFormatException e){
                System.out.println(UsefulConstants.NOT_A_NUMBER);
                numberOfUsedAttempts ++;
                continue;
            }

            if (guessNumberInt == numberToGuees){
                System.out.println(UsefulConstants.YES);
                numberHasBeenGuessed = true;
                break;
            }
            else if (guessNumberInt > numberToGuees){
                System.out.println(UsefulConstants.TO_MUCH);
            }
            else if (guessNumberInt < numberToGuees){
                System.out.println(UsefulConstants.TO_LESS);

            }

            numberOfUsedAttempts ++;
            }

        if (numberHasBeenGuessed){
            System.out.println(UsefulConstants.CONGRATULATIONS);
        }
        else{
            System.out.println(UsefulConstants.UNFORTUNATELY);
        }
    }

}
