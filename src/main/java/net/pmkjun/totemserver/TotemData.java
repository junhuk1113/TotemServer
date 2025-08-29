package net.pmkjun.totemserver;

public class TotemData {
    private String username ;
    private int valueTotemCooldown ;
    private int valueTotemActiveTime ;
    private int valueTotemRange ;
    private long lastTotemtime ;
    private int totem_X ;
    private int totem_Z ;
    private String totemWorld ;

    public TotemData(String username, int valueTotemCooldown, int valueTotemActiveTime, int valueTotemRange, long lastTotemtime, int totem_X, int totem_Z, String totemWorld){
        this.username = username;
        this.valueTotemCooldown = valueTotemCooldown;
        this.valueTotemActiveTime = valueTotemActiveTime;
        this.valueTotemRange = valueTotemRange;
        this.lastTotemtime = lastTotemtime;
        this.totem_X = totem_X;
        this.totem_Z = totem_Z;
        this.totemWorld = totemWorld;
    }

    public boolean isExpired(){
        return System.currentTimeMillis() - this.lastTotemtime >= (this.valueTotemActiveTime+ this.valueTotemCooldown) * 1000 * 60L;
    }
}
