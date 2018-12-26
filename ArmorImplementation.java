public class ArmorImplementation implements Armor {
    private int slot;
    private int rating;
    private String name;
    private int goldValue;
    private double weight;

    public ArmorImplementation(int slot, int rating, String name, int goldValue, double weight){
        this.slot = slot;
        this.rating = rating;
        this.name = name;
        this.goldValue = goldValue;
        this.weight = weight;
        if(rating < 0){
            this.rating = 0;
        }
        if(goldValue < 0){
            this.goldValue = 0;
        }
        if(weight < 0){
            this.weight = 0;
        }
    }


    @Override
    public int getSlot() {
        return slot;
    }

    @Override
    public int getRating() {
        return rating;
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
        String ar = " AR";
        String formatted = String.format("%-15s %-15d %-15.1f %-15.1f %d %s" ,getName(),getGoldValue(),getWeight(),getValueWeightRatio(), getRating(), ar);
        return formatted;
    }
}
