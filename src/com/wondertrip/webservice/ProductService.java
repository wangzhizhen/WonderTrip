package com.wondertrip.webservice;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import com.wondertrip.model.*;


import android.util.Log;

public class ProductService {
	private final String serviceURL = "http://www.chenniao.com:8085/admin/webservice/WebServiceProduct.asmx";
	private final String NameSpace = "http://com.webservice.product/";

	public Product queryProduct(int productId) {
		String SOAPAction = "http://com.webservice.product/queryProduct";
		String methodName = "queryProduct";
		SoapObject request = new SoapObject(NameSpace, methodName);
		request.addProperty("productId", productId);
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.bodyOut = request;
		envelope.dotNet = true;
		envelope.setOutputSoapObject(request);
		HttpTransportSE ht = new HttpTransportSE(serviceURL);
		try {
			ht.call(SOAPAction, envelope);
			if (envelope.getResponse() != null) {
				SoapObject result = (SoapObject) envelope.getResponse();
				Product product = parseProduct(result);
				return product;
			} else {
				Log.d("b2c", "connection fail");
				return null;
			}
		} catch (Exception e) {
			Log.d("b2c", e.toString());
			e.printStackTrace();
			return null;
		}
	}

	public Product parseProduct(SoapObject obj) {
		Product product = new Product();
		product.setId(Integer.parseInt(obj.getPropertyAsString(0)));
		product.setClassId(Integer.parseInt(obj.getPropertyAsString(1)));
		product.setName(obj.getPropertyAsString(2));
		product.setDescription(obj.getPropertyAsString(3));
		product.setSource(obj.getPropertyAsString(4));
		product.setPrice(Float.parseFloat(obj.getPropertyAsString(5)));
		product.setProductType(Integer.parseInt(obj.getPropertyAsString(6)));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			product.setLastUpdateDate(sdf.parse("1990-01-01 00:00:00"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// product.setPageUrl(obj.getPropertyAsString(6));
		product.setImgUrl(obj.getPropertyAsString(8));
		product.setComplexProduct(Boolean.parseBoolean(obj.getPropertyAsString(9)));
		//if(product.isComplexProduct()){
			//product.setCombinationProduct(combinationProduct)
		//}
		return product;
	}

	public ArrayList<Product> queryAll(int type, int start, int size) {
		// TODO Auto-generated method stub
		String SOAPAction = "http://com.webservice.product/queryAll";
		String methodName = "queryAll";
		SoapObject request = new SoapObject(NameSpace, methodName);
		request.addProperty("type", type);
		request.addProperty("start", start);
		request.addProperty("size", size);
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.bodyOut = request;
		envelope.dotNet = true;
		envelope.setOutputSoapObject(request);
		HttpTransportSE ht = new HttpTransportSE(serviceURL);
		ArrayList<Product> list = new ArrayList<Product>();
		try {
			ht.call(SOAPAction, envelope);
			if (envelope.getResponse() != null) {
				SoapObject result = (SoapObject) envelope.getResponse();
				int productnum = result.getPropertyCount();
				for (int i = 0; i < productnum; i++) {
					Product product = parseProduct((SoapObject) result
							.getProperty(i));
					list.add(product);
				}
				return list;
			} else {
				Log.d("b2c", "connection fail");
				return null;
			}
		} catch (Exception e) {
			Log.d("b2c", e.toString());
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<SightspotRun> querySightspotRun(int sightspotId,
			String date) {
		String SOAPAction = "http://com.webservice.product/querySightspotRun";
		String methodName = "querySightspotRun";
		SoapObject request = new SoapObject(NameSpace, methodName);
		request.addProperty("sightspotId", sightspotId);
		request.addProperty("day", date);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		Log.d("queryRunDate", date);
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.bodyOut = request;
		envelope.dotNet = true;
		envelope.setOutputSoapObject(request);
		HttpTransportSE ht = new HttpTransportSE(serviceURL);
		ArrayList<SightspotRun> list = new ArrayList<SightspotRun>();
		try {
			ht.call(SOAPAction, envelope);
			if (envelope.getResponse() != null) {
				SoapObject result = (SoapObject) envelope.getResponse();
				int sightspotRunNum = result.getPropertyCount();
				for (int i = 0; i < sightspotRunNum; i++) {
					SightspotRun sightspotRun = parseSightspotRun((SoapObject) result
							.getProperty(i));
					list.add(sightspotRun);
				}
				return list;
			} else {
				Log.d("b2c", "connection fail");
				return null;
			}
		} catch (Exception e) {
			Log.d("b2c", e.toString());
			e.printStackTrace();
			return null;
		}
	}

	private SightspotRun parseSightspotRun(SoapObject obj) {
		// TODO Auto-generated method stub
		SightspotRun sightspotRun = new SightspotRun();
		sightspotRun.setId(Integer.parseInt(obj.getPropertyAsString(0)));
		sightspotRun.setTicketIds(obj.getPropertyAsString(1));
		sightspotRun.setCanSaleCount(Integer.parseInt(obj
				.getPropertyAsString(2)));
		return sightspotRun;
	}

	public ArrayList<String> querySightspotRundate(int sightspotId,
			String begin, String end) {
		String SOAPAction = "http://com.webservice.product/querySightspotRunDate";
		String methodName = "querySightspotRunDate";
		SoapObject request = new SoapObject(NameSpace, methodName);
		request.addProperty("sightspotId", sightspotId);
		request.addProperty("begin", begin);
		request.addProperty("end", end);
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.bodyOut = request;
		envelope.dotNet = true;
		Log.d("service","query sightspot id :"+sightspotId);
		envelope.setOutputSoapObject(request);
		HttpTransportSE ht = new HttpTransportSE(serviceURL);
		ArrayList<String> list = new ArrayList<String>();
		try {
			ht.call(SOAPAction, envelope);
			if (envelope.getResponse() != null) {
				SoapObject result = (SoapObject) envelope.getResponse();
				int sightspotRundateNum = result.getPropertyCount();
				for (int i = 0; i < sightspotRundateNum; i++) {
					String xmlDate = result.getProperty(i).toString();
					String sightspotRundate = xmlDate.substring(0,
							xmlDate.indexOf("T"));
					list.add(sightspotRundate);
				}
				return list;
			} else {
				Log.d("b2c", "connection fail");
				return null;
			}
		} catch (Exception e) {
			Log.d("b2c", e.toString());
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<Ticket> querySightspotTicket(String tickets) {
		String SOAPAction = "http://com.webservice.product/querySightspotTicket";
		String methodName = "querySightspotTicket";
		SoapObject request = new SoapObject(NameSpace, methodName);
		request.addProperty("ticketIds", tickets);
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER10);
		envelope.bodyOut = request;
		envelope.dotNet = true;
		envelope.setOutputSoapObject(request);
		HttpTransportSE ht = new HttpTransportSE(serviceURL);
		ArrayList<Ticket> list = new ArrayList<Ticket>();
		try {
			ht.call(SOAPAction, envelope);
			if (envelope.getResponse() != null) {
				SoapObject result = (SoapObject) envelope.getResponse();
				int sightspotTicketNum = result.getPropertyCount();
				for (int i = 0; i < sightspotTicketNum; i++) {
					Ticket ticket = parseTicket((SoapObject) result
							.getProperty(i));
					list.add(ticket);
				}
				return list;
			} else {
				Log.d("b2c", "connection fail");
				return null;
			}
		} catch (Exception e) {
			Log.d("b2c", e.toString());
			e.printStackTrace();
			return null;
		}
	}

	private Ticket parseTicket(SoapObject obj) {
		// TODO Auto-generated method stub
		Ticket ticket = new Ticket();
		ticket.setId(Integer.parseInt(obj.getPropertyAsString(0)));
		ticket.setName(obj.getPropertyAsString(1));
		ticket.setStandPrice(obj.getPropertyAsString(2));
		ticket.setChecked(Integer.parseInt(obj.getPropertyAsString(3)));
		return ticket;
	}

	public ArrayList<HotelRoom> queryHotelRoom(int hotelId, String startDate,
			String endDate) {
		String SOAPAction = "http://com.webservice.product/queryHotelRoom";
		String methodName = "queryHotelRoom";
		SoapObject request = new SoapObject(NameSpace, methodName);
		request.addProperty("hotelId", hotelId);
		request.addProperty("startDate", startDate);
		request.addProperty("endDate", endDate);
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER10);
		envelope.bodyOut = request;
		envelope.dotNet = true;
		envelope.setOutputSoapObject(request);
		HttpTransportSE ht = new HttpTransportSE(serviceURL);
		ArrayList<HotelRoom> list = new ArrayList<HotelRoom>();
		try {
			ht.call(SOAPAction, envelope);
			if (envelope.getResponse() != null) {
				SoapObject result = (SoapObject) envelope.getResponse();
				int hotelRoomNum = result.getPropertyCount();
				for (int i = 0; i < hotelRoomNum; i++) {
					HotelRoom hotelRoom = parseHotelRoom((SoapObject) result
							.getProperty(i));
					list.add(hotelRoom);
				}
				return list;
			} else {
				Log.d("b2c", "connection fail");
				return null;
			}
		} catch (Exception e) {
			Log.d("b2c", e.toString());
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<HotelRoomRun> queryHotelRoomRun(int roomId,
			String startDate, String endDate) {
		String SOAPAction = "http://com.webservice.product/queryHotelRoomRun";
		String methodName = "queryHotelRoomRun";
		SoapObject request = new SoapObject(NameSpace, methodName);
		request.addProperty("roomId", roomId);
		request.addProperty("startDate", startDate);
		request.addProperty("endDate", endDate);
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER10);
		envelope.bodyOut = request;
		envelope.dotNet = true;
		envelope.setOutputSoapObject(request);
		HttpTransportSE ht = new HttpTransportSE(serviceURL);
		ArrayList<HotelRoomRun> list = new ArrayList<HotelRoomRun>();
		try {
			ht.call(SOAPAction, envelope);
			if (envelope.getResponse() != null) {
				SoapObject result = (SoapObject) envelope.getResponse();
				int hotelRoomNum = result.getPropertyCount();
				for (int i = 0; i < hotelRoomNum; i++) {
					HotelRoomRun hotelRoom = parseHotelRoomRun((SoapObject) result
							.getProperty(i));
					list.add(hotelRoom);
				}
				return list;
			} else {
				Log.d("b2c", "connection fail");
				return null;
			}
		} catch (Exception e) {
			Log.d("b2c", e.toString());
			e.printStackTrace();
			return null;
		}
	}

	private HotelRoomRun parseHotelRoomRun(SoapObject obj) {
		// TODO Auto-generated method stub
		HotelRoomRun hotelRoomRun = new HotelRoomRun();
		hotelRoomRun.setId(Integer.parseInt(obj.getPropertyAsString(1)));
		hotelRoomRun.setActive(Integer.parseInt(obj.getPropertyAsString(3)));
		hotelRoomRun.setActive_date(obj.getPropertyAsString(9));
		hotelRoomRun.setTotal_count(Integer.parseInt(obj
				.getPropertyAsString(10)));
		hotelRoomRun.setStand_price(Float.parseFloat(obj
				.getPropertyAsString(12)));
		hotelRoomRun
				.setSet_price(Float.parseFloat(obj.getPropertyAsString(13)));
		return hotelRoomRun;
	}

	private HotelRoom parseHotelRoom(SoapObject obj) {
		// TODO Auto-generated method stub
		HotelRoom hotelRoom = new HotelRoom();
		hotelRoom.setRoomId(Integer.parseInt(obj.getPropertyAsString(0)));
		hotelRoom.setRoomType(obj.getPropertyAsString(1));
		hotelRoom.setStandPrice(obj.getPropertyAsString(2));
		hotelRoom.setPromotePrice(obj.getPropertyAsString(3));
		hotelRoom.setBreakfast(obj.getPropertyAsString(4));
		hotelRoom.setBedType(obj.getPropertyAsString(5));
		hotelRoom.setInternet(obj.getPropertyAsString(6));
		hotelRoom
				.setAvailable(Boolean.parseBoolean(obj.getPropertyAsString(7)));
		return hotelRoom;
	}

	public HotelRoom queryHotelRoomById(int roomId) {
		String SOAPAction = "http://com.webservice.product/queryRoomById";
		String methodName = "queryRoomById";
		SoapObject request = new SoapObject(NameSpace, methodName);
		request.addProperty("roomId", roomId);
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER10);
		envelope.bodyOut = request;
		envelope.dotNet = true;
		envelope.setOutputSoapObject(request);
		HttpTransportSE ht = new HttpTransportSE(serviceURL);
		try {
			ht.call(SOAPAction, envelope);
			if (envelope.getResponse() != null) {
				SoapObject result = (SoapObject) envelope.getResponse();
				HotelRoom room = new HotelRoom();
				room.setRoomId(Integer.parseInt(result.getPropertyAsString(0)));
				room.setRoomType(result.getPropertyAsString(1));
				room.setBreakfast(result.getPropertyAsString(2));
				room.setBedType(result.getPropertyAsString(3));
				room.setInternet(result.getPropertyAsString(4));
				room.setPromotePrice(result.getPropertyAsString(6));
				room.setStandPrice(result.getPropertyAsString(13));
				room.setSquare(result.getPropertyAsString(14));
				return room;
			} else {
				Log.d("b2c", "connection fail");
				return null;
			}
		} catch (Exception e) {
			Log.d("b2c", e.toString());
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<String> queryCpRunDate(int[] ids, int[] days,
			String begin, String end) {
		ArrayList<ArrayList<String>> datesArray = new ArrayList<ArrayList<String>>();
		for (int id : ids) {
			int classId = queryProduct(id).getClassId();
			ArrayList<String> dates = querySightspotRundate(classId, begin, end);
			datesArray.add(dates);
		}
		int index = 0;
		ArrayList<String> list = new ArrayList<String>();

		for (String date : datesArray.get(0)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date d = sdf.parse(date);
				index=0;
				for (ArrayList<String> dates : datesArray) {
					Date d2 = new Date(d.getTime() + 1000L * 60L * 60L * 24L
							* (long)(days[index]-1));
					String date2 = sdf.format(d2);
					if (index != 0) {
						if (!dates.contains(date2)) {
							continue;
						}
					}
					index++;
					if (index == datesArray.size()) {
						list.add(date);
					}
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}

		}

		return list;
	}

	public ArrayList<String> queryTrafficRunDate(int id, String beginDate,
			String endDate) {
		// TODO Auto-generated method stub
		String SOAPAction = "http://com.webservice.product/queryTrafficRunDate";
		String methodName = "queryTrafficRunDate";
		SoapObject request = new SoapObject(NameSpace, methodName);
		request.addProperty("trafficId", id);
		request.addProperty("begin", beginDate);
		request.addProperty("end", endDate);
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.bodyOut = request;
		envelope.dotNet = true;
		envelope.setOutputSoapObject(request);
		HttpTransportSE ht = new HttpTransportSE(serviceURL);
		ArrayList<String> list = new ArrayList<String>();
		try {
			ht.call(SOAPAction, envelope);
			if (envelope.getResponse() != null) {
				SoapObject result = (SoapObject) envelope.getResponse();
				int sightspotRundateNum = result.getPropertyCount();
				for (int i = 0; i < sightspotRundateNum; i++) {
					String xmlDate = result.getProperty(i).toString();
					String trafficRunDate = xmlDate.substring(0,
							xmlDate.indexOf("T"));
					list.add(trafficRunDate);
				}
				return list;
			} else {
				Log.d("b2c", "connection fail");
				return null;
			}
		} catch (Exception e) {
			Log.d("b2c", e.toString());
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<TrafficRun> queryTrafficRun(int classId, String date) {
		// TODO Auto-generated method stub
		String SOAPAction = "http://com.webservice.product/queryTrafficRun";
		String methodName = "queryTrafficRun";
		SoapObject request = new SoapObject(NameSpace, methodName);
		request.addProperty("trafficId", classId);
		request.addProperty("day", date);
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.bodyOut = request;
		envelope.dotNet = true;
		envelope.setOutputSoapObject(request);
		HttpTransportSE ht = new HttpTransportSE(serviceURL);
		ArrayList<TrafficRun> list = new ArrayList<TrafficRun>();
		try {
			ht.call(SOAPAction, envelope);
			if (envelope.getResponse() != null) {
				SoapObject result = (SoapObject) envelope.getResponse();
				for (int i = 0; i < result.getPropertyCount(); i++) {
					TrafficRun trafficRun = parseTrafficRun((SoapObject) result
							.getProperty(i));
					list.add(trafficRun);
				}
				return list;
			} else {
				Log.d("b2c", "connection fail");
				return null;
			}
		} catch (Exception e) {
			Log.d("b2c", e.toString());
			e.printStackTrace();
			return null;
		}
	}

	private TrafficRun parseTrafficRun(
			SoapObject trafficRunObj) {
		// TODO Auto-generated method stub
		TrafficRun trafficRun = new TrafficRun();
		trafficRun.setRunId(Integer.parseInt(trafficRunObj.getPropertyAsString(0)));
		trafficRun.setCanSaleCount(Integer.parseInt(trafficRunObj.getPropertyAsString(1)));
		trafficRun.setTicketIds(trafficRunObj.getPropertyAsString(2));
		trafficRun.setStartTime(trafficRunObj.getPropertyAsString(3));
		trafficRun.setGoContent(trafficRunObj.getPropertyAsString(4));
		trafficRun.setBackContent(trafficRunObj.getPropertyAsString(5));
		return trafficRun;
	}

	public ArrayList<Ticket> queryTrafficTicket(String ticketIds) {
		// TODO Auto-generated method stub
		String SOAPAction = "http://com.webservice.product/queryTrafficTicket";
		String methodName = "queryTrafficTicket";
		SoapObject request = new SoapObject(NameSpace, methodName);
		request.addProperty("ticketIds", ticketIds);
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER10);
		envelope.bodyOut = request;
		envelope.dotNet = true;
		envelope.setOutputSoapObject(request);
		HttpTransportSE ht = new HttpTransportSE(serviceURL);
		ArrayList<Ticket> list = new ArrayList<Ticket>();
		try {
			ht.call(SOAPAction, envelope);
			if (envelope.getResponse() != null) {
				SoapObject result = (SoapObject) envelope.getResponse();
				int sightspotTicketNum = result.getPropertyCount();
				for (int i = 0; i < sightspotTicketNum; i++) {
					Ticket ticket = parseTicket((SoapObject) result
							.getProperty(i));
					list.add(ticket);
				}
				return list;
			} else {
				Log.d("b2c", "connection fail");
				return null;
			}
		} catch (Exception e) {
			Log.d("b2c", e.toString());
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<Product> recommendForUser(int uid) {
		// TODO Auto-generated method stub
		String SOAPAction = "http://com.webservice.product/recommendForUser";
		String methodName = "recommendForUser";
		SoapObject request = new SoapObject(NameSpace, methodName);
		request.addProperty("uid", uid);
		request.addProperty("size", 10);
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER10);
		envelope.bodyOut = request;
		envelope.dotNet = true;
		envelope.setOutputSoapObject(request);
		HttpTransportSE ht = new HttpTransportSE(serviceURL);
		ArrayList<Product> list = new ArrayList<Product>();
		try {
			ht.call(SOAPAction, envelope);
			if (envelope.getResponse() != null) {
				SoapObject result = (SoapObject) envelope.getResponse();
				int productnum = result.getPropertyCount();
				for (int i = 0; i < productnum; i++) {
					Product product = parseProduct((SoapObject) result
							.getProperty(i));
					list.add(product);
				}
				return list;
			} else {
				Log.d("b2c", "connection fail");
				return null;
			}
		} catch (Exception e) {
			Log.d("b2c", e.toString());
			e.printStackTrace();
			return null;
		}
	}
	
	public String getProductByLimitation(ArrayList<Product> list){
		Product product = new Product();
		product.setName("test");
		product.setPrice(100.0f);
		Product product2 = new Product();
		product2.setName("test set ");
		product2.setComplexProduct(true);
		product2.addProduct(product);
		product2.addProduct(product);
		product2.setPrice(150.0f);
		list.add(product2.copy());
		list.add(product2.copy());
		product = new Product();
		product.setName("上海--杭州包车");
		product.setPrice(100);
		list.add(product.copy());
		product.setName("杭州一日游");
		product.setPrice(200);
		list.add(product.copy());
		product.setName("杭州--上海包车");
		product.setPrice(100);
		list.add(product.copy());
		product.setName("test");
		product.setPrice(100.0f);
		list.add(product.copy());
		list.add(product.copy());
		list.add(product.copy());
		list.add(product.copy());
		return "success";
	}

	public ArrayList<RouteRun> queryRouteRun(int routeId,
			String date) {
		String SOAPAction = "http://com.webservice.product/queryCircuitryRun";
		String methodName = "queryCircuitryRun";
		SoapObject request = new SoapObject(NameSpace, methodName);
		request.addProperty("circuitryId", routeId);
		request.addProperty("day", date);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.bodyOut = request;
		envelope.dotNet = true;
		envelope.setOutputSoapObject(request);
		HttpTransportSE ht = new HttpTransportSE(serviceURL);
		ArrayList<RouteRun> list = new ArrayList<RouteRun>();
		try {
			ht.call(SOAPAction, envelope);
			if (envelope.getResponse() != null) {
				SoapObject result = (SoapObject) envelope.getResponse();
				int sightspotRunNum = result.getPropertyCount();
				for (int i = 0; i < sightspotRunNum; i++) {
					RouteRun routeRun = parseRouteRun((SoapObject) result
							.getProperty(i));
					list.add(routeRun);
				}
				return list;
			} else {
				Log.d("b2c", "connection fail");
				return null;
			}
		} catch (Exception e) {
			Log.d("b2c", e.toString());
			e.printStackTrace();
			return null;
		}
	}

	private RouteRun parseRouteRun(SoapObject obj) {
		// TODO Auto-generated method stub
		RouteRun routeRun = new RouteRun();
		routeRun.setId(Integer.parseInt(obj.getPropertyAsString(0)));
		routeRun.setTicketIds(obj.getPropertyAsString(1));
		routeRun.setCanSaleCount(Integer.parseInt(obj
				.getPropertyAsString(2)));
		return routeRun;
	}

	public ArrayList<String> queryRouteRundate(int routeId,
			String begin, String end) {
		String SOAPAction = "http://com.webservice.product/queryCircuitryRunDate";
		String methodName = "queryCircuitryRunDate";
		SoapObject request = new SoapObject(NameSpace, methodName);
		request.addProperty("circuitryId", routeId);
		request.addProperty("begin", begin);
		request.addProperty("end", end);
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.bodyOut = request;
		envelope.dotNet = true;
		envelope.setOutputSoapObject(request);
		HttpTransportSE ht = new HttpTransportSE(serviceURL);
		ArrayList<String> list = new ArrayList<String>();
		try {
			ht.call(SOAPAction, envelope);
			if (envelope.getResponse() != null) {
				SoapObject result = (SoapObject) envelope.getResponse();
				int sightspotRundateNum = result.getPropertyCount();
				for (int i = 0; i < sightspotRundateNum; i++) {
					String xmlDate = result.getProperty(i).toString();
					String sightspotRundate = xmlDate.substring(0,
							xmlDate.indexOf("T"));
					list.add(sightspotRundate);
				}
				return list;
			} else {
				Log.d("b2c", "connection fail");
				return null;
			}
		} catch (Exception e) {
			Log.d("b2c", e.toString());
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<Ticket> queryRouteTicket(String tickets) {
		String SOAPAction = "http://com.webservice.product/queryRouteTicket";
		String methodName = "queryRouteTicket";
		SoapObject request = new SoapObject(NameSpace, methodName);
		request.addProperty("ticketIds", tickets);
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER10);
		envelope.bodyOut = request;
		envelope.dotNet = true;
		envelope.setOutputSoapObject(request);
		HttpTransportSE ht = new HttpTransportSE(serviceURL);
		ArrayList<Ticket> list = new ArrayList<Ticket>();
		try {
			ht.call(SOAPAction, envelope);
			if (envelope.getResponse() != null) {
				SoapObject result = (SoapObject) envelope.getResponse();
				int sightspotTicketNum = result.getPropertyCount();
				for (int i = 0; i < sightspotTicketNum; i++) {
					Ticket ticket = parseTicket((SoapObject) result
							.getProperty(i));
					list.add(ticket);
				}
				return list;
			} else {
				Log.d("b2c", "connection fail");
				return null;
			}
		} catch (Exception e) {
			Log.d("b2c", e.toString());
			e.printStackTrace();
			return null;
		}
	}
}
