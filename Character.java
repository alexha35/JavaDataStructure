import java.util.Iterator;

public interface Character {
    Iterator<Item> inventory();
    void addItem(Item itemTemplate);
    void dropItem(Item item);
    double getTotalWeight();
    
    Armor getEquippedArmor(int slot);
    int getTotalArmorRating();
    void equipArmor(Armor armor);
    void unequipArmor(int slot);
    
    Weapon getEquippedWeapon();
    void equipWeapon(Weapon weapon);
    void unequipWeapon();
    
    void optimizeInventory(double maximumWeight);
    void optimizeEquipment();
}
