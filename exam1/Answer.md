# 1) Equivalence Partitioning (EP)

## Equivalence Classes

| Partition ID | Condition | Expected Result |
|--------------|-----------|----------------|
| EP1 | kWh ≤ 0 | IllegalArgumentException |
| EP2 | 0 < kWh ≤ 500 | 0.0 |
| EP3 | 500 < kWh ≤ 1500 AND none | 0.0 |
| EP4 | 500 < kWh ≤ 1500 AND one true | 0.10 |
| EP5 | 500 < kWh ≤ 1500 AND both true | 0.15 |
| EP6 | kWh > 1500 AND none/one | 0.05 |
| EP7 | kWh > 1500 AND both true | 0.20 |

---

## EP Test Cases

| Test ID | kWh | Smart | Opt-Out | Expected |
|----------|------|--------|----------|----------|
| TC_EP1 | -10 | F | F | Exception |
| TC_EP2 | 300 | F | F | 0.0 |
| TC_EP3 | 1000 | F | F | 0.0 |
| TC_EP4 | 1000 | T | F | 0.10 |
| TC_EP5 | 1000 | T | T | 0.15 |
| TC_EP6 | 2000 | F | T | 0.05 |
| TC_EP7 | 2000 | T | T | 0.20 |

---

# 2) Boundary Value Analysis (BVA)

## Boundaries
- 0
- 500
- 1500

## BVA Test Cases

| Test ID | kWh | Smart | Opt-Out | Expected |
|----------|------|--------|----------|----------|
| BVA1 | 0 | F | F | Exception |
| BVA2 | 1 | F | F | 0.0 |
| BVA3 | 500 | F | F | 0.0 |
| BVA4 | 501 | T | F | 0.10 |
| BVA5 | 1500 | T | T | 0.15 |
| BVA6 | 1501 | F | F | 0.05 |

---

# 3) Decision Table

## Decision Table

| Rule | Tier | Smart | Opt | Rebate |
|------|------|--------|------|--------|
| R1 | 1 | X | X | 0% |
| R2 | 2 | F | F | 0% |
| R3 | 2 | T | F | 10% |
| R4 | 2 | F | T | 10% |
| R5 | 2 | T | T | 15% |
| R6 | 3 | F | F | 5% |
| R7 | 3 | T | F | 5% |
| R8 | 3 | F | T | 5% |
| R9 | 3 | T | T | 20% |

---



#  Branch Coverage

Branches to test both true and false outcomes:

1. kwh <= 0
2. kwh > 500 && kwh <= 1500
3. hasSmartDevice && peakOptOut
4. hasSmartDevice || peakOptOut
5. kwh > 1500
6. Tier3 inner condition

Decision table tests achieve full branch coverage.

---

# Fault Injection Analysis

if (kwh <= 0) -> if (kwh < 0)

---

if (kwh > 500 && kwh <= 1500) -> if (kwh >= 500 && kwh <= 1500)


---
I Changed:
    else if (hasSmartDevice || peakOptOut) -> else if (hasSmartDevice)


---
Removed:
    rebatePercent = 0.05;

since iif tests don't execute that statement, coverage tool flags it.

---

if (hasSmartDevice && peakOptOut) -> if (hasSmartDevice || peakOptOut)

Statements still execute.
Code coverage = 100%.

---

# code

```java
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class EnergyCalculatorFullTest {

    EnergyCalculator calc = new EnergyCalculator();


    @Test
    void testInvalidUsage() {
        assertThrows(IllegalArgumentException.class,
            () -> calc.calculateRebate(-10, false, false));
    }

    @Test
    void testTier1() {
        assertEquals(0.0, calc.calculateRebate(300, false, false));
    }

    @Test
    void testTier2None() {
        assertEquals(0.0, calc.calculateRebate(1000, false, false));
    }

    @Test
    void testTier2OneSmart() {
        assertEquals(0.10, calc.calculateRebate(1000, true, false));
    }

    @Test
    void testTier2OneOpt() {
        assertEquals(0.10, calc.calculateRebate(1000, false, true));
    }

    @Test
    void testTier2Both() {
        assertEquals(0.15, calc.calculateRebate(1000, true, true));
    }

    @Test
    void testTier3None() {
        assertEquals(0.05, calc.calculateRebate(2000, false, false));
    }

    @Test
    void testTier3Both() {
        assertEquals(0.20, calc.calculateRebate(2000, true, true));
    }

    // bva tests

    @Test
    void testZeroBoundary() {
        assertThrows(IllegalArgumentException.class,
            () -> calc.calculateRebate(0, false, false));
    }

    @Test
    void testBoundary500() {
        assertEquals(0.0, calc.calculateRebate(500, false, false));
    }

    @Test
    void testBoundary501() {
        assertEquals(0.10, calc.calculateRebate(501, true, false));
    }

    @Test
    void testBoundary1500() {
        assertEquals(0.15, calc.calculateRebate(1500, true, true));
    }

    @Test
    void testBoundary1501() {
        assertEquals(0.05, calc.calculateRebate(1501, false, false));
    }
}
```
