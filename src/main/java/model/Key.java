package model;

/**
 * ��������� ������� ��� ����� ����, ������� � ������� �������� ������� �������� �������
 * @author Aleksandrov Oleg
 * @version 0.1
 */
public class Key extends KeyBased {
    private final long timeLife;

    public Key(Object key, long timeLife) {
        super(key);
        this.timeLife = timeLife + System.currentTimeMillis();
    }

    @Override
    public boolean isNotALive() {
        return System.currentTimeMillis() >= timeLife;
    }
}
