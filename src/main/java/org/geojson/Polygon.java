package org.geojson;

import java.util.Arrays;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Polygon extends Geometry<List<List<LngLatAlt>>> {

	public Polygon() {
		super(new ArrayList<List<LngLatAlt>>());
	}

	public Polygon(List<LngLatAlt> polygon) {
		super(new ArrayList<List<LngLatAlt>>(Collections.singletonList(polygon)));
	}

	public Polygon(LngLatAlt... polygon) {
		this(Arrays.asList(polygon));
	}

	public void setExteriorRing(List<LngLatAlt> points) {
		if (bbox.isEmpty()) {
			bbox.add(0, points);
		} else {
			bbox.set(0, points);
		}
	}

	@JsonIgnore
	public List<LngLatAlt> getExteriorRing() {
		assertExteriorRing();
		return bbox.get(0);
	}

	@JsonIgnore
	public List<List<LngLatAlt>> getInteriorRings() {
		assertExteriorRing();
		return bbox.subList(1, bbox.size());
	}

	public List<LngLatAlt> getInteriorRing(int index) {
		assertExteriorRing();
		return bbox.get(1 + index);
	}

	public void addInteriorRing(List<LngLatAlt> points) {
		assertExteriorRing();
		bbox.add(points);
	}

	public void addInteriorRing(LngLatAlt... points) {
		assertExteriorRing();
		bbox.add(Arrays.asList(points));
	}

	private void assertExteriorRing() {
		if (bbox.isEmpty()) {
			throw new RuntimeException("No exterior ring definied");
		}
	}

	@Override
	public <T> T accept(GeoJsonObjectVisitor<T> geoJsonObjectVisitor) {
		return geoJsonObjectVisitor.visit(this);
	}

	@Override
	public String toString() {
		return "Polygon{} " + super.toString();
	}
}
