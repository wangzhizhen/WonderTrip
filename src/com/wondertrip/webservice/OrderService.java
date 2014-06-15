package com.wondertrip.webservice;

import java.util.ArrayList;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import com.wondertrip.model.Order;
import com.wondertrip.model.User;
import com.wondertrip.model.OrderDetail;
import android.util.Log;

public class OrderService {
/*
 * 1146-55 -54
 * 
 * 
 */
	private final String serviceURL ="http://www.chenniao.com:8085/admin/webservice/WebServiceOrder.asmx";
	private final String NameSpace = "http://com.webservice.order/";
	
	public int addOrder (ArrayList<Order> orders){
		String SOAPAction = "http://com.webservice.order/addOrder";
    	String methodName = "addOrder";    	
    	SoapObject request = new SoapObject(NameSpace, methodName);  
    	request.addProperty("username",User.currUser.getUsername());
    	SoapObject ids = new SoapObject(NameSpace,methodName);
    	SoapObject dates = new SoapObject(NameSpace,methodName);
    	SoapObject counts = new SoapObject(NameSpace,methodName);
    	SoapObject prices = new SoapObject(NameSpace,methodName);
    	SoapObject runIds = new SoapObject(NameSpace,methodName);
    	SoapObject ticketIds = new SoapObject(NameSpace,methodName);
    	for(Order order:orders){
    		ids.addProperty("int", order.getId());
    		dates.addProperty("dateTime",order.getDate());
    		counts.addProperty("int",order.getCount());
    		//change to float after the webservice is modified
    		prices.addProperty("decimal",order.getPrice()+"");
    		//
    		runIds.addProperty("int",order.getRunId());
    		ticketIds.addProperty("int",order.getTicketId());
    	}
    	request.addProperty("orderedIds",ids);
    	request.addProperty("orderedDates",dates);
    	request.addProperty("orderedCounts",counts);
    	request.addProperty("orderedPrices",prices);
    	request.addProperty("orderedRunIds",runIds);
    	request.addProperty("orderedTicketIds",ticketIds);
    	request.addProperty("payType",1);
    	request.addProperty("sendType",1);
    	request.addProperty("area1",1);
    	request.addProperty("area2",493);
    	request.addProperty("area3",386);
    	request.addProperty("realname","");
    	request.addProperty("gender",User.currUser.getGender());
    	request.addProperty("mobile","");
    	request.addProperty("phone","");
    	request.addProperty("email","test@test.test");
    	request.addProperty("identityNum","");
    	request.addProperty("address","");
    	request.addProperty("post","");
    	request.addProperty("tip","");
    	SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
    	envelope.bodyOut = request;
    	envelope.dotNet = true;
    	envelope.setOutputSoapObject(request);    	
    	HttpTransportSE ht = new HttpTransportSE(serviceURL);
    	try{
    		ht.call(SOAPAction, envelope);
    		if(envelope.getResponse() != null){
    			Object result = (Object) envelope.getResponse();
    			int orderId = Integer.parseInt(result.toString());
    			Log.d("order success",orderId+"");
    			return orderId;
    		}
    		else{
    			Log.d("b2c","connection fail");
    			return 0;
    		}
    	} catch(Exception e){ 
    		Log.d("b2c",e.toString());
    		e.printStackTrace();
			return 0;
    	} 
	}
	
	public ArrayList<OrderDetail> queryOrderDetail(int orderId) {
		// TODO Auto-generated method stub
		String SOAPAction = "http://com.webservice.order/queryOrderDetail";
    	String methodName = "queryOrderDetail";    	
    	SoapObject request = new SoapObject(NameSpace, methodName);  
    	request.addProperty("orderMainId",orderId);
    	SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
    	envelope.bodyOut = request;
    	envelope.dotNet = true;
    	envelope.setOutputSoapObject(request);    	
    	HttpTransportSE ht = new HttpTransportSE(serviceURL);
    	ArrayList<OrderDetail> list = new ArrayList<OrderDetail>();
    	try{
    		ht.call(SOAPAction, envelope);
    		if(envelope.getResponse() != null){
    			SoapObject result = (SoapObject) envelope.getResponse();
    			for(int i=0;i<result.getPropertyCount();i++){
    				SoapObject orderDetailObj = (SoapObject)result.getProperty(i);
    				OrderDetail orderDetail = parseOrderDetail(orderDetailObj);
    				list.add(orderDetail);
    			}
    		}
    		else{
    			Log.d("b2c","connection fail");
    		}
			return list;
    	} catch(Exception e){ 
    		Log.d("b2c",e.toString());
    		e.printStackTrace();
			return list;
    	} 
	}  

	private OrderDetail parseOrderDetail(
			SoapObject orderDetailObj) {
		// TODO Auto-generated method stub
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setId(Integer.parseInt(orderDetailObj.getPropertyAsString(0)));
		orderDetail.setOrderId(Integer.parseInt(orderDetailObj.getPropertyAsString(1)));
		orderDetail.setProductId(Integer.parseInt(orderDetailObj.getPropertyAsString(2)));
		orderDetail.setProductName(orderDetailObj.getPropertyAsString(3));
		orderDetail.setProductCount(Integer.parseInt(orderDetailObj.getPropertyAsString(4)));
		orderDetail.setProductPrice(orderDetailObj.getPropertyAsString(5));
		return orderDetail;
	}

	public boolean payOrder(int orderId, String alipay_trade_no) {
		// TODO Auto-generated method stub
		String SOAPAction = "http://com.webservice.order/payOrder";
    	String methodName = "payOrder";    	
    	SoapObject request = new SoapObject(NameSpace, methodName);  
    	request.addProperty("orderId",orderId);
    	request.addProperty("alipay_trade_no",alipay_trade_no);
    	SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
    	envelope.bodyOut = request;
    	envelope.dotNet = true;
    	envelope.setOutputSoapObject(request);    	
    	HttpTransportSE ht = new HttpTransportSE(serviceURL);
    	try{
    		ht.call(SOAPAction, envelope);
    		if(envelope.getResponse() != null){
    			return Boolean.parseBoolean(envelope.getResponse().toString());
    		}
    		else{
    			Log.d("b2c","connection fail");
    		}
			return false;
    	} catch(Exception e){ 
    		Log.d("b2c",e.toString());
    		e.printStackTrace();
			return false;
    	} 
	}
	
	
}