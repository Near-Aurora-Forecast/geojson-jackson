package org.geojson.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.geojson.GeoJsonObject;
import org.geojson.LngLatAlt;
import org.geojson.Point;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.Assert.*;

public class PointTest {

	private ObjectMapper mapper = new ObjectMapper();

	public static void assertLngLatAlt(double expectedLongitude, double expectedLatitude, double expectedAltitude,
									   LngLatAlt point) {
		assertEquals(expectedLongitude, point.getLongitude(), 0.00001);
		assertEquals(expectedLatitude, point.getLatitude(), 0.00001);
		if (Double.isNaN(expectedAltitude)) {
			assertFalse(point.hasAltitude());
		} else {
			assertEquals(expectedAltitude, point.getAltitude(), 0.00001);
		}
	}

	@Test
	public void itShouldSerializeAPoint() throws Exception {
		Point point = new Point(100, 0);
		assertEquals("{\"type\":\"Point\",\"coordinates\":[100.0,0.0]}",
				mapper.writeValueAsString(point));
	}

	@Test
	public void itShouldDeserializeAPoint() throws Exception {
		GeoJsonObject value = mapper
				.readValue("{\"type\":\"Point\",\"coordinates\":[100.0,5.0]}", GeoJsonObject.class);
		assertNotNull(value);
		assertTrue(value instanceof Point);
		Point point = (Point)value;
		assertLngLatAlt(100, 5, Double.NaN, point.getBbox());
	}

	@Test
	public void itShouldDeserializeAPointWithAltitude() throws Exception {
		GeoJsonObject value = mapper.readValue("{\"type\":\"Point\",\"coordinates\":[100.0,5.0,123]}",
				GeoJsonObject.class);
		Point point = (Point)value;
		assertLngLatAlt(100, 5, 123, point.getBbox());
	}

	@Test
	public void itShouldSerializeAPointWithAltitude() throws Exception {
		Point point = new Point(100, 0, 256);
		assertEquals("{\"type\":\"Point\",\"coordinates\":[100.0,0.0,256.0]}",
				mapper.writeValueAsString(point));
	}

}
