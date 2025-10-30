import java.util.ArrayList;
import java.util.List;

public class FertilizerAdvisorySystem_24RP01069 {
    // Counters for the Summary Report
    private int balancedCount = 0;
    private int deficientCount = 0;
    private int excessCount = 0;

    /**
     * Processes a list of SoilAnalysis objects, prints detailed outputs,
     * and updates the summary counters.
     */
    public void processSamples(SoilAnalysis[] samples) {
        System.out.println("\n--- Sample Output ---");

        for (SoilAnalysis sample : samples) {
            String recommendation;
            String outputHeader = "Recommendation";

            // Output template header
            System.out.println("\nFarmer ID: " + sample.getFarmerId());
            System.out.println("District: " + sample.getDistrictName());
            System.out.println("Crop Type: " + sample.getCropType());

            try {
                // Attempt to calculate recommendation (may throw IllegalArgumentException)
                recommendation = sample.calculateFertilizerNeeded();

                // Update summary counters based on successful recommendation type
                if (recommendation.startsWith("OPTIMAL")) {
                    balancedCount++;
                } else if (recommendation.startsWith("DEFICIENT")) {
                    deficientCount++;
                } else if (recommendation.startsWith("EXCESS")) {
                    excessCount++;
                }
            } catch (IllegalArgumentException e) {
                // Catch the Invalid nutrient reading exception (for F004)
                outputHeader = "Error for Farmer ID " + sample.getFarmerId();
                recommendation = e.getMessage(); // "Invalid nutrient reading"
            }

            // Print the final result (Recommendation or Error)
            System.out.println(outputHeader + ": " + recommendation);
        }

        // Print Summary Report
        System.out.println("\n--- SUMMARY REPORT ---");
        System.out.println("Balanced Samples: " + balancedCount);
        System.out.println("Deficient Samples: " + deficientCount);
        System.out.println("Excess Samples: " + excessCount);
    }

    public static void main(String[] args) {
        System.out.println("--- Starting Fertilizer Advisory System ---");

        // Create at least five SoilAnalysis objects with diverse test data
        SoilAnalysis F001 = new SoilAnalysis("F001", "Kirehe", 50, 70, 80, "Maize");        // Balanced (OPTIMAL)
        SoilAnalysis F002 = new SoilAnalysis("F002", "Bugesera", 10, 45, 60, "Rice");      // N < 20 (DEFICIENT)
        SoilAnalysis F003 = new SoilAnalysis("F003", "Nyagatare", 110, 90, 85, "Beans");    // N > 100 (EXCESS)
        SoilAnalysis F004 = new SoilAnalysis("F004", "Gatsibo", -5, 40, 60, "Maize");      // N <= 0 (INVALID)
        SoilAnalysis F005 = new SoilAnalysis("F005", "Huye", 15, 15, 18, "Rice");          // N, P, K < 20 (DEFICIENT - N, P, K)

        SoilAnalysis[] samples = {F001, F002, F003, F004, F005};

        // Create and run the system
        FertilizerAdvisorySystem_24RP01069 system = new FertilizerAdvisorySystem_24RP01069();
        system.processSamples(samples);
    }
}