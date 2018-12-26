/*
Name: Alex Ha
ID: 4570526
x500: haxxx185
 */

import java.util.Iterator;
import java.util.Scanner;

public class CharacterImplementation implements Character {
    private LinkedList inventoryList;
    private boolean[] armorSlots = new boolean[6];//Returns true or false if there is armor equipped
    private Armor[] armorType = new Armor[6]; //Returns the armor
    private boolean[] weaponSlot = new boolean[1];//Returns true or false if there is a weapon equipped
    private Weapon[] weaponType = new Weapon[1];//Type of weapon equipped

    public CharacterImplementation() {
        inventoryList = new LinkedList();
    }

    @Override
    public Iterator<Item> inventory() {
        inventoryList.sort(new LinkedList.CompareTo());
        return inventoryList.iterator();
    }

    public void menu() {
        System.out.println("=======================================");
        System.out.println("Welcome to RPG Inventory Manager!");
        System.out.println("=======================================");
        System.out.println();
        System.out.println("Select an option:");
        System.out.println("0: Print Inventory");
        System.out.println("1: Add Unequippable Item");
        System.out.println("2: Add Armor");
        System.out.println("3: Add Weapon");
        System.out.println("4: Optimize Inventory");
        System.out.println("5: Optimize Equipment");
        System.out.println("6: Quit");
        System.out.println("=======================================");
        System.out.println();
    }//UI Prints out the menu

    public int userInput() {
        boolean validAnswer = false;
        int num = 0;

        while (!validAnswer) {
            menu();
            Scanner scan = new Scanner(System.in);
            num = 0;
            try{
                int number = scan.nextInt();

                if (number > 6 || number < 0) {
                    System.out.println("Invalid input2");
                    System.out.println();
                    validAnswer = false;
                } else {
                    num = number;
                    validAnswer = true;
                }
            }
            catch (Exception e){
                validAnswer = false;
            }
        }
        return num;
    }//takes user input

    public void userItem() {
        Scanner scan = new Scanner(System.in);
        boolean validEntry = false;

        while (!validEntry) {
            try {
                System.out.print("Enter item name: ");
                String name = scan.nextLine();
                System.out.print("Enter item weight: ");
                double weight = scan.nextDouble();
                System.out.print("Enter item value: ");
                int value = scan.nextInt();
                ItemImplementation newItem = new ItemImplementation(name, value, weight);
                addItem(newItem);
                validEntry = true;
            } catch (Exception e) {
                scan.nextLine();
                System.out.println("Invalid input3");
                validEntry = false;
            }
        }
    }//UI add item

    public void userArmor() {
        Scanner scan = new Scanner(System.in);
        boolean validEntry = false;

        while (!validEntry) {
            try {//add catch for numbers not 0-5
                System.out.println("Enter armor slot: 0=chest, 1=legs, 2=hands, 3=feet, 4=helmet, 5=shield");
                int slot = scan.nextInt();
                if(slot > 5 || slot < 0){
                    scan.nextLine();
                    System.out.println("Please enter a number 0-5 inclusively");
                    validEntry = false;
                }
                else{
                    scan.nextLine();
                    System.out.print("Enter armor name: ");
                    String name = scan.nextLine();
                    System.out.print("Enter armor rating: ");
                    int rating = scan.nextInt();
                    System.out.print("Enter armor weight: ");
                    double weight = scan.nextDouble();
                    System.out.print("Enter armor value: ");
                    int value = scan.nextInt();
                    ArmorImplementation newArmor = new ArmorImplementation(slot, rating, name, value, weight);
                    addItem(newArmor);
                    validEntry = true;
                }

            } catch (Exception e) {
                scan.nextLine();
                System.out.println("Invalid input4");
                validEntry = false;
            }
        }
    }//UI add Armor

    public void userWeapon() {
        Scanner scan = new Scanner(System.in);
        boolean validEntry = false;

        while (!validEntry) {
            try {
                System.out.print("Enter weapon name: ");
                String name = scan.nextLine();
                System.out.print("Enter weapon damage: ");
                int damage = scan.nextInt();
                System.out.print("Enter weapon weight: ");
                double weight = scan.nextDouble();
                System.out.print("Enter weapon value: ");
                int value = scan.nextInt();
                WeaponImplementation newWeapon = new WeaponImplementation(damage, name, value, weight);
                addItem(newWeapon);
                validEntry = true;

            } catch (Exception e) {
                scan.nextLine();
                System.out.println("Invalid input5");
                validEntry = false;
            }
        }
    }//UI add Weapon

    public void userOptimizeInventory() {
        Scanner scan = new Scanner(System.in);
        boolean validEntry = false;

        while(!validEntry){
            try{
                System.out.print("Enter target carry weight: ");
                double number = scan.nextInt();
                if(number < 0){
                    throw new IllegalArgumentException("Has to be greater than zero!");
                }
                else{
                    optimizeInventory(number);
                    validEntry = true;
                }
            }
            catch (Exception e){
                System.out.println("Invalid Input");
            }
        }

    }//UI optimizing inventory

    @Override
    public void addItem(Item itemTemplate) {
        if (itemTemplate == null) {
            throw new NullPointerException();
        } else if (itemTemplate instanceof Armor) {
            ArmorImplementation newItem = new ArmorImplementation(((Armor) itemTemplate).getSlot(), ((Armor) itemTemplate).getRating(), itemTemplate.getName(), itemTemplate.getGoldValue(), itemTemplate.getWeight());
            inventoryList.addFirst(newItem);
        } else if (itemTemplate instanceof Weapon) {
            WeaponImplementation newItem = new WeaponImplementation(((Weapon) itemTemplate).getDamage(), itemTemplate.getName(), itemTemplate.getGoldValue(), itemTemplate.getWeight());
            inventoryList.addFirst(newItem);
        } else {
            ItemImplementation newItem = new ItemImplementation(itemTemplate.getName(), itemTemplate.getGoldValue(), itemTemplate.getWeight());
            inventoryList.addFirst(newItem);
        }
    }

    public boolean itemInInventory(Item item) {
        Iterator<Item> it = inventory();
        boolean validItem = false;
        while (it.hasNext()) {
            if (it.next() == item) {
                validItem = true;
            }
        }
        return validItem;
    }//Checks if item is in inventory

    @Override
    public void dropItem(Item item) {
        if (item == null) {
            throw new NullPointerException();
        } else {
            boolean itemFound = false;
            Iterator it = inventory();
            while (it.hasNext()) {
                if (it.next() == item) {//Iterates until item is found
                    it.remove();
                    itemFound = true;
                }
            }
            if (!itemFound) {
                throw new ItemNotFoundException();
            }
        }
    }//Removes item from inventory

    @Override
    public double getTotalWeight() {
        if(inventoryList.isEmpty()){
            return 0.0;
        }
        else{
            double totalWeight = 0;
            Iterator<Item> it = inventory();
            while (it.hasNext()) {
                totalWeight += it.next().getWeight();
            }
            return totalWeight;
        }
    }//Gets total weight of inventory

    @Override
    public Armor getEquippedArmor(int slot) {
        Armor returnArmor = null;
        if (slot < 0 || slot > 5) {
            throw new IllegalArgumentException();
        } else {
            returnArmor = armorType[slot]; //Should return null if not item is in equipped in the slot
        }
        return returnArmor;
    }//Returns Armor in current slot

    @Override
    public int getTotalArmorRating() {
        int totalArmorRating = 0;
        for (int i = 0; i < armorType.length; i++) {
            if (armorType[i] != null) {
                totalArmorRating += armorType[i].getRating();//looks at equipped armor and keeps track of rating
            }
        }
        return totalArmorRating;
    }

    @Override
    public void equipArmor(Armor armor) {
        boolean valid = itemInInventory(armor);

        if (armorSlots[armor.getSlot()] && valid) {//Has a item currently in the armor slot, add item into inventory and equip armor then removes it from inventory
            addItem(armorType[armor.getSlot()]);
            armorType[armor.getSlot()] = armor;
            dropItem(armor);
        } else if (!armorSlots[armor.getSlot()] && valid) {//Empty armor slot and removes it from inventory
            armorType[armor.getSlot()] = armor;
            armorSlots[armor.getSlot()] = true;
            dropItem(armor);
        }
    }

    @Override
    public void unequipArmor(int slot) {
        if (slot < 0 || slot > 5) {
            throw new IllegalArgumentException("Number has to be between 0 and 5 inclusive");
        } else if (armorSlots[slot]) {
            addItem(armorType[slot]);
            armorSlots[slot] = false;
            armorType[slot] = null;
        }
    }

    @Override
    public Weapon getEquippedWeapon() {
        return weaponType[0];
    }

    @Override
    public void equipWeapon(Weapon weapon) {
        boolean valid = itemInInventory(weapon);

        if (weapon == null) {
            throw new ItemNotFoundException();//CREATE EXCEPTION
        } else {
            if (weaponSlot[0] && valid) {
                addItem(weaponType[0]);
                weaponType[0] = weapon;
                dropItem(weaponType[0]);
            } else if (valid && weaponSlot[0] == false) {
                weaponType[0] = weapon;
                weaponSlot[0] = true;
                dropItem(weaponType[0]);
            }
        }

    }

    @Override
    public void unequipWeapon() {
        if (weaponSlot[0]) {
            addItem(weaponType[0]);
            weaponSlot[0] = false;
            weaponType[0] = null;
        }
    }

    @Override
    public void optimizeInventory(double maximumWeight) {
        if(inventoryList.isEmpty()){
            printInventory();
            return;
        }
        else{
            Item[] droppedList = new Item[inventoryList.size()+1];//keeps track of dropped items to print out later
            int count = 0;

            Iterator<Item> it = inventory();
            double currentWeight = 0.0;
            while(it.hasNext()){
                Item current = it.next();
                if(currentWeight + current.getWeight() <= maximumWeight) {//if the weight + counter is less than max than don't touch
                    currentWeight += current.getWeight();
                }
                else{
                    droppedList[count] = current;
                    count++;
                    dropItem(current);//drop item if item weight + current weight is greater than max weight
                }

            }

            int x = 0;
            System.out.println("Dropped: ");
            while(droppedList[x] != null){//Prints drop list
                System.out.println(droppedList[x].toString());
                x++;
            }
        }
        System.out.println();
        System.out.println("Total carry weight: " + getTotalWeight());

    }

    @Override
    public void optimizeEquipment() {

        if (inventoryList.isEmpty()) {
            System.out.println("Empty");
            System.out.println();
        }
        else {
            Iterator<Item> it = inventory();

            while (it.hasNext()) {
                Item current = it.next();
                if (current instanceof Weapon) {
                    if (weaponSlot[0] == false) {//If no weapon is currently equipped then equip if a weapon is found in inventory
                        equipWeapon((Weapon) current);
                        if(it.hasNext()){
                            it.next();
                        }

                    } else if (weaponSlot[0] && ((Weapon) current).getDamage() > weaponType[0].getDamage()) {//If a better weapon is found then equip the better weapon
                        equipWeapon((Weapon) current);
                        it.next();
                    }
                }
                else if (current instanceof Armor) {//works the same as weapon
                    if (armorSlots[((Armor) current).getSlot()] && armorType[((Armor) current).getSlot()].getRating() < ((Armor) current).getRating() ||
                            armorSlots[((Armor) current).getSlot()] == false) {
                        equipArmor((Armor) current);
                        if(it.hasNext()){
                            it.next();
                        }
                    }
                }

            }
        }
    }//good

    public void printInventory(){
        System.out.println();
        if(inventoryList.isEmpty() && !weaponSlot[0] && !armorSlots[0] && !armorSlots[1] && !armorSlots[2] && !armorSlots[3] && !armorSlots[4] && !armorSlots[5]){
            System.out.println("Empty");
            System.out.println();
        }
        else if((weaponSlot[0] || armorSlots[0] || armorSlots[1] || armorSlots[2] || armorSlots[3] || armorSlots[4] || armorSlots[5]) && inventoryList.isEmpty()){
            String[] armorParts = {"Chest", "Legs", "Hands", "Feet", "Head", "Shield"};
            System.out.println("=======================================");
            System.out.println("EQUIPPED ARMOR");
            System.out.printf("%s %15s %15s %15s %n","Item","Value","Weight","Ratio");

            for(int i = 0; i < armorType.length; i++){

                if(armorType[i] == null){
                    System.out.printf("%d %s %-10s %s %n", i, "/",armorParts[i], "Empty");
                }
                else{
                    System.out.println(armorType[i]);
                }
            }
            System.out.println();
            System.out.println("Total AR: " + getTotalArmorRating());


            System.out.println();
            System.out.println("EQUIPPED WEAPON");
            System.out.printf("%s %15s %15s %15s %n","Item","Value","Weight","Ratio");

            if(weaponType[0] == null){
                System.out.println("Empty");
            }
            else{
                System.out.println(weaponType[0]);
            }
        }
        else{
            Iterator<Item> it = inventory();
            String[] armorParts = {"Chest", "Legs", "Hands", "Feet", "Head", "Shield"};
            System.out.println("=======================================");
            System.out.println("EQUIPPED ARMOR");
            System.out.printf("%s %15s %15s %15s %n","Item","Value","Weight","Ratio");

            for(int i = 0; i < armorType.length; i++){

                if(armorType[i] == null){
                    System.out.printf("%d %s %-10s %s %n", i, "/",armorParts[i], "Empty");
                }
                else{
                    System.out.println(armorType[i]);
                }
            }
            System.out.println();
            System.out.println("Total AR: " + getTotalArmorRating());


            System.out.println();
            System.out.println("EQUIPPED WEAPON");
            System.out.printf("%s %15s %15s %15s %n","Item","Value","Weight","Ratio");


            if(weaponType[0] == null){
                System.out.println("Empty");
            }
            else{
                System.out.println(weaponType[0]);
            }


            System.out.println();
            System.out.println("INVENTORY");
            System.out.printf("%s %15s %15s %15s","Item","Value","Weight","Ratio");
            System.out.println();

            while(it.hasNext()){
                System.out.println(it.next().toString());
            }

        }
    }//Prints inventory and equipped items

    public int numberOfItems(){
        return inventoryList.size();
    }//Keeps track of inventory size

}

