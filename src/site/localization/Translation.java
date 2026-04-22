package site.localization;

import java.util.Objects;

public class Translation implements Comparable<Translation> {

    private final String key;
    private String value;
    private String updated;

    public Translation(String key, String value, String updated) {
        this.key = key;
        this.value = value;
        this.updated = updated;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public String getTranslated() {
        return this.updated;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getUpdated() {
        return this.updated;
    }

    @Override
    public int compareTo(Translation other) {
        return this.key.compareTo(other.key);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (!(o instanceof Translation)) {
            return false;
        }
        Translation ds = (Translation) o;
        return ds.getKey().equals(this.getKey());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getKey());
    }

}
