package com.crowdsourcing;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/DeviceService")
public class DeviceService {
	
	DeviceInfoDao deviceInfoDao = new DeviceInfoDao();
   
   @GET
   @Path("/deviceinfo")
   @Produces(MediaType.APPLICATION_JSON)
   public List<Device> getDevices(){
      return deviceInfoDao.getAllDevices();
   }

}