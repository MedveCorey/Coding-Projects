import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public interface Graphic {
    public void draw();    
} 

public class Panel implements Graphic{
    private List<Graphic> children = new ArrayList<>();
    
    @Override
    public void draw(){
        Sytem.out.println("Panel: "+ children.size() + "element");
        for(Graphic graphic : children){
            graphic.draw();
        }
    }

    @Override
    public void addGraphic(Graphic graphic){
        children.add(graphic);
    }

    @Override
    public void removeGraphic(Graphic graphic){
        children.remove(graphic);
    }

    @Override
    public Graphic getChildren(int index){
        if(index < 0 || index >= children.size()){
            return null;
        }
        return children.get(index);
    }
}

public class Button implements Graphic{
    @Override
    public void draw(){
        System.out.println("Drawing Button");
    }
}

public class Label implements Graphic{
    @Override()
    public void draw(){
        System.out.println("Drawing Label");
    }
}