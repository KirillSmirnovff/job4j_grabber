package ru.job4j.design.lsp;

public class ExampleTwo {

    public class BuildingPriceCalculator {

        private double budget;

        public BuildingPriceCalculator(double budget) {
            this.budget = budget;
        }

        public double calculate(int materialCount, double eachMaterialPrice) {
            double price = materialCount * eachMaterialPrice;
            if (price > budget) {
                throw  new IllegalArgumentException("Not enough money to build this");
            }
            return price;
        }
    }

    public class HouseBuildingPriceCalculator extends  BuildingPriceCalculator {

        private double budget;

        public HouseBuildingPriceCalculator(double budget) {
            super(budget);
        }

        @Override
        public double calculate(int materialCount, double eachMaterialPrice) {
            double price = materialCount * eachMaterialPrice;
            return price;
        }
    }
}
