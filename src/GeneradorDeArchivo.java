import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class GeneradorDeArchivo {
    public void guardarJson(List<VolumeInfo> biblioteca) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FileWriter escritura = new FileWriter("biblioteca.json");
        escritura.write(gson.toJson(biblioteca));
        escritura.close();
    }
}
