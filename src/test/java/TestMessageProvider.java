
import org.infai.ses.senergy.operators.Builder;
import org.infai.ses.senergy.operators.Config;
import org.infai.ses.senergy.operators.Message;
import org.infai.ses.senergy.utils.ConfigProvider;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestMessageProvider {

    public static List<Message> getTestMesssagesSet() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("src/test/resources/sample-data-small.json"));
        Builder builder = new Builder("1", "1");
        Config config = new Config(getConfig());
        ConfigProvider.setConfig(config);
        List<Message> messageSet = new ArrayList<>();
        String line;
        Message m;
        JSONObject jsonObjectRead, jsonObject;
        while ((line = br.readLine()) != null) {
            jsonObjectRead = new JSONObject(line);
            jsonObject = new JSONObject().put("device_id", "1").put("value", new JSONObject().put("reading", jsonObjectRead));
            m = new Message(builder.formatMessage(jsonObject.toString()));
            messageSet.add(m);
        }
        return messageSet;
    }

    public static String getConfig() {
        JSONObject config = new JSONObject().put("inputTopics",new JSONArray().put(new JSONObject().put("Name", "test")
                .put("FilterType", "DeviceId")
                .put("FilterValue", "1")
                .put("Mappings", new JSONArray()
                        .put(new JSONObject().put("Source", "value.reading.value1").put("Dest", "value1"))
                        .put(new JSONObject().put("Source", "value.reading.timestamp1").put("Dest", "timestamp1"))
                        .put(new JSONObject().put("Source", "value.reading.value2").put("Dest", "value2"))
                        .put(new JSONObject().put("Source", "value.reading.timestamp2").put("Dest", "timestamp2"))
                        .put(new JSONObject().put("Source", "value.reading.value").put("Dest", "value"))
                        .put(new JSONObject().put("Source", "value.reading.timestamp").put("Dest", "timestamp"))
                )));
        return config.toString();
    }
}
