# Design and Create Tests for the Following Scenario

# Tester and TestBreaker

I will provide a set of rules—similar to the problems we've been covering in class and in the labs.

For the rules, you will need to develop test cases using:

- Equivalence Partitions  
- Boundary Value Analysis  
- Decision Tables  
- Code Coverage  
- Branch Coverage  

After creating the tests, you will create faults for the code:

- Fault 1: Caught by Equivalence Partitions (Ch. 2)  
- Fault 2: Missed by EQs, but caught by BVA (Ch. 3)  
- Fault 3: Missed by BVA, but caught by Decision Table (Ch. 4)  
- Fault 4: Missed by Decision Tables, but caught by Code Coverage (Ch. 5)  
- Fault 5: Missed by Code Coverage, but caught by Branch Coverage (Ch. 6)  

The point of this exercise is to ensure that you know not just how to create the tests, but why each type of test design method might be useful compared to the others.

## The Scenario: The "Smart-Home Energy Rebate"

A utility company offers a rebate on electricity bills based on three factors:

1. **Usage Tier**: Measured in kWh (Kilowatt-hours).
2. **Smart Device Integration**: Whether the user has a "Smart Thermostat" connected.
3. **Peak Hours Opt-Out**: Whether the user agreed to reduce power during high-demand events.

---

## The Business Rules

- **Tier 1 (0 < kWh ≤ 500)**  
  No rebate regardless of other factors (base rate).

- **Tier 2 (500 < kWh ≤ 1500)**  
  - 10% rebate if they have a Smart Thermostat **OR** Opt-Out.  
  - 15% rebate if they have **BOTH**.

- **Tier 3 (kWh > 1500)**  
  - 20% rebate if they have a Smart Thermostat **AND** Opt-Out.  
  - Otherwise, 5% "consolation" rebate just for being a high-volume user.

- **Invalid Input**  
  Any kWh ≤ 0 should throw an `IllegalArgumentException`.

---

## Provided Java Program for Testing

```java
public class EnergyCalculator {

    public double calculateRebate(double kwh, boolean hasSmartDevice, boolean peakOptOut) {

        if (kwh <= 0) {
            throw new IllegalArgumentException("Usage must be positive.");
        }

        double rebatePercent = 0.0;

        if (kwh > 500 && kwh <= 1500) {
            if (hasSmartDevice && peakOptOut) {
                rebatePercent = 0.15;
            } else if (hasSmartDevice || peakOptOut) {
                rebatePercent = 0.10;
            }

        } else if (kwh > 1500) {
            if (hasSmartDevice && peakOptOut) {
                rebatePercent = 0.20;
            } else {
                rebatePercent = 0.05;
            }
        }

        return rebatePercent;
    }
}
```
