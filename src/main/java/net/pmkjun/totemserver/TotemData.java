package net.pmkjun.totemserver;

public class TotemData {
    public String username ;
    public int valueTotemCooldown ;
    public int valueTotemActiveTime ;
    public int valueTotemRange ;
    public long lastTotemtime ;
    public long lastTotemCooldownTime;
    public int totem_X ;
    public int totem_Z ;
    public String totemWorld ;
    public String shareTotemPercentage;
    public boolean isMythicalWaterActive = false;
    public boolean isExpBoosterActive = false;
    public boolean isOverHotspotActive = false;
    public boolean isTreasureHunterActive = false;
    public boolean isEntropyHoarder = false;
    public String totemSlotString;

    public TotemData(String username, int valueTotemCooldown, int valueTotemActiveTime, int valueTotemRange, long lastTotemtime,
                     long lastTotemCooldownTime, int totem_X, int totem_Z, String totemWorld, String shareTotemPercentage,
                     boolean isMythicalWaterActive, boolean isExpBoosterActive, boolean isOverHotspotActive,
                     boolean isTreasureHunterActive, boolean isEntropyHoarder, String totemSlotString){
        this.username = username;
        this.valueTotemCooldown = valueTotemCooldown;
        this.valueTotemActiveTime = valueTotemActiveTime;
        this.valueTotemRange = valueTotemRange;
        this.lastTotemtime = lastTotemtime;
        this.lastTotemCooldownTime = lastTotemCooldownTime;
        this.totem_X = totem_X;
        this.totem_Z = totem_Z;
        this.totemWorld = totemWorld;
        this.shareTotemPercentage = shareTotemPercentage;
        this.isMythicalWaterActive = isMythicalWaterActive;
        this.isExpBoosterActive = isExpBoosterActive;
        this.isOverHotspotActive = isOverHotspotActive;
        this.isTreasureHunterActive = isTreasureHunterActive;
        this.isEntropyHoarder = isEntropyHoarder;
        this.totemSlotString = totemSlotString;
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
