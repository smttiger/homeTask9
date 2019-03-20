package by.itstep.fabiyanski.site;

import java.util.Objects;
import java.util.Set;

public class Gamer {
    private String name;
    private Set<String> listOfGames;

    public Gamer(String name, Set<String> listOfGames) {
        this.name = name;
        this.listOfGames = listOfGames;
    }

    public String getName() {
        return name;
    }

    public Set<String> getListOfGames() {
        return listOfGames;
    }

    @Override
    public String toString() {
        return "{" +
                name +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gamer gamer = (Gamer) o;
        return Objects.equals(getName(), gamer.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
