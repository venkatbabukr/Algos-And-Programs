package com.venkat.algos.greedy;

import java.util.Arrays;
import java.util.List;

public class TourPetrolPumpSolverOptimized extends TourPetrolPumpSolver {

    @Override
    public int getTourStart(List<TourPoint> input) {
        int totalPoints = input.size();
        for (int tourStart = 0; tourStart < totalPoints; /* Donot increment tourStart, as we get the updated tourStart from inside the loop */) {
            TourPoint p = input.get(tourStart);
            int tourLength, remGas, tourEnd;
            for (tourEnd = (tourStart + 1) % totalPoints, tourLength = 1, remGas = p.getGasQuantity() - p.getNextPumpDist();
                     remGas > 0 && tourLength < totalPoints; tourLength++, tourEnd = (tourEnd + 1) % totalPoints) {
                p = input.get(tourEnd);
                remGas += p.getGasQuantity() - p.getNextPumpDist();
            }
            if (tourLength == totalPoints && remGas >= 0) // We've found the complete tour from the tourStart point!
                return tourStart;
            else if (tourEnd < tourStart) // Happens when we are finding complete tour from tourStart and circled around
                                          // the array in process. We can safely break the loop here as there is no need
                                          // to search rest of the array for tourSart, since we couldn't  complete the tour
                                          // from this point onwards.
                return -1;
            else tourStart = tourEnd;
        }
        return -1;
    }

    public static void main(String[] args) {
        TourPetrolPumpSolverOptimized solver = new TourPetrolPumpSolverOptimized();
        
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
