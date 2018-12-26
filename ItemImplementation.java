public class ItemImplementation implements Item{
    private String name;
    private int goldValue;
    private double weight;

    public ItemImplementation(String name, int goldValue, double weight){
        this.name = name;
        this.goldValue = goldValue;
        this.weight = weight;
        if(goldValue < 0){
            this.goldValue = 0;
        }
        if(weight < 0){
            this.weight = 0;
        }
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
        String formatted = String.format("%-15s %-15d %-15.1f %-15.1f" ,getName(),getGoldValue(),getWeight(),getValueWeightRatio());
        return formatted;
    }
}
