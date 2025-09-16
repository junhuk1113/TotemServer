package net.pmkjun.totemserver;

import com.google.gson.Gson;

import java.util.ArrayList;

public class TotemDB {
    private static TotemDB instance;

    private ArrayList<String> username = new ArrayList<>();
    private ArrayList<Integer> valueTotemCooldown = new ArrayList<>();
    private ArrayList<Integer> valueTotemActiveTime = new ArrayList<>();
    private ArrayList<Integer> valueTotemRange = new ArrayList<>();
    private ArrayList<Long> lastTotemtime = new ArrayList<>();
    private ArrayList<Long> lastTotemCooldownTime = new ArrayList<>();
    private ArrayList<Integer> totem_X = new ArrayList<>();
    private ArrayList<Integer> totem_Y = new ArrayList<>();
    private ArrayList<Integer> totem_Z = new ArrayList<>();
    private ArrayList<String> totemWorld = new ArrayList<>();
    private ArrayList<String> shareTotemPercentage = new ArrayList<>();
    private ArrayList<Boolean> isMythicalWaterActive = new ArrayList<>();
    private ArrayList<Boolean> isExpBoosterActive = new ArrayList<>();
    private ArrayList<Boolean> isOverHotspotActive = new ArrayList<>();
    private ArrayList<Boolean> isTreasureHunterActive = new ArrayList<>();
    private ArrayList<Boolean> isEntropyHoarder = new ArrayList<>();
    private ArrayList<String> totemSlotString = new ArrayList<>();

    private final Gson gson = new Gson();

    public TotemDB() {
        instance = this;
    }

    public int addTotemData(String username, int valueTotemCooldown, int valueTotemActiveTime, int valueTotemRange, long lastTotemtime,
                            long lastTotemCooldownTime,int totem_X, int totem_Z, String totemWorld, String shareTotemPercentage,
                            boolean isMythicalWaterActive, boolean isExpBoosterActive, boolean isOverHotspotActive,
                            boolean isTreasureHunterActive, boolean isEntropyHoarder, String totemSlotString){
        if(this.username.contains(username)){
            this.valueTotemCooldown.set(this.username.indexOf(username), valueTotemCooldown);
            this.valueTotemActiveTime.set(this.username.indexOf(username), valueTotemActiveTime);
            this.valueTotemRange.set(this.username.indexOf(username), valueTotemRange);
            this.lastTotemtime.set(this.username.indexOf(username), lastTotemtime);
            this.lastTotemCooldownTime.set(this.username.indexOf(username), lastTotemCooldownTime);
            this.totem_X.set(this.username.indexOf(username), totem_X);
            this.totem_Z.set(this.username.indexOf(username), totem_Z);
            this.totemWorld.set(this.username.indexOf(username), totemWorld);
            this.shareTotemPercentage.set(this.username.indexOf(username), shareTotemPercentage);
            this.isMythicalWaterActive.set(this.username.indexOf(username), isMythicalWaterActive);
            this.isExpBoosterActive.set(this.username.indexOf(username), isExpBoosterActive);
            this.isOverHotspotActive.set(this.username.indexOf(username), isOverHotspotActive);
            this.isTreasureHunterActive.set(this.username.indexOf(username), isTreasureHunterActive);
            this.isEntropyHoarder.set(this.username.indexOf(username), isEntropyHoarder);
            this.totemSlotString.set(this.username.indexOf(username), totemSlotString);
            return 1;
        }
        this.username.add(username);
        this.valueTotemCooldown.add(valueTotemCooldown);
        this.valueTotemActiveTime.add(valueTotemActiveTime);
        this.valueTotemRange.add(valueTotemRange);
        this.lastTotemtime.add(lastTotemtime);
        this.lastTotemCooldownTime.add(lastTotemCooldownTime);
        this.totem_X.add(totem_X);
        this.totem_Z.add(totem_Z);
        this.totemWorld.add(totemWorld);
        this.shareTotemPercentage.add(shareTotemPercentage);
        this.isMythicalWaterActive.add(isMythicalWaterActive);
        this.isExpBoosterActive.add(isExpBoosterActive);
        this.isOverHotspotActive.add(isOverHotspotActive);
        this.isTreasureHunterActive.add(isTreasureHunterActive);
        this.isEntropyHoarder.add(isEntropyHoarder);
        this.totemSlotString.add(totemSlotString);

        return 0;
    }

    public String getTotemData(String username){
        for(int i = 0; i < this.username.size(); i++){
            if(this.username.get(i).equals(username)){
                return gson.toJson(new TotemData(this.username.get(i), this.valueTotemCooldown.get(i), this.valueTotemActiveTime.get(i),
                        this.valueTotemRange.get(i), this.lastTotemtime.get(i), this.lastTotemCooldownTime.get(i),this.totem_X.get(i),
                        this.totem_Z.get(i), this.totemWorld.get(i), this.shareTotemPercentage.get(i), this.isMythicalWaterActive.get(i),
                        this.isExpBoosterActive.get(i), this.isOverHotspotActive.get(i), this.isTreasureHunterActive.get(i),
                        this.isEntropyHoarder.get(i), this.totemSlotString.get(i)));
            }
        }
        return null;
    }

    public String getTotemData(int index){
        int i;
        i = index;
        return gson.toJson(new TotemData(this.username.get(i), this.valueTotemCooldown.get(i), this.valueTotemActiveTime.get(i),
                this.valueTotemRange.get(i), this.lastTotemtime.get(i), this.lastTotemCooldownTime.get(i),this.totem_X.get(i),
                this.totem_Z.get(i), this.totemWorld.get(i), this.shareTotemPercentage.get(i), this.isMythicalWaterActive.get(i),
                this.isExpBoosterActive.get(i), this.isOverHotspotActive.get(i), this.isTreasureHunterActive.get(i),
                this.isEntropyHoarder.get(i), this.totemSlotString.get(i)));
    }

    public String findTotemData(double playerX, double playerZ, String playerWorld){
        ArrayList<TotemData> totemDataList = new ArrayList<>();
        TotemData totemData;

        for (int i = 0; i < this.totem_X.size(); i++) {
            if(!this.totemWorld.get(i).equals(playerWorld)){
                continue;
            }
            double dx = totem_X.get(i) - playerX;
            double dz = totem_Z.get(i) - playerZ;
            double distanceSquared = dx * dx + dz * dz;
            double rangeSquared = valueTotemRange.get(i) * valueTotemRange.get(i);

            if (distanceSquared <= rangeSquared) {
                totemData = new TotemData(this.username.get(i), this.valueTotemCooldown.get(i), this.valueTotemActiveTime.get(i),
                        this.valueTotemRange.get(i), this.lastTotemtime.get(i), this.lastTotemCooldownTime.get(i),this.totem_X.get(i),
                        this.totem_Z.get(i), this.totemWorld.get(i), this.shareTotemPercentage.get(i), this.isMythicalWaterActive.get(i),
                        this.isExpBoosterActive.get(i), this.isOverHotspotActive.get(i), this.isTreasureHunterActive.get(i),
                        this.isEntropyHoarder.get(i), this.totemSlotString.get(i));
                if(!totemData.isExpired()){
                    totemDataList.add(totemData);
                }
                else{
                    removeTotemData(this.username.get(i));
                }

                //return "username: " + this.username.get(i) + "\n" + "valueTotemCooldown: " + this.valueTotemCooldown.get(i) + "\n" + "valueTotemActiveTime: " + this.valueTotemActiveTime.get(i) + "\n" + "valueTotemRange: " + this.valueTotemRange.get(i) + "\n" + "lastTotemtime: " + this.lastTotemtime.get(i) + "\n" + "totem_X: " + this.totem_X.get(i) + "\n" + "totem_Z: " + this.totem_Z.get(i);

            }
        }

        if(!totemDataList.isEmpty()){
            Gson gson = new Gson();
            return gson.toJson(totemDataList);
        }
        return null;
    }

    public int reduceTotemCooldown(String username, long lastTotemCooldownTime){
        if(this.username.contains(username)){
            int i = this.username.indexOf(username);
            this.lastTotemCooldownTime.set(i, lastTotemCooldownTime);
            return 1;
        }
        else{
            return 0;
        }
    }

    public int removeTotemData(String username){
            if(this.username.contains(username)){
                int i = this.username.indexOf(username);
                this.username.remove(i);
                this.valueTotemCooldown.remove(i);
                this.valueTotemActiveTime.remove(i);
                this.valueTotemRange.remove(i);
                this.lastTotemtime.remove(i);
                this.lastTotemCooldownTime.remove(i);
                this.totem_X.remove(i);
                this.totem_Z.remove(i);
                this.totemWorld.remove(i);
                this.shareTotemPercentage.remove(i);
                this.isMythicalWaterActive.remove(i);
                this.isExpBoosterActive.remove(i);
                this.isOverHotspotActive.remove(i);
                this.isTreasureHunterActive.remove(i);
                this.isEntropyHoarder.remove(i);
                this.totemSlotString.remove(i);
                return 1;
            }
            return 0;
    }

    public int size(){
        return this.username.size();
    }

    public static TotemDB getInstance() {
        return instance;
    }

    public ArrayList<Boolean> getIsEntropyHoarder() {
        return isEntropyHoarder;
    }
}
