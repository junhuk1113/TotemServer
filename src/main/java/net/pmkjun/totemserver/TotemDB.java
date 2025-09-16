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

    private final Gson gson = new Gson();

    public TotemDB() {
        instance = this;
    }

    public int addTotemData(String username, int valueTotemCooldown, int valueTotemActiveTime, int valueTotemRange, long lastTotemtime, long lastTotemCooldownTime,int totem_X, int totem_Z, String totemWorld){
        if(this.username.contains(username)){
            this.valueTotemCooldown.set(this.username.indexOf(username), valueTotemCooldown);
            this.valueTotemActiveTime.set(this.username.indexOf(username), valueTotemActiveTime);
            this.valueTotemRange.set(this.username.indexOf(username), valueTotemRange);
            this.lastTotemtime.set(this.username.indexOf(username), lastTotemtime);
            this.lastTotemCooldownTime.set(this.username.indexOf(username), lastTotemCooldownTime);
            this.totem_X.set(this.username.indexOf(username), totem_X);
            this.totem_Z.set(this.username.indexOf(username), totem_Z);
            this.totemWorld.set(this.username.indexOf(username), totemWorld);
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

        return 0;
    }

    public String getTotemData(String username){
        for(int i = 0; i < this.username.size(); i++){
            if(this.username.get(i).equals(username)){
                return gson.toJson(new TotemData(this.username.get(i), this.valueTotemCooldown.get(i), this.valueTotemActiveTime.get(i), this.valueTotemRange.get(i), this.lastTotemtime.get(i), this.lastTotemCooldownTime.get(i),this.totem_X.get(i), this.totem_Z.get(i), this.totemWorld.get(i)));
            }
        }
        return null;
    }

    public String getTotemData(int index){
        int i;
        i = index;
        return gson.toJson(new TotemData(this.username.get(i), this.valueTotemCooldown.get(i), this.valueTotemActiveTime.get(i), this.valueTotemRange.get(i), this.lastTotemtime.get(i), this.lastTotemCooldownTime.get(i),this.totem_X.get(i), this.totem_Z.get(i), this.totemWorld.get(i)));
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
                        this.valueTotemRange.get(i), this.lastTotemtime.get(i), this.lastTotemCooldownTime.get(i),this.totem_X.get(i), this.totem_Z.get(i), this.totemWorld.get(i));
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

}
