## Test code

https://github.com/harithh0/software-testing/blob/master/lab4/maven-test/src/test/java/com/baarsch_bytes/LibraryServiceTest.java

run: `mvn -Dtest=LibraryServiceTest test` in root

## Test Design

| EP | Parameter           | Min           | Max | Output    |
| -- | ------------------- | ------------- | --- | --------- |
|    | ResourceID          | Null          |     | error     |
|    |                     | Exists        |     | TRUE      |
|    |                     | Doesn't exist |     | FALSE     |
|    | isResourceAvailable |               |     |           |
|    |                     | TRUE          |     | TRUE      |
|    |                     | FALSE         |     | FALSE     |
|    | statusUpdated       |               |     |           |
|    |                     | TRUE          |     | TRUE      |
|    |                     | FALSE         |     | exception |
|    | emailSent           |               |     |           |
|    |                     | TRUE          |     | TRUE      |
|    |                     | FALSE         |     | exception |

| Cause               | Rule 1 | Rule 2 | Rule 3    | Rule 4    | Rule 5 | Rule 6    | Rule 7    | Rule 8    | Rule 9 | Rule 10   | Rule 11   | Rule 12   | Rule 13 | Rule 14   | Rule 15   | Rule 16   |
| ------------------- | ------ | ------ | --------- | --------- | ------ | --------- | --------- | --------- | ------ | --------- | --------- | --------- | ------- | --------- | --------- | --------- |
| ResourceID          | TRUE   | TRUE   | TRUE      | TRUE      | TRUE   | TRUE      | TRUE      | TRUE      | FALSE  | FALSE     | FALSE     | FALSE     | FALSE   | FALSE     | FALSE     | FALSE     |
| isResourceAvailable | TRUE   | TRUE   | TRUE      | TRUE      | FALSE  | FALSE     | FALSE     | FALSE     | TRUE   | TRUE      | TRUE      | TRUE      | FALSE   | FALSE     | FALSE     | FALSE     |
| statusUpdated       | TRUE   | TRUE   | FALSE     | FALSE     | TRUE   | TRUE      | FALSE     | FALSE     | TRUE   | TRUE      | FALSE     | FALSE     | TRUE    | TRUE      | FALSE     | FALSE     |
| emailSent           | TRUE   | FALSE  | TRUE      | FALSE     | TRUE   | FALSE     | TRUE      | FALSE     | TRUE   | FALSE     | TRUE      | FALSE     | TRUE    | FALSE     | TRUE      | FALSE     |
| Output              | TRUE   | FALSE  | exception | exception | FALSE  | exception | exception | exception | FALSE  | exception | exception | exception | FALSE   | exception | exception | exception |

| TestCases | ResourceID | isResourceAvailable | statusUpdated | emailSent | Output |
| --------- | ---------- | ------------------- | ------------- | --------- | ------ |
| 1         | null       | FALSE               | TRUE          | TRUE      | FALSE  |
| 2         | TRUE       | TRUE                | TRUE          | TRUE      | TRUE   |
| 2.2       | TRUE       | FALSE               | TRUE          | TRUE      | FALSE  |
| 2.3       | FALSE      | TRUE                | TRUE          | TRUE      | FALSE  |
| 2.4       | FALSE      | FALSE               | TRUE          | TRUE      | FALSE  |

| ErrorTestCases | ResourceID | isResourceAvailable | statusUpdated | emailSent | Output    |
| --------- | ---------- | ------------------- | ------------- | --------- | --------- |
| 2.1       | TRUE       | TRUE                | TRUE          | FALSE     | exception |
| 2.5       | TRUE       | TRUE                | FALSE         | TRUE      | exception |
| 2.6       | TRUE       | TRUE                | FALSE         | FALSE     | exception |
| 2.7       | TRUE       | FALSE               | TRUE          | FALSE     | exception |
| 2.8       | TRUE       | FALSE               | FALSE         | TRUE      | exception |
| 2.9       | TRUE       | FALSE               | FALSE         | FALSE     | exception |
| 2.11      | FALSE      | TRUE                | TRUE          | FALSE     | exception |
| 2.12      | FALSE      | TRUE                | FALSE         | TRUE      | exception |
| 2.13      | FALSE      | TRUE                | FALSE         | FALSE     | exception |
| 2.14      | FALSE      | FALSE               | TRUE          | FALSE     | exception |
| 2.15      | FALSE      | FALSE               | FALSE         | TRUE      | exception |
| 2.16      | FALSE      | FALSE               | FALSE         | FALSE     | exception |


