package App_for_transport;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;

public class SubwayPrinter {
    private PrintStream out;
    public SubwayPrinter(OutputStream out){
        this.out=new PrintStream(out);
    }
    public void printDirections(List route){
        Connection connection=(Connection) route.get(0);
        String currentLIne=connection.getLinename();
        String previusLine=currentLIne;
        out.println("Start out at "+
                connection.getStation1().getName()+".");
        out.println("Get on the "+currentLIne+" heading towards "+
                connection.getStation2().getName()+".");
        for(int i=1;i<route.size();i++){
            connection=(Connection) route.get(i);
            currentLIne=connection.getLinename();
            if(currentLIne.equals(previusLine)){
                out.println(" Continue past "+
                        connection.getStation1().getName()+"...");
            }else{
                out.println("When you get to "+
                        connection.getStation1().getName()+", get off the "+ previusLine+".");
                out.println("Switch over the "+currentLIne+", heading towards "+connection.getStation2().getName()+".");
                previusLine=currentLIne;
            }
        }
        out.println("Get off at "+connection.getStation2().getName()+
                "and enjoy yourself!");
    }
}
