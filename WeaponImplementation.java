public class WeaponImplementation implements Weapon {
    private int damage;
    private String name;
    private int goldValue;
    private double weight;

    public WeaponImplementation(int damage, String name, int goldValue, double weight){
        this.damage = damage;
        this.name = name;
        this.goldValue = goldValue;
        this.weight = weight;
        if(damage < 0){
            this.damage = 0;
        }
        if(goldValue < 0){
            this.goldValue = 0;
        }
        if(weight < 0){
            this.weight = 0;
        }
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getGoldValue() {
        return goldValue;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public double getValueWeightRatio() {
        return (double) goldValue/weight;
    }

    public String toString(){
        String damage = "DMG";
        String formatted = String.format("%-15s %-15d %-15.1f %-15.1f %d %s" ,getName(),getGoldValue(),getWeight(),getValueWeightRatio(), getDamage(), damage);
        return formatted;
    }
}
