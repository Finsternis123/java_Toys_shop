public class Toys {
    
    protected String name;

    protected int id;

    public Toys(int id, String name){
        this.id = id;
        this.name = name;   
    }

    public String getName(){
        return name;
    }

    public int getId(){
        return id;
    }


    @Override
    public String toString() {
        return String.format("%d) %s ",
                id, name);
    }


}
