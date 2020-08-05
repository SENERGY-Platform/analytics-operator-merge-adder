import com.jayway.jsonpath.JsonPath;
import org.infai.ses.senergy.operators.Message;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;


public class AdderTest {
    @Test
    public void test() throws Exception {
        Adder adder = new Adder();
        List<Message> messages = TestMessageProvider.getTestMesssagesSet();
        for (Message m : messages) {
            adder.configMessage(m);
            adder.run(m);

            m.addInput("value");
            m.addInput("timestamp");

            double valueActual = m.getInput("value").getValue();
            double valueExpected = Double.parseDouble(m.getMessageString().split("value\":")[1].split("}")[0]);
            String timestampExpected = m.getInput("timestamp").getString();
            String timestampActual = m.getMessageString().split("timestamp\":\"")[1].split("\"")[0];
            Assert.assertEquals(valueExpected, valueActual, 0.01);
            Assert.assertEquals(timestampExpected, timestampActual);
        }
    }

    @Test
    public void testComplex() throws Exception {
        Adder adder = new Adder();
        List<Message> messages = TestMessageProviderComplex.getTestMesssagesSet();
        for (Message m : messages) {
            adder.configMessage(m);
            adder.run(m);

            m.addInput("value");
            m.addInput("timestamp");

            double valueActual = JsonPath.read(m.getMessageString(), "$.analytics.value");

            String timestampActual = JsonPath.read(m.getMessageString(), "$.analytics.timestamp");
            Assert.assertEquals(2.78, valueActual, 0.01);
            Assert.assertEquals("2020-07-31T07:38:13.000Z", timestampActual);
        }
    }
}
