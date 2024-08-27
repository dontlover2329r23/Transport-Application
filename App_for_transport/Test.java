package App_for_transport;

import java.io.File;
import java.util.List;

public class Test {
    public static void main(String[] args)  {
        try {
            SubwayLoader loader = new SubwayLoader();
            Subway objectville = loader.loadFromFile(new File("C:/Users/SherLock/IdeaProjects/TEstst/src/App_for_transport/ObjectvilleSubway.txt"));
            SubwayPrinter printer=new SubwayPrinter(System.out);
            List route = objectville.getDirections("Чеховская", "Кузнецкий Мост");
            printer.printDirections(route);
        } catch (Exception ex){ex.printStackTrace();}

    }
}
