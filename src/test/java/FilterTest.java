import com.gridnine.testing.filter.ArrivalBeforeDeparture;
import com.gridnine.testing.filter.DepartureBeforeTheCurrentTime;
import com.gridnine.testing.filter.Filter;
import com.gridnine.testing.filter.WaitingTimeIsMoreThanTwoHours;
import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.time.LocalDateTime.of;

public class FilterTest {


    public static final Segment seg1 = new Segment(of(2024,1,25,16,00),
            of(2024,1,25,18,00));

    public static final Segment seg2 = new Segment(LocalDateTime.now().minusHours(1),
            of(2024,1,25,18,00));

    public static final Segment seg3 = new Segment(of(2024,1,25,16,00),
            of(2024,1,25,18,00));

    public static final Segment seg4 = new Segment(of(2024,1,25,19,00),
            of(2024,1,25,23,00));

    public static final Segment seg5 = new Segment(of(2024,1,26,01,00),
            of(2024,1,26,10,00));

    public static final Segment seg6 = new Segment(of(2024,1,25,16,00),
            of(2024,1,25,11,00));

    /* normal case */
    public static final Flight flight1 = new Flight(Arrays.asList(seg1));

    /*departure date is before requested date*/
    public static final Flight flight2 = new Flight(Arrays.asList(seg2));

    /* normal case, 2 segments*/
    public static final Flight flight3 = new Flight(Arrays.asList(seg3, seg4));

    /* ground time is more than 2 hours */
    public static final Flight flight4 = new Flight(Arrays.asList(seg3, seg4, seg5));

    /* arrival dateTime is before departure dateTime */
    public static final Flight flight5 = new Flight(Arrays.asList(seg6));

    public static List<Flight> getList() {
        return Arrays.asList(flight1,flight2,flight3,flight4,flight5);
    }

    List<Flight> testFlights;
    Filter flightFilter = new DepartureBeforeTheCurrentTime();
    Filter flightFilter2 = new ArrivalBeforeDeparture();
    Filter flightFilter3 = new WaitingTimeIsMoreThanTwoHours();

    @BeforeEach
    void setUp() {
        testFlights = getList();
    }

    @AfterEach
    void reset() {
        testFlights = null;
    }

    @Test
    public void DepartureBeforeTheCurrentTimeTest(){
        List<Flight> expected = new ArrayList<>(testFlights);
        List<Flight> actual = flightFilter.filter(testFlights);
        expected.remove(1);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void ArrivalBeforeDepartureTest() {
        List<Flight> expected = new ArrayList<>(testFlights);
        List<Flight> actual = flightFilter2.filter(testFlights);
        expected.remove(4);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void WaitingTimeIsMoreThanTwoHoursTest() {
        List<Flight> expected = new ArrayList<>(testFlights);
        List<Flight> actual = flightFilter3.filter(testFlights);
        expected.remove(3);
        Assertions.assertEquals(expected, actual);
    }

}
