import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class HandMaster {

    private int totalWinnings = 0;

    private ArrayList<Hand> fiveAKindList = new ArrayList<>();
    private ArrayList<Hand> fourAKindList = new ArrayList<>();
    private ArrayList<Hand> fullHouseList = new ArrayList<>();
    private ArrayList<Hand> threeAKindList = new ArrayList<>();
    private ArrayList<Hand> twoPairsList = new ArrayList<>();
    private ArrayList<Hand> onePairList = new ArrayList<>();
    private ArrayList<Hand> junkList = new ArrayList<>();

    public void addHand(String hand){
        Hand newHand = new Hand(hand);
        String handType = newHand.getType();
        if (Objects.equals(handType, "5AKind")){
            fiveAKindList.add(newHand);
        } else if (Objects.equals(handType, "4AKind")){
            fourAKindList.add(newHand);
        } else if (Objects.equals(handType, "FullHouse")){
            fullHouseList.add(newHand);
        } else if (Objects.equals(handType, "3AKind")){
            threeAKindList.add(newHand);
        } else if (Objects.equals(handType, "2Pairs")){
            twoPairsList.add(newHand);
        } else if (Objects.equals(handType, "1Pair")){
            onePairList.add(newHand);
        } else if (Objects.equals(handType, "Junk")){
            junkList.add(newHand);
        } else {System.out.println("Warning, unrecognised hand type : "+ handType);}
    }

    public void calculateWinnings() {
        int rank = 0;
        Collections.sort(junkList);
        rank = winningsFromList(junkList, rank);
        Collections.sort(onePairList);
        rank = winningsFromList(onePairList, rank);
        Collections.sort(twoPairsList);
        rank = winningsFromList(twoPairsList, rank);
        Collections.sort(threeAKindList);
        rank = winningsFromList(threeAKindList, rank);
        Collections.sort(fullHouseList);
        rank = winningsFromList(fullHouseList, rank);
        Collections.sort(fourAKindList);
        rank = winningsFromList(fourAKindList, rank);
        Collections.sort(fiveAKindList);
        winningsFromList(fiveAKindList, rank);
        System.out.println(totalWinnings);
    }

    private int winningsFromList(ArrayList<Hand> handsList, int rank) {
        for (Hand hand: handsList) {
            rank++;
            this.totalWinnings += rank * hand.getBid();
        }
        return rank;
    }

}
