package xyz.colinholzman.makina;

import com.google.cloud.functions.HttpFunction;
import com.google.cloud.functions.HttpRequest;
import com.google.cloud.functions.HttpResponse;
import java.io.BufferedWriter;
import java.io.IOException;

public class MakinaFunction implements HttpFunction {
    @Override
    public void service(HttpRequest request, HttpResponse response) throws IOException {
        String input[] = new String[1];
        input[0] = "hi";
        xyz.colinholzman.makina.MainKt.main(input);
        BufferedWriter writer = response.getWriter();
        writer.write("Hello World!");
    }
}
