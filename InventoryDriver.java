
public class InventoryDriver {

    public static void main(String[] args) {
        CharacterImplementation user = new CharacterImplementation();
        int uInput = 0;

        do{
            int answer = user.userInput();
            if (answer == 0) {
                user.printInventory();
            }
            else if (answer == 1) {
                user.userItem();
            }
            else if (answer == 2) {
                user.userArmor();
            }
            else if (answer == 3) {
                user.userWeapon();
            }
            else if (answer == 4) {
                user.userOptimizeInventory();
            }
            else if (answer == 5) {
                int i = 0;
                while(i <= user.numberOfItems()){//Goes through the whole invenotry list and equips the best
                    user.optimizeEquipment();
                    i++;
                }
                user.printInventory();
            }
            else if (answer == 6) {
                uInput = answer;
            }
        }
        while(uInput != 6);
    }

}
