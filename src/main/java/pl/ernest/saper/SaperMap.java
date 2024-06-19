package pl.ernest.saper;

import java.util.*;

public class SaperMap {

    private final int size;

    private final int mineAmount;

    private final Map<Position, State> mines;

    private final Map<Position, State> visibleMap;


    public SaperMap(int size, int mineAmount) {
        if(size*size < mineAmount)
            throw new RuntimeException("Cannot fill all mines into the map!");
        this.size = size;
        this.mineAmount = mineAmount;
        this.mines = new HashMap<>();
        this.visibleMap = new HashMap<>();
    }

    public Map<Position, State> getVisibleMap() {
        return visibleMap;
    }

    //action after any unknown button was pressed
    public State registerNewPress(Position pressedPosition){
        revealMap(pressedPosition);
        return mines.get(pressedPosition);
    }

    //action after button that was already known was pressed
    public State checkSafeClear(Position pressedPosition){
        //count everything
        int expectedMines = visibleMap.get(pressedPosition).stateToInt();
        int correctFlags = 0;
        int totalFlags = 0;
        for (Position position : pressedPosition.getNeighbours(this.size)){
            if(State.flag.equals(visibleMap.get(position))){
                totalFlags += 1;
                if(State.mine.equals(mines.get(position))){
                    correctFlags += 1;
                }
            }
        }
        //if everything is right -> reveal empty spaces
        if (correctFlags == expectedMines && totalFlags == expectedMines)
            return State.flag;
        //if there are enough flags but the flags are placed incorrect -> fail
        if (correctFlags != expectedMines && totalFlags == expectedMines)
            return State.mine;
        //if not enough or too many mines are flagged -> do nothing
        return State.empty;
    }

    //map generation after first press

    public void generateMap(Position pressedPosition){
        List<Position> potentialMines = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                potentialMines.add(new Position(i,j));
            }
        }
        Collections.shuffle(potentialMines);
        Iterator<Position> positionIterator = potentialMines.iterator();
        int placedMines = 0;
        while(placedMines < mineAmount){
            Position currMine = positionIterator.next();
            if (currMine.equals(pressedPosition)) continue;
            mines.put(currMine,State.mine);
            placedMines++;
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Position currPos = new Position(i,j);
                mines.putIfAbsent(currPos,calculatePositionState(currPos));
            }
        }
        revealMap(pressedPosition);
    }

    private State calculatePositionState(Position currPosition){
        int neighbourMines = 0;
        List<Position> neighbours = currPosition.getNeighbours(this.size);
        for (Position position : neighbours) {
            if (State.mine.equals(mines.get(position))) {
                neighbourMines++;
            }
        }
        return State.intToState(neighbourMines);
    }

    private void revealMap(Position position){
        //todo: inform front to update
        visibleMap.put(position,mines.get(position));
        //open others if empty
        if (mines.get(position).equals(State.empty)){
            for(Position nextPosition : position.getNeighbours(this.size)){
                revealMap(nextPosition);
            }
        }
    }





}
