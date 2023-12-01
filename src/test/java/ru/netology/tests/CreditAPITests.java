package ru.netology.tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.DBHelper;
import ru.netology.data.DataHelper;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.data.DBHelper.cleanDatabase;

public class CreditAPITests {

    private static List<DBHelper.PaymentEntity> payments;
    private static List<DBHelper.CreditRequestEntity> credits;
    private static List<DBHelper.OrderEntity> orders;
    private static final String creditUrl = "/credit";

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeAll
    public static void setUp() {
        cleanDatabase();
    }

    @AfterEach
    public void tearDown() {
        cleanDatabase();
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    public void shouldValidTestCreditCardApprovedEntityAdded() {
        var cardInfo = DataHelper.getValidCardApproved();
        DBHelper.getBody(cardInfo, creditUrl, 200);
        payments = DBHelper.getPayments();
        credits = DBHelper.getCreditRequests();

        assertEquals(0, payments.size());
        assertEquals(1, credits.size());
        assertEquals("APPROVED", DBHelper.getCreditStatus());
    }

    @Test
    public void shouldValidTestCreditCardDeclinedEntityAdded() {
        var cardInfo = DataHelper.getValidCardDeclined();
        DBHelper.getBody(cardInfo, creditUrl, 200);
        payments = DBHelper.getPayments();
        credits = DBHelper.getCreditRequests();

        assertEquals(0, payments.size());
        assertEquals(1, credits.size());
        assertEquals("DECLINED", DBHelper.getCreditStatus());
    }

    @Test
    public void shouldCreditPOSTBodyEmpty() {
        var cardInfo = DataHelper.getAllEmpty();
        DBHelper.getBody(cardInfo, creditUrl, 400);
        payments = DBHelper.getPayments();
        credits = DBHelper.getCreditRequests();
        orders = DBHelper.getOrders();

        assertEquals(0, payments.size());
        assertEquals(0, credits.size());
        assertEquals(0, orders.size());
    }

    @Test
    public void shouldCreditPOSTNumberEmpty() {
        var cardInfo = DataHelper.getCardEmpty();
        DBHelper.getBody(cardInfo, creditUrl, 400);
        payments = DBHelper.getPayments();
        credits = DBHelper.getCreditRequests();
        orders = DBHelper.getOrders();

        assertEquals(0, payments.size());
        assertEquals(0, credits.size());
        assertEquals(0, orders.size());
    }

    @Test
    public void shouldCreditPOSTMonthEmpty() {
        var cardInfo = DataHelper.getMonthEmpty();
        DBHelper.getBody(cardInfo, creditUrl, 400);
        payments = DBHelper.getPayments();
        credits = DBHelper.getCreditRequests();
        orders = DBHelper.getOrders();

        assertEquals(0, payments.size());
        assertEquals(0, credits.size());
        assertEquals(0, orders.size());
    }

    @Test
    public void shouldCreditPOSTYearEmpty() {
        var cardInfo = DataHelper.getYearEmpty();
        DBHelper.getBody(cardInfo, creditUrl, 400);
        payments = DBHelper.getPayments();
        credits = DBHelper.getCreditRequests();
        orders = DBHelper.getOrders();

        assertEquals(0, payments.size());
        assertEquals(0, credits.size());
        assertEquals(0, orders.size());
    }

    @Test
    public void shouldCreditPOSTHolderEmpty() {
        var cardInfo = DataHelper.getHolderEmpty();
        DBHelper.getBody(cardInfo, creditUrl, 400);
        payments = DBHelper.getPayments();
        credits = DBHelper.getCreditRequests();
        orders = DBHelper.getOrders();

        assertEquals(0, payments.size());
        assertEquals(0, credits.size());
        assertEquals(0, orders.size());
    }

    @Test
    public void shouldCreditPOSTCvcEmpty() {
        var cardInfo = DataHelper.getCvcEmpty();
        DBHelper.getBody(cardInfo, creditUrl, 400);
        payments = DBHelper.getPayments();
        credits = DBHelper.getCreditRequests();
        orders = DBHelper.getOrders();

        assertEquals(0, payments.size());
        assertEquals(0, credits.size());
        assertEquals(0, orders.size());
    }
}
