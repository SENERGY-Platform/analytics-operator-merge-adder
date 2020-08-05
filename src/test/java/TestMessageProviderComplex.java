
import org.infai.ses.senergy.operators.Builder;
import org.infai.ses.senergy.operators.Config;
import org.infai.ses.senergy.operators.Message;
import org.infai.ses.senergy.utils.ConfigProvider;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestMessageProviderComplex {

    public static List<Message> getTestMesssagesSet() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("src/test/resources/complex.json"));
        Builder builder = new Builder("1", "1");
        Config config = new Config(getConfig());
        ConfigProvider.setConfig(config);
        List<Message> messageSet = new ArrayList<>();
        String line;
        StringBuilder full = new StringBuilder();
        while ((line = br.readLine()) != null) {
            full.append(line);
        }
        Message m = new Message(builder.formatMessage("{}"));
        m.setMessage(full.toString());
        messageSet.add(m);
        return messageSet;
    }

    public static String getConfig() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("src/test/resources/complex-config.json"));
        String line;
        StringBuilder full = new StringBuilder();
        while ((line = br.readLine()) != null) {
            full.append(line);
        }
        return full.toString();
    }
}
