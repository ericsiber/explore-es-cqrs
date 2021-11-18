package fr.annuaire;

import java.util.Objects;

public class CompteIdentifier {

    private final String value;

    private CompteIdentifier(String value) {
        this.value = value;
    }

    public static CompteIdentifier of(String value) {
        return new CompteIdentifier(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompteIdentifier that = (CompteIdentifier) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
