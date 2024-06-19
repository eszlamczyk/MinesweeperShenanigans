package pl.ernest.saper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StateTest {
    @Test
    public void intToStateTest(){
        //given
        State[] states = {State.empty,State.one,State.two,State.three,State.four,State.five,
                                                    State.six,State.seven,State.eight};

        //

        for (int i = 0; i <= 8; i++){
            assertEquals(states[i], State.intToState(i));
        }
        assertThrows(RuntimeException.class, () -> State.intToState(-1));
        assertThrows(RuntimeException.class, () -> State.intToState(9));
        assertThrows(RuntimeException.class, () -> State.intToState(123515251));
    }
}
