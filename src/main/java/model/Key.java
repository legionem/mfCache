package model;

/**
 * Реализует Обертку для ключа кэша, добаляя к объекту параметр времени создания объекта
 * @author Aleksandrov Oleg
 * @version 0.1
 */
public class Key {
    private final Object key;
    private final long timeLife;

    public Key(Object key, long timeLife) {
        this.key = key;
        this.timeLife = timeLife + System.currentTimeMillis();
    }

    public Key(Object key) {
        this.key = key;
        timeLife = 0;
    }

    public Object getKey() {
        return key;
    }
    public boolean isNotALive(Long time) {
        return time > timeLife;
    }

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

        Key key1 = (Key) o;

        return !(key != null ? !key.equals(key1.key) : key1.key != null);

    }

    @Override
    public int hashCode() {
        return key != null ? key.hashCode() : 0;
    }
}
