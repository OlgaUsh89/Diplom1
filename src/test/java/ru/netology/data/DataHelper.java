package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataHelper {
    private static Faker fakerEng = new Faker(new Locale("en"));
    private static Faker fakerRu = new Faker(new Locale("ru"));

    private DataHelper() {}

    @Value
    public static class CardInfo {
        String number;
        String month;
        String year;
        String holder;
        String cvc;
    }

    public static String getValidHolder() {
        return fakerEng.name().fullName().toUpperCase();
    }

    public static String getMonth(int month) {
        return LocalDate.now().plusMonths(month).format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String getYear(int year) {
        return LocalDate.now().plusYears(year).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getYearLessCurrent(int year) {
        return LocalDate.now().minusYears(year).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getValidCvc() {
        return fakerEng.numerify("###");
    }

    public static CardInfo getValidCardApproved() {
        return new CardInfo("4444 4444 4444 4441", getMonth(1), getYear(1), getValidHolder(),
                getValidCvc());
    }

    public static CardInfo getValidCardDeclined() {
        return new CardInfo("4444 4444 4444 4442", getMonth(1), getYear(1), getValidHolder(),
                getValidCvc());
    }


    public static CardInfo getRandomCard16char() {
        return new CardInfo(fakerEng.numerify("#### #### #### ####"), getMonth(1), getYear(1), getValidHolder(),
                getValidCvc());
    }

    public static CardInfo getRandomCard15char() {
        return new CardInfo(fakerEng.numerify("#### #### #### ###"), getMonth(1), getYear(1), getValidHolder(),
                getValidCvc());
    }

    public static CardInfo getRandomCard17char() {
        return new CardInfo(fakerEng.numerify("#### #### #### #### #"), getMonth(1), getYear(1), getValidHolder(),
                getValidCvc());
    }

    public static CardInfo getRandomCardSymbols() {
        return new CardInfo(fakerEng.letterify("???? ???? ???? ????"), getMonth(1), getYear(1), getValidHolder(),
                getValidCvc());
    }

    public static CardInfo getCardEmpty() {
        return new CardInfo("", getMonth(1), getYear(1), getValidHolder(), getValidCvc());
    }

    public static CardInfo getMonth1char() {
        return new CardInfo("4444 4444 4444 4441", String.valueOf(fakerEng.number().numberBetween(1, 9)),
                getYear(1), getValidHolder(), getValidCvc());
    }

    public static CardInfo getInvalidMonthOver12() {
        return new CardInfo("4444 4444 4444 4441", String.valueOf(fakerEng.number().numberBetween(13, 99)),
                getYear(1), getValidHolder(), getValidCvc());
    }

    public static CardInfo getInvalidMonthNull() {
        return new CardInfo("4444 4444 4444 4441", "00", getYear(1), getValidHolder(), getValidCvc());
    }


    public static CardInfo getMonthEmpty() {
        return new CardInfo("4444 4444 4444 4441", "", getYear(1), getValidHolder(), getValidCvc());
    }

    public static CardInfo getInvalidYearLessCurrent() {
        return new CardInfo("4444 4444 4444 4441", getMonth(1), getYearLessCurrent(1), getValidHolder(),
                getValidCvc());
    }

    public static CardInfo getRandomYearSymbols() {
        return new CardInfo("4444 4444 4444 4441", getMonth(1), fakerEng.letterify("??"), getValidHolder(),
                getValidCvc());
    }
    public static CardInfo getYearEmpty() {
        return new CardInfo("4444 4444 4444 4441", getMonth(1), "", getValidHolder(), getValidCvc());
    }

    public static CardInfo getValidHolderWithSpaceMiddle() {
        return new CardInfo("4444 4444 4444 4441", getMonth(1), getYear(1), fakerEng.name().firstName().toUpperCase()
                + " " + fakerEng.name().lastName().toUpperCase() + " " + fakerEng.name().lastName().toUpperCase(), getValidCvc());
    }

    public static CardInfo getValidHolderWithDashMiddle() {
        return new CardInfo("4444 4444 4444 4441", getMonth(1), getYear(1), fakerEng.name().firstName().toUpperCase()
                + "-" + fakerEng.name().lastName().toUpperCase() + "-" + fakerEng.name().lastName().toUpperCase(), getValidCvc());
    }

    public static CardInfo getHolderWithDashFirst() {
        return new CardInfo("4444 4444 4444 4441", getMonth(1), getYear(1), "-" + fakerEng.name().fullName().toUpperCase(),
                getValidCvc());
    }

    public static CardInfo getHolderWithDashEnd() {
        return new CardInfo("4444 4444 4444 4441", getMonth(1), getYear(1), fakerEng.name().fullName().toUpperCase() + "-",
                getValidCvc());
    }

    public static CardInfo getHolderWithSpaceFirst() {
        return new CardInfo("4444 4444 4444 4441", getMonth(1), getYear(1), " " + fakerEng.name().fullName().toUpperCase(),
                getValidCvc());
    }

    public static CardInfo getHolderWithSpaceEnd() {
        return new CardInfo("4444 4444 4444 4441", getMonth(1), getYear(1), fakerEng.name().fullName().toUpperCase() + " ",
                getValidCvc());
    }

    public static CardInfo getHolderLowercase() {
        return new CardInfo("4444 4444 4444 4441", getMonth(1), getYear(1), fakerEng.name().fullName().toLowerCase(),
                getValidCvc());
    }

    public static CardInfo getHolderRu() {
        return new CardInfo("4444 4444 4444 4441", getMonth(1), getYear(1), fakerRu.name().firstName().toUpperCase()
                + " " + fakerRu.name().lastName().toUpperCase(), getValidCvc());
    }

    public static CardInfo getHolderNumbers() {
        return new CardInfo("4444 4444 4444 4441", getMonth(1), getYear(1), fakerEng.name().firstName().toUpperCase()
                + " " + fakerEng.numerify("###"), getValidCvc());
    }

    public static CardInfo getHolderSymbols() {
        return new CardInfo("4444 4444 4444 4441", getMonth(1), getYear(1), fakerEng.name().firstName().toUpperCase()
                + " @" + fakerEng.name().lastName().toUpperCase(), getValidCvc());
    }

    public static CardInfo getHolderEmpty() {
        return new CardInfo("4444 4444 4444 4441", getMonth(1), getYear(1), "", getValidCvc());
    }

    public static CardInfo getInvalidCvc2char() {
        return new CardInfo("4444 4444 4444 4441", getMonth(1), getYear(1), getValidHolder(),
                fakerEng.numerify("##"));
    }


    public static CardInfo getInvalidCvc4char() {
        return new CardInfo("4444 4444 4444 4441", getMonth(1), getYear(1), getValidHolder(),
                fakerEng.numerify("####"));
    }

    public static CardInfo getInvalidCvcSymbols() {
        return new CardInfo("4444 4444 4444 4441", getMonth(1), getYear(1), getValidHolder(),
                fakerEng.letterify("???"));
    }

    public static CardInfo getCvcEmpty() {
        return new CardInfo("4444 4444 4444 4441", getMonth(1), getYear(1), getValidHolder(), "");
    }

    public static CardInfo getAllEmpty() {
        return new CardInfo("", "", "", "", "");
    }
}
