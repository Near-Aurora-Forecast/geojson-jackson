package org.geojson;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class MultiLineString extends Geometry<List<List<LngLatAlt>>> {

	public MultiLineString() {
		super(new ArrayList<List<LngLatAlt>>());
	}

	public MultiLineString(List<LngLatAlt> line) {
		super(new ArrayList<List<LngLatAlt>>(Collections.singletonList(line)));
	}

	public MultiLineString add(List<LngLatAlt> line) {
		bbox.add(line);
		return this;
	}

	@Override
	public <T> T accept(GeoJsonObjectVisitor<T> geoJsonObjectVisitor) {
		return geoJsonObjectVisitor.visit(this);
	}

	@Override
	public String toString() {
		return "MultiLineString{} " + super.toString();
	}
}
