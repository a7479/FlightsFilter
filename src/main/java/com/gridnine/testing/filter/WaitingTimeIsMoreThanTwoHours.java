package com.gridnine.testing.filter;

import com.gridnine.testing.model.Flight;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class WaitingTimeIsMoreThanTwoHours implements Filter {

    /**
     * Найти перелеты, где общее время на земле превышает 2 часа
     *
     * @param flights
     * @return список вылетов
     */
    @Override
    public List<Flight> filter(List<Flight> flights) {

        List<Flight> result = new ArrayList<>(flights);

        for (Flight f : flights) {
            long countMinutes = 0;
            for (int i = 0; i < f.getSegments().size() - 1; i++) {
                LocalDateTime segment1 = f.getSegments().get(i).getArrivalDate();
                LocalDateTime segment2 = f.getSegments().get(i + 1).getDepartureDate();
                countMinutes += ChronoUnit.MINUTES.between(segment1, segment2);
            }
            if (countMinutes > 120) {
                result.remove(f);
            }
        }
        return result;
    }
}
