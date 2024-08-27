package App_for_transport;

public class Connection {
    private Station station1,station2;
    private String linename;
    public Connection(Station station1,Station station2,String linename){
        this.linename=linename;
        this.station1=station1;
        this.station2=station2;
    }
    public Station getStation1(){
        return station1;
    }
    public Station getStation2(){
        return station2;
    }

    public String getLinename() {
        return linename;
    }
}
