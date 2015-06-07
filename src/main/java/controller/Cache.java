package controller;

import model.Key;

import java.util.Map;
import java.util.concurrent.*;
/**
 * Реализует Кэширование
 * @author Aleksandrov Oleg
 * @version 0.1
 */
public class Cache<K,V> {
    private static Cache ourInstance = new Cache();
    private boolean isInit = false;

    public static Cache getInstance() {
        return ourInstance;
    }

    private Cache() {
    }

    private volatile Map<Key,V> cacheMap = new ConcurrentHashMap<Key,V>();
    private long timeOut;
    private ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor(/*new ThreadFactory() {
        public Thread newThread(Runnable r) {
            Thread th = new Thread(r);
            th.setDaemon(true);
            return th;
        }
    }*/);

    /**
     * Инициализация кэша
     * @param timeOut
     * @return
     */
    public Cache init(long timeOut) {
        if(!isInit) {
            this.timeOut = timeOut;
            scheduler.scheduleWithFixedDelay(new Runnable() {
                public void run() {
                    for (Key key : cacheMap.keySet()) {
                        long time = System.currentTimeMillis();
                        if (key.isNotALive(time)) {
                            cacheMap.remove(key);
                        }
                    }
                }
            }, 0, getWaitTime(timeOut), TimeUnit.MILLISECONDS);
            isInit = true;
        }
        return ourInstance;
    }

    private long getWaitTime(long timeOut) {
        return timeOut/5;
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
        return cacheMap.get(new Key(key));
    }

    /**
     * количество элементов в кэше
     * @return
     */
    public int size() {
        return cacheMap.size();
    }

    public boolean isContains(K key) {
        return cacheMap.containsKey(new Key(key));
    }

    /**
     * удаление элемента их кэша по ключу
     * @param key
     */
    public void remove(K key) {
        cacheMap.remove(new Key(key));
    }

    /**
     * Удаление всех данных их кэша
     */
    public void clearAll() {
        cacheMap.clear();
    }

}
