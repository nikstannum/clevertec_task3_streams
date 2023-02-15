package ru.clevertec;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import ru.clevertec.model.Animal;
import ru.clevertec.model.Car;
import ru.clevertec.model.Flower;
import ru.clevertec.model.House;
import ru.clevertec.model.Person;
import ru.clevertec.util.Util;

import static java.lang.System.out;
import static java.time.Period.between;

public class App {
    public static final String NOT_FOUND = "Nothing was found according to your conditions";
    public static final String MALE = "male";
    public static final String FEMALE = "female";
    public static final String JAPANESE = "Japanese";
    public static final String HUNGARIAN = "Hungarian";
    public static final String OCEANIA = "Oceania";
    public static final String TURKMENISTAN = "Turkmenistan";
    public static final String KAZAKHSTAN = "Kazakhstan";
    public static final String UZBEKISTAN = "Uzbekistan";
    public static final String KYRGYZSTAN = "Kyrgyzstan";
    public static final String RUSSIA = "Russia";
    public static final String MONGOLIA = "Mongolia";
    public static final String INDONESIAN = "Indonesian";
    public static final String COUNTRY_NONE = "None";
    public static final String LETTER_A = "A";
    public static final char CHAR_C = 'C';
    public static final char CHAR_S = 'S';
    public static final String HOSPITAL = "hospital";
    public static final String PERSON = "person";
    public static final String JAGUAR = "Jaguar";
    public static final String BMW = "bmw";
    public static final String LEXUS = "lexus";
    public static final String CHRYSLER = "chrysler";
    public static final String TOYOTA = "toyota";
    public static final String GMC = "GMC";
    public static final String DODGE = "Dodge";
    public static final String CIVIC = "Civic";
    public static final String CHEROKEE = "Cherokee";
    public static final String COLOR_WHITE = "white";
    public static final String COLOR_BLACK = "black";
    public static final String COLOR_YELLOW = "yellow";
    public static final String COLOR_RED = "red";
    public static final String COLOR_GREEN = "green";
    public static final String COLOR_BLUE = "blue";
    public static final String NUM_59 = "59";
    public static final String MATERIAL_GLASS = "Glass";
    public static final String MATERIAL_ALUMINUM = "Aluminum";
    public static final String MATERIAL_STEEL = "Steel";

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
        task16();
    }

    private static void task1() throws IOException {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .filter(a -> a.getAge() >= 10 && a.getAge() <= 20)
                .sorted(Comparator.comparing(Animal::getAge))
                .skip(14)
                .limit(7)
                .forEach(out::println);
    }

    private static void task2() throws IOException {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .filter(animal -> JAPANESE.equalsIgnoreCase(animal.getOrigin()))
                .peek(animal -> animal.setBread(animal.getBread().toUpperCase()))
                .filter(animal -> FEMALE.equalsIgnoreCase(animal.getGender()))
                .map(Animal::getBread)
                .forEach(out::println);
    }

    private static void task3() throws IOException {
        List<Animal> animals = Util.getAnimals();
        animals.stream().filter(animal -> animal.getAge() > 30)
                .map(Animal::getOrigin)
                .distinct()
                .filter(origin -> origin.startsWith(LETTER_A))
                .forEach(out::println);
    }

    private static void task4() throws IOException {
        List<Animal> animals = Util.getAnimals();
        long count = animals.stream()
                .filter(animal -> FEMALE.equalsIgnoreCase(animal.getGender()))
                .count();
        out.println("Female animals = " + count);

    }

    private static void task5() throws IOException {
        List<Animal> animals = Util.getAnimals();
        boolean isFromHungarian = animals.stream()
                .anyMatch(animal -> animal.getAge() >= 20
                        && animal.getAge() <= 30
                        && HUNGARIAN.equalsIgnoreCase(animal.getOrigin()));
        out.println("Is anything from Hungarian? " + isFromHungarian);
    }

    private static void task6() throws IOException {
        List<Animal> animals = Util.getAnimals();
        boolean allMaleFemale = animals.stream()
                .allMatch(animal -> MALE.equalsIgnoreCase(animal.getGender())
                        || FEMALE.equalsIgnoreCase(animal.getGender()));
        out.println("Is everyone male or female? " + allMaleFemale);
    }

    private static void task7() throws IOException {
        List<Animal> animals = Util.getAnimals();
        boolean isNotFromOceania = animals.stream()
                .noneMatch(animal -> OCEANIA.equalsIgnoreCase(animal.getOrigin()));
        out.println("No one from Oceania? " + isNotFromOceania);
    }

    private static void task8() throws IOException {
        List<Animal> animals = Util.getAnimals();
        int oldestAnimal = animals.stream()
                .sorted(Comparator.comparing(Animal::getBread))
                .limit(100)
                .mapToInt(Animal::getAge)
                .max()
                .orElseThrow(() -> new RuntimeException(NOT_FOUND));
        out.println("The oldest animal is " + oldestAnimal + " years old");
    }

    private static void task9() throws IOException {
        List<Animal> animals = Util.getAnimals();
        int smallestArray = animals.stream()
                .mapToInt(animal -> animal.getBread().toCharArray().length)
                .min()
                .orElseThrow(() -> new RuntimeException(NOT_FOUND));
        out.println("The long of the smallest array is " + smallestArray);
    }

    private static void task10() throws IOException {
        List<Animal> animals = Util.getAnimals();
        out.println("The total age of all animals is " + animals.stream().mapToInt(Animal::getAge).sum());
    }

    private static void task11() throws IOException {
        List<Animal> animals = Util.getAnimals();
        double avg = animals.stream()
                .filter(animal -> INDONESIAN.equalsIgnoreCase(animal.getOrigin()))
                .mapToInt(Animal::getAge)
                .average()
                .orElseThrow(() -> new RuntimeException(NOT_FOUND));
        out.println("Average age of all animals from Indonesian is " + avg);
    }

    private static void task12() throws IOException {
        List<Person> people = Util.getPersons();
        people.stream()
                .filter(p -> MALE.equalsIgnoreCase(p.getGender()))
                .filter(p -> between(p.getDateOfBirth(), LocalDate.now()).getYears() >= 18
                        && between(p.getDateOfBirth(), LocalDate.now()).getYears() <= 27)
                .sorted(Comparator.comparing(Person::getRecruitmentGroup))
                .limit(200)
                .forEach(out::println);
    }

    private static void task13() throws IOException {
        List<House> houses = Util.getHouses();
        houses.stream()
                .flatMap(h -> h.getPersonList()
                        .stream()
                        .map(p -> Map.of(HOSPITAL, HOSPITAL.equalsIgnoreCase(h.getBuildingType()), PERSON, p)))
                .sorted((mp1, mp2) -> {
                    int p1Weight = mp1.get(HOSPITAL).equals(true) ? 10 : (isChildOrOldPerson((Person) mp1.get(PERSON)) ? 1 : 0);
                    int p2Weight = mp2.get(HOSPITAL).equals(true) ? 10 : (isChildOrOldPerson((Person) mp2.get(PERSON)) ? 1 : 0);
                    return p2Weight - p1Weight;
                })
                .map(mp -> (Person) mp.get(PERSON))
                .limit(500)
                .toList()
                .forEach(out::println);
    }

    private static boolean isChildOrOldPerson(Person person) {
        String gender = person.getGender();
        int fullYears = between(person.getDateOfBirth(), LocalDate.now()).getYears();
        boolean child = fullYears < 18;
        boolean oldWoman = FEMALE.equalsIgnoreCase(gender) && fullYears >= 58;
        boolean oldMan = MALE.equalsIgnoreCase(gender) && fullYears >= 63;
        return child || oldMan || oldWoman;
    }

    private static void task14() throws IOException {
        List<Car> cars = Util.getCars();
        Map<String, IntSummaryStatistics> map = cars.stream()
                .collect(Collectors.groupingBy(App::getCountryToFollow, Collectors.summarizingInt(Car::getMass)));
        map.remove(COUNTRY_NONE);
        map.forEach((k, v) -> {
            out.print("Country: " + k);
            out.print("\ttotal mass: " + v.getSum());
            out.println("\ttotal cost: " + BigDecimal.valueOf(v.getSum() * 7.14 / 1000).setScale(2, RoundingMode.HALF_UP));
        });
        double total = map.values().stream()
                .mapToLong(IntSummaryStatistics::getSum)
                .sum() * 7.14 / 1000;
        out.println("TOTAL REVENUE = " + BigDecimal.valueOf(total).setScale(2, RoundingMode.HALF_UP));
    }

    private static String getCountryToFollow(Car car) {
        if (JAGUAR.equalsIgnoreCase(car.getCarMake()) || COLOR_WHITE.equalsIgnoreCase(car.getColor())) {
            return TURKMENISTAN;
        }
        boolean makeCondition;
        switch (car.getCarMake().toLowerCase()) {
            case BMW, LEXUS, CHRYSLER, TOYOTA -> makeCondition = true;
            default -> makeCondition = false;
        }
        if (car.getMass() < 1500 && makeCondition) {
            return UZBEKISTAN;
        }
        if (COLOR_BLACK.equalsIgnoreCase(car.getColor()) && car.getMass() > 4000
                || GMC.equalsIgnoreCase(car.getCarMake())
                || DODGE.equalsIgnoreCase(car.getCarMake())) {
            return KAZAKHSTAN;
        }
        if (car.getReleaseYear() < 1982
                || CIVIC.equalsIgnoreCase(car.getCarModel())
                || CHEROKEE.equalsIgnoreCase(car.getCarModel())) {
            return KYRGYZSTAN;
        }
        boolean colorCondition;
        switch (car.getColor().toLowerCase()) {
            case COLOR_YELLOW, COLOR_RED, COLOR_GREEN, COLOR_BLUE -> colorCondition = false;
            default -> colorCondition = true;
        }
        if (colorCondition || car.getPrice() > 40_000) {
            return RUSSIA;
        }
        if (car.getVin().contains(NUM_59)) {
            return MONGOLIA;
        }
        return COUNTRY_NONE;
    }

    private static void task15() throws IOException {
        List<Flower> flowers = Util.getFlowers();
        BigDecimal total = flowers.stream()
                .sorted((f1, f2) -> {
                    int resultCompare = f2.getOrigin().compareToIgnoreCase(f1.getOrigin());
                    if (resultCompare == 0) {
                        resultCompare = f1.getPrice() - f2.getPrice();
                    }
                    if (resultCompare == 0) {
                        Double waterConsumption1 = f1.getWaterConsumptionPerDay();
                        Double waterConsumption2 = f2.getWaterConsumptionPerDay();
                        resultCompare = waterConsumption2.compareTo(waterConsumption1);
                    }
                    return resultCompare;
                })
                .filter(f -> {
                    char firstChar = f.getCommonName().charAt(0);
                    return firstChar >= CHAR_C && firstChar <= CHAR_S;
                })
                .filter(Flower::isShadePreferred)
                .filter(f -> {
                    List<String> vaseMaterials = f.getFlowerVaseMaterial();
                    return vaseMaterials.contains(MATERIAL_GLASS)
                            || vaseMaterials.contains(MATERIAL_ALUMINUM)
                            || vaseMaterials.contains(MATERIAL_STEEL);
                })
                .map(f -> f.getPrice() + f.getWaterConsumptionPerDay() * 365 * 5 * 1.39 / 1000)
                .reduce(BigDecimal.ZERO, (startVal, p) -> startVal.add(BigDecimal.valueOf(p)).setScale(2, RoundingMode.HALF_UP), BigDecimal::add);
        out.println("General expenses = " + total);
    }

    private static void task16() throws IOException {
        List<Flower> flowers = Util.getFlowers();
        Map<String, List<Flower>> groupCountryFamily = flowers.stream()
                .filter(f -> !f.isShadePreferred())
                .collect(Collectors.groupingBy(f -> f.getOrigin() + ":" + f.getPlantFamily(), Collectors.toList()));

        groupCountryFamily.entrySet().stream()
                .filter(list -> list.getValue().size() >= 20 && list.getValue().size() <= 50)
                .sorted((l1, l2) -> {
                    Double waterCons1 = l1.getValue().stream()
                            .reduce(0.0, (startVal, f) -> startVal += f.getWaterConsumptionPerDay(), Double::sum);
                    Double waterCons2 = l2.getValue().stream()
                            .reduce(0.0, (startVal, f) -> startVal += f.getWaterConsumptionPerDay(), Double::sum);
                    return waterCons1.compareTo(waterCons2);
                })
                .limit(1)
                .forEach(out::println);
    }
}