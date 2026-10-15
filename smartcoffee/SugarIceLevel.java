package smartcoffee.smartcoffee;

/** Mức đường / đá của cà phê truyền thống. */
public enum SugarIceLevel {
    P0(0), P50(50), P100(100);

    private final int percent;
    SugarIceLevel(int percent) {
        this.percent = percent;
    }
    public int getPercent() {
        return percent;
    }
    @Override
    public String toString() {
        return percent + "%";
    }
}
