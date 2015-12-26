package com.crowdsourcing;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class DeviceInfoDao {

	Logger log = Logger.getLogger("Alpit");

	public List<Device> getAllDevices() {

		MongoClient mongoClient = null;
		DB db = null;
		List<Device> data = null;

		try {

			data = new ArrayList<Device>();
			mongoClient = new MongoClient("XXX.XXX.XXX.XXX", 27017);

			db = mongoClient.getDB("SpecsenseDB");
			System.out.println("Connect to database successfully");

			boolean auth = db.authenticate("XXXXX", "XXXXX".toCharArray());
			System.out.println("Authentication: " + auth);

			DBCollection coll = db.getCollection("UE_Measurements");

			System.out
					.println("Collection UE_Measurements selected successfully");

			DBCursor cursor = coll.find();
			log.info("Data getting retrived...");

			while (cursor.hasNext()) {
				DBObject o = cursor.next();

				Device device = new Device();

				device.setLast_scanned((String) o.get("last_scanned"));
				device.setMac((String) o.get("mac"));
				device.setUe_battery_power((String) o.get("ue_battery_power"));
				device.setUe_channel_scanned((Integer) o
						.get("ue_channel_scanned"));
				device.setUe_channel_scanned_power((Double) o
						.get("ue_channel_scanned_power"));
				DBObject dbObject = (DBObject) o.get("loc");

				Location location = new Location();
				location.setType((String) dbObject.get("type"));
				location.setCoordinates((List<Double>) dbObject
						.get("coordinates"));
				device.setLoct(location);

				data.add(device);
			}

		} catch (Exception e) {
			System.out.println("Error occured");
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}

		finally {
			db.cleanCursors(true);
			mongoClient.close();
		}
		return data;
	}
}
