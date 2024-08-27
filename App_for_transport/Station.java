package App_for_transport;

public class Station {
    private String name;

    public String getName() {
        return name;
    }
    public Station(String name){
        this.name=name;
    }
    public boolean equals(Object obj){
        if ( obj instanceof Station){
            Station other_Station=(Station) obj;
            if(other_Station.getName().equalsIgnoreCase(this.name)){
                return true;
            }
        }
        return false;
    }
    public int hashCode(){
        return name.toLowerCase().hashCode();
    }

}
