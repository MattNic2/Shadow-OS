import java.io.*;
import java.util.*;
/*
*  Feature 1 - User Input: userInput -> RAM;
*  Feature 2 - Load program: copy RAM -> CPU;
*  Extra Feature 1: encrypt CPU
*  Feature 3 - Refresh RAM: transfer CPU -> RAM
*  Extra Feature 2: decrypt RAM
*  Feature 4 - User Output: output RAM -> txt file ( userOutput )
*  Extra Feature 3: Clear all files
*/
public class Shadow {

    private ArrayList<String> RAM;
    private ArrayList<String> CPU;
    private int ENCRYPT_KEY = 2; // default 2 for testing

//    public static void main(String[] args) throws FileNotFoundException {
//        Shadow shadow = new Shadow();
//
//        shadow.osExplanation(); // starting prompt / start-up
//
//        shadow.userInteraction(); //handles user's userInput
//
//        shadow.clearOS(); // clears the os before exiting
//    }

    //constructor
    public Shadow() {
        RAM = new ArrayList<>();
        CPU = new ArrayList<>();
    }

    public void userInteraction() throws FileNotFoundException {
        Scanner scan = new Scanner(System.in);
        String userInput = "";

        while (!userInput.equals("EXIT")) {
            System.out.print("=>> ");
            userInput = scan.nextLine();
            switch (userInput) {
                case "LOAD" -> loadProgram();
                case "REFRESH" -> refresh();
                case "HELP" -> helpList();
                case "EMPTY" -> checkArray();
                case "INTRO" -> introSteps();
                case "PRINT_RAM" -> printRAM();
                case "PRINT_CPU" -> printCPU();
                default -> RAM.add(userInput);
            }
        }

        System.out.println("Goodbye!");
    }

    // prints RAM to a file
    public void printRAM() throws FileNotFoundException {
        PrintStream output = new PrintStream(new File("RAM.txt"));

        for (String str : RAM) {
            output.println(str);
        }
    }

    // prints CPU to a file
    public void printCPU() throws FileNotFoundException {
        PrintStream output = new PrintStream(new File("CPU.txt"));

        for (String str : CPU) {
            output.println(str);
        }
    }

    // checks array contents
    public void checkArray(){
        if(RAM.isEmpty()){
            System.out.println("There is nothing stored in the RAM. Write a secret already!!");
        } else {
            System.out.println("There is something in the RAM. If you wish to encrypt your secrets, please enter the command 'LOAD' and press ENTER.");
        }
    }

    // copies RAM to CPU
    public void loadProgram (){
        if (!CPU.isEmpty()) {
            CPU.clear();
        }

        for (String str : RAM) {
            String encrypted = changeString(str, ENCRYPT_KEY, true);
            CPU.add(encrypted);
        }
    }

    // copies CPU to RAM 
    public void refresh() {
        if (!RAM.isEmpty()) {
            RAM.clear();
        }

        for (String str : CPU) {
            String encrypted = changeString(str, ENCRYPT_KEY, false);
            RAM.add(encrypted);
        }
    }

    // encrypt and decrypt (features 1 & 2)
    // doEncrypt is true if encrypting, false if decrypting
    public String changeString(String str, int encryptionKey, boolean doEncrypt) {
        StringBuilder newString = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c;
            if (doEncrypt) {
                c = (char) (str.charAt(i) + encryptionKey);
            } else {
                c = (char) (str.charAt(i) - encryptionKey);
            }
            newString.append(c);
        }

        return newString.toString();
    }

    //feature 3: wipe file contents
    public void clearOS() throws FileNotFoundException {
        //wipe RAM and CPU ArrayLists
        RAM.clear();
        CPU.clear();

        //wipe RAM and CPU text files
        PrintStream ramFile = new PrintStream(new File("RAM.txt"));
        ramFile.println();
        PrintStream cpuFile = new PrintStream(new File("CPU.txt"));
        cpuFile.println();
    }

    public void osExplanation(){
        System.out.println("=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
        System.out.println("=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
        System.out.println("              ███████╗██╗  ██╗ █████╗ ██████╗  ██████╗ ██╗    ██╗ ██████╗ ███████╗");
        System.out.println("              ██╔════╝██║  ██║██╔══██╗██╔══██╗██╔═══██╗██║    ██║██╔═══██╗██╔════╝");
        System.out.println("              ███████╗███████║███████║██║  ██║██║   ██║██║ █╗ ██║██║   ██║███████╗");
        System.out.println("              ╚════██║██╔══██║██╔══██║██║  ██║██║   ██║██║███╗██║██║   ██║╚════██║");
        System.out.println("              ███████║██║  ██║██║  ██║██████╔╝╚██████╔╝╚███╔███╔╝╚██████╔╝███████║");
        System.out.println("              ╚══════╝╚═╝  ╚═╝╚═╝  ╚═╝╚═════╝  ╚═════╝  ╚══╝╚══╝  ╚═════╝ ╚══════╝");
        System.out.println("=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
        System.out.println("=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
        System.out.println("shadowOS is an encryption operating system focused on keeping your information safe.");
        System.out.println("Enter HELP to see list of commands.");
        System.out.println("Enter INTRO to show order of commands.");
        System.out.println("Enter an integer to use for encrypting your information.");
        System.out.print("=>> ");
        setKey();
        System.out.println("Now please start typing in your darkest secrets:");
    }

    private void setKey() {
        Scanner scan = new Scanner (System.in);
        ENCRYPT_KEY = scan.nextInt();
    }

    public void introSteps(){
        System.out.println("1. Enter your secrets/info into the console.");
        System.out.println("2. Enter 'LOAD' to save current info to CPU to be encrypted.");
        System.out.println("3. When you are finished writing, enter 'REFRESH' to copy CPU encrypted info to RAM.");
        System.out.println("4. If you would like to see the contents of RAM or CPU," +
                "enter 'PRINT_RAM' or 'PRINT_CPU' to print the contents into a file.");
        System.out.println("5. Enter 'EXIT' to clear all info from OS and files.");
    }

    public void helpList(){
        System.out.println("Commands:");
        System.out.println("\"LOAD\": copies the RAM into CPU and encrypts the data.");
        System.out.println("\"REFRESH\": Transfers encrypted data back to RAM");
        System.out.println("\"EMPTY\": checks to see if the RAM is empty or not");
        System.out.println("\"PRINT_RAM\": print the contents of RAM to RAM.txt");
        System.out.println("\"PRINT_CPU\": print the contents of CPU to CPU.txt");
        System.out.println("\"EXIT\": Sends all data entered (encrypted & decrypted) to txt file and exits OS.");
    }

    // for testing
    public List<String> getRAM() {
        return RAM;
    }

    public List<String> getCPU() {
        return CPU;
    }

    public int getKey() {
        return ENCRYPT_KEY;
    }
}