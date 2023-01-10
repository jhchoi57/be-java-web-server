package http;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpHeader {

    private Map<String, String> headers = new HashMap<>();

    public HttpHeader(List<String> headers){
        for(String header : headers){
            String[] splitedHeader = header.split(":");
            String key = splitedHeader[0].trim();
            String value = splitedHeader[1].trim();
            this.headers.put(key, value);
        }
    }

    public static List<String> readHeaders(BufferedReader br) throws IOException {
        List<String> headers = new ArrayList<>();
        String header = br.readLine();
        while(!header.equals("")){
            headers.add(header);
            header = br.readLine();
        }
        return headers;
    }


}
