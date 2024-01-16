package lab05;

import java.util.ArrayList;
import java.util.List;

public class Lab05 {

    public static void main(String[] args) {
        List<merch_info> Merchandise = new ArrayList();

        Adult_clothes adult_t_shirt = new Adult_clothes("Blue", "Large", "Cotton", 12.99, true, "Nike");
        Adult_clothes adult_t_shirt_2 = new Adult_clothes("Purple", "Medium", "Burlap", 11.75, false, "Ford");
        Childs_clothes childs_t_shirt = new Childs_clothes("Safe to Wear", "GM", 7.99, "Lululemon", true, "Nylon", "Medium");
        Childs_clothes childs_t_shirt_2 = new Childs_clothes("Safe to Wear", "US Department of Agriculture", 8.49, "Under Armor", true, "nylon", "medium");
        Watches rolex = new Watches("Digital","Platinum","Rolex",300.00,true,"True Platinum","Dio Mendez");
        Watches toy_story_watch = new Watches("Digital","Plastic","Rolex",15.00,false,"Toy Watch","Dio Mendez");
        Rings ring_pop = new Rings(12,true,"Ring Pop",.25,true,"Not Actually a Ring","Walmart");
        Rings wedding_ring = new Rings(8,true,"Mary Kay",599.99,true,"24k Diamond","Jeff Bezo");
        Mechanic_Tools Grinder = new Mechanic_Tools("Metal",200.00,true,"Dewalt",32547,"Dewalt");
        Mechanic_Tools Drill = new Mechanic_Tools("Metal",250.00,true,"Kobalt",32548,"Kobalt");
        House_tools Hammer = new House_tools("Metal",50.00,true,"Dewalt",true);
        House_tools Screwdriver = new House_tools("Metal",5.00,true,"Arm and Hammer",false);
        
        Merchandise.add(adult_t_shirt);
        Merchandise.add(adult_t_shirt_2);
        Merchandise.add(childs_t_shirt);
        Merchandise.add(childs_t_shirt_2);
        Merchandise.add(rolex);
        Merchandise.add(toy_story_watch);
        Merchandise.add(ring_pop);
        Merchandise.add(wedding_ring);
        Merchandise.add(Grinder);
        Merchandise.add(Drill);
        Merchandise.add(Hammer);
        Merchandise.add(Screwdriver);
        
        for(merch_info currentItem: Merchandise){
            System.out.println(currentItem.toString());
        }
    }

}
