package terepasztal.tests;

public class TrainCrashedException extends Exception {

    public TrainCrashedException() {
        super("Összetört a vonatod! :(");
    }
}
