package net.pmkjun.totemserver;

public class TotemData {
    private String username ;
    private int valueTotemCooldown ;
    private int valueTotemActiveTime ;
    private int valueTotemRange ;
    private long lastTotemtime ;
    private long lastTotemCooldownTime;
    private int totem_X ;
    private int totem_Z ;
    private String totemWorld ;

    public TotemData(String username, int valueTotemCooldown, int valueTotemActiveTime, int valueTotemRange, long lastTotemtime, long lastTotemCooldownTime, int totem_X, int totem_Z, String totemWorld){
        this.username = username;
        this.valueTotemCooldown = valueTotemCooldown;
        this.valueTotemActiveTime = valueTotemActiveTime;
        this.valueTotemRange = valueTotemRange;
        this.lastTotemtime = lastTotemtime;
        this.lastTotemCooldownTime = lastTotemCooldownTime;
        this.totem_X = totem_X;
        this.totem_Z = totem_Z;
        this.totemWorld = totemWorld;
    }

    public boolean isExpired(){
        if(System.currentTimeMillis() > this.lastTotemCooldownTime){
            return  0 > this.valueTotemCooldown * 60 - (int) this.getDifference(this.lastTotemCooldownTime);
        }
        return false;
    }

    private float getDifference(long time) {
        return (float)(System.currentTimeMillis() - time) / 1000.0F;
    }
}
