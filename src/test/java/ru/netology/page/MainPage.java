package ru.netology.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MainPage {

    private SelenideElement heading = $$("h2").find(text("Путешествие дня"));
    private SelenideElement paymentButton = $$("button").find(text("Купить"));
    private SelenideElement creditButton = $$("button").find(text("Купить в кредит"));
    private SelenideElement form = $("form");
    private SelenideElement successNotification = $(".notification_status_ok");
    private SelenideElement errorNotification = $(".notification_status_error");

    public MainPage() {
        heading.shouldBe(visible);
        paymentButton.shouldBe(visible);
        creditButton.shouldBe(visible);
        form.shouldBe(hidden);
        successNotification.shouldBe(hidden);
        errorNotification.shouldBe(hidden);
    }

    public PaymentPage paymentButtonClick() {
        paymentButton.click();
        form.shouldBe(visible);
        return new PaymentPage();
    }

    public CreditPage creditButtonClick() {
        creditButton.click();
        form.shouldBe(visible);
        return new CreditPage();
    }
}
