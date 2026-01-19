| Test Case | Inputs | Expected Outputs | What the test shows |
| ---  | ---  | --- |  --- |
| 1  | 2000  | true  | Req 1: it shows that the case when input is divisible by 400  | 
| 2  | 1000   | false  | Req 2: it shows that the case when input is divisible by 100 | 
| 3  | 400   | true  | Req 1: it shows that the case when input is divisible by 400  | 
| 4  | 520   | true  | Req 3: it shows that the case when input is divisible by 4  | 
| 5  | 1948   | true  | Req 3: it shows that the case when input is divisible by 4  | 
| 6  | 950   | false  | Req 4: it shows that the case when input is not divisible by either 400, 100, or 4 | 
| 7  | -48   | false  | Req 4: it shows that the unit handles unexpected or unrealistic values | 
| 8  | 0  | false  | Req 4: it shows that the unit handles unexpected or unrealistic values | 



I believe that the tests I chose are sufficent because they test every requirement and unexpected inputs. It checks 
that the logic works and that it handles inputs that don't make sense.



```java

public static void main(string args){

    // Part I
    is_leap_year(2000); // true
    is_leap_year(1000); // false
    is_leap_year(400); // true
    is_leap_year(520); // true
    is_leap_year(950); // false

}

// Part II - possible bad implementations that will pass my tests, but be wrong
boolean is_leap_year(int year){
   
    // checking only hardcoded years
    if (year == 1948){
        return true;
    }

    // not checking for negatives or 0 inputs
    // -48 will return true
    if (year % 4 == 0){
        return true;
    }

    // the implementation is incorrect / wrong order
    // eg. if we test 1948, it will return false, since it will check 100 first
    // fix: it must be checked top-down
    if (year % 100){
        return false;
    }
    if (year % 400){
        return true
    }
    if (year % 4){
        return true;
    }

}


```
