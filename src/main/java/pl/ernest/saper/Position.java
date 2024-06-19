package pl.ernest.saper;

import java.util.ArrayList;
import java.util.List;

public record Position(int x, int y) {

    public List<Position> getNeighbours(int maxSize){
        List<Position> neighbours = new ArrayList<>();
        for (int i = Math.max(x-1,0); i < Math.min(x+1, maxSize); i++){
            for(int j = Math.max(y-1,0); j < Math.min(y+1, maxSize); j++){
                if (i != x || j != y){
                    neighbours.add(new Position(i,j));
                }
            }
        }
        return neighbours;
    }
}
