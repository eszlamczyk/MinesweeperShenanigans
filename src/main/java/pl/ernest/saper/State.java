package pl.ernest.saper;

public enum State {
    mine,
    hidden,
    flag,
    empty,
    one,
    two,
    three,
    four,
    five,
    six,
    seven,
    eight;

    public static State intToState(int number){
        return switch (number){
            case 0 -> empty;
            case 1 -> one;
            case 2 -> two;
            case 3 -> three;
            case 4 -> four;
            case 5 -> five;
            case 6 -> six;
            case 7 -> seven;
            case 8 -> eight;
            default -> throw new IllegalStateException("Unexpected value: " + number);
        };
    }

    public int stateToInt(){
        return switch (this){
            case empty -> 0;
            case one -> 1;
            case two -> 2;
            case three -> 3;
            case four -> 4;
            case five -> 5;
            case six -> 6;
            case seven -> 7;
            case eight -> 8;
            default -> throw new IllegalStateException("Cannot parse this state to number of mines adjusted");
        };
    }
}
