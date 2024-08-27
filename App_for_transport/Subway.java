package App_for_transport;

import java.util.*;

public class Subway {
    private List stations;
    private List connections;
    private Map route;
    public Subway(){
        stations=new LinkedList();
        connections=new LinkedList();
        route=new HashMap();
    }
    public void addStation(String station_name){
        if(!this.hasStation(station_name)){
            Station station=new Station(station_name);
            stations.add(station);
        }
    }
    public boolean hasStation(String station_name) {
        System.out.println("Checking if station exists: " + station_name);
        return stations.contains(new Station(station_name));
    }

    public Connection addConnection(String station1_name, String station2_name, String linename) {
        if (!this.hasStation(station1_name) || !this.hasStation(station2_name)) {
            throw new RuntimeException("One or both stations do not exist in the system: " + station1_name + ", " + station2_name);
        }

        System.out.println("Adding connection between " + station1_name + " and " + station2_name + " on line " + linename);

        Station station1 = new Station(station1_name);
        Station station2 = new Station(station2_name);

        if (station1 == null || station2 == null) {
            throw new RuntimeException("Failed to create station objects: station1 = " + station1 + ", station2 = " + station2);
        }

        Connection connection = new Connection(station1, station2, linename);
        connections.add(connection);
        connections.add(new Connection(station2, station1, linename));
        addtoRoute(station1, station2);
        addtoRoute(station2, station1);

        return connection;
    }
    public List getDirections(String startStationName,String endStationName){
        if ((!this.hasStation(startStationName)) || (!this.hasStation(endStationName))) {
            throw new RuntimeException("Stations entered do not exist on this subway");
        }
        Station start = new Station(startStationName);
        Station end = new Station(endStationName);
        List network = new LinkedList();
        List reachableStations = new LinkedList();
        Map previusStations = new HashMap();

        // Получаем список соседей
        List neighbors = (List) route.get(start);

        // Проверка на null
        if (neighbors == null) {
            throw new RuntimeException("No route found for station: " + startStationName);
        }

        for (Iterator i = neighbors.iterator(); i.hasNext();) {
            Station station = (Station) i.next();
            if (station.equals(end)) {
                network.add(getConnection(start, end));
                return network;
            } else {
                reachableStations.add(station);
                previusStations.put(station, start);
            }
        }
        List nextStations=new LinkedList();
        nextStations.addAll(neighbors);
        Station currentStation=start;
        searchLoop:
        for(int i=1;i<stations.size();i++){
            List tmpNextStations=new LinkedList();
            for(Iterator j =nextStations.iterator();j.hasNext();){
                Station station=(Station) j.next();
                reachableStations.add(station);
                currentStation=station;
                List currentNeighbors=(List) route.get(currentStation);
                for(Iterator k= currentNeighbors.iterator();k.hasNext();){
                    Station neighbor=(Station) k.next();
                    if(neighbor.equals(end)){
                        reachableStations.add(neighbor);
                        previusStations.put(neighbor,currentStation);
                        break searchLoop;
                    } else if (!reachableStations.contains(neighbor)) {
                        reachableStations.add(neighbor);
                        tmpNextStations.add(neighbor);
                        previusStations.put(neighbor,currentStation);
                    }
                }

            }
            nextStations=tmpNextStations;
        }
        boolean keepLooping= true;
        Station keyStation=end;
        Station station;
        while(keepLooping){
            station=(Station) previusStations.get(keyStation);
            network.add(0,getConnection(station,keyStation));
            if(start.equals(station)){
                keepLooping=false;
            }
            keyStation=station;
        }
        return network;

    }
    private Connection getConnection(Station station1, Station station2) {
        if (station1 == null || station2 == null) {
            throw new RuntimeException("One of the stations is null: station1 = " + station1 + ", station2 = " + station2);
        }

        for (Iterator i = connections.iterator(); i.hasNext(); ) {
            Connection connection = (Connection) i.next();
            Station one = connection.getStation1();
            Station two = connection.getStation2();

            if (one == null || two == null) {
                throw new RuntimeException("Connection contains a null station: one = " + one + ", two = " + two);
            }

            if (station1.equals(one) && station2.equals(two)) {
                return connection;
            }
        }

        return null;
    }
    private void addtoRoute(Station station1, Station station2) {
        List connectingStations = (List) route.get(station1);


        if (connectingStations == null) {
            connectingStations = new LinkedList();
            route.put(station1, connectingStations);
        }

        if (!connectingStations.contains(station2)) {
            connectingStations.add(station2);
        }
    }

    public boolean hasConnection(String stationName1,String stationName2,String lineName){
        Station station1=new Station(stationName1);
        Station station2=new Station(stationName2);
        for(Iterator i=connections.iterator();i.hasNext();){
            Connection connection=(Connection) i.next();
            if(connection.getLinename().equalsIgnoreCase(lineName)){
                if((connection.getStation1().equals(station1))&&(connection.getStation2().equals(station2))){
                    return true;
                }
            }
        }
        return false;
    }
}
