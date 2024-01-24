import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static void main(String[] args)
    {
        HandMaster handMaster = new HandMaster();
        try {
//            File myObj = new File("src/main/resources/test.txt");
            File myObj = new File("src/main/resources/input.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String wholeData = myReader.nextLine();
                handMaster.addHand(wholeData);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        handMaster.calculateWinnings();
    }
}


