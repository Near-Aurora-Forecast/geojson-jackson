package org.geojson;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class Geometry<T> extends GeoJsonObject {

	@JsonProperty("coordinates")
	protected T bbox;

	public Geometry() {
	}

	public Geometry(T bbox) {
		this.bbox = bbox;
	}

	public T getBbox() {
		return this.bbox;
	}

	public void setBbox(T bbox) {
		this.bbox = bbox;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Geometry)) {
			return false;
		}
		if (!super.equals(o)) {
			return false;
		}
		Geometry geometry = (Geometry)o;
		return !(bbox != null ? !bbox.equals(geometry.bbox) : geometry.bbox != null);
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (bbox != null ? bbox.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Geometry{" + "coordinates=" + bbox + "} " + super.toString();
	}
}
