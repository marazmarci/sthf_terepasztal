package terepasztal;

import terepasztal.annotations.New;
import terepasztal.annotations.UmlTodo;

/** A szerelvények létrehozására való MasterStation osztályban lévő állapotgép állapotait tartalmazó felsorolt típus. */
@UmlTodo @New
public enum MasterStationState {
    /** Várakozás, hogy megkezdődjön a következő vonat elkészítése. */
    WAITING,
    /** 1 db mozdony készítése. */
    PRODUCING_LOCOMOTIVE,
    /** Adott számú (akár 0) vagon hozzáillesztése sorban a mozdonyhoz. */
    PRODUCING_TRAIN
}
