package model;

public abstract class KeyBased {
    protected final Object key;

    public KeyBased(Object key) {
        this.key = key;
    }

    public Object getKey() {
        return key;
    }

    public abstract boolean isNotALive();


    @Override
    public String toString() {
        return "Key{" +
                "key=" + key +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KeyBased keyBased = (KeyBased) o;

        return !(key != null ? !key.equals(keyBased.key) : keyBased.key != null);

    }

    @Override
    public int hashCode() {
        return key != null ? key.hashCode() : 0;
    }
}
