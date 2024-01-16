package com.gridnine.testing;

import com.gridnine.testing.builder.FlightBuilder;
import com.gridnine.testing.filter.ArrivalBeforeDeparture;
import com.gridnine.testing.filter.DepartureBeforeTheCurrentTime;
import com.gridnine.testing.filter.WaitingTimeIsMoreThanTwoHours;
import com.gridnine.testing.model.Flight;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Flight> flights = FlightBuilder.createFlights();


        System.out.println(" Вывод всех полетов:");
        printFlights(flights);
        System.out.println("\n Список вылетов, за исключением вылетов до текущего момента времени:");
        printFlights(new DepartureBeforeTheCurrentTime().filter(flights));
        System.out.println("\n Список вылетов, за исключением сегментов с датой прилёта раньше даты вылета:");
        printFlights(new ArrivalBeforeDeparture().filter(flights));
        System.out.println("\n Список вылетов, за исключением перелетов, где общее время, проведённое на земле, превышает два часа:");
        printFlights(new WaitingTimeIsMoreThanTwoHours().filter(flights));
    }
        public static void printFlights(List<Flight> flights) {
            if (!flights.isEmpty()) {
                for (Flight f : flights) {
                    System.out.println(f.toString());
                }
            } else {
                System.out.println("Перелеты не найдены");
            }

    }
}