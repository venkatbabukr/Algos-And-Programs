package com.venkat.algos.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TourPetrolPumpSolver {
    
    private static final class TourPoint {
        int pumpPetrolQty;
        int nextPumpDist;
        
        public TourPoint(int petrolQty, int nextDist) {
            this.pumpPetrolQty = petrolQty;
            this.nextPumpDist = nextDist;
        }
        
        public int getPumpPetrolQty() {
            return pumpPetrolQty;
        }
        
        public int getNextPumpDist() {
            return nextPumpDist;
        }
        
        public String toString() {
            return String.format("{%d, %d}", pumpPetrolQty, nextPumpDist);
        }
    }
    
    private int getTourEndPoint(int[] consumptionMatrix, int startPoint) {
        int petrolBalance = consumptionMatrix[startPoint];
        int cover = 1;
        int endPoint = (startPoint + 1) % consumptionMatrix.length;
        while (petrolBalance > 0 && cover < consumptionMatrix.length) {
            petrolBalance += consumptionMatrix[endPoint];
            endPoint = (endPoint + 1) % consumptionMatrix.length;
            cover++;
        }
        return cover > 1 ? endPoint : -1;
    }

    public int getTourStart(List<TourPoint> input) {
        int[] consumptionMatrix = input.stream()
                                      .mapToInt(point -> point.getPumpPetrolQty() - point.getNextPumpDist())
                                      .toArray();
        int tourStartPoint = 0;
        int tourEndPoint = -1;

        // This extra step is required for all cases when the tour starts with points where
        // consumption is < 0 (i.e. petrolPumpQty < nextPumpDist
        for (tourStartPoint = 0 ; tourStartPoint < consumptionMatrix.length && consumptionMatrix[tourStartPoint] < 0 ; tourStartPoint++);

        while (tourStartPoint < consumptionMatrix.length) {
            tourEndPoint = getTourEndPoint(consumptionMatrix, tourStartPoint);
            if (tourEndPoint <= tourStartPoint) {
                break;
            } else {
            	// This is the pruning condition... Instead of starting to search from tourStartPoint + 1,
            	// we can start search again tourEndPoint, coz, for points in b/w any way tour is not possible...
                tourStartPoint = tourEndPoint;
            }
        }
        if (tourStartPoint == tourEndPoint) {
            return tourStartPoint;
        }
        return -1;
    }
    
    public void printTour(int startPoint, List<TourPoint> tourList) {
        if (startPoint < 0) {
            System.out.format("Tour not possible for given tourList %s\n!", tourList);
            return;
        }
        List<TourPoint> tourPrintList = new ArrayList<>(tourList);
        Collections.rotate(tourPrintList, -1 * startPoint);
        System.out.println("Tour start point: " + startPoint);
        int petrolRemaining = 0;
        for (TourPoint p : tourPrintList) {
            petrolRemaining += (p.pumpPetrolQty - p.nextPumpDist);
            System.out.format("%s--[petrolRem=%d]-->", p, petrolRemaining);
        }
    }
    
    public void solve(List<TourPoint> points) {
        int tourStartPoint = getTourStart(points);
        printTour(tourStartPoint, points);
    }

    public static void main(String[] args) {
        TourPetrolPumpSolver solver = new TourPetrolPumpSolver();
        
        List<TourPoint> points = Arrays.asList(new TourPoint[] {
                new TourPoint(4, 6),
                new TourPoint(6, 5),
                new TourPoint(7, 3),
                new TourPoint(4, 5)
        });
        
        solver.solve(points);
    }

}
