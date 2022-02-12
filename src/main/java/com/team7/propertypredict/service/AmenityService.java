package com.team7.propertypredict.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.team7.propertypredict.model.Amenity;
import com.team7.propertypredict.model.AmenityType;

public interface AmenityService {
	
	// Save a list of amenities into the database
	void saveAmenities(List<String> searchList, String amenityType);
	
	// Get AmenityType by type
	AmenityType findByType(String type);

	// Find all Amenities
	List<Amenity> findAllAmenities();
	
	//Find all Amenity Types
	List<AmenityType> findAllAmenityTypes();
	
	// Find all Amenities by Amenity Types
	List<Amenity> findAmenitiesByAmenityType(Integer tid);
}
