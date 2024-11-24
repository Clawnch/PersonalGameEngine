package GameTesting.BasicGui;

public class CoordinateDegrees {

    private int hourNS, minuteNS, secondNS;
    private int hourEW, minuteEW, secondEW;

    private final double radianToDegreeRatio = 57.2958;

    public CoordinateDegrees(int hourNS, int minuteN, int secondN, int hourEW, int minuteEW, int secondEW) {
        this.hourNS = hourNS;
        this.minuteNS = minuteN;
        this.secondNS = secondN;
        this.hourEW = hourEW;
        this.minuteEW = minuteEW;
        this.secondEW = secondEW;
    }

    public DecimalPair calculateCoordinates(int distanceFeet, double direction) {
        DecimalPair initialDegree = convertHourMinuteSecondToDecimalPair();
        double milesDistance = (double)distanceFeet / 5280d;
        double statuteMiles = milesDistance / 69d;

        double distanceEW = Math.cos(direction / radianToDegreeRatio) * statuteMiles;
        double distanceNS = Math.sin(direction / radianToDegreeRatio) * statuteMiles;

//        System.out.println(String.format("""
//                MilesDistance: %s
//                StatuteMiles: %s
//                DistanceEW: %s
//                DistanceNS = %s
//                """, milesDistance, statuteMiles, distanceEW, distanceNS));
        initialDegree.setX(initialDegree.getX() + distanceNS);
        initialDegree.setY(initialDegree.getY() + distanceEW);

        setDMSFromDecimalPair(initialDegree);
        return initialDegree;
    }

    private DecimalPair convertHourMinuteSecondToDecimalPair() {
        double decimalDegreeNS = hourNS;
        decimalDegreeNS = (decimalDegreeNS + ((double) minuteNS / 60));
        decimalDegreeNS = (decimalDegreeNS + ((double) secondNS / (60 * 60)));
        double decimalDegreeEW = hourEW;
        decimalDegreeEW = (decimalDegreeEW + ((double)minuteEW / 60));
        decimalDegreeEW = (decimalDegreeEW + ((double)secondEW / (60 * 60)));



        return new DecimalPair(decimalDegreeNS, decimalDegreeEW);
    }

    private void setDMSFromDecimalPair(DecimalPair decimalDegrees) {
        double decimalX = decimalDegrees.getX();
        hourNS = (int)decimalX;
        decimalX = (decimalX - hourNS) * 60;
        minuteNS = (int)decimalX;
        decimalX = (decimalX - minuteNS) * 60;
        secondNS = (int)decimalX;

        double decimalY = decimalDegrees.getY();
        hourEW = (int)decimalY;
        decimalY = (decimalY - hourEW) * 60;
        minuteEW = (int)decimalY;
        decimalY = (decimalY - minuteEW) * 60;
        secondEW = (int)decimalY;
    }

    public int getHourNS() {
        return hourNS;
    }

    public int getMinuteNS() {
        return minuteNS;
    }

    public int getSecondNS() {
        return secondNS;
    }

    public int getHourEW() {
        return hourEW;
    }

    public int getMinuteEW() {
        return minuteEW;
    }

    public int getSecondEW() {
        return secondEW;
    }
}
