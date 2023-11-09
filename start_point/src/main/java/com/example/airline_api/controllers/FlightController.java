package com.example.airline_api.controllers;

import com.example.airline_api.models.BookingDTO;
import com.example.airline_api.models.Flight;
import com.example.airline_api.models.Passenger;
import com.example.airline_api.models.PassengerDTO;
import com.example.airline_api.repositories.FlightRepository;
import com.example.airline_api.repositories.PassengerRepository;
import com.example.airline_api.services.FlightService;
import com.example.airline_api.services.PassengerService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    FlightRepository flightRepository;

    @Autowired
    FlightService flightService;

    @Autowired
    PassengerRepository passengerRepository;

    @Autowired
    PassengerService passengerService;
    // Display all available flights
    @GetMapping
    public ResponseEntity<List<Flight>> getAllFlights(){

        return new ResponseEntity<>(flightRepository.findAll(), HttpStatus.OK);
    }

    // Display a specific flight
    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<Flight>> getFlightById(@PathVariable Long id){
        return new ResponseEntity<>(flightRepository.findById(id), HttpStatus.OK);
    }

    // Add details of a new flight
    @PostMapping
    public ResponseEntity<List<Flight>> addNewFlight(@RequestBody Flight flight){
        flightRepository.save(flight);
        return new ResponseEntity<>(flightRepository.findAll(), HttpStatus.CREATED);
    }

    // Book passenger on a flight
//    @PatchMapping(value = "/{flightId}")
//    public ResponseEntity<Flight> addPassengerToFlight(@PathVariable long flightId, @RequestBody long passengerId){
//        Flight flightToAddPassenger = flightRepository.findById(flightId).get();
//        Passenger passengerToAdd = passengerRepository.findById(passengerId).get();
//        flightToAddPassenger.addPassenger(passengerToAdd);
//
//        flightRepository.save(flightToAddPassenger);
//        return new ResponseEntity<>(flightToAddPassenger, HttpStatus.OK);
//    }



    @PatchMapping(value = "/{id}")
    public ResponseEntity<Flight> addPassengerToFlight(@RequestBody BookingDTO bookingDTO,
                                                       @PathVariable Long id){
        Flight flightToAddPassenger = flightService.addPassengerToFlight(id, bookingDTO.getPassengerId());
        return new ResponseEntity<>(flightToAddPassenger, HttpStatus.OK);
    }

        // Cancel flight
    @DeleteMapping(value = "/{id}")
    public ResponseEntity cancelFlight(@PathVariable long id){

        flightService.cancelFlight(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

}
