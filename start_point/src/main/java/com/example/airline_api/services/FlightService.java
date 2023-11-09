package com.example.airline_api.services;


import com.example.airline_api.models.Flight;
import com.example.airline_api.models.Passenger;
import com.example.airline_api.models.PassengerDTO;
import com.example.airline_api.repositories.FlightRepository;
import com.example.airline_api.repositories.PassengerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FlightService {

    @Autowired
    FlightRepository flightRepository;
    @Autowired
    PassengerRepository passengerRepository;

    public Flight findFlight(Long id){
        return flightRepository.findById(id).get();
    }


//    public Passenger addPassenger(long flightId, long passengerId){
//        Flight flight = flightRepository.findById(flightId).get();
//        Passenger passenger = passengerRepository.findById(passengerId).get();
//        flight.addPassenger(passenger);
//
//    }

//    @Transactional
//    public Passenger addPassengerToFlight(long flightId, long passengerId);

    @Transactional
    public Flight addPassengerToFlight(long flightId, long passengerId){
        Flight flightToAddPassenger = flightRepository.findById(flightId).get();
        Passenger passengerToBook = passengerRepository.findById(passengerId).get();
        List<Passenger> flight = flightToAddPassenger.getPassengers();
        flight.add(passengerToBook);
        flightToAddPassenger.setPassengers(flight);
        flightRepository.save(flightToAddPassenger);
        return flightToAddPassenger;
    }

    public void cancelFlight(long id){
        Flight flight = flightRepository.findById(id).get();
        for (Passenger passenger : flight.getPassengers()){
            passenger.removeFlight(flight);
        }
        flightRepository.deleteById(id);
    }

}
