package com.f1.f1results.raceresult;

public class PointsScoringSystem {

    public static double getPointsScoredByPosition(int position, boolean halfPoints) {
        double points;
        switch (position) {
            case 1:
                points = 25;
                break;
            case 2:
                points = 18;
                break;
            case 3:
                points = 15;
                break;
            case 4:
                points = 12;
                break;
            case 5:
                points = 10;
                break;
            case 6:
                points = 8;
                break;
            case 7:
                points = 6;
                break;
            case 8:
                points = 4;
                break;
            case 9:
                points = 2;
                break;
            case 10:
                points = 1;
                break;
            default:
                points = 0;
        }
        return halfPoints ? points / 2 : points;
    }

}
