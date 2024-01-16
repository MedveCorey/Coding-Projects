package paintbrushapp;

public abstract class Shape {

    protected String shapeType;
    protected double[] shapeDimensions;
    
    public Shape(){
        shapeDimensions = new double[] {5.0,10.0};
    }

    public Shape(double[] shapeDimensions) {
        this.shapeDimensions = shapeDimensions;
    }
    public double[] getDimensions(){
        return shapeDimensions;
    }
    public void setDimensions(double[] newDimensions){
        int index = 0;
        for(double currentDimensions : newDimensions){
            if (currentDimensions > 0){
              shapeDimensions[index]= currentDimensions;
              index++;
            }else{
                System.out.println("Invalid value");
            }
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(shapeType).append("info");
        int index = 1;
        for (double currentDimensions: shapeDimensions){
            sb.append("Dimension #").append(index).append(": ").append(currentDimensions).append("\n");
            index++;
        }
        return sb.toString();
    }

    public abstract double computeArea();

    public abstract double computePerimeter();
}
