package org.geojson;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class MultiPolygon extends Geometry<List<List<List<LngLatAlt>>>> {

	public MultiPolygon() {
		this(Collections.<List<List<LngLatAlt>>>emptyList());

	}

	public MultiPolygon(Polygon polygon) {
		this(Collections.singletonList(polygon.getBbox()));
	}

	private MultiPolygon(List<List<List<LngLatAlt>>> elements) {
		super(new ArrayList<List<List<LngLatAlt>>>(elements));
	}

	public MultiPolygon add(Polygon polygon) {
		bbox.add(polygon.getBbox());
		return this;
	}

	@Override
	public <T> T accept(GeoJsonObjectVisitor<T> geoJsonObjectVisitor) {
		return geoJsonObjectVisitor.visit(this);
	}

	@Override
	public String toString() {
		return "MultiPolygon{} " + super.toString();
	}
}
