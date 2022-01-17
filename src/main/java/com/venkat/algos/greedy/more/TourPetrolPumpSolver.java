package com.venkat.algos.greedy.more;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TourPetrolPumpSolver {
    
    protected static final class TourPoint {
        int gasQuantity;
        int nextPumpDist;
        
        public TourPoint(int gasQty, int nextDist) {
            this.gasQuantity = gasQty;
            this.nextPumpDist = nextDist;
        }
        
        public int getGasQuantity() {
            return gasQuantity;
        }
        
        public int getNextPumpDist() {
            return nextPumpDist;
        }
        
        public String toString() {
            return String.format("{%d, %d}", gasQuantity, nextPumpDist);
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
                                      .mapToInt(point -> point.getGasQuantity() - point.getNextPumpDist())
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
            System.out.format("Tour not possible for given list %s!%n", tourList);
            return;
        }
        List<TourPoint> tourPrintList = new ArrayList<>(tourList);
        Collections.rotate(tourPrintList, -1 * startPoint);
        System.out.format("----%nList: %s%nTour start point: %d%nTour route: ", tourList, startPoint);
        int petrolRemaining = 0;
        for (TourPoint p : tourPrintList) {
            petrolRemaining += (p.gasQuantity - p.nextPumpDist);
            System.out.format("%s--[gasRem=%d]-->", p, petrolRemaining);
        }
        System.out.println("\n----");
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
        
        points = Arrays.asList(new TourPoint[] {
                new TourPoint(2, 3),
                new TourPoint(3, 4),
                new TourPoint(4, 3)
        });
        
        solver.solve(points);
    }

}
