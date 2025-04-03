package edu.redko;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*
  @author User
  @project lab3
  @class ConverterTest
  @version 1.0.0
  @since 03.04.2025 - 13.18
*/class ConverterTest {

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @Test
    void whenArabic_3_ThenRoman_III() {
        Assertions.assertEquals("III", Converter.convertToRoman(3));
    }

    @Test
    void whenArabic_8_ThenRoman_VIII() {
        Assertions.assertEquals("VIII", Converter.convertToRoman(8));
    }

    @Test
    void whenArabic_10_ThenRoman_X() {
        Assertions.assertEquals("X", Converter.convertToRoman(10));
    }

    @Test
    void whenArabic_20_ThenRoman_XX() {
        Assertions.assertEquals("XX", Converter.convertToRoman(20));
    }

    @Test
    void whenArabic_29_ThenRoman_XXIX() {
        Assertions.assertEquals("XXIX", Converter.convertToRoman(29));
    }

    @Test
    void whenArabic_30_ThenRoman_XXX() {
        Assertions.assertEquals("XXX", Converter.convertToRoman(30));
    }

    @Test
    void whenArabic_48_ThenRoman_XLVIII() {
        Assertions.assertEquals("XLVIII", Converter.convertToRoman(48));
    }

    @Test
    void whenArabic_50_ThenRoman_L() {
        Assertions.assertEquals("L", Converter.convertToRoman(50));
    }

    @Test
    void whenArabic_64_ThenRoman_LXIV() {
        Assertions.assertEquals("LXIV", Converter.convertToRoman(64));
    }

    @Test
    void whenArabic_98_ThenRoman_XCVIII() {
        Assertions.assertEquals("XCVIII", Converter.convertToRoman(98));
    }

    @Test
    void whenArabic_100_ThenRoman_C() {
        Assertions.assertEquals("C", Converter.convertToRoman(100));
    }

    @Test
    void whenArabic_204_ThenRoman_CCIV() {
        Assertions.assertEquals("CCIV", Converter.convertToRoman(204));
    }

    @Test
    void whenArabic_379_ThenRoman_CCCLXXIX() {
        Assertions.assertEquals("CCCLXXIX", Converter.convertToRoman(379));
    }

    @Test
    void whenArabic_481_ThenRoman_CDLXXXI() {
        Assertions.assertEquals("CDLXXXI", Converter.convertToRoman(481));
    }

    @Test
    void whenArabic_534_ThenRoman_DXXXIV() {
        Assertions.assertEquals("DXXXIV", Converter.convertToRoman(534));
    }

    @Test
    void whenArabic_900_ThenRoman_CM() {
        Assertions.assertEquals("CM", Converter.convertToRoman(900));
    }

    @Test
    void whenArabic_1000_ThenRoman_M() {
        Assertions.assertEquals("M", Converter.convertToRoman(1000));
    }

    @Test
    void whenArabic_1667_ThenRoman_MDCLXVII() {
        Assertions.assertEquals("MDCLXVII", Converter.convertToRoman(1667));
    }

    @Test
    void whenArabic_1987_ThenRoman_MCMLXXXVII() {
        Assertions.assertEquals("MCMLXXXVII", Converter.convertToRoman(1987));
    }

    @Test
    void whenArabic_2025_ThenRoman_MMXXV() {
        Assertions.assertEquals("MMXXV", Converter.convertToRoman(2025));
    }

    @Test
    void whenArabic_2344_ThenRoman_MMCCCXLIV() {
        Assertions.assertEquals("MMCCCXLIV", Converter.convertToRoman(2344));
    }

    @Test
    void whenArabic_3729_ThenRoman_MMMDCCXXIX() {
        Assertions.assertEquals("MMMDCCXXIX", Converter.convertToRoman(3729));
    }

    @Test
    void whenArabic_3999_ThenRoman_MMMCMXCIX() {
        Assertions.assertEquals("MMMCMXCIX", Converter.convertToRoman(3999));
    }


    @Test
    void whenArabic_0_ThenException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Converter.convertToRoman(0));
    }

    @Test
    void whenArabic_Negative_ThenException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Converter.convertToRoman(-1));
    }
}