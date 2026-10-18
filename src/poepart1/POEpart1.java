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
            System.out.println("3) Stored Messages Menu (Part 3)");
            System.out.println("4) Quit");
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
    System.out.println("\n--- Recently Sent Messages ---");
    if (Message.sentMessagesList.isEmpty()) {
        System.out.println("No messages have been sent yet.");
    } else {
        for (Message m : Message.sentMessagesList) {
            System.out.println(m.printMessages());
            System.out.println("--------------------------------");
        }
    }
    break;
                case 3:
                    // Part 3: Stored Messages Menu Option[cite: 11, 12]
                    boolean subRunning = true;
                    while (subRunning) {
                        System.out.println("\n--- Stored Messages Menu ---");
                        System.out.println("a) Display sender and recipient of all stored messages");
                        System.out.println("b) Display the longest stored message");
                        System.out.println("c) Search for a message ID");
                        System.out.println("d) Search messages by recipient");
                        System.out.println("e) Delete a message using message hash");
                        System.out.println("f) Display full report of stored messages");
                        System.out.println("g) Return to Main Menu");
                        System.out.print("Select an option (a-g): ");
                        
                        String subChoice = scanner.nextLine().trim().toLowerCase();

                        switch (subChoice) {
                            case "a":
                                System.out.println("\n--- Stored Messages (Sender & Recipient) ---");
                                if (Message.storedMessagesList.isEmpty()) {
                                    System.out.println("No stored messages found.");
                                } else {
                                    for (Message m : Message.storedMessagesList) {
                                        System.out.println("Recipient: " + m.getRecipientCell() + " | Message: " + m.getMessageText());
                                    }
                                }
                                break;

                            case "b":
                                System.out.println("\n--- Longest Stored Message ---");
                                if (Message.storedMessagesList.isEmpty()) {
                                    System.out.println("No stored messages found.");
                                } else {
                                    Message longest = Message.storedMessagesList.get(0);
                                    for (Message m : Message.storedMessagesList) {
                                        if (m.getMessageText().length() > longest.getMessageText().length()) {
                                            longest = m;
                                        }
                                    }
                                    System.out.println("Longest Message: " + longest.getMessageText());
                                }
                                break;

                            case "c":
                                System.out.print("Enter Message ID to search: ");
                                String searchID = scanner.nextLine();
                                boolean foundID = false;
                                for (Message m : Message.storedMessagesList) {
                                    if (m.getMessageID().equals(searchID)) {
                                        System.out.println("Found! Recipient: " + m.getRecipientCell() + " | Message: " + m.getMessageText());
                                        foundID = true;
                                        break;
                                    }
                                }
                                if (!foundID) System.out.println("Message ID not found.");
                                break;

                            case "d":
                                System.out.print("Enter Recipient Cell Number to search: ");
                                String searchRecipient = scanner.nextLine();
                                boolean foundRecip = false;
                                for (Message m : Message.storedMessagesList) {
                                    if (m.getRecipientCell().equalsIgnoreCase(searchRecipient)) {
                                        System.out.println("- " + m.getMessageText());
                                        foundRecip = true;
                                    }
                                }
                                if (!foundRecip) System.out.println("No messages found for this recipient.");
                                break;

                            case "e":
                                System.out.print("Enter Message Hash to delete: ");
                                String searchHash = scanner.nextLine().toUpperCase();
                                boolean removed = Message.storedMessagesList.removeIf(m -> m.getMessageHash().equals(searchHash));
                                if (removed) {
                                    System.out.println("Message successfully deleted.");
                                } else {
                                    System.out.println("Message hash not found.");
                                }
                                break;

                            case "f":
                                System.out.println("\n--- Full Stored Messages Report ---");
                                if (Message.storedMessagesList.isEmpty()) {
                                    System.out.println("No stored messages to display.");
                                } else {
                                    for (Message m : Message.storedMessagesList) {
                                        System.out.println(m.printMessages());
                                        System.out.println("--------------------------------");
                                    }
                                }
                                break;

                            case "g":
                                subRunning = false;
                                break;

                            default:
                                System.out.println("Invalid choice. Please pick from a to g.");
                        }
                    }
                    break;

                case 4:
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