package ru.netology.tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.DBHelper;
import ru.netology.data.DataHelper;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.data.DBHelper.cleanDatabase;


public class PaymentAPITests {

    private static List<DBHelper.PaymentEntity> payments;
    private static List<DBHelper.CreditRequestEntity> credits;
    private static List<DBHelper.OrderEntity> orders;
    private static final String paymentUrl = "/payment";

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
    public void shouldValidTestCardApprovedEntityAdded() {
        var cardInfo = DataHelper.getValidCardApproved();
        DBHelper.getBody(cardInfo, paymentUrl,200);
        payments = DBHelper.getPayments();
        credits = DBHelper.getCreditRequests();

        assertEquals(1, payments.size());
        assertEquals(0, credits.size());
        assertEquals("APPROVED", DBHelper.getPaymentStatus());
    }

    @Test
    public void shouldValidTestCardDeclinedEntityAdded() {
        var cardInfo = DataHelper.getValidCardDeclined();
        DBHelper.getBody(cardInfo, paymentUrl, 200);
        payments = DBHelper.getPayments();
        credits = DBHelper.getCreditRequests();

        assertEquals(1, payments.size());
        assertEquals(0, credits.size());
        assertEquals("DECLINED", DBHelper.getPaymentStatus());
    }

    @Test
    public void shouldPOSTBodyEmpty() {
        var cardInfo = DataHelper.getAllEmpty();
        DBHelper.getBody(cardInfo, paymentUrl, 400);
        payments = DBHelper.getPayments();
        credits = DBHelper.getCreditRequests();
        orders = DBHelper.getOrders();

        assertEquals(0, payments.size());
        assertEquals(0, credits.size());
        assertEquals(0, orders.size());
    }

    @Test
    public void shouldPOSTNumberEmpty() {
        var cardInfo = DataHelper.getCardEmpty();
        DBHelper.getBody(cardInfo, paymentUrl, 400);
        payments = DBHelper.getPayments();
        credits = DBHelper.getCreditRequests();
        orders = DBHelper.getOrders();

        assertEquals(0, payments.size());
        assertEquals(0, credits.size());
        assertEquals(0, orders.size());
    }

    @Test
    public void shouldPOSTMonthEmpty() {
        var cardInfo = DataHelper.getMonthEmpty();
        DBHelper.getBody(cardInfo, paymentUrl, 400);
        payments = DBHelper.getPayments();
        credits = DBHelper.getCreditRequests();
        orders = DBHelper.getOrders();

        assertEquals(0, payments.size());
        assertEquals(0, credits.size());
        assertEquals(0, orders.size());
    }

    @Test
    public void shouldPOSTYearEmpty() {
        var cardInfo = DataHelper.getYearEmpty();
        DBHelper.getBody(cardInfo, paymentUrl, 400);
        payments = DBHelper.getPayments();
        credits = DBHelper.getCreditRequests();
        orders = DBHelper.getOrders();

        assertEquals(0, payments.size());
        assertEquals(0, credits.size());
        assertEquals(0, orders.size());
    }

    @Test
    public void shouldPOSTHolderEmpty() {
        var cardInfo = DataHelper.getHolderEmpty();
        DBHelper.getBody(cardInfo, paymentUrl, 400);
        payments = DBHelper.getPayments();
        credits = DBHelper.getCreditRequests();
        orders = DBHelper.getOrders();

        assertEquals(0, payments.size());
        assertEquals(0, credits.size());
        assertEquals(0, orders.size());
    }


    @Test
    public void shouldPOSTCvcEmpty() {
        var cardInfo = DataHelper.getCvcEmpty();
        DBHelper.getBody(cardInfo, paymentUrl, 400);
        payments = DBHelper.getPayments();
        credits = DBHelper.getCreditRequests();
        orders = DBHelper.getOrders();

        assertEquals(0, payments.size());
        assertEquals(0, credits.size());
        assertEquals(0, orders.size());
    }
}
