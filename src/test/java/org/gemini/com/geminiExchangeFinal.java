package org.gemini.com;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static java.nio.charset.StandardCharsets.*;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class geminiExchangeFinal 
{
int errorId = 0;
private static String resultFileName = "./Output/GeminiResults";
private static String gemini_api_key =    "account-BQudD0pPBZ3RmCeZ1zQ6";
private static String gemini_api_secret1 = "8cmQ7jr9DZhswuQ7gj1E3BPbR7a";
private static byte[] ptext = gemini_api_secret1.getBytes(UTF_8); 
private static String gemini_api_secret = new String(ptext, UTF_8);
private static final String HMAC_SHA1_ALGORITHM = "HmacSHA384";
public static Map<String,String>transactionStatus = new LinkedHashMap<String,String>();
public static Map<String,JSONObject> orderJSONObjecMap = new LinkedHashMap<String,JSONObject>();
//Get the base URI
private static String url = "https://api.sandbox.gemini.com/v1/order/new";;

@DataProvider(name = "data-provider")
public Object[][] dataProviderMethod() {
	return new Object[][] { 
		{"{\"request\": \"/v1/order/new\", \"nonce\": nonce_unixTime, \"symbol\": \"ltcusd\", \"amount\": \"amountParam\", \"price\": \"priceParam\", \"side\": \"buy\", \"type\": \"exchange limit\", \"options\": [\"maker-or-cancel\"]}"}, 
		{ "{\"request\": \"/v1/order/new\", \"nonce\": nonce_unixTime, \"symbol\": \"ltceth\", \"amount\": \"amountParam\", \"price\": \"priceParam\", \"side\": \"buy\", \"type\": \"exchange limit\", \"options\": [\"maker-or-cancel\"]}"}, 
		{ "{\"request\": \"/v1/order/new\", \"nonce\": nonce_unixTime, \"symbol\": \"btcusd\", \"amount\": \"amountParam\", \"price\": \"priceParam\", \"side\": \"buy\", \"type\": \"exchange limit\", \"options\": [\"maker-or-cancel\"]}"},
	    { "{\"request\": \"/v1/order/new\", \"nonce\": nonce_unixTime, \"symbol\": \"ethusd\", \"amount\": \"amountParam\", \"price\": \"priceParam\", \"side\": \"buy\", \"type\": \"exchange limit\", \"options\": [\"immediate-or-cancel\"]}"},
		{ "{\"request\": \"/v1/order/new\", \"nonce\": nonce_unixTime, \"symbol\": \"ethbtc\", \"amount\": \"amountParam\", \"price\": \"5.0\", \"side\": \"buy\", \"type\": \"exchange limit\", \"options\": [\"fill-or-kill\"]}"},
		{ "{\"request\": \"/v1/order/new\", \"nonce\": nonce_unixTime, \"symbol\": \"ethusd\", \"amount\": \"amountParam\", \"price\": \"priceParam\", \"side\": \"buy\", \"type\": \"exchange limit\", \"options\": [\"maker-or-cancel\"]}"},
		{ "{\"request\": \"/v1/order/new\", \"nonce\": nonce_unixTime, \"symbol\": \"ethbtc\", \"amount\": \"amountParam\", \"price\": \"5.0\", \"side\": \"buy\", \"type\": \"exchange limit\", \"options\": [\"maker-or-cancel\"]}"},
		{ "{\"request\": \"/v1/order/new\", \"nonce\": nonce_unixTime, \"symbol\": \"zecusd\", \"amount\": \"amountParam\", \"price\": \"priceParam\", \"side\": \"buy\", \"type\": \"exchange limit\", \"options\": [\"maker-or-cancel\"]}"},
		{ "{\"request\": \"/v1/order/new\", \"nonce\": nonce_unixTime, \"symbol\": \"zecbtc\", \"amount\": \"amountParam\", \"price\": \"priceParam\", \"side\": \"buy\", \"type\": \"exchange limit\", \"options\": [\"maker-or-cancel\"]}"},
		{ "{\"request\": \"/v1/order/new\", \"nonce\": nonce_unixTime, \"symbol\": \"zeceth\", \"amount\": \"amountParam\", \"price\": \"priceParam\", \"side\": \"buy\", \"type\": \"exchange limit\", \"options\": [\"maker-or-cancel\"]}"},
		{ "{\"request\": \"/v1/order/new\", \"nonce\": nonce_unixTime, \"symbol\": \"zecltc\", \"amount\": \"amountParam\", \"price\": \"priceParam\", \"side\": \"buy\", \"type\": \"exchange limit\", \"options\": [\"maker-or-cancel\"]}"},
		{ "{\"request\": \"/v1/order/new\", \"nonce\": nonce_unixTime, \"symbol\": \"bchusd\", \"amount\": \"amountParam\", \"price\": \"priceParam\", \"side\": \"buy\", \"type\": \"exchange limit\", \"options\": [\"maker-or-cancel\"]}"}, 
		{ "{\"request\": \"/v1/order/new\", \"nonce\": nonce_unixTime, \"symbol\": \"bcheth\", \"amount\": \"amountParam\", \"price\": \"priceParam\", \"side\": \"buy\", \"type\": \"exchange limit\", \"options\": [\"maker-or-cancel\"]}"}, 
		{ "{\"request\": \"/v1/order/new\", \"nonce\": nonce_unixTime, \"symbol\": \"ltcusd\", \"amount\": \"amountParam\", \"price\": \"priceParam\", \"side\": \"buy\", \"type\": \"exchange limit\", \"options\": [\"maker-or-cancel\"]}"}, 
		{ "{\"request\": \"/v1/order/new\", \"nonce\": nonce_unixTime, \"symbol\": \"ltceth\", \"amount\": \"amountParam\", \"price\": \"priceParam\", \"side\": \"buy\", \"type\": \"exchange limit\", \"options\": [\"maker-or-cancel\"]}"}, 
		{ "{\"request\": \"/v1/order/new\", \"nonce\": nonce_unixTime, \"symbol\": \"btcusd\", \"amount\": \"amountParam\", \"price\": \"priceParam\", \"side\": \"buy\", \"type\": \"exchange limit\", \"options\": [\"maker-or-cancel\"]}"},
		{ "{\"request\": \"/v1/order/new\", \"nonce\": nonce_unixTime, \"symbol\": \"ethusd\", \"amount\": \"amountParam\", \"price\": \"priceParam\", \"side\": \"sell\", \"type\": \"exchange limit\", \"options\": [\"maker-or-cancel\"]}"},
		{ "{\"request\": \"/v1/order/new\", \"nonce\": nonce_unixTime, \"symbol\": \"ethbtc\", \"amount\": \"amountParam\", \"price\": \"5.0\", \"side\": \"buy\", \"type\": \"exchange limit\", \"options\": [\"maker-or-cancel\"]}"},
		{ "{\"request\": \"/v1/order/new\", \"nonce\": nonce_unixTime, \"symbol\": \"zecusd\", \"amount\": \"amountParam\", \"price\": \"priceParam\", \"side\": \"sell\", \"type\": \"exchange limit\", \"options\": [\"maker-or-cancel\"]}"},
		{ "{\"request\": \"/v1/order/new\", \"nonce\": nonce_unixTime, \"symbol\": \"zecbtc\", \"amount\": \"amountParam\", \"price\": \"priceParam\", \"side\": \"sell\", \"type\": \"exchange limit\", \"options\": [\"maker-or-cancel\"]}"},
		{ "{\"request\": \"/v1/order/new\", \"nonce\": nonce_unixTime, \"symbol\": \"zeceth\", \"amount\": \"amountParam\", \"price\": \"priceParam\", \"side\": \"sell\", \"type\": \"exchange limit\", \"options\": [\"maker-or-cancel\"]}"},
		{ "{\"request\": \"/v1/order/new\", \"nonce\": nonce_unixTime, \"symbol\": \"zecltc\", \"amount\": \"amountParam\", \"price\": \"priceParam\", \"side\": \"sell\", \"type\": \"exchange limit\", \"options\": [\"maker-or-cancel\"]}"},
		{ "{\"request\": \"/v1/order/new\", \"nonce\": nonce_unixTime, \"symbol\": \"bchusd\", \"amount\": \"amountParam\", \"price\": \"priceParam\", \"side\": \"sell\", \"type\": \"exchange limit\", \"options\": [\"maker-or-cancel\"]}"}, 
		{ "{\"request\": \"/v1/order/new\", \"nonce\": nonce_unixTime, \"symbol\": \"bcheth\", \"amount\": \"amountParam\", \"price\": \"priceParam\", \"side\": \"sell\", \"type\": \"exchange limit\", \"options\": [\"maker-or-cancel\"]}"},
		{ "{\"request\": \"/v1/order/new\", \"nonce\": nonce_unixTime, \"symbol\": \"btcusd\", \"amount\": \"amountParam\", \"price\": \"23.83190155\", \"side\": \"buy\", \"type\": \"exchange limit\", \"options\": [\"maker-or-cancel\"]}"},
		{ "{\"request\": \"/v1/order/new\", \"nonce\": nonce_unixTime, \"symbol\": \"bcheth\", \"amount\": \"5.467567890\", \"price\": \"priceParam\", \"side\": \"buy\", \"type\": \"exchange limit\", \"options\": [\"maker-or-cancel\"]}"} ,
		{ "{\"request\": \"/v1/order/new\", \"nonce\": nonce_unixTime, \"symbol\": \"bcheth\", \"amount\": \"amountParam\", \"price\": \"priceParam\", \"side\": \"buy1\", \"type\": \"exchange limit\", \"options\": [\"maker-or-cancel\"]}"},
		{ "{\"request\": \"/v1/order/new\", \"nonce\": nonce_unixTime, \"symbol\": \"bchusd\", \"amount\": \"amountParam\", \"price\": \"priceParam\", \"side\": \"buy\", \"type\": \"exchange stop limit\", \"options\": [\"maker-or-cancel\"]}"},
		{ "{\"request\": \"/v1/order/new\", \"nonce\": nonce_unixTime, \"symbol\": \"bchusd\", \"amount\": \"amountParam\", \"price\": \"priceParam\", \"side\": \"buy\", \"type\": \"exchange limit1\", \"options\": [\"maker-or-cancel\"]}"}, 
		{ "{\"request\": \"/v1/order/new\", \"nonce\": nonce_unixTime, \"symbol\": \"btcusd1\", \"amount\": \"amountParam\", \"price\": \"priceParam\", \"side\": \"buy\", \"type\": \"exchange limit\", \"options\": [\"maker-or-cancel\"]}" },
		{ "{\"request\": \"/v1/order/new\", \"nonce\": nonce_unixTime, \"symbol\": \"zecbch\", \"amount\": \"amountParam\", \"price\": \"priceParam\", \"side\": \"buy\", \"type\": \"exchange limit\", \"options\": [\"maker-or-cancel\"]}"},
		{ "{\"request\": \"/v1/order/new\", \"nonce\": nonce_unixTime, \"symbol\": \"ltcbch\", \"amount\": \"amountParam\", \"price\": \"priceParam\", \"side\": \"buy\", \"type\": \"exchange limit\", \"options\": [\"maker-or-cancel\"]}"},
		{ "{\"request\": \"/v1/order/new\", \"nonce\": nonce_unixTime, \"symbol\": \"zecusd\", \"amount\": \"amountParam\", \"price\": \"priceParam\", \"side\": \"buy\", \"type\": \"exchange limit\", \"options\": [\"auction-only\"]}"},
		{ "{\"request\": \"/v1/order/new\", \"nonce\": nonce_unixTime, \"symbol\": \"zecbtc\", \"amount\": \"amountParam\", \"price\": \"priceParam\", \"side\": \"buy\", \"type\": \"exchange limit\", \"options\": [\"indication-of-interest\"]}"},
		{ "{\"request\": \"/v1/order/new\", \"nonce\": nonce_unixTime, \"symbol\": \"ethusd\", \"amount\": \"amountParam\", \"price\": \"priceParam\", \"side\": \"buy\", \"type\": \"exchange limit\", \"options\": [\"maker-or-cancel1\"]}"},
		{"{\"request\": \"/v1/order/new\", \"nonce\": nonce_unixTime, \"client_order_id\": \"orderClientSuppliedId\", \"symbol\": \"ltcusd\", \"amount\": \"amountParam\", \"price\": \"priceParam\", \"side\": \"BUY\", \"type\": \"exchange limit\", \"options\": [\"maker-or-cancel\"]}"}, 
		{"{\"request\": \"/v1/order/ne\", \"nonce\": nonce_unixTime, \"client_order_id\": \"orderClientSuppliedId\", \"symbol\": \"ltcusd\", \"amount\": \"amountParam\", \"price\": \"priceParam\", \"side\": \"BUY\", \"type\": \"exchange limit\", \"options\": [\"maker-or-cancel\"]}"},
		{"{\"request\": \"/v1/order/new\", \"nonc\": nonce_unixTime, \"client_order_id\": \"orderClientSuppliedId\", \"symbol\": \"ltcusd\", \"amount\": \"amountParam\", \"price\": \"priceParam\", \"side\": \"BUY\", \"type\": \"exchange limit\", \"options\": [\"maker-or-cancel\"]}"},
		{"{\"request\": \"/v1/order/new\", \"nonce\": nonce_unixTime, \"client_order_id\": 3456789, \"symbol\": \"ltcusd\", \"amount\": \"amountParam\", \"price\": \"priceParam\", \"side\": \"BUY\", \"type\": \"exchange limit\", \"options\": [\"maker-or-cancel\"]}"}
		 

	};
}

//to generate the API Signature key need as per SHA384 algoritham
private static String geminiBytesToHex(final byte[] hash) {
    final StringBuffer hexString = new StringBuffer();
    for (int i = 0; i < hash.length; i++) {
        final String hex = Integer.toHexString(0xff & hash[i]);
        if (hex.length() == 1) {
            hexString.append('0');
        }
        hexString.append(hex);
    }
    return hexString.toString();
}

	//gemini new order rest API call and response validation
	@Test(dataProvider = "data-provider",priority=1)
	public void geminiNewOrderRestfulService(String payload1) throws Exception
	{
		double maxPrice = 100.00;
		double minPrice = 0.00;

		
		String nonce_unixTime = String.valueOf(System.currentTimeMillis() );
		String payload = payload1.replaceAll("nonce_unixTime", nonce_unixTime);
	
		Random rand = new Random();
		
		//random price generation to predict and mimic real life exchange situation
		String price =  "";
		double price1 = Math.abs(rand.nextFloat() * (maxPrice - minPrice) + 0.0);
	    DecimalFormat df2 = new DecimalFormat("#.##");
	    price= df2.format(price1);
	    
	    //Generate random number to mimic real life situation
		String amount = "";
		amount = Integer.toString(rand.nextInt(10)+1);
		
		//generate a clinet id for testcase where user can supply their own order_id
		String orderClientSuppliedId = Integer.toString(rand.nextInt(100000)+1);
		

		payload = payload.replaceAll("amountParam", amount);
		payload = payload.replaceAll("priceParam", price);
		payload = payload.replaceAll("orderClientSuppliedId", orderClientSuppliedId);


		//System.out.print("nonce_unixTime"+nonce_unixTime);
		//Base64 encoding of the payload
		Base64.Encoder encoder = Base64.getEncoder();
		gemini_api_secret =  encoder.encodeToString(gemini_api_secret.getBytes());


		//Base64 encoding needed for the REST API call
		String encodedString = Base64.getEncoder().encodeToString(payload.getBytes());
		//System.out.println("encodedString:  "+encodedString);
		

		//encode the Secret Key using the HMACSHA384 algorithm
	 	SecretKeySpec signingKey = new SecretKeySpec(gemini_api_secret1.getBytes(), HMAC_SHA1_ALGORITHM);
	    Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
	    mac.init(signingKey);
	    String Signature = geminiBytesToHex(mac.doFinal(encodedString.getBytes()));

		


		
	//make the call to gemini exchange
	    Response response = RestAssured.given().auth().none()
	    		//.when()
	    		//.log().all()           
	    //		.header("Content-Type", "text/plain")
	       		.header("X-GEMINI-APIKEY", gemini_api_key)
	    		.header("X-GEMINI-PAYLOAD", encodedString)
	    	    .header("X-GEMINI-SIGNATURE", Signature)	
	    	    .post(url);
	    	  	    
	
	    
	   int statusCode = response.getStatusCode();
	   System.out.println("Response Successfully for country details request - Status Code: + Status Response"+statusCode+":"+response.asString());
       
       
       //transactionStatus
       
       //get the response into JSONArray of JSON Objects
       JSONParser parser = new JSONParser();
       JSONObject ob = (JSONObject) parser.parse(response.asString());
       
       //get order id
       String orderId = response.jsonPath().get("order_id");
       //System.out.println("orderId:"+orderId);
       
       //Assert.assertEquals(statusCode, 200,"Correct status code recieved");
       if(statusCode==200)
       {
    	   //check for status code 200 followed by input request and response
    	   JSONObject requestObject = (JSONObject) parser.parse(payload.toString());
    	   boolean match =  geminiExchangeFinal.compareoutBoundRequestWithInboundResponseNewOrder(requestObject, ob);    	     
           orderJSONObjecMap.put(orderId, ob);
           
           if(match==true)
           {
    	      transactionStatus.put(orderId, "PASS:Input-Output Matches:->"+statusCode+":"+response.asString());
           }
           else
           {
        	   transactionStatus.put(orderId, "FAIL:Input-Output does not match:->"+statusCode+":"+response.asString());

           }
    	   
       }
       else if(statusCode==406)
       {
    	   orderId = "OrderErrorId" + errorId;
    	   errorId= errorId+1;
           orderJSONObjecMap.put(orderId, ob);
    	   transactionStatus.put(orderId, "PASS:->"+statusCode+":->"+payload+":->"+response.asString());
       }
       else
       {
    	   //create dummy reference for failuer aolong with 
    	   if(ob.toString().contains("InvalidSymbol"))
    	   {
    	   orderId = "OrderErrorId" + errorId;
    	   errorId= errorId+1;
           orderJSONObjecMap.put(orderId, ob);	
    	   transactionStatus.put(orderId,  "PASS:->"+statusCode+":->"+payload+":->"+response.asString());
    	   }
    	   else  if(ob.toString().contains("InvalidPrice"))
    	   {
    	   orderId = "OrderErrorId" + errorId;
    	   errorId= errorId+1;
           orderJSONObjecMap.put(orderId, ob);	
    	   transactionStatus.put(orderId,  "PASS:->"+statusCode+":->"+payload+":->"+response.asString());
    	   }
    	   else  if(ob.toString().contains("InvalidQuantity"))
    	   {
    	   orderId = "OrderErrorId" + errorId;
    	   errorId= errorId+1;
           orderJSONObjecMap.put(orderId, ob);	
    	   transactionStatus.put(orderId,  "PASS:->"+statusCode+":->"+payload+":->"+response.asString());
    	   }
    	   else  if(ob.toString().contains("InvalidSide"))
    	   {
    	   orderId = "OrderErrorId" + errorId;
    	   errorId= errorId+1;
           orderJSONObjecMap.put(orderId, ob);	
    	   transactionStatus.put(orderId,  "PASS:->"+statusCode+":->"+payload+":->"+response.asString());
    	   }
    	   else  if(ob.toString().contains("Stop Limit orders only support Standard order behavior"))
    	   {
    	   orderId = "OrderErrorId" + errorId;
    	   errorId= errorId+1;
           orderJSONObjecMap.put(orderId, ob);	
    	   transactionStatus.put(orderId,  "PASS:->"+statusCode+":->"+payload+":->"+response.asString());
    	   }
    	   else  if(ob.toString().contains("InvalidOrderType"))
    	   {
    	   orderId = "OrderErrorId" + errorId;
    	   errorId= errorId+1;
           orderJSONObjecMap.put(orderId, ob);	
    	   transactionStatus.put(orderId,  "PASS:->"+statusCode+":->"+payload+":->"+response.asString());
    	   }
     	   else  if(ob.toString().contains("UnsupportedOption"))
    	   {
    	   orderId = "OrderErrorId" + errorId;
    	   errorId= errorId+1;
           orderJSONObjecMap.put(orderId, ob);	
    	   transactionStatus.put(orderId,  "PASS:->"+statusCode+":->"+payload+":->"+response.asString());
    	   }
       	   else  if(ob.toString().contains("MarketNotOpen"))
    	   {
    	   orderId = "OrderErrorId" + errorId;
    	   errorId= errorId+1;
           orderJSONObjecMap.put(orderId, ob);	
    	   transactionStatus.put(orderId,  "PASS:->"+statusCode+":->"+payload+":->"+response.asString());
    	   }   	  
       	   else  if(ob.toString().contains("AuctionNotOpen"))
    	   {
    	   orderId = "OrderErrorId" + errorId;
    	   errorId= errorId+1;
           orderJSONObjecMap.put(orderId, ob);	
    	   transactionStatus.put(orderId,  "PASS:->"+statusCode+":->"+payload+":->"+response.asString());
    	   }
      	   else  if(ob.toString().contains("EndpointMismatch"))
    	   {
    	   orderId = "OrderErrorId" + errorId;
    	   errorId= errorId+1;
           orderJSONObjecMap.put(orderId, ob);	
    	   transactionStatus.put(orderId,  "PASS:->"+statusCode+":->"+payload+":->"+response.asString());
    	   }
      	   else  if(ob.toString().contains("ClientOrderIdMustBeString"))
    	   {
    	   orderId = "OrderErrorId" + errorId;
    	   errorId= errorId+1;
           orderJSONObjecMap.put(orderId, ob);	
    	   transactionStatus.put(orderId,  "PASS:->"+statusCode+":->"+payload+":->"+response.asString());
    	   }
    	   else  if(ob.toString().contains("MissingNonce"))
    	   {
    	   orderId = "OrderErrorId" + errorId;
    	   errorId= errorId+1;
           orderJSONObjecMap.put(orderId, ob);	
    	   transactionStatus.put(orderId,  "PASS:->"+statusCode+":->"+payload+":->"+response.asString());
    	   }
    	   else
    	   {
    		   orderId = "OrderErrorId" + errorId;
        	   errorId= errorId+1;
               orderJSONObjecMap.put(orderId, ob);	
        	   transactionStatus.put(orderId,  "FAIL:->"+statusCode+":->"+payload+":->"+response.asString());   
    	   }

       }
}

//write to excel the results	
 @Test(priority=2)	
 public static void writeToExcelFinal()
 {
	 //Use Unique filename
	 Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	 geminiExchangeFinal.resultFileName = geminiExchangeFinal.resultFileName + timestamp.getTime() + ".xlsx";
	//Write the WebService response to output excel if Status 200 is received
	 excelWriteTransactionIds.writeToExecel(geminiExchangeFinal.orderJSONObjecMap,geminiExchangeFinal.transactionStatus,geminiExchangeFinal.resultFileName);
 }
 
 //compare the data - Request data against response data
 public static boolean compareoutBoundRequestWithInboundResponseNewOrder(JSONObject requestObject,JSONObject responseObject)
 {
	boolean requestMatchResponse = false;
	 //add the key value pair
	 Map<String,String> inputToOutputMapping = new LinkedHashMap<String,String>();
	 inputToOutputMapping.put("symbol", "symbol");
	 inputToOutputMapping.put("amount", "original_amount");
	 inputToOutputMapping.put("price", "price");
	 inputToOutputMapping.put("side", "side");
	 inputToOutputMapping.put("type", "type");
	 //	inputToOutputMapping.put("exchange limit", "exchange limit");
	// inputToOutputMapping.put("nonce", "timestampms");
	 inputToOutputMapping.put("options", "options");

	//iterate over the mapping and verify input and output for common attributes
	 for(Object key : inputToOutputMapping.keySet())
	 {
		System.out.println(requestObject.get(key).toString());
		System.out.println(responseObject.get(inputToOutputMapping.get(key)).toString());

		
		String requestValue = requestObject.get(key).toString();
		String responseValue = responseObject.get(inputToOutputMapping.get(key)).toString();
		if(requestValue.equalsIgnoreCase(responseValue))
		{
			requestMatchResponse = true;
		}
		else
		{
			requestMatchResponse = false;	
		}		 
    }
	 
	 //For response only attributes - Was Forced
	 if(!(responseObject.get("was_forced").toString().trim().equals("false")))
	 {
			requestMatchResponse = false;
	 }
	 
	
	 
	 //For response only attributes - Is_hidden
	  if(!(responseObject.get("is_hidden").toString().trim().equals("false")))
	 {
			requestMatchResponse = false;
	 }
	 
		 
	 //For response only attributes - Is_live
	 if( (responseObject.get("is_live").toString().trim().equals("false"))  || (responseObject.get("is_live").toString().trim().equals("true")) )
	 {
			requestMatchResponse = true;
	 } 
	 
	 //For response only attributes - Is_live
	// if( (responseObject.get("is_cancelled").equals("false"))  || (responseObject.get("is_cancelled").equals("true")) )
	 if( (responseObject.get("is_cancelled").toString().trim().equals("false"))  || (responseObject.get("is_cancelled").toString().trim().equals("true")) )	 
	 {
			requestMatchResponse = true;
	 } 
	 
	 
	 //For response only attributes - exchange
	 if(!responseObject.get("type").toString().trim().equals("exchange limit"))
	 {
			requestMatchResponse = false;
	 } 
	 
	 
	return requestMatchResponse;	 
 }
 


}
