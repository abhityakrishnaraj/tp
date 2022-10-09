package seedu.duke;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import seedu.duke.FinanceException.exceptionCollection;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        Ui.showWelcomeMessage();

        List<String> existingUserNames;

        existingUserNames = UserNameFileWorkings.userNameFile();

        boolean isProgramEnd = false;

        while (!isProgramEnd) {
            try {
                Ui.showPromptInfo();
                Commands commandType;
                try {
                    commandType = Commands.valueOf(in.nextLine().toUpperCase());
                } catch (IllegalArgumentException e) {
                    throw new FinanceException(exceptionCollection.COMMAND_TYPE_EXCEPTION);
                }
                switch (commandType) {
                case REGISTER:
                    Ui.showRegisterInfo();
                    String userName = " ";

                    //checks if the username already exists
                    do {
                        System.out.print("Username: ");
                        userName = in.nextLine();
                        System.out.println(" ");
                    } while (existingUserNames.contains(userName));

                    int passWordLength = 0;
                    String passWord = "";
                    do {
                        System.out.print("Password: ");
                        passWord = in.nextLine();
                        System.out.println(" ");
                        passWordLength = passWord.length();
                    } while (passWordLength > 8);

                    existingUserNames.add(userName);
                    UserNameFileWorkings.writeToUserNames(userName);

                    Wallet newWallet = new Wallet(userName, passWord);
                    WalletFile.createNewWallet(newWallet);
                    Ui.showRegisterSuccessInfo();
                    break;
                case BYE:
                    isProgramEnd = true;
                    Ui.showExitMessage();
                    break;
                case LOGIN:
                    System.out.println("login will be implemented soon");
                    break;
                default:
                    throw new FinanceException(exceptionCollection.COMMAND_TYPE_EXCEPTION);
                }
            } catch (FinanceException e) {
                e.handleException();
            }
        }

    }
}
