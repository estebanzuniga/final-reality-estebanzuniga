package controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PhaseTest extends GameControllerTest {

    @BeforeEach
    void setup() {
        super.basicSetUp();
    }

    @Test
    public void someMethodsTest() {
        checkSomeControllerMethods();
    }

    @Test
    public void someQueueMethods() {
        checkQueue(testEngineer, testAxe);
    }

    @Test
    public void InvalidTransitionExceptionTest() {
        checkPhasesTransitions();
    }

}
