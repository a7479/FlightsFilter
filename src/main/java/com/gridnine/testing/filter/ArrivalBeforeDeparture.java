package com.gridnine.testing.filter;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;

import java.util.ArrayList;
import java.util.List;

public class ArrivalBeforeDeparture implements Filter {

    /**
     * Найти сегменты с датой прилета, раньше даты вылета
     *
     * @param flights
     * @return список вылетов
     */

    @Override
    public List<Flight> filter(List<Flight> flights) {

        List<Flight> result = new ArrayList<>(flights);
        for (Flight flight : flights) {
            List<Segment> segments = flight.getSegments();
            for (Segment segment : segments) {
                if (segment.getArrivalDate().isBefore(segment.getDepartureDate())) {
                    result.remove(flight);
                }
            }
        }
        return result;

    }


}
