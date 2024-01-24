import java.util.Arrays;
import java.util.Collections;

public class Hand implements  Comparable<Hand>{

    private String hand;
    private int bid;
    private String type;
    // Each slot of this array stores the number of cards of position, ordered by power. EG if there are three 2s
    // (lowest power), then cardList[0] = 3. If there are two J (9th power), cardList[9] = 2...
    private Integer[] cardList = new Integer[12];
    private int numJokersList = 0;

    public Hand(String hand) {
        String[] inputSplit = hand.split(" ");
        this.hand = inputSplit[0];
        this.bid = Integer.parseInt(inputSplit[1]);
        parseCardList(this.hand);
        this.type = findType(this.hand);
    }

    private void parseCardList(String hand) {
        Arrays.fill(cardList,0);
        for (char card: hand.toCharArray()) {
            if (card == 'J'){
                numJokersList++;
            } else {
                int cardPower = cardStrength(card);
                // power list position = power -2
                cardList[cardPower - 2]++;
            }
        }
    }

    private String findType(String hand) {
        Arrays.sort(cardList, Collections.reverseOrder());
        if (cardList[0] + numJokersList == 5){
            return "5AKind";
        } else if (cardList[0] + numJokersList == 4){
            return "4AKind";
        } else if (cardList[0] + numJokersList == 3){
            if (cardList[1] == 2){
                return "FullHouse";
            } else {
                return "3AKind";
            }
        } else if (cardList[0] + numJokersList == 2){
            if (cardList[1] == 2){
                return "2Pairs";
            } else {
                return "1Pair";
            }
        } else {
            return "Junk";
        }
    }

    public String getHand() {
        return hand;
    }

    public int getBid() {
        return bid;
    }

    public String getType() {
        return type;
    }

    @Override
    public int compareTo(Hand otherHand) {
        return compareCardAt(0, otherHand);
    }

    private int compareCardAt(int i, Hand otherHand) {
        char myCard = hand.charAt(i);
        char otherCard = otherHand.getHand().charAt(i);
        if (myCard == otherCard){
            return compareCardAt(i+1, otherHand);
        }
        return cardStrength(myCard) - cardStrength(otherCard);
    }

    private int cardStrength(char myCard) {
        switch (myCard){
            case '2':
                return 2;
            case '3':
                return 3;
            case '4':
                return 4;
            case '5':
                return 5;
            case '6':
                return 6;
            case '7':
                return 7;
            case '8':
                return 8;
            case '9':
                return 9;
            case 'T':
                return 10;
            case 'J':
                return 1;
            case 'Q':
                return 11;
            case 'K':
                return 12;
            case 'A':
                return 13;
            default:
                System.out.println("Warning, unrecognised character : "+ myCard);
                return -1;
        }
    }
}
