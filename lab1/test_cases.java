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

