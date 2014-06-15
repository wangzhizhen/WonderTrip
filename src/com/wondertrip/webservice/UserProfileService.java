package com.wondertrip.webservice;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import com.wondertrip.model.User;

import android.util.Log;

public class UserProfileService {
	private final String serviceURL = "http://www.chenniao.com:8085/admin/webservice/WebServiceProfile.asmx";
	private final String NameSpace = "http://com.webservice.profile/";
	
	public User queryProfile(String username){
		String SOAPAction = "http://com.webservice.profile/queryProfile";
    	String methodName = "queryProfile";    	
    	SoapObject request = new SoapObject(NameSpace, methodName);  
    	request.addProperty("username",username);
    	SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
    	envelope.bodyOut = request;
    	envelope.dotNet = true;
    	envelope.setOutputSoapObject(request);    	
    	HttpTransportSE ht = new HttpTransportSE(serviceURL);
    	try{
    		ht.call(SOAPAction, envelope);
    		if(envelope.getResponse() != null){
    			SoapObject result = (SoapObject) envelope.getResponse();
    			User user = parseProfile(result);  			
    			if(user.getId() == -1)
    			   return null;
    			else{
    				User.setCurrProfile(user);
        			Log.i("exist id",user.getId()+"");
        			return user;
    			}
    		}
    		else{
    			Log.d("b2c","connection fail");
    			return null;
    		}
    	} catch(Exception e){ 
    		Log.d("b2c",e.toString());
    		e.printStackTrace();
			return null;
    	} 
	}
	
	public User login(String username,String password){
		String SOAPAction = "http://com.webservice.profile/login";
    	String methodName = "login";    	
    	SoapObject request = new SoapObject(NameSpace, methodName);  
    	request.addProperty("username",username);
    	request.addProperty("password",password);
    	SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
    	envelope.bodyOut = request;
    	envelope.dotNet = true;
    	envelope.setOutputSoapObject(request);    	
    	HttpTransportSE ht = new HttpTransportSE(serviceURL);
    	try{
    		ht.call(SOAPAction, envelope);
    		if(envelope.getResponse() != null){
    			SoapObject result = (SoapObject)envelope.getResponse();
    			User user = parseProfile(result);
    			return user;
    		}
    		else{
    			Log.d("b2c","connection fail");
    			return null;
    		}
    	} catch(Exception e){ 
    		Log.d("b2c",e.toString());
    		e.printStackTrace();
			return null;
    	} 
	}
	
	public User updateProfile(User user){
		String SOAPAction = "http://com.webservice.profile/updateProfile";
    	String methodName = "updateProfile";    	
    	SoapObject request = new SoapObject(NameSpace, methodName);  
    	request.addProperty("username",user.getUsername());
    	request.addProperty("gender",user.getGender());
    	request.addProperty("educatedLevel",user.getEducatedLevel());
    	request.addProperty("salaryLevel",user.getSalaryLevel());
    	SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
    	envelope.bodyOut = request;
    	envelope.dotNet = true;
    	envelope.setOutputSoapObject(request);    	
    	HttpTransportSE ht = new HttpTransportSE(serviceURL);
    	try{
    		ht.call(SOAPAction, envelope);
    		if(envelope.getResponse() != null){
    			SoapObject result = (SoapObject) envelope.getResponse();
    			user = parseProfile(result);
    			user.setCurrProfile(user);
    			return user;
    		}
    		else{
    			Log.d("b2c","connection fail");
    			return null;
    		}
    	} catch(Exception e){ 
    		Log.d("b2c",e.toString());
    		e.printStackTrace();
    		return null;
    	} 
	}
	
	public User parseProfile(SoapObject obj){
		User user = new User();
		user.setId(Integer.parseInt(obj.getPropertyAsString(0)));
		user.setUsername(obj.getPropertyAsString(1));
		user.setEducatedLevel(obj.getPropertyAsString(2));
		user.setSalaryLevel(obj.getPropertyAsString(3));
		user.setGender(obj.getPropertyAsString(4));
		return user;
	}
	
	public int register(String username,String password,String male,String nickname){
		String SOAPAction = "http://com.webservice.profile/registerUserByString";
    	String methodName = "registerUserByString";    	
    	SoapObject request = new SoapObject(NameSpace, methodName);  
    	request.addProperty("username",username);
    	request.addProperty("password",password);
    	request.addProperty("gender",male);
    	request.addProperty("nick",nickname);
    	SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
    	envelope.bodyOut = request;
    	envelope.dotNet = true;
    	envelope.setOutputSoapObject(request);    	
    	HttpTransportSE ht = new HttpTransportSE(serviceURL);
    	try{
    		ht.call(SOAPAction, envelope);
    		if(envelope.getResponse() != null){
    			SoapPrimitive result = (SoapPrimitive) envelope.getResponse();
    			int id = Integer.parseInt(result.toString());
    			return id;
    		}
    		else{
    			Log.d("b2c","connection fail");
    			return -1;
    		}
    	} catch(Exception e){ 
    		Log.d("b2c",e.toString());
    		e.printStackTrace();
    		return -1;
    	} 
	}
}
