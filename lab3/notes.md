> my notes :)

- We don't test for child/elder discounts in the decision table since this will be tested in other tables and decision tables are mainly used for interacting conditions such as `Resident` AND `Veteran` - interaction 
  - Age discount logic has no interaction and is better tested using EPs.
  - You can use `age` in the test cases but not as a cause in the decision table
  - Keep age & nights constant for decision-table tests, but change the causes

- Testing boundaries
  - Should test one boundary at a time, keep the other inputs safe, middle values.

