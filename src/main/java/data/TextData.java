package data;

import java.util.Objects;

public class TextData {
    private String data;

    public TextData(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TextData TextData = (TextData) o;
        return Objects.equals(data, TextData.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data);
    }

    @Override
    public String toString() {
        return data;
    }

    public String getText() {
        return data;
    }
}
