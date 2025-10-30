public class SoilAnalysis {
    private String farmerId;
    private String districtName;
    private double nitrogenLevel;
    private double phosphorusLevel;
    private double potassiumLevel;
    private String cropType;

    // Constructor to initialize all six fields
    public SoilAnalysis(String farmerId, String districtName, double nitrogenLevel,
                        double phosphorusLevel, double potassiumLevel, String cropType) {
        this.farmerId = farmerId;
        this.districtName = districtName;
        this.nitrogenLevel = nitrogenLevel;
        this.phosphorusLevel = phosphorusLevel;
        this.potassiumLevel = potassiumLevel;
        this.cropType = cropType;
    }

    // Getters
    public String getFarmerId() { return farmerId; }
    public String getDistrictName() { return districtName; }
    public double getNitrogenLevel() { return nitrogenLevel; }
    public double getPhosphorusLevel() { return phosphorusLevel; }
    public double getPotassiumLevel() { return potassiumLevel; }
    public String getCropType() { return cropType; }

    /**
     * Checks if all three nutrient levels (N, P, K) are between 20 and 100 ppm inclusive.
     * @return true if balanced, otherwise false.
     */
    public boolean isBalanced() {
        return (nitrogenLevel >= 20 && nitrogenLevel <= 100) &&
                (phosphorusLevel >= 20 && phosphorusLevel <= 100) &&
                (potassiumLevel >= 20 && potassiumLevel <= 100);
    }

    // Helper method to enforce the mandatory exception check
    private void checkInvalidNutrients() {
        if (nitrogenLevel <= 0 || phosphorusLevel <= 0 || potassiumLevel <= 0) {
            throw new IllegalArgumentException("Invalid nutrient reading");
        }
    }

    /**
     * Calculates and returns the fertilizer recommendation based on nutrient values.
     * Throws IllegalArgumentException if any nutrient is 0 or negative.
     * @return The fertilizer recommendation string.
     */
    public String calculateFertilizerNeeded() {
        // 1. Check for invalid data first (must throw exception)
        checkInvalidNutrients();

        // 2. Optimal Check
        if (isBalanced()) {
            return "OPTIMAL - Maintenance fertilizer only";
        }

        // StringBuilders to collect nutrients falling into deficient or excess categories
        StringBuilder deficientNutrients = new StringBuilder();
        StringBuilder excessNutrients = new StringBuilder();

        // 3. Deficient Check (below 20 ppm)
        if (nitrogenLevel < 20) deficientNutrients.append("Nitrogen, ");
        if (phosphorusLevel < 20) deficientNutrients.append("Phosphorus, ");
        if (potassiumLevel < 20) deficientNutrients.append("Potassium, ");

        if (deficientNutrients.length() > 0) {
            // Remove the trailing ", "
            String names = deficientNutrients.substring(0, deficientNutrients.length() - 2);
            return "DEFICIENT - High application needed for " + names;
        }

        // 4. Excess Check (above 100 ppm)
        if (nitrogenLevel > 100) excessNutrients.append("Nitrogen, ");
        if (phosphorusLevel > 100) excessNutrients.append("Phosphorus, ");
        if (potassiumLevel > 100) excessNutrients.append("Potassium, ");

        if (excessNutrients.length() > 0) {
            // Remove the trailing ", "
            String names = excessNutrients.substring(0, excessNutrients.length() - 2);
            return "EXCESS - Reduce " + names + " application";
        }

        // Should not be reached if the logic covers all states (deficient, optimal, excess)
        return "UNKNOWN RECOMMENDATION";
    }
}