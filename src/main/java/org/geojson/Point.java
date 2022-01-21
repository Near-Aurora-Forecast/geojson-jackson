package org.geojson;

public class Point extends Geometry<LngLatAlt> {

	public Point() {
		super();
	}

	public Point(LngLatAlt bbox) {
		this.bbox = bbox;
	}

	public Point(double longitude, double latitude) {
		this(new LngLatAlt(longitude, latitude));
	}

	public Point(double longitude, double latitude, double altitude) {
		this(new LngLatAlt(longitude, latitude, altitude));
	}

	@Override
	public <T> T accept(GeoJsonObjectVisitor<T> geoJsonObjectVisitor) {
		return geoJsonObjectVisitor.visit(this);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Point)) {
			return false;
		}
		if (!super.equals(o)) {
			return false;
		}
		Point point = (Point) o;
		return !(bbox != null ? !bbox.equals(point.bbox)
				: point.bbox != null);
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (bbox != null ? bbox.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Point{} " + super.toString();
	}
}
