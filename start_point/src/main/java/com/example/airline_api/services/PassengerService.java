package com.example.airline_api.services;

import com.example.airline_api.models.Flight;
import com.example.airline_api.models.Passenger;
import com.example.airline_api.models.PassengerDTO;
import com.example.airline_api.repositories.FlightRepository;
import com.example.airline_api.repositories.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PassengerService {

    @Autowired
    PassengerRepository passengerRepository;

    @Autowired
    FlightService flightService;

    public Passenger savePassenger(PassengerDTO passengerDTO){
        Passenger passenger = new Passenger(passengerDTO.getName(), passengerDTO.getEmail());

        for (Long flightId : passengerDTO.getFlightIds()){
            Flight flight = flightService.findFlight(flightId);
            passenger.addFlight(flight);
        }
        passengerRepository.save(passenger);
        return passenger;
    }

    public Optional<Passenger> findPassenger(Long id){
        return passengerRepository.findById(id);
    }
}
