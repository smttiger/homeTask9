package by.itstep.fabiyanski.site;


import by.itstep.fabiyanski.site.exceptions.GamerAlreadyExistsException;
import by.itstep.fabiyanski.site.exceptions.GamerDoesntPlaysThisGameException;
import by.itstep.fabiyanski.site.exceptions.GamerIsNotRegisteredOnTheSiteException;

public interface GamerSite {


    void register(Gamer gamer) throws GamerAlreadyExistsException;

    void addPoints(Gamer gamer, String game, int winPoints) throws GamerDoesntPlaysThisGameException;

    void printListOfGamesEverybodyPlays();

    void printWholeGameList();

    void printRating(String name, String Game) throws GamerDoesntPlaysThisGameException, GamerIsNotRegisteredOnTheSiteException;

    void printTenBestGamers(String game) throws GamerDoesntPlaysThisGameException, GamerIsNotRegisteredOnTheSiteException;

    void printTenBestGamersInAllGames();
}
