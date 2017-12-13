package terepasztal;

import terepasztal.annotations.Implemented;
import terepasztal.annotations.TodoStateMachine;


/** Egy jelzőlámpát (szemafort) reprezentáló osztály.
 * Lehetséges állapotai a SemaphoreState felsorolt típusban vannak definiálva.
 * Változtatható és lekérdezhető az állapota.
 * */
public class Semaphore {

    /** A szemafor aktuális állapota. */
    private SemaphoreState state = SemaphoreState.CLEAR;

    /** Szabad jelzést ad-e a szemafor? */
    public boolean isClear() {
        return state == SemaphoreState.CLEAR;
    }

    /** Jelzőlámpa állapotának változtatása egyikből a másikba. */
    @TodoStateMachine
    @Implemented
    public void toggle() {
        int idx = state.ordinal();
        SemaphoreState[] values = SemaphoreState.values();
        int enumLength = values.length;
        idx++;
        if (idx >= enumLength)
            idx = 0;
        state = values[idx];
    }

}