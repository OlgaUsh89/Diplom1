package ru.netology.tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.DBHelper;
import ru.netology.data.DataHelper;
import ru.netology.page.MainPage;
import ru.netology.page.PaymentPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.data.DBHelper.cleanDatabase;

public class PaymentUITests {
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void setUp() {
        open("http://localhost:8080/");
    }

    @AfterAll
    public static void tearDown() {
        cleanDatabase();
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    // Positive tests
    @Test
    public void shouldValidTestCardApproved() {
        MainPage mainPage = new MainPage();
        var CardInfo = DataHelper.getValidCardApproved();
        PaymentPage paymentPage = mainPage.paymentButtonClick();
        paymentPage.inputData(CardInfo);
        paymentPage.getSuccessNotification();
        assertEquals("APPROVED", DBHelper.getPaymentStatus());
    }

    @Test
    public void shouldValidTestCardDeclined() {
        MainPage mainPage = new MainPage();
        var CardInfo = DataHelper.getValidCardDeclined();
        PaymentPage paymentPage = mainPage.paymentButtonClick();
        paymentPage.inputData(CardInfo);
        paymentPage.getErrorNotification();
        assertEquals("DECLINED", DBHelper.getPaymentStatus());
    }

    @Test
    public void shouldNumberField15char() {
        MainPage mainPage = new MainPage();
        var CardInfo = DataHelper.getRandomCard15char();
        PaymentPage paymentPage = mainPage.paymentButtonClick();
        paymentPage.inputData(CardInfo);
        paymentPage.getInputInvalid("Неверный формат");
    }

    @Test
    public void shouldNumberField17char() {
        MainPage mainPage = new MainPage();
        var CardInfo = DataHelper.getRandomCard17char();
        PaymentPage paymentPage = mainPage.paymentButtonClick();
        paymentPage.inputData(CardInfo);
        paymentPage.getErrorNotification();
    }

    @Test
    public void shouldNumberField16char() {
        MainPage mainPage = new MainPage();
        var CardInfo = DataHelper.getRandomCard16char();
        PaymentPage paymentPage = mainPage.paymentButtonClick();
        paymentPage.inputData(CardInfo);
        paymentPage.getErrorNotification();
    }

    @Test
    public void shouldNumberFieldSymbols() {
        MainPage mainPage = new MainPage();
        var CardInfo = DataHelper.getRandomCardSymbols();
        PaymentPage paymentPage = mainPage.paymentButtonClick();
        paymentPage.inputData(CardInfo);
        paymentPage.getInputInvalid("Неверный формат");
    }

    @Test
    public void shouldNumberFieldEmpty() {
        MainPage mainPage = new MainPage();
        var CardInfo = DataHelper.getCardEmpty();
        PaymentPage paymentPage = mainPage.paymentButtonClick();
        paymentPage.inputData(CardInfo);
        paymentPage.getInputInvalid("Поле обязательно для заполнения");
    }

    @Test
    public void shouldMonthFieldMore12() {
        MainPage mainPage = new MainPage();
        var CardInfo = DataHelper.getInvalidMonthOver12();
        PaymentPage paymentPage = mainPage.paymentButtonClick();
        paymentPage.inputData(CardInfo);
        paymentPage.getInputInvalid("Неверно указан срок действия карты");
    }

    @Test
    public void shouldMonthFieldNull() {
        MainPage mainPage = new MainPage();
        var CardInfo = DataHelper.getInvalidMonthNull();
        PaymentPage paymentPage = mainPage.paymentButtonClick();
        paymentPage.inputData(CardInfo);
        paymentPage.getInputInvalid("Неверно указан срок действия карты");
    }

    @Test
    public void shouldMonthField1char() {
        MainPage mainPage = new MainPage();
        var CardInfo = DataHelper.getMonth1char();
        PaymentPage paymentPage = mainPage.paymentButtonClick();
        paymentPage.inputData(CardInfo);
        paymentPage.getSuccessNotification();
    }

    @Test
    public void shouldMonthFieldEmpty() {
        MainPage mainPage = new MainPage();
        var CardInfo = DataHelper.getMonthEmpty();
        PaymentPage paymentPage = mainPage.paymentButtonClick();
        paymentPage.inputData(CardInfo);
        paymentPage.getInputInvalid("Поле обязательно для заполнения");
    }

    @Test
    public void shouldYearFieldLessCurrent() {
        MainPage mainPage = new MainPage();
        var CardInfo = DataHelper.getInvalidYearLessCurrent();
        PaymentPage paymentPage = mainPage.paymentButtonClick();
        paymentPage.inputData(CardInfo);
        paymentPage.getInputInvalid("Истёк срок действия карты");
    }

    @Test
    public void shouldYearFieldEmpty() {
        MainPage mainPage = new MainPage();
        var CardInfo = DataHelper.getYearEmpty();
        PaymentPage paymentPage = mainPage.paymentButtonClick();
        paymentPage.inputData(CardInfo);
        paymentPage.getInputInvalid("Поле обязательно для заполнения");
    }
    @Test
    public void shouldYearFieldSymbols() {
        MainPage mainPage = new MainPage();
        var CardInfo = DataHelper.getRandomYearSymbols();
        PaymentPage paymentPage = mainPage.paymentButtonClick();
        paymentPage.inputData(CardInfo);
        paymentPage.getInputInvalid("Неверный формат");
    }
    @Test
    public void shouldHolderFieldWithSpaceMiddle() {
        MainPage mainPage = new MainPage();
        var cardInfo = DataHelper.getValidHolderWithSpaceMiddle();
        PaymentPage paymentPage = mainPage.paymentButtonClick();
        paymentPage.inputData(cardInfo);
        paymentPage.getSuccessNotification();
    }

    @Test
    public void shouldHolderFieldWithDashMiddle() {
        MainPage mainPage = new MainPage();
        var CardInfo = DataHelper.getValidHolderWithDashMiddle();
        PaymentPage paymentPage = mainPage.paymentButtonClick();
        paymentPage.inputData(CardInfo);
        paymentPage.getSuccessNotification();
    }

    @Test
    public void shouldHolderFieldWithDashFirst() {
        MainPage mainPage = new MainPage();
        var CardInfo = DataHelper.getHolderWithDashFirst();
        PaymentPage paymentPage = mainPage.paymentButtonClick();
        paymentPage.inputData(CardInfo);
        paymentPage.getInputInvalid("Неверный формат");
    }

    @Test
    public void shouldHolderFieldWithDashEnd() {
        MainPage mainPage = new MainPage();
        var CardInfo = DataHelper.getHolderWithDashEnd();
        PaymentPage paymentPage = mainPage.paymentButtonClick();
        paymentPage.inputData(CardInfo);
        paymentPage.getInputInvalid("Неверный формат");
    }

    @Test
    public void shouldHolderFieldWithSpaceFirst() {
        MainPage mainPage = new MainPage();
        var CardInfo = DataHelper.getHolderWithSpaceFirst();
        PaymentPage paymentPage = mainPage.paymentButtonClick();
        paymentPage.inputData(CardInfo);
        paymentPage.getInputInvalid("Неверный формат");
    }

    @Test
    public void shouldHolderFieldWithSpaceEnd() {
        MainPage mainPage = new MainPage();
        var CardInfo = DataHelper.getHolderWithSpaceEnd();
        PaymentPage paymentPage = mainPage.paymentButtonClick();
        paymentPage.inputData(CardInfo);
        paymentPage.getInputInvalid("Неверный формат");
    }

    @Test
    public void shouldHolderFieldLowercase() {
        MainPage mainPage = new MainPage();
        var CardInfo = DataHelper.getHolderLowercase();
        PaymentPage paymentPage = mainPage.paymentButtonClick();
        paymentPage.inputData(CardInfo);
        paymentPage.getSuccessNotification();
    }


    @Test
    public void shouldHolderFieldRu() {
        MainPage mainPage = new MainPage();
        var CardInfo = DataHelper.getHolderRu();
        PaymentPage paymentPage = mainPage.paymentButtonClick();
        paymentPage.inputData(CardInfo);
        paymentPage.getInputInvalid("Неверный формат");
    }

    @Test
    public void shouldHolderFieldNumbers() {
        MainPage mainPage = new MainPage();
        var CardInfo = DataHelper.getHolderNumbers();
        PaymentPage paymentPage = mainPage.paymentButtonClick();
        paymentPage.inputData(CardInfo);
        paymentPage.getInputInvalid("Неверный формат");
    }

    @Test
    public void shouldHolderFieldSymbols() {
        MainPage mainPage = new MainPage();
        var CardInfo = DataHelper.getHolderSymbols();
        PaymentPage paymentPage = mainPage.paymentButtonClick();
        paymentPage.inputData(CardInfo);
        paymentPage.getInputInvalid("Неверный формат");
    }

    @Test
    public void shouldHolderFieldEmpty() {
        MainPage mainPage = new MainPage();
        var CardInfo = DataHelper.getHolderEmpty();
        PaymentPage paymentPage = mainPage.paymentButtonClick();
        paymentPage.inputData(CardInfo);
        paymentPage.getInputInvalid("Поле обязательно для заполнения");
    }

    @Test
    public void shouldCVCField2char() {
        MainPage mainPage = new MainPage();
        var CardInfo = DataHelper.getInvalidCvc2char();
        PaymentPage paymentPage = mainPage.paymentButtonClick();
        paymentPage.inputData(CardInfo);
        paymentPage.getInputInvalid("Неверный формат");
    }

    @Test
    public void shouldCVCField4char() {
        MainPage mainPage = new MainPage();
        var CardInfo = DataHelper.getInvalidCvc4char();
        PaymentPage paymentPage = mainPage.paymentButtonClick();
        paymentPage.inputData(CardInfo);
        paymentPage.getSuccessNotification();
    }

    @Test
    public void shouldCVCFieldSymbols() {
        MainPage mainPage = new MainPage();
        var CardInfo = DataHelper.getInvalidCvcSymbols();
        PaymentPage paymentPage = mainPage.paymentButtonClick();
        paymentPage.inputData(CardInfo);
        paymentPage.getInputInvalid("Неверный формат");
    }

    @Test
    public void shouldCVCFieldEmpty() {
        MainPage mainPage = new MainPage();
        var CardInfo = DataHelper.getCvcEmpty();
        PaymentPage paymentPage = mainPage.paymentButtonClick();
        paymentPage.inputData(CardInfo);
        paymentPage.getInputInvalid("Поле обязательно для заполнения");
    }

    @Test
    public void shouldAllFieldsEmpty() {
        MainPage mainPage = new MainPage();
        var CardInfo = DataHelper.getAllEmpty();
        PaymentPage paymentPage = mainPage.paymentButtonClick();
        paymentPage.inputData(CardInfo);
        $(byText("Номер карты")).parent().$(".input__sub").shouldBe(visible).
                shouldHave(text("Поле обязательно для заполнения"));
        $(byText("Месяц")).parent().$(".input__sub").shouldBe(visible).
                shouldHave(text("Поле обязательно для заполнения"));
        $(byText("Год")).parent().$(".input__sub").shouldBe(visible).
                shouldHave(text("Поле обязательно для заполнения"));
        $(byText("Владелец")).parent().$(".input__sub").shouldBe(visible).
                shouldHave(text("Поле обязательно для заполнения"));
        $(byText("CVC/CVV")).parent().$(".input__sub").shouldBe(visible).
                shouldHave(text("Поле обязательно для заполнения"));
    }
}