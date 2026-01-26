### Part One: Triangle Classifier

Outputs: equilateral, isoceles, scalene, or invalid
Inputs: `int a, b, c`

Analysis

- What makes a tringle equilateral?
  - All sides are equal
- isoceles?
  - triangle that has two sides of equal length and two angles of equal measure
- scalene?
  - All three sides are different lenghts



Partition 1 -> `a=b=c` (all three equal) -> Equilateral
Partition 2 -> (exactly two equal) -> Isoceles
Partition 3 -> (no sides equal) -> Scalene
Partition 4 -> (any side <=0) -> Error
Partition 5 -> (sum of two sides are not greater than the third side) -> Error


|Test case|parameter|Equivalance Partition|
|---|---|---|
|1|a=60, b=60, c=60| all three equal|
|2|a=60, b=60, c=90| exactly two equal|
|3|a=40, b=60, c=90| no sides equal|
|4|a=0, b=60, c=90| any side <=0|
|5|a=6, b=3, c=10| sum of two sides are not greater than the third side|


### Part Two: Date Validator

Input: `int month, day, year`
Output: `boolean` valid/invalid combination

Analysis

- 31-day months: Jan, Mar, May, July, Aug, Oct, Dec.
- 30-day months: Apr, June, Sept, Nov.
- February: The "special case" that depends on the year.
- Invaid months: `month < 1 || month > 12`



| Partition | Type    | Description                                                                 |
|-----------|---------|-----------------------------------------------------------------------------|
| P1        | Valid   | Month in {1, 3, 5, 7, 8, 10, 12}, Day 1–31                                    |
| P2        | Valid   | Month in {4, 6, 9, 11}, Day 1–30                                              |
| P3        | Valid   | Month 2, isLeapYear = True, Day 1–29                                         |
| P4        | Valid   | Month 2, isLeapYear = False, Day 1–28                                        |
| P5        | Invalid | Month < 1 or > 12                                                           |
| P6        | Invalid | Day < 1 or Day > Max for that specific month                                |
| P7        | Invalid | Year < 1 (Assuming 0 or negative years are invalid)       |


| Test Case | Month | Day | Year | expected_output | Partition |
|---| ---|---|---|---|---|
| 1 | 1 | 31 | 2005 | valid | P1|
| 2 | 4 | 21 | 2005 | valid | P2|
| 3 | 2 | 29 | 2024 | valid | P3|
| 4 | 2 | 29 | 2023 | invalid | P4|
| 5 | 0 | 10 | 2023 | invalid | P5|
| 6 | 3 | 32 | 2023 | invalid | P6|
| 7 | 3 | 29 | 0 | invalid | P7|





