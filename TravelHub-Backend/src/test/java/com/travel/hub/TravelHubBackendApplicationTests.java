package com.travel.hub;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.travel.model.*;
import com.travel.model.Review;
import com.travel.model.User;
import com.travel.model.profile;
import com.travel.repository.*;
import com.travel.service.TravelHubService;

@SpringBootTest
@AutoConfigureMockMvc
class TravelHubBackendApplicationTests {

    @Autowired
    private MockMvc mockMvc;
    
    @Mock
    private TravelHubService Service;
    
    @InjectMocks
    private TravelHubService travelHubService;
    
    @Mock
    private UserRepository userRepository;
    @Mock
    private HotelRepository hotelRepository;
    
    @Mock
    private ProfileRepository profileRepository;
    
    @Mock
    private ReviewRepository reviewRepository;
    @Mock
    private NotifyRepository notifyRepository;

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private hotelbookingrepository hotelBookingRepository;

    @Mock
    private flightticketrepository flightTicketRepository;

    @Mock
    private TrainTicketRepository trainTicketRepository;

    @Mock
    private BusTicketRepository busTicketRepository;

    @Mock
    private carbookingrepository carBookingRepository;
    
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @AfterEach
    public void tearDown() {
        Mockito.reset(Service);
    }
    @Test
    public void healthCheckReturnsSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/health")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Application is success!"));

    }

    @Test
    public void rootPathReturnsSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Application is success!"));
    }

	
	/*
	 * @Test public void testSaveUser() throws Exception { User user = new User(1,
	 * "test12@example.com", "password");
	 * when(userRepository.save(user)).thenReturn(user);
	 * 
	 * User savedUser = travelHubService.saveUser(user);
	 * 
	 * assertNotNull(savedUser); assertEquals(user.getEmail(),
	 * savedUser.getEmail()); assertEquals(user.getPassword(),
	 * savedUser.getPassword()); }
	 */
	 
    @Test
    public void testFetchEmail() {
        String email = "test@example.com";
        User user = new User(1, email, "password");
        when(userRepository.findByEmail(email)).thenReturn(user);

        User fetchedUser = travelHubService.fetchemail(email);

        assertNotNull(fetchedUser);
        assertEquals(user.getEmail(), fetchedUser.getEmail());
    }
    @Test
    public void testFetchProfile() {
        String email = "reshmavaase123@gmaiil.com";
        profile userProfile = new profile();
        when(profileRepository.findByEmail(email)).thenReturn(userProfile);

        profile fetchedProfile = travelHubService.fetchprofile(email);

        assertNotNull(fetchedProfile);
        assertEquals(userProfile.getEmail(), fetchedProfile.getEmail());
    }
    @Test
    public void testGetReview() {
        // Prepare test data: Insert some Review objects into the database
        
        // Call the actual repository to retrieve the reviews from the database
        List<Review> reviewsFromDatabase = reviewRepository.findAll();

        // Call the service method to retrieve reviews
        List<Review> retrievedReviews = travelHubService.getReview();

        // Verify that the service returns the same list of reviews as retrieved from the database
        assertEquals(reviewsFromDatabase, retrievedReviews);
    }
    @Test
    public void testGetAllHotels() {
        // Prepare test data: Insert some Hotel objects into the database
        List<Hotel> hotels = new ArrayList<>();
        // Add some hotels to the list

        // Mock the behavior of the hotel repository to return the list of hotels
        when(hotelRepository.findAll()).thenReturn(hotels);

        // Call the service method to retrieve all hotels
        List<Hotel> retrievedHotels = travelHubService.getAllHotels();

        // Verify that the service returns the same list of hotels as retrieved from the repository
        assertEquals(hotels, retrievedHotels);
    }
    @Test
    public void testGetAllNotify() {
        // Prepare test data: Insert some Notify objects into the database
        List<Notify> notifies = new ArrayList<>();
        // Add some notifies to the list

        // Mock the behavior of the notify repository to return the list of notifies
        when(notifyRepository.findAll()).thenReturn(notifies);

        // Call the service method to retrieve all notifies
        List<Notify> retrievedNotifies = travelHubService.getAllnotify("test@test.com");

        // Verify that the service returns the same list of notifies as retrieved from the repository
        assertEquals(notifies, retrievedNotifies);
    }
    @Test
    public void testGetAllRestaurants() {
        // Prepare test data: Insert some Restaurant objects into the database
        List<Restaurant> restaurants = new ArrayList<>();
        // Add some restaurants to the list

        // Mock the behavior of the restaurant repository to return the list of restaurants
        when(restaurantRepository.findAll()).thenReturn(restaurants);

        // Call the service method to retrieve all restaurants
        List<Restaurant> retrievedRestaurants = travelHubService.getallrestaurants();

        // Verify that the service returns the same list of restaurants as retrieved from the repository
        assertEquals(restaurants, retrievedRestaurants);
    }
    @Test
    public void testGetBookingsByUserEmail() {
        // Prepare test data: Insert some HotelBooking objects into the database
        List<HotelBooking> bookings = new ArrayList<>();
        // Add some bookings to the list

        // Mock the behavior of the hotel booking repository to return the list of bookings
        when(hotelBookingRepository.findByUserEmail("reshmavaase123@gmail.com")).thenReturn(bookings);

        // Call the service method to retrieve all hotel bookings for the provided email
        List<HotelBooking> retrievedBookings = travelHubService.getBookingsByUserEmail("reshmavaase123@gmail.com");

        // Verify that the service returns the same list of hotel bookings as retrieved from the repository
        assertEquals(bookings, retrievedBookings);
    }
    @Test
    public void testGetFlightTicketsByUserEmail() {
        List<FlightTicket> flightTickets = new ArrayList<>();
        // Add some flight tickets to the list

        when(flightTicketRepository.findByUserEmail("reshmavaase123@gmail.com")).thenReturn(flightTickets);

        List<FlightTicket> retrievedFlightTickets = travelHubService.getflightByUserEmail("reshmavaase123@gmail.com");

        assertEquals(flightTickets, retrievedFlightTickets);
    }

    @Test
    public void testGetTrainTicketsByUserEmail() {
        List<TrainTicket> trainTickets = new ArrayList<>();
        // Add some train tickets to the list

        when(trainTicketRepository.findByUserEmail("reshmavaase123@gmail.com")).thenReturn(trainTickets);

        List<TrainTicket> retrievedTrainTickets = travelHubService.getTrainByUserEmail("reshmavaase123@gmail.com");

        assertEquals(trainTickets, retrievedTrainTickets);
    }

    @Test
    public void testGetBusTicketsByUserEmail() {
        List<BusTicket> busTickets = new ArrayList<>();
        // Add some bus tickets to the list

        when(busTicketRepository.findByUserEmail("reshmavaase123@gmail.com")).thenReturn(busTickets);

        List<BusTicket> retrievedBusTickets = travelHubService.getBusByUserEmail("reshmavaase123@gmail.com");

        assertEquals(busTickets, retrievedBusTickets);
    }

    @Test
    public void testGetCarBookingsByUserEmail() {
        List<CarBooking> carBookings = new ArrayList<>();
        // Add some car bookings to the list

        when(carBookingRepository.findByUserEmail("reshmavaase123@gmail.com")).thenReturn(carBookings);

        List<CarBooking> retrievedCarBookings = travelHubService.getCarByUserEmail("reshmavaase123@gmail.com");

        assertEquals(carBookings, retrievedCarBookings);
    }
    @Test
    public void testFetchUserByEmailAndPassword() {
        String email = "reshmavaase123@gmail.com";
        String password = "demo123";

        // Create a mock User object
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        // Mock the behavior of the userRepository
        when(userRepository.findByEmailAndPassword(email, password)).thenReturn(user);

        // Call the method under test
        User retrievedUser = travelHubService.fetchuseremailandpassword(email, password);

        // Verify that the returned user matches the expected user
        assertEquals(email, retrievedUser.getEmail());
        assertEquals(password, retrievedUser.getPassword());
    }
}
