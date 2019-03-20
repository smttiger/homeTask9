package by.itstep.fabiyanski.site;


import by.itstep.fabiyanski.site.exceptions.GamerAlreadyExistsException;
import by.itstep.fabiyanski.site.exceptions.GamerDoesntPlaysThisGameException;
import by.itstep.fabiyanski.site.exceptions.GamerIsNotRegisteredOnTheSiteException;

import java.util.*;

public class TheGamerSite implements GamerSite {

    private Map<Gamer, Map<String, Integer>> allGamers = new HashMap<>(); // Map< игрок, Map<название игры,очки в этой игре>>

    public Map<Gamer, Map<String, Integer>> getAllGamers() {
        return allGamers;
    }

    public void register(Gamer gamer) throws GamerAlreadyExistsException {
        if (allGamers.containsKey(gamer)) {
            System.out.println("The gamer with such nickname is already exists!");
            throw new GamerAlreadyExistsException();
        } else {
            Map<String, Integer> mapOfGamesAndPoints = new HashMap<String, Integer>();
            for (String element : gamer.getListOfGames()) {
                mapOfGamesAndPoints.put(element, 0);
            }
            allGamers.put(gamer, mapOfGamesAndPoints);
        }

    }

    public void addPoints(Gamer gamer, String game, int winPoints) throws GamerDoesntPlaysThisGameException { //правильнее добавлять очки за выйгрыш
        Map value = allGamers.get(gamer);
        if (!value.containsKey(game)) {
            System.out.println("The gamer doesn't plays this game!");
            throw new GamerDoesntPlaysThisGameException();
        } else {
            int points = (int) value.get(game);
            value.put(game, points + winPoints);
        }
    }

    public void printListOfGamesEverybodyPlays() {//выводит список игр, в каждую из которых играет любой игрок на сайте
        Set<String> allGames = new HashSet<>();
        List<Gamer> gamerList = new ArrayList(allGamers.keySet());
        Gamer gamer = gamerList.get(0);
        allGames.addAll(gamer.getListOfGames());
        gamerList.forEach((item) -> allGames.retainAll(item.getListOfGames()));
        if (allGames.isEmpty()) System.out.println("There are no such games ");
        else
            System.out.println("The list of games everybody plays: " + allGames);
    }


    public void printWholeGameList() { //выводит общий список игр
        Set<String> allGames = new HashSet<>();
        List<Gamer> gamerList = new ArrayList(allGamers.keySet());
        gamerList.forEach((item) -> allGames.addAll(item.getListOfGames()));
        if (allGames.isEmpty()) System.out.println("The list of games is empty  ");
        else
            System.out.println("The whole list of games : " + allGames);
    }

    public void printRating(String name, String game) throws GamerDoesntPlaysThisGameException, GamerIsNotRegisteredOnTheSiteException {
        System.out.println("Rating of " + name + " in " + game + " = " + getRating(name, game));
    }

    private int getRating(String name, String game) throws GamerDoesntPlaysThisGameException, GamerIsNotRegisteredOnTheSiteException {
        // логика построена так, что у игрока с наибольшим количеством очков рейтинг равен 1
        int thisGamePoints = -1;
        int rating;
        List<Integer> allGamersThisGamePoints = new ArrayList<>();
        for (Map.Entry<Gamer, Map<String, Integer>> entry : allGamers.entrySet()) {
            Gamer gamer = entry.getKey();
            Map<String, Integer> map = entry.getValue();
            if ((gamer.getName() == name) && (map.containsKey(game)))
                thisGamePoints = map.get(game);
            else if (!map.containsKey(game)) {
                System.out.println("The gamer doesn't plays this game!");
                throw new GamerDoesntPlaysThisGameException();
            }
            allGamersThisGamePoints.add(map.get(game));
        }
        if (thisGamePoints == -1) {
            System.out.println("This gamer is not registered on the site!");
            throw new GamerIsNotRegisteredOnTheSiteException();
        } else {
            Collections.sort(allGamersThisGamePoints);
            rating = allGamersThisGamePoints.size() - allGamersThisGamePoints.indexOf(thisGamePoints);
        }
        return rating;
    }

    public void printTenBestGamers(String game) throws GamerDoesntPlaysThisGameException, GamerIsNotRegisteredOnTheSiteException {
        Map<Integer, Gamer> tenBestPlayers = new HashMap<>();
        for (Map.Entry<Gamer, Map<String, Integer>> entry : allGamers.entrySet()) {
            Gamer gamer = entry.getKey();
            tenBestPlayers.put(getRating(gamer.getName(), game), gamer);
        }
        System.out.println("Ten best gamers in " + game + ":");
        for (int i = 1; i <= 10; i++) {
            if (tenBestPlayers.containsKey(i)) {
                Gamer gamer = tenBestPlayers.get(i);
                System.out.println(i + " " + gamer.getName() + " (number of points=" + allGamers.get(gamer).get(game) + ")");
            }
        }
    }

    public void printTenBestGamersInAllGames() {
        Map<Integer, String> totalPointsOfGamers = new HashMap<>();
        List<Integer> pointsOfAllGamers = new ArrayList<>();
        for (Map.Entry<Gamer, Map<String, Integer>> entry : allGamers.entrySet()) {
            Gamer gamer = entry.getKey();
            Map<String, Integer> map = entry.getValue();
            List<Integer> gamerPoints = new ArrayList<>(map.values());
            int sum = 0;
            for (Integer points : gamerPoints) sum = sum + points;
            totalPointsOfGamers.put(sum, gamer.getName());
            pointsOfAllGamers.add(sum);
        }
        Collections.sort(pointsOfAllGamers);
        System.out.println("Ten best gamers considering all games:");
        for (int i = pointsOfAllGamers.size() - 1; i >= pointsOfAllGamers.size() - 10; i--) {
            int rating = pointsOfAllGamers.size() - i;
            System.out.println(rating + " " + totalPointsOfGamers.get(pointsOfAllGamers.get(i)) + " (number of points= " + pointsOfAllGamers.get(i) + ")");
        }
    }
}

