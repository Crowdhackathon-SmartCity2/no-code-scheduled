import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class Server extends Block {
	
	private int Num = 0;
	private String PreviusHash;


	public Server() throws IOException {
		int port = 9000;
		File f = new File("config.txt");
		if(f.exists() && !f.isDirectory()) { 
			 
			BufferedReader br = new BufferedReader(new FileReader(f));
			Num = Integer.parseInt(br.readLine());
			PreviusHash = br.readLine();
			br.close();
		}else {
            write("config.txt", "0", "-");
            Num = 0;
		}
		HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
		System.out.println("server started at " + port);
		server.createContext("/", new RootHandler());
		server.createContext("/echoHeader", new EchoHeaderHandler());
		server.createContext("/GetGiveData", new GetGiveHandler());
		server.createContext("/GetSearch", new GetSearch());
		server.createContext("/echoPost", new EchoPostHandler());
		server.setExecutor(null);
		server.start();
	}
	
	public class RootHandler implements HttpHandler {

        public void handle(HttpExchange he) throws IOException {
                String response = "<h1>Server start success if you see this message</h1>" + "<h1>Port: 9000 </h1>";
                he.sendResponseHeaders(200, response.length());
                OutputStream os = he.getResponseBody();
                os.write(response.getBytes());
                os.close();
        }
	}
	
	public class EchoHeaderHandler implements HttpHandler {

        public void handle(HttpExchange he) throws IOException {
                Headers headers = he.getRequestHeaders();
                Set<Map.Entry<String, List<String>>> entries = headers.entrySet();
                String response = "";
                for (Map.Entry<String, List<String>> entry : entries)
                         response += entry.toString() + "\n";
                he.sendResponseHeaders(200, response.length());
                OutputStream os = he.getResponseBody();
                os.write(response.toString().getBytes());
                os.close();

        
        }}

	
	
	public class GetGiveHandler implements HttpHandler {

        public void handle(HttpExchange he) throws IOException {
                // parse request
                Map<String, Object> parameters = new HashMap<String, Object>();
                URI requestedUri = he.getRequestURI();
                String query = requestedUri.getRawQuery();
                parseQuery(query, parameters);

                // send response
                String response = "";
                String Obj = new String();
               	response = Num + "\n";
                System.out.println("run");
                for (String key : parameters.keySet()) {  
                	if(key.matches("hash")) {
                		Obj = (String) parameters.get(key);
                	}
                    response += key + " = " + parameters.get(key) + "\n";
                }
                System.out.println("run");
                Block hash = new Block(PreviusHash,Obj);
            	write(Num+".txt", hash.getBlockHash());
            	Num++;
            	PreviusHash = hash.getBlockHash();
            	write("config.txt", Num+1, PreviusHash);
                he.sendResponseHeaders(200, response.length());
                OutputStream os = he.getResponseBody();
                os.write(response.toString().getBytes());
                os.close();
        }
}
	
	public class EchoPostHandler implements HttpHandler {

        public void handle(HttpExchange he) throws IOException {
                // parse request
                Map<String, Object> parameters = new HashMap<String, Object>();
                InputStreamReader isr = new InputStreamReader(he.getRequestBody(), "utf-8");
                BufferedReader br = new BufferedReader(isr);
                String query = br.readLine();
                parseQuery(query, parameters);

                // send response
                String response = "";
                for (String key : parameters.keySet())
                         response += key + " = " + parameters.get(key) + "\n";
                he.sendResponseHeaders(200, response.length());
                OutputStream os = he.getResponseBody();
                os.write(response.toString().getBytes());
                os.close();
        }
}
	
	
	public class GetSearch implements HttpHandler {

        public void handle(HttpExchange he) throws IOException {
                // parse request
                Map<String, Object> parameters = new HashMap<String, Object>();
                URI requestedUri = he.getRequestURI();
                String query = requestedUri.getRawQuery();
                parseQuery(query, parameters);

                // send response
                String response = "";
                Object[] Obj = new Object[16];
                int pos = 0;
                for (String key : parameters.keySet()) {
                	if(key.matches("pos")) {
                    	pos = Integer.parseInt((String) parameters.get(key));

                	}else {
                		Arrange modify = new Arrange(Obj, key.toLowerCase(), parameters.get(key).toString().toLowerCase());
                		Obj = modify.getObj();
                	}
                }
                Search src = new Search(pos, Arrays.toString(Obj));
                System.out.println("end");
                response = src.getResult();
                he.sendResponseHeaders(200, response.length());
                OutputStream os = he.getResponseBody();
                os.write(response.toString().getBytes());

                os.close();
        }
}
	
	
	
	public static void parseQuery(String query, Map<String, 
			Object> parameters) throws UnsupportedEncodingException {

		         if (query != null) {
		                 String pairs[] = query.split("[&]");
		                 for (String pair : pairs) {
		                          String param[] = pair.split("[=]");
		                          String key = null;
		                          String value = null;
		                          if (param.length > 0) {
		                          key = URLDecoder.decode(param[0], 
		                          	System.getProperty("file.encoding"));
		                          }

		                          if (param.length > 1) {
		                                   value = URLDecoder.decode(param[1], 
		                                   System.getProperty("file.encoding"));
		                          }

		                          if (parameters.containsKey(key)) {
		                                   Object obj = parameters.get(key);
		                                   if (obj instanceof List<?>) {
		                                            @SuppressWarnings("unchecked")
													List<String> values = (List<String>) obj;
		                                            values.add(value);

		                                   } else if (obj instanceof String) {
		                                            List<String> values = new ArrayList<String>();
		                                            values.add((String) obj);
		                                            values.add(value);
		                                            parameters.put(key, values);
		                                   }
		                          } else {
		                                   parameters.put(key, value);
		                          }
		                 }
		         }
		}

	private void write(String name,Object content) throws FileNotFoundException, UnsupportedEncodingException {
		PrintWriter writer = new PrintWriter(name, "UTF-8");
        writer.println(content);
        writer.close();

	}
	
	private void write(String name,Object content,Object content2) throws FileNotFoundException, UnsupportedEncodingException {
		PrintWriter writer = new PrintWriter(name, "UTF-8");
        writer.println(content);
        writer.println(content2);
        writer.close();

	}
	
}
