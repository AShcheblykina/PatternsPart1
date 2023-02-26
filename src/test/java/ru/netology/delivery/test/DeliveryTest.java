package ru.netology.delivery.test;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.delivery.data.DataGenerator;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

class DeliveryTest {
    private static Faker faker;

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }


    @Test
    @DisplayName("Should successful plan and replan meeting")
    void shouldSuccessfulPlanAndReplanMeeting() {
        var validUser = DataGenerator.Registration.generateUser("ru");
        var daysToAddForFirstMeeting = 4;
        var firstMeetingDate = DataGenerator.generateDate(daysToAddForFirstMeeting);
        var daysToAddForSecondMeeting = 7;
        var secondMeetingDate = DataGenerator.generateDate(daysToAddForSecondMeeting);
        $(("[data-test-id='city'] input")).setValue(validUser.getCity());
        $(("[data-test-id='date'] input")).sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $(("[data-test-id='date'] input")).setValue(firstMeetingDate);
        $(("[data-test-id='name'] input")).setValue(validUser.getName());
        $(("[data-test-id='phone'] input")).setValue(validUser.getPhone());
        $x("//span[contains(text(),'Я соглашаюсь с условиями')]").click();
        $x("//span[contains(text(),'Запланировать')]").click();
        $(("[data-test-id='success-notification'] div")).shouldBe(visible, Duration.ofSeconds(15));
        $(("[data-test-id='date'] input")).sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $(("[data-test-id='date'] input")).setValue(secondMeetingDate);
        $x("//span[contains(text(),'Запланировать')]").click();
        $(("[data-test-id='replan-notification'] div")).shouldBe(visible, Duration.ofSeconds(15));
        $x("//span[contains(text(),'Перепланировать')]").click();
        $(("[data-test-id='success-notification'] div")).shouldBe(visible, Duration.ofSeconds(15));

    }
}
