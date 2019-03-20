import by.itstep.fabiyanski.site.Gamer;
import by.itstep.fabiyanski.site.TheGamerSite;
import by.itstep.fabiyanski.site.exceptions.GamerAlreadyExistsException;
import by.itstep.fabiyanski.site.exceptions.GamerDoesntPlaysThisGameException;
import by.itstep.fabiyanski.site.exceptions.GamerIsNotRegisteredOnTheSiteException;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class TheGamerSiteTest {

    @Test(expected = GamerAlreadyExistsException.class)
    public void registerTest() throws GamerAlreadyExistsException {
        Set<String> set1 = new HashSet<>();
        set1.add("CS");
        set1.add("StarCraft");
        Gamer gamer1 = new Gamer("Sergey", set1);
        Assert.assertNotNull(gamer1);

        Set<String> set2 = new HashSet<>();
        set2.add("CS");
        set2.add("StarCraft");
        set2.add("Quake");
        Gamer gamer2 = new Gamer("Masha", set2);
        Assert.assertNotNull(gamer2);

        Set<String> set3 = new HashSet<>();
        set3.add("CS");
        set3.add("WarCraft");
        Gamer gamer3 = new Gamer("Masha", set3);
        Assert.assertNotNull(gamer3);

        TheGamerSite site = new TheGamerSite();
        Assert.assertNotNull(site);
        site.register(gamer1);
        site.register(gamer2);
        site.register(gamer3);

    }

    @Test
    public void addPointsTest() throws GamerDoesntPlaysThisGameException, GamerAlreadyExistsException {
        Set<String> set1 = new HashSet<>();
        set1.add("CS");
        set1.add("StarCraft");
        Gamer gamer1 = new Gamer("Sergey", set1);
        TheGamerSite site = new TheGamerSite();
        site.register(gamer1);
        System.out.println(site.getAllGamers().toString());
        site.addPoints(gamer1, "CS", 10);
        System.out.println(site.getAllGamers().toString());
        site.addPoints(gamer1, "StarCraft", 25);
        System.out.println(site.getAllGamers().toString());

    }

    @Test
    public void printListOfGamesTest() throws GamerAlreadyExistsException {
        Set<String> set1 = new HashSet<>();
        set1.add("CS");
        set1.add("StarCraft");
        Gamer gamer1 = new Gamer("Sergey", set1);

        Set<String> set2 = new HashSet<>();
        set2.add("CS");
        set2.add("StarCraft");
        set2.add("Quake");
        Gamer gamer2 = new Gamer("Masha", set2);

        Set<String> set3 = new HashSet<>();
        set3.add("Heroes");
        set3.add("CS");
        set3.add("Mario");
        Gamer gamer3 = new Gamer("Timur", set3);

        TheGamerSite site = new TheGamerSite();
        site.register(gamer1);
        site.register(gamer2);
        site.register(gamer3);
        site.printListOfGamesEverybodyPlays();
        site.printWholeGameList();

    }

    @Test
    public void printRatingPositiveTest() throws GamerAlreadyExistsException, GamerDoesntPlaysThisGameException, GamerIsNotRegisteredOnTheSiteException {
        Set<String> set1 = new HashSet<>();
        set1.add("CS");
        Gamer gamer1 = new Gamer("Sergey", set1);

        Set<String> set2 = new HashSet<>();
        set2.add("CS");
        Gamer gamer2 = new Gamer("Masha", set2);

        Set<String> set3 = new HashSet<>();
        set3.add("CS");
        Gamer gamer3 = new Gamer("Timur", set3);

        TheGamerSite site = new TheGamerSite();
        site.register(gamer1);
        site.register(gamer2);
        site.register(gamer3);
        site.addPoints(gamer1, "CS", 5);
        site.addPoints(gamer2, "CS", 10);
        site.addPoints(gamer3, "CS", 15);

        site.printRating("Timur", "CS");
        site.printRating("Masha", "CS");
        site.printRating("Sergey", "CS");
    }

    @Test(expected = GamerIsNotRegisteredOnTheSiteException.class)
    public void printRatingInvalidGamerTest() throws GamerDoesntPlaysThisGameException, GamerIsNotRegisteredOnTheSiteException {
        TheGamerSite site = new TheGamerSite();
        site.printRating("Sergey", "CS");
    }

    @Test(expected = GamerDoesntPlaysThisGameException.class)
    public void printRatingInvalidGameTest() throws GamerDoesntPlaysThisGameException, GamerIsNotRegisteredOnTheSiteException, GamerAlreadyExistsException {
        TheGamerSite site = new TheGamerSite();
        Set<String> set1 = new HashSet<>();
        set1.add("CS");
        set1.add("StarCraft");
        Gamer gamer1 = new Gamer("Sergey", set1);
        site.register(gamer1);
        site.printRating("Sergey", "Quake");
    }

    @Test
    public void printTenBestGamersTest() throws GamerDoesntPlaysThisGameException, GamerIsNotRegisteredOnTheSiteException, GamerAlreadyExistsException {
        TheGamerSite site = new TheGamerSite();

        Set<String> set1 = new HashSet<>();
        set1.add("CS");
        set1.add("StarCraft");

        Gamer gamer1 = new Gamer("Sergey", set1);
        site.register(gamer1);
        site.addPoints(gamer1, "CS", 5);
        site.addPoints(gamer1, "StarCraft", 5);

        Gamer gamer2 = new Gamer("Artem", set1);
        site.register(gamer2);
        site.addPoints(gamer2, "CS", 10);
        site.addPoints(gamer2, "StarCraft", 10);

        Gamer gamer3 = new Gamer("Sveta", set1);
        site.register(gamer3);
        site.addPoints(gamer3, "CS", 15);
        site.addPoints(gamer3, "StarCraft", 15);

        Gamer gamer4 = new Gamer("Masha", set1);
        site.register(gamer4);
        site.addPoints(gamer4, "CS", 20);
        site.addPoints(gamer4, "StarCraft", 20);

        Gamer gamer5 = new Gamer("Ivan", set1);
        site.register(gamer5);
        site.addPoints(gamer5, "CS", 25);
        site.addPoints(gamer5, "StarCraft", 25);

        Gamer gamer6 = new Gamer("Sasha", set1);
        site.register(gamer6);
        site.addPoints(gamer6, "CS", 30);
        site.addPoints(gamer6, "StarCraft", 30);

        Gamer gamer7 = new Gamer("Igor", set1);
        site.register(gamer7);
        site.addPoints(gamer7, "CS", 35);
        site.addPoints(gamer7, "StarCraft", 35);

        Gamer gamer8 = new Gamer("Vika", set1);
        site.register(gamer8);
        site.addPoints(gamer8, "CS", 40);
        site.addPoints(gamer8, "StarCraft", 40);

        Gamer gamer9 = new Gamer("Julia", set1);
        site.register(gamer9);
        site.addPoints(gamer9, "CS", 45);
        site.addPoints(gamer9, "StarCraft", 45);

        Gamer gamer10 = new Gamer("Alex", set1);
        site.register(gamer10);
        site.addPoints(gamer10, "CS", 50);
        site.addPoints(gamer10, "StarCraft", 50);

        Gamer gamer11 = new Gamer("John", set1);
        site.register(gamer11);
        site.addPoints(gamer11, "CS", 55);
        site.addPoints(gamer11, "StarCraft", 55);

        Gamer gamer12 = new Gamer("Katy", set1);
        site.register(gamer12);
        site.addPoints(gamer12, "CS", 60);
        site.addPoints(gamer12, "StarCraft", 60);

        site.printTenBestGamers("CS");
        System.out.println();
        site.printTenBestGamersInAllGames();

    }
}
