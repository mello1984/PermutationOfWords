package words.permutation;

import java.util.Objects;

class Word {
    private String string;
    private char firstLetter;
    private char lastLetter;
    private boolean differentLetters;

    Word(String string) {
        this.string = string;
        firstLetter = Character.toLowerCase(string.charAt(0));
        lastLetter = Character.toLowerCase(string.charAt(string.length() - 1));
        differentLetters = firstLetter != lastLetter;
    }

    Word join(Word word) throws WordException {
        if (lastLetter == word.firstLetter) return new Word(String.join(" ", this.string, word.string));
        else if (firstLetter == word.lastLetter) return new Word(String.join(" ", word.string, this.string));
        else throw new WordException(String.format("Can't join words: '%s' and '%s'", this.string, word.string));
    }

    public String getString() {
        return string;
    }

    public char getFirstLetter() {
        return firstLetter;
    }

    public char getLastLetter() {
        return lastLetter;
    }

    public boolean isDifferentLetters() {
        return differentLetters;
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

    class WordException extends Exception {
        public WordException(String message) {
            super(message);
        }
    }
}