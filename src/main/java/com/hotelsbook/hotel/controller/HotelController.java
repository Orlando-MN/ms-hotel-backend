package com.hotelsbook.hotel.controller;

import com.hotelsbook.hotel.dto.HotelAvailableDTO;
import com.hotelsbook.hotel.response.ErrorResponse;
import com.hotelsbook.hotel.service.HotelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/api/hotels")
public class HotelController {
	
	private static final Logger logger = LoggerFactory.getLogger(HotelController.class);

	@Autowired
    private HotelService hotelService;

    @GetMapping("/available")
    public ResponseEntity<?> getAvailableHotelsWithServices(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate,
            @RequestParam("cityId") Integer cityId) {
    	
    	try {
    	
	    	logger.info("método getAvailableHotelsWithServices()");
	    	
	        List<HotelAvailableDTO> hotels = hotelService.getAvailableHotelsWithServicesAndReviews(startDate, endDate, cityId);
	        
	        if (hotels.isEmpty()) {
	            return new ResponseEntity<>(new ErrorResponse(404, "No se encontraron registros"), HttpStatus.NOT_FOUND);
	        }
	        
	        return ResponseEntity.ok(hotels);
	        
    	} catch ( Exception e ) {
    		logger.error("error in getAvailableHotelsWithServices", e);
    		ErrorResponse error = new ErrorResponse(500, "Error interno del servidor");
    		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    }
}
