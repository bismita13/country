package country;
import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import redis.clients.jedis.Jedis;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
//import country.ClientDetailsProto.ClientInfo.Builder;

public class json {
	
	public static void main(String[] args)throws IOException, JSONException {
		
		// TODO Auto-generated method stub
		
		OkHttpClient client = new OkHttpClient();

		MediaType mediaType = MediaType.parse("application/json;charset=UTF-8");
		RequestBody body = RequestBody.create(mediaType, "{\"apiKey\":\"4bce2f32de125ab7c19526af6af6d8ad4069\",\"deviceId\":\"341235132513412\",\"appId\":\"ios.appid\",\"platform\":1,\"version\":2}");
		Request request = new Request.Builder()
		  .url("http://localhost:8080/laaslm/getLicenseInfoJSON")
		  .post(body)
		  .addHeader("content-type", "application/json;charset=UTF-8")
		  .addHeader("cache-control", "no-cache")
		  .build();
		//String json="{\"apiKey\":\"4bce2f32de125ab7c19526af6af6d8ad4069\",\"deviceId\":\"341235132513412\",\"appId\":\"ios.appid\",\"platform\":1,\"version\":2}";
		//String author = JsonPath.read(json, "$.apiKey");

		//System.out.println(author);

		Response response = client.newCall(request).execute();
		//System.out.println(response);
		JSONObject jObject = new JSONObject(response.body().string());
		String jsonText = jObject.toString();
		JSONArray jarr = jObject.toJSONArray(jObject.names());
		String localization=jarr.get(0).toString();
		String apikey=jarr.get(1).toString();
		
		System.out.println(localization );
		System.out.println(jarr.get(1).toString() );
		System.out.println("The json array:"+" "+jsonText);
		//System.out.println( jarr);
		
		/*jedis connection to the server*/
		Jedis jedis = new Jedis("localhost");
	      System.out.println("Connection to server sucessfully");
	      //check whether server is running or not
	      System.out.println("Server is running: "+jedis.ping());
	      String key="ikoo";
	      String key1="ikoo";
	      jedis.set(key.toString(), jsonText);
	      jedis.set("key", "bdjhg");
	      System.out.println(jedis.get(key.toString()));
	      //System.out.println(jedis.get(jgfh.toString()));
	      System.out.println("written in redis cache");
	      ArrayList<String> apiKeyList = new ArrayList<String>();
	      		apiKeyList.add("apikey");
	      
		ClientDetailsProto.ClientDetails cd = ClientDetailsProto.ClientDetails.newBuilder()
	    		  				.setName("Ayushman Dash")
	    		  				.addAllApikey(apiKeyList)
	    		  				.setCategory("Banking")
	    		  				.setDescription("It is a small banking solutions app")
	    		                  .setEmail("ayushmanmaku@gmail.com")
	    		                  .setRefemail("bhupen.chauhan@reverieinc.com")
	    		                  .setCreatedby("bhupen.chauhan@reverieinc.com")
	    		                  .setPassword("4606269395bf2dccb")
	    		                  .setPhonenum(990132069)
	    		                  .build();

		System.out.println(cd.toString());
		jedis.set("key1", cd.toString());
		 System.out.println("hjjx"+jedis.get(key1.toString()));
		
}
}

  