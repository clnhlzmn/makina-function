package xyz.colinholzman.makina;

import com.google.cloud.functions.HttpFunction;
import com.google.cloud.functions.HttpRequest;
import com.google.cloud.functions.HttpResponse;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.File;
import java.nio.file.Paths;
import java.nio.charset.Charset;
import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import xyz.colinholzman.makina.Machine;
import xyz.colinholzman.makina.Parse;
import xyz.colinholzman.makina.CodeGenerator;

public class MakinaFunction implements HttpFunction {
    @Override
    public void service(HttpRequest request, HttpResponse response) throws IOException {
        
        StringBuilder inputTextStringBuilder = new StringBuilder();
        String inputLine = request.getReader().readLine();
        while (inputLine != null) {
            inputTextStringBuilder.append(inputLine);
            inputTextStringBuilder.append("\n");
            inputLine = request.getReader().readLine();
        }
        String inputText = inputTextStringBuilder.toString();
        
        ByteArrayOutputStream headerStream = new ByteArrayOutputStream();
        ByteArrayOutputStream implementationStream = new ByteArrayOutputStream();
        
        PrintWriter headerWriter = new PrintWriter(headerStream);
        PrintWriter implementationWriter = new PrintWriter(implementationStream);
        
        response.appendHeader("Access-Control-Allow-Origin", "*");
        response.appendHeader("Access-Control-Allow-Methods", "POST");
        response.appendHeader("Content-Type", "text/plain");
        BufferedWriter writer = response.getWriter();
        
        try {
            Machine machine = Parse.Companion.fileFromString(inputText);
            CodeGenerator generator = new CodeGenerator(machine);
            
            generator.generateHeader(headerWriter);
            generator.generateSource(implementationWriter);
            headerWriter.flush();
            implementationWriter.flush();
            writer.write(headerStream.toString());
            writer.write("***");
            writer.write(implementationStream.toString());
        } catch (RuntimeException e) {
            writer.write("Exception:\n");
            writer.write(e.toString());
        }
    }
}
