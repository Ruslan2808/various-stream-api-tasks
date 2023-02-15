package by.kantsevich;

import by.kantsevich.model.Animal;
import by.kantsevich.model.Car;
import by.kantsevich.model.Flower;
import by.kantsevich.model.House;
import by.kantsevich.model.Person;
import by.kantsevich.util.Util;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws IOException {
        task1();
        task2();
        task3();
        task4();
        task5();
        task6();
        task7();
        task8();
        task9();
        task10();
        task11();
        task12();
        task13();
        task14();
        task15();
    }

    private static void task1() throws IOException {
        List<Animal> animals = Util.getAnimals();

        System.out.println("\nTask №1:");

        AtomicInteger zooIndex = new AtomicInteger(0);

        animals.stream()
                .filter(animal -> animal.getAge() >= 10)
                .filter(animal -> animal.getAge() <= 20)
                .sorted(Comparator.comparing(Animal::getAge))
                .collect(Collectors.groupingBy(animal -> zooIndex.getAndIncrement() / 7))
                .get(2)
                .forEach(System.out::println);
    }

    private static void task2() throws IOException {
        List<Animal> animals = Util.getAnimals();

        System.out.println("\nTask №2:");

        animals.stream()
                .filter(animal -> "Japanese".equals(animal.getOrigin()))
                .peek(animal -> animal.setBread(animal.getBread().toUpperCase()))
                .filter(animal -> "Female".equals(animal.getGender()))
                .map(Animal::getBread)
                .forEach(System.out::println);
    }

    private static void task3() throws IOException {
        List<Animal> animals = Util.getAnimals();

        System.out.println("\nTask №3:");

        animals.stream()
                .filter(animal -> animal.getAge() > 30)
                .map(Animal::getOrigin)
                .filter(origin -> origin.startsWith("A"))
                .distinct()
                .forEach(System.out::println);
    }

    private static void task4() throws IOException {
        List<Animal> animals = Util.getAnimals();

        System.out.println("\nTask №4:");

        long numberAnimalsFemale = animals.stream()
                .filter(animal -> "Female".equals(animal.getGender()))
                .count();

        System.out.println("Number of female animals: " + numberAnimalsFemale);
    }

    private static void task5() throws IOException {
        List<Animal> animals = Util.getAnimals();

        System.out.println("\nTask №5:");

        boolean isAnyoneFromHungarian = animals.stream()
                .filter(animal -> animal.getAge() >= 20)
                .filter(animal -> animal.getAge() <= 30)
                .anyMatch(animal -> "Hungarian".equals(animal.getOrigin()));

        System.out.println("Is there any animal from Hungarian? " + isAnyoneFromHungarian);
    }

    private static void task6() throws IOException {
        List<Animal> animals = Util.getAnimals();

        System.out.println("\nTask №6:");

        boolean isAnimalsMaleAndFemale = animals.stream()
                .allMatch(animal ->
                        "Male".equals(animal.getGender()) ||
                        "Female".equals(animal.getGender())
                );

        System.out.println("Are all animals male and female? " + isAnimalsMaleAndFemale);
    }

    private static void task7() throws IOException {
        List<Animal> animals = Util.getAnimals();

        System.out.println("\nTask №7:");

        boolean isNobodyFromOceania = animals.stream()
                .noneMatch(animal -> "Oceania".equals(animal.getOrigin()));

        System.out.println("No animal has an Oceania country of origin? " + isNobodyFromOceania);
    }

    private static void task8() throws IOException {
        List<Animal> animals = Util.getAnimals();

        System.out.println("\nTask №8:");

        int ageOldestAnimal = animals.stream()
                .sorted(Comparator.comparing(Animal::getBread))
                .limit(100)
                .mapToInt(Animal::getAge)
                .max()
                .orElse(0);

        System.out.println("Age of the oldest animal: " + ageOldestAnimal);
    }

    private static void task9() throws IOException {
        List<Animal> animals = Util.getAnimals();

        System.out.println("\nTask №9:");

        int lengthShortestArray = animals.stream()
                .map(Animal::getBread)
                .map(String::toCharArray)
                .mapToInt(bread -> bread.length)
                .min()
                .orElse(0);

        System.out.println("Length of the shortest array: " + lengthShortestArray);
    }

    private static void task10() throws IOException {
        List<Animal> animals = Util.getAnimals();

        System.out.println("\nTask №10:");

        int totalAgeAnimals = animals.stream()
                .mapToInt(Animal::getAge)
                .sum();

        System.out.println("The total age of all animals: " + totalAgeAnimals);
    }

    private static void task11() throws IOException {
        List<Animal> animals = Util.getAnimals();

        System.out.println("\nTask №11:");

        double averageAgeAnimalsFromIndonesian = animals.stream()
                .filter(animal -> "Indonesian".equals(animal.getOrigin()))
                .mapToInt(Animal::getAge)
                .average()
                .orElse(0);

        System.out.println("Average age of animals from Indonesian: " + averageAgeAnimalsFromIndonesian);
    }

    private static void task12() throws IOException {
        List<Person> people = Util.getPersons();

        System.out.println("\nTask №12:");

        people.stream()
                .filter(person -> "Male".equals(person.getGender()))
                .filter(person -> Period.between(person.getDateOfBirth(), LocalDate.now()).getYears() >= 18)
                .filter(person -> Period.between(person.getDateOfBirth(), LocalDate.now()).getYears() <= 27)
                .sorted(Comparator.comparing(Person::getRecruitmentGroup))
                .limit(200)
                .forEach(System.out::println);
    }

    private static void task13() throws IOException {
        List<House> houses = Util.getHouses();

        System.out.println("\nTask №13:");

        Stream.concat(
                Stream.concat(
                        houses.stream()
                                .filter(house -> "Hospital".equals(house.getBuildingType()))
                                .flatMap(house -> house.getPersonList().stream()),
                        houses.stream()
                                .filter(house -> !"Hospital".equals(house.getBuildingType()))
                                .flatMap(house -> house.getPersonList().stream())
                                .filter(person ->
                                        Period.between(person.getDateOfBirth(), LocalDate.now()).getYears() < 18 ||
                                        (Period.between(person.getDateOfBirth(), LocalDate.now()).getYears() >= 58 &&
                                                "Female".equals(person.getGender())) ||
                                        (Period.between(person.getDateOfBirth(), LocalDate.now()).getYears() >= 63 &&
                                                "Male".equals(person.getGender()))
                                )
                ),
                houses.stream()
                        .flatMap(house -> house.getPersonList().stream())
            )
            .distinct()
            .limit(500)
            .forEach(System.out::println);
    }

    private static void task14() throws IOException {
        List<Car> cars = Util.getCars();

        System.out.println("\nTask №14:");

        List<Car> carsToTurkmenistan = cars.stream()
                .filter(car ->
                        "Jaguar".equals(car.getCarMake()) ||
                        "White".equals(car.getColor())
                )
                .toList();

        cars.removeAll(carsToTurkmenistan);

        List<Car> carsToUzbekistan = cars.stream()
                .filter(car -> car.getMass() < 1_500)
                .filter(car -> Arrays.asList("BMW", "Lexus", "Chrysler", "Toyota").contains(car.getCarMake()))
                .toList();

        cars.removeAll(carsToUzbekistan);

        List<Car> carsToKazakhstan = cars.stream()
                .filter(car ->
                        ("Black".equals(car.getColor()) && car.getMass() > 4_000) ||
                        Arrays.asList("GMC", "Dodge").contains(car.getCarMake())
                )
                .toList();

        cars.removeAll(carsToKazakhstan);

        List<Car> carsToKyrgyzstan = cars.stream()
                .filter(car ->
                        car.getReleaseYear() < 1982 ||
                        Arrays.asList("Civic", "Cherokee").contains(car.getCarModel())
                )
                .toList();

        cars.removeAll(carsToKyrgyzstan);

        List<Car> carsToRussia = cars.stream()
                .filter(car ->
                        !Arrays.asList("Yellow", "Red", "Green", "Blue").contains(car.getColor()) ||
                        car.getPrice() > 40_000
                )
                .toList();

        cars.removeAll(carsToRussia);

        List<Car> carsToMongolia = cars.stream()
                .filter(car -> car.getVin().contains("59"))
                .toList();

        double totalRevenue = Stream.of(
                        carsToTurkmenistan,
                        carsToUzbekistan,
                        carsToKazakhstan,
                        carsToKyrgyzstan,
                        carsToRussia,
                        carsToMongolia
                )
                .mapToDouble(carsToCountry ->
                        carsToCountry.stream()
                                .mapToDouble(Car::getMass)
                                .sum()
                )
                .map(mass -> mass / 1_000.0 * 7.14)
                .peek(transportCost -> System.out.printf("%.2f%n", transportCost))
                .sum();

        System.out.printf("Total revenue: %.2f%n", totalRevenue);
    }

    private static void task15() throws IOException {
        List<Flower> flowers = Util.getFlowers();

        System.out.println("\nTask №15:");

        double plantMaintenanceCost = flowers.stream()
                .sorted(
                        Comparator.comparing(Flower::getOrigin).reversed()
                        .thenComparing(Flower::getPrice)
                        .thenComparing(Flower::getWaterConsumptionPerDay).reversed()
                )
                .filter(flower -> flower.getCommonName().charAt(0) >= 'C')
                .filter(flower -> flower.getCommonName().charAt(0) <= 'S')
                .filter(Flower::isShadePreferred)
                .filter(flower ->
                        flower.getFlowerVaseMaterial().contains("Glass") ||
                        flower.getFlowerVaseMaterial().contains("Aluminum") ||
                        flower.getFlowerVaseMaterial().contains("Steel")
                )
                .mapToDouble(flower ->
                        flower.getPrice() +
                        flower.getWaterConsumptionPerDay() / 1000 * 1.39 * 365 * 5)
                .sum();

        System.out.printf("The total cost of maintaining all plants: %.2f%n", plantMaintenanceCost);
    }

}