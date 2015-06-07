package controller;

import model.Key;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class CacheBased<V,K> {
    protected volatile Map<Key,V> cacheMap;
    protected abstract Map<Key,V> initMap();
    protected long timeOut;

    public CacheBased() {
        cacheMap = initMap();
    }

    /**
     * Добавление новых значений в кэш
     * @param key
     * @param value
     */
    public void put(K key, V value) {
        cacheMap.put(new Key(key,timeOut), value);
    }

    /**
     * получение значений их кэша по ключу
     * @param key
     * @return
     */
    public V get(K key) {
        return cacheMap.get(new Key(key,0));
    }

    /**
     * количество элементов в кэше
     * @return
     */
    public int size() {
        return cacheMap.size();
    }

    public boolean isContains(K key) {
        return cacheMap.containsKey(new Key(key,0));
    }

    /**
     * удаление элемента их кэша по ключу
     * @param key
     */
    public void remove(K key) {
        cacheMap.remove(new Key(key,0));
    }

    /**
     * Удаление всех данных их кэша
     */
    public void clearAll() {
        cacheMap.clear();
    }

    private long getWaitTime(long timeOut) {
        return timeOut/5;
    }
}
