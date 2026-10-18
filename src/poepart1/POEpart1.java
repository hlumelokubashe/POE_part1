package poepart1;

import java.util.Scanner;

public class POEpart1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to QuickChat.");

        boolean running = true;
        while (running) {
            System.out.println("\nPlease select an option:");
            System.out.println("1) Send Messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Quit");
            System.out.print("Choice: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("How many messages would you like to enter? ");
                    int totalMessages;
                    try {
                        totalMessages = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid number. Returning to main menu.");
                        break;
                    }

                    for (int i = 1; i <= totalMessages; i++) {
                        System.out.println("\n--- Message " + i + " of " + totalMessages + " ---");
                        
                        System.out.print("Enter Recipient Cell Number (e.g., +27718693002): ");
                        String recipient = scanner.nextLine();

                        System.out.print("Enter Message text: ");
                        String msgText = scanner.nextLine();

                        Message msg = new Message(recipient, msgText);

                        String lengthCheck = msg.validateMessageLength();
                        if (lengthCheck.contains("exceeds")) {
                            System.out.println(lengthCheck);
                            i--;
                            continue;
                        }

                        String cellCheck = msg.checkRecipientCell();
                        System.out.println(cellCheck);
                        if (cellCheck.contains("incorrectly formatted")) {
                            i--;
                            continue;
                        }

                        msg.createMessageHash(i);

                        System.out.println("\nChoose an action:");
                        System.out.println("1) Send Message");
                        System.out.println("2) Disregard Message");
                        System.out.println("3) Store Message to send later");
                        System.out.print("Action choice: ");
                        
                        int actionChoice;
                        try {
                            actionChoice = Integer.parseInt(scanner.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid action choice. Defaulting to disregard.");
                            actionChoice = 2;
                        }

                        System.out.println(msg.SentMessage(actionChoice));

                        System.out.println("\n--- Message Details ---");
                        System.out.println(msg.printMessages());
                    }

                    System.out.println("\nTotal messages sent: " + Message.returnTotalMessagess());
                    break;

                case 2:
                    System.out.println("Coming Soon.");
                    break;

                case 3:
                    running = false;
                    System.out.println("Thank you for using QuickChat. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }
}