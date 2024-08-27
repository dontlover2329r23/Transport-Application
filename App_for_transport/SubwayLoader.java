package App_for_transport;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class SubwayLoader {
    private Subway subway;
    public SubwayLoader(){
        this.subway=new Subway();
    }
    public Subway loadFromFile(File subwatFile) throws IOException{
        BufferedReader reader=new BufferedReader(new FileReader(subwatFile));
        loadStations(subway,reader);
        String linename=reader.readLine();
        while((linename!=null)&&(linename.length()>0)){
            loadLine(subway,reader,linename);
            linename=reader.readLine();
        }
        return subway;
    }
    private void loadStations(Subway subway, BufferedReader reader) throws IOException {
        String currentLine = reader.readLine();
        while (currentLine != null && currentLine.length() > 0) {
            System.out.println("Loading station: " + currentLine);
            subway.addStation(currentLine);
            currentLine = reader.readLine();
        }
    }
    private void loadLine(Subway subway, BufferedReader reader, String linename) throws IOException {
        String stationName1 = reader.readLine();
        String stationName2 = reader.readLine();

        while (stationName1 != null && stationName2 != null && stationName1.length() > 0 && stationName2.length() > 0) {
            subway.addConnection(stationName1, stationName2, linename);
            stationName1 = stationName2;
            stationName2 = reader.readLine();
        }
    }

}
