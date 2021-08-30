package ru.netology.web;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class DeliveryTest {

    @Test
    void shouldSendCorrectFields() {
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Москва");
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        $("[data-test-id=name] input").setValue("Иванов Иван-Петр");
        $("[data-test-id=phone] input").setValue("+79060000000");
        $("[data-test-id=agreement]").click();
        $(byText("Забронировать")).click();
        $(byText("Успешно!")).shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldSendShortName() {
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Казань");
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        $("[data-test-id=name] input").setValue("и");
        $("[data-test-id=phone] input").setValue("+00000000000");
        $("[data-test-id=agreement]").click();
        $(byText("Забронировать")).click();
        $(byText("Успешно!")).shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldSendIncorrectName() {
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Мурманск");
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        $("[data-test-id=name] input").setValue("-");
        $("[data-test-id=phone] input").setValue("+00000000000");
        $("[data-test-id=agreement]").click();
        $(byText("Забронировать")).click();
        $(byText("Успешно!")).shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

}
