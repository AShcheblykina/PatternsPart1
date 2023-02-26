package ru.netology.delivery.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


public class DataGenerator {
    private static Faker faker;

    private DataGenerator() {
    }

    public static String generateDate(int shift) {
        return LocalDate.now().plusDays(shift).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static String generateCity(String locale) {
        faker = new Faker(new Locale("ru"));
        String city = faker.address().city();
        return city;
    }

    public static String generateName(String locale) {
        faker = new Faker(new Locale("ru"));
        String name = faker.name().fullName();
        return name;
    }

    public static String generatePhone(String locale) {
        faker = new Faker(new Locale("ru"));
        String phone = faker.phoneNumber().phoneNumber();
        return phone;
    }

    public static class Registration {
        private Registration() {
        }

        public static UserInfo generateUser(String locale) {
            faker = new Faker(new Locale("ru"));
            return new UserInfo(generateCity(locale), generateName(locale), generatePhone(locale));
        }

    }

    @Value
    public static class UserInfo {
        String city;
        String name;
        String phone;


    }
}
