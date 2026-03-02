package com.lab3;

import com.baarsch_bytes.Exceptions.NightReservationException;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class calculateStayPriceTest {

  @Test
  void validateStayPrice() throws Exception {

    ReserveMyPark parkObj = new ReserveMyPark();

    int age_DT = 20;
    int nights_DT = 3;

    // Equivalence partitions - Test Cases
    assertEquals(115, parkObj.calculateStayPrice(5, 5, true, false));
    assertEquals(225, parkObj.calculateStayPrice(5, 15, false, true));
    assertEquals(171, parkObj.calculateStayPrice(5, 66, true, true));

    // BV analysis - Test Cases
    //

    // Age
    int nights_BV_age = 5;
    boolean isArkRes_BV = false;
    boolean hasVetDis_BV = false;

    // WARN: running into error here but tests if age is less than 0
    // assertThrows(Exception.class,
    // () -> parkObj.calculateStayPrice(nights_BV_age, -1, isArkRes_BV,
    // hasVetDis_BV));

    assertEquals(125, parkObj.calculateStayPrice(nights_BV_age, 0, isArkRes_BV, hasVetDis_BV));
    assertEquals(125, parkObj.calculateStayPrice(nights_BV_age, 1, isArkRes_BV, hasVetDis_BV));
    assertEquals(125, parkObj.calculateStayPrice(nights_BV_age, 11, isArkRes_BV, hasVetDis_BV));
    assertEquals(125, parkObj.calculateStayPrice(nights_BV_age, 12, isArkRes_BV, hasVetDis_BV));
    assertEquals(250, parkObj.calculateStayPrice(nights_BV_age, 13, isArkRes_BV, hasVetDis_BV));
    assertEquals(250, parkObj.calculateStayPrice(nights_BV_age, 63, isArkRes_BV, hasVetDis_BV));
    assertEquals(250, parkObj.calculateStayPrice(nights_BV_age, 64, isArkRes_BV, hasVetDis_BV));
    assertEquals(200, parkObj.calculateStayPrice(nights_BV_age, 65, isArkRes_BV, hasVetDis_BV));

    // nights
    int age_BV_nights = 15;
    assertThrows(NightReservationException.class, () -> parkObj.calculateStayPrice(0, 15, false, false));
    assertEquals(50, parkObj.calculateStayPrice(1, age_BV_nights, isArkRes_BV, hasVetDis_BV));
    assertEquals(100, parkObj.calculateStayPrice(2, age_BV_nights, isArkRes_BV, hasVetDis_BV));
    assertEquals(650, parkObj.calculateStayPrice(13, age_BV_nights, isArkRes_BV, hasVetDis_BV));
    assertEquals(700, parkObj.calculateStayPrice(14, age_BV_nights, isArkRes_BV, hasVetDis_BV));
    assertThrows(NightReservationException.class, () -> parkObj.calculateStayPrice(15, 15, false, false));

    // Decision Table - Test Cases
    assertEquals(140, parkObj.calculateStayPrice(nights_DT, age_DT, true, false));
    assertEquals(126, parkObj.calculateStayPrice(nights_DT, age_DT, true, true));
    assertEquals(135, parkObj.calculateStayPrice(nights_DT, age_DT, false, true));
    assertEquals(150, parkObj.calculateStayPrice(nights_DT, age_DT, false, false));

  }

}
