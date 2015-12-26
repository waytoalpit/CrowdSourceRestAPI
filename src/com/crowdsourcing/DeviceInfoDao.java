package com.crowdsourcing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class DeviceInfoDao {

	Logger log = Logger.getLogger("DeviceInfoDao");
	Properties prop = null;

	DB db = null;
	List<Device> data = null;
	Connection conn = null;

	public DeviceInfoDao() {

		prop = new Properties();
		try {
			prop.load(getClass().getResourceAsStream("/com/resources/config.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		conn = new Connection();
	}

	public List<Device> getAllDevices() {
		try {

			db = conn.connectMongoDB();
			conn.authenticateDB();

			data = new ArrayList<Device>();

			String deviceCollection = prop.getProperty("deviceCollection");
			DBCollection coll = db.getCollection(deviceCollection);

			log.info("Collection UE_Measurements selected successfully");

			DBCursor cursor = coll.find();
			log.info("Retrieving data");

			while (cursor.hasNext()) {
				DBObject o = cursor.next();

				Device device = new Device();

				device.setLast_scanned((String) o.get("last_scanned"));
				device.setMac((String) o.get("mac"));
				device.setUe_battery_power((String) o.get("ue_battery_power"));
				device.setUe_channel_scanned((Integer) o.get("ue_channel_scanned"));
				device.setUe_channel_scanned_power((Double) o.get("ue_channel_scanned_power"));
				DBObject dbObject = (DBObject) o.get("loc");

				Location location = new Location();
				location.setType((String) dbObject.get("type"));
				location.setCoordinates((List<Double>) dbObject.get("coordinates"));
				device.setLoct(location);

				data.add(device);
			}

			log.info("data retrieved successfully");

		} catch (Exception e) {
			System.out.println("Error occured");
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}

		finally {
			db.cleanCursors(true);
			conn.mongoClient.close();
		}
		return data;
	}

}