
Test code path: https://github.com/harithh0/software-testing/blob/master/lab3/software-testing-java-code/src/test/java/com/lab3/calculateStayPriceTest.java


| | Inputs |
| :--- | :--- |
| TCI 1 | age >= 0 |
| TCI 2 | age < 0 |
| TCI 3 | night <= 14 |
| TCI 4 | nights >= 1 |
| TCI 5 | nights > 14 |
| TCI 6 | nights < 1 |
| TCI 7 | isArkRes = true |
| TCI 8 | isArkRes = false |
| TCI 9 | hasVetDis = true |
| TCI 10 | hasVetDis = false |


### Equivalence partitioning

| | Parameter | Outcome | BV |
| :--- | :--- | :--- | :--- |
| | **age** | | 0, (12/13), (64/65) |
| EP1 | 0-12 | 50% off | -1, 0, 1 |
| EP2 | 13-64 | full price | 11, 12, 13 |
| EP3 | 65..int.max | 20% off | 63, 64, 65 |
| | age < 0 | error | |
| | **nights** | | 1, 14 |
| EP4 | 1-14 | valid | 0, 1, 2, 13, 14, 15 |
| | nights < 1 | error | |
| | nights > 14 | error | |
| | **isArkRes** | | |
| EP5 | TRUE | 10$ off | |
| EP6 | FALSE | normal price | |
| | **HasVetDiscount** | | |
| EP7 | TRUE | 10% off | |
| EP8 | FALSE | normal price | |



| EP covered | Test cases | age | nights | isArkRes | hasVetDiscount | outcome |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| EP: 1,4,5,8 | 1 | 5 | 5 | T | F | 115 |
| EP: 2, 4, 6, 7 | 2 | 15 | 5 | F | T | 225 |
| EP: 3, 4, 5, 7 | 3 | 66 | 5 | T | T | 171 |


### Boundary Value Analysis

#### age
| BVA - Age | age | nights | isArkRes | hasVetDiscount | outcome |
| :--- | :--- | :--- | :--- | :--- | :--- |
| | -1 | 5 | F | F | Error |
| | 0 | 5 | F | F | 125 |
| | 1 | 5 | F | F | 125 |
| | 11 | 5 | F | F | 125 |
| | 12 | 5 | F | F | 125 |
| | 13 | 5 | F | F | 250 |
| | 63 | 5 | F | F | 250 |
| | 64 | 5 | F | F | 250 |
| | 65 | 5 | F | F | 200 |


#### nights

| BVA - Nights | age | nights | isArkRes | hasVetDiscount | outcome |
| :--- | :--- | :--- | :--- | :--- | :--- |
| | 15 | 0 | F | F | Error |
| | 15 | 1 | F | F | 50 |
| | 15 | 2 | F | F | 100 |
| | 15 | 13 | F | F | 650 |
| | 15 | 14 | F | F | 700 |
| | 15 | 15 | F | F | Error |



### Decision Table 

| Causes/Effects | DT Rule 1 | DT Rule 2 | DT Rule 3 | DT Rule 4 |
| :--- | :--- | :--- | :--- | :--- |
| **Causes** | | | | |
| age 0 - 12 | T | F | F | F |
| age 13-64 | F | T | F | F |
| age 65+ | F | F | T | T |
| nights 1-14 | T | T | T | T |
| isArkRes | T | T | F | F |
| hasVetDiscount | F | T | T | F |
| **Effects** | | | | |
| Apply 10% | F | T | T | F |
| Apply 10$ (First) | T | T | F | F |


#### Test cases

| DT Rule covered | Test cases | age | nights | isArkRes | hasVetDiscount | Price |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| 1 | TCI 1 | 20 | 3 | T | F | 140 |
| 2 | TCI 2 | 20 | 3 | T | T | 126 |
| 3 | TCI 3 | 20 | 3 | F | T | 135 |
| 4 | TCI 4 | 20 | 3 | F | F | 150 |

