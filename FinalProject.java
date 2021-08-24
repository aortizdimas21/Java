/*
Alison Ortiz Dimas
May 19th, 2021
Final Project: Inspired by my sister who has a large book collection (600+) and is constantly forgetting whether she already
owns a book so she ends up buying the same book twice (it happens quite often). User must submit a txt file called
book.txt with the information of the books in "title by author" format. For this purpose, books.txt is a sample book list.
Program asks user whether they want to add a book, check if they own it, or just view their current collection. Adding
a book writes the name into the book.txt file that they submitted. Would like to expand to maybe using barcode scanning
to find book information to make it easier for the user to input books information/check if they already own it within
a simple mobile app.
 */

public class FinalProject {
    public static void main(String[] args) throws java.io.IOException {

        introduction();
        takeRequest();
    }

    static void introduction() {

        System.out.println("* * * * * * * * * * * * * * * * * * * * * * * *");
        System.out.println("Welcome to the Personal Library Management System");
        System.out.println("* * * * * * * * * * * * * * * * * * * * * * * *");

        System.out.println("\nWhat is your name?");

        java.util.Scanner scan1 = new java.util.Scanner(System.in);
        String name = scan1.nextLine();

        System.out.println("\nHi " + name + "! Thank you for the " +
                "text file named books.txt with your book information!");
    }

    static int countLines(java.util.Scanner textFile) {
        int count = 0;
        while (textFile.hasNextLine()) {
            textFile.nextLine();
            count++;
        }
        return (int) count;
    }


    static void checkIfOwn() throws java.io.IOException{
        java.util.Scanner scan9 = new java.util.Scanner(new java.io.FileReader("books.txt"));

        java.util.Scanner forCheck = new java.util.Scanner(new java.io.FileReader("books.txt")); //scan needs to be reset

        int count = countLines(scan9); //count lines in txt file

        System.out.println("\nSure! Let's make sure you don't already own this." +
                " Please provide the name of the book for me. Careful, this input is case-sensitive.");
        java.util.Scanner scan4 = new java.util.Scanner(System.in);
        String checkBook = scan4.nextLine();
        String[] bookNames = new String[count];
        String[] bookAuthor = new String[count];

        for (int i = 0; i < count; i++) {
            String rowInTxt = forCheck.nextLine();
            String[] book = rowInTxt.split(" by ");
            String title = book[0];
            String author = book[1];
            bookNames[i] = title;
            bookAuthor[i] = author;
        }

        java.util.List<String> bookNameList = new java.util.ArrayList<>(java.util.Arrays.asList(bookNames));

        if (bookNameList.contains(checkBook)) {
            System.out.println("\nYou already own this! Choose another book.");
        }
        else{
            System.out.println("\nThis book is not in your library! Go ahead and buy it!");
            System.out.println("Would you like to add it to your library now? Type yes or no");
            java.util.Scanner scan11 = new java.util.Scanner(System.in);
            String response = scan11.nextLine();

            switch (response) {
                case "yes ":
                case "yes":
                    addingBook();
                    break;

            }
        }

    }
    static void viewAll() throws java.io.IOException {

        java.util.Scanner scan = new java.util.Scanner(new java.io.FileReader("books.txt"));
        java.util.Scanner forView = new java.util.Scanner(new java.io.FileReader("books.txt")); //scan needs to be reset

        int count = countLines(scan); //count lines in txt file

        System.out.println("\nHere is your current collection: ");
        for (int i = 0; i < count; i++) {
            String rowInTxt = forView.nextLine();
            System.out.println(rowInTxt);
        }
    }


    static void addingBook() {
        System.out.println("\nGreat! I'll be glad to help you with that." +
                " What is the name of the book you are adding to the library today?");
        java.util.Scanner nameOfBook = new java.util.Scanner(System.in);
        String title = nameOfBook.nextLine();
        System.out.println("\nWho is the author of the book?");
        java.util.Scanner nameOfAuthor = new java.util.Scanner(System.in);
        String author = nameOfAuthor.nextLine();

        //adding new information to existing file
        java.io.PrintWriter out = null;
        try {
            out = new java.io.PrintWriter(new java.io.BufferedWriter(new java.io.FileWriter("books.txt", true)));
            out.println(title + " by " + author);
        } catch (java.io.IOException e) {
            System.err.println(e);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    static void takeRequest() throws java.io.IOException{
        System.out.println("\nWhat do you need help with today? Type add to add a book to your existing library " +
                "\nand check to check if you already own the book. Alternatively, " +
                "you could type view \nand I can show you every book you already own.");
        java.util.Scanner scan2 = new java.util.Scanner(System.in);
        String request = scan2.nextLine();
        switch (request) {
            case "add":
            case "add ":
                addingBook();
                ask();
                break;

            case "check":
            case "check ":
                checkIfOwn();
                ask();
                break;
            case "view":
            case "view ":
                viewAll();
                ask();
                break;

            default:
                System.out.println("I'm sorry. Something isn't right. Check to make sure you typed the word correctly. " +
                        "Reload and try again");
        }
    }
    static void ask() throws java.io.IOException{
        System.out.println("\nWould you like help with something else? Type yes or no");
        java.util.Scanner scan6 = new java.util.Scanner(System.in);
        String answer = scan6.nextLine();

        if (answer.equals("yes") || answer.equals("yes ")) {
            takeRequest();
        }
        else {
            System.out.println("\nOkay! If you need anything else, just load me again!");
        }
    }


}

