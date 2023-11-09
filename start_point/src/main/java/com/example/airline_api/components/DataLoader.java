package com.example.airline_api.components;

import com.example.airline_api.models.Flight;
import com.example.airline_api.models.Passenger;
import com.example.airline_api.repositories.FlightRepository;
import com.example.airline_api.repositories.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    PassengerRepository passengerRepository;

    @Autowired
    FlightRepository flightRepository;

    public DataLoader(){

    }

    @Override
    public void run(ApplicationArguments args) throws Exception{
        Flight germany = new Flight("Germany",120,"11/12/23","0800");
        flightRepository.save(germany);

        Passenger john = new Passenger("John","john@gmail.com");
        john.addFlight(germany);
        passengerRepository.save(john);

        Passenger james = new Passenger("James","james@gmail.com");
        james.addFlight(germany);
        passengerRepository.save(james);

        Flight spain = new Flight("Spain", 80, "22/12/23", "1000");
        flightRepository.save(spain);

        Passenger jim = new Passenger("Jim", "jim@gmail.com");
        jim.addFlight(spain);
        jim.addFlight(germany);
        passengerRepository.save(jim);


    }
}
