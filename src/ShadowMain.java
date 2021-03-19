import java.io.FileNotFoundException;

public class ShadowMain {
    public static void main(String[] args) throws FileNotFoundException {
        Shadow shadow = new Shadow();

        shadow.osExplanation(); // starting prompt / start-up

        shadow.userInteraction(); //handles user's userInput

        shadow.clearOS(); // clears the os before exiting
    }
}
