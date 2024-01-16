package com.gridnine.testing.filter;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DepartureBeforeTheCurrentTime implements Filter {

    /**
     * Найти вылет до текущего времени
     *
     * @param flights
     * @return список вылетов
     */
    @Override
    public List<Flight> filter(List<Flight> flights) {

        List<Flight> result = new ArrayList<>(flights);
        for (Flight flight : flights) {
            for (Segment segment : flight.getSegments()) {
                if (segment.getDepartureDate().isBefore(LocalDateTime.now())) {
                    result.remove(flight);
                }
            }
        }
        return result;
    }
}
