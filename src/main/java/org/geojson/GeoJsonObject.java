package org.geojson;

import java.io.Serializable;
import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import lombok.Data;
import lombok.Setter;

@JsonTypeInfo(property = "type", use = Id.NAME)
@JsonSubTypes({ @Type(Feature.class), @Type(Polygon.class), @Type(MultiPolygon.class), @Type(FeatureCollection.class),
		@Type(Point.class), @Type(MultiPoint.class), @Type(MultiLineString.class), @Type(LineString.class),
                @Type(GeometryCollection.class) })
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Setter
public abstract class GeoJsonObject implements Serializable {

	Crs crs;
	double[] coordinates;	

	public abstract <T> T accept(GeoJsonObjectVisitor<T> geoJsonObjectVisitor);

	@Override public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		GeoJsonObject that = (GeoJsonObject)o;
		if (crs != null ? !crs.equals(that.crs) : that.crs != null)
			return false;
		return Arrays.equals(coordinates, that.coordinates);
	}

	@Override public int hashCode() {
		int result = crs != null ? crs.hashCode() : 0;
		result = 31 * result + (coordinates != null ? Arrays.hashCode(coordinates) : 0);
		return result;
	}

	@Override
	public String toString() {
		return "GeoJsonObject{}";
	}
}
