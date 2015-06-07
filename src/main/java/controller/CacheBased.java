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
     * ���������� ����� �������� � ���
     * @param key
     * @param value
     */
    public void put(K key, V value) {
        cacheMap.put(new Key(key,timeOut), value);
    }

    /**
     * ��������� �������� �� ���� �� �����
     * @param key
     * @return
     */
    public V get(K key) {
        return cacheMap.get(new Key(key,0));
    }

    /**
     * ���������� ��������� � ����
     * @return
     */
    public int size() {
        return cacheMap.size();
    }

    public boolean isContains(K key) {
        return cacheMap.containsKey(new Key(key,0));
    }

    /**
     * �������� �������� �� ���� �� �����
     * @param key
     */
    public void remove(K key) {
        cacheMap.remove(new Key(key,0));
    }

    /**
     * �������� ���� ������ �� ����
     */
    public void clearAll() {
        cacheMap.clear();
    }

    private long getWaitTime(long timeOut) {
        return timeOut/5;
    }
}
