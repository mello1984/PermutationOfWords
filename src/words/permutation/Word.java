package words.permutation;

import java.util.Objects;

class Word {
    private final String string;
    private final char firstLetter;
    private final char lastLetter;

    Word(String string) {
        this.string = string;
        firstLetter = Character.toLowerCase(string.charAt(0));
        lastLetter = Character.toLowerCase(string.charAt(string.length() - 1));
    }

    String getString() {
        return string;
    }

    char getFirstLetter() {
        return firstLetter;
    }

    char getLastLetter() {
        return lastLetter;
    }

    String toFullString() {
        return "Word{" +
                "string='" + string + '\'' +
                ", firstLetter=" + firstLetter +
                ", lastLetter=" + lastLetter +
                '}';
    }

    @Override
    public String toString() {
        return string;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word = (Word) o;
        return string.equals(word.string);
    }

    @Override
    public int hashCode() {
        return Objects.hash(string);
    }
}