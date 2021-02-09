package org.gourav.flight;

import org.gourav.flight.*;
import org.junit.jupiter.api.*;

public class TestAirportFlight {
    private static final String BUSINESS = "Business";
    private static final String ECONOMY = "Economy";
    private static final String PREMIUM = "Premium";
    Flight business = null;
    Flight economy = null;
    Flight premium = null;
    Passenger usualPassengerSaytam = null;
    Passenger vipPassengerShashi = null;

    @Test
    @BeforeEach
    public void setup() {
        business = new BusinessFlight(BUSINESS);
        economy = new EconomyFlight(ECONOMY);
        premium = new PremiumFlight(PREMIUM);

        usualPassengerSaytam = new Passenger("Satyam", false);
        vipPassengerShashi = new Passenger("Shashi", true);
    }

    @DisplayName("Given there is a Economy Flight")
    @Nested
    class TestEconomyFlight {

        @DisplayName("Validate flight Name")
        @Test
        public void testFlightName() {
            Assertions.assertEquals(ECONOMY, economy.getFlightType());
            System.out.println(economy);
        }

        @DisplayName("When there is a Usual Passenger")
        @Nested
        class NormalPassenger {

            @DisplayName("Then adding passenger")
            @Test
            public void testEconomyAddPassenger() {
                Assertions.assertTrue(economy.addPassenger(usualPassengerSaytam));
                Assertions.assertTrue(economy.addPassenger(vipPassengerShashi));
                Assertions.assertEquals(2, economy.getPassengerSet().size());
            }

            @DisplayName("Then removing passenger")
            @Test
            public void testEconomyRemovePassenger() {
                economy.addPassenger(usualPassengerSaytam);
                economy.addPassenger(vipPassengerShashi);
                Assertions.assertEquals(2, economy.getPassengerSet().size());
                Assertions.assertTrue(economy.removePassenger(usualPassengerSaytam));
                Assertions.assertFalse(economy.removePassenger(vipPassengerShashi));
                Assertions.assertEquals(1, economy.getPassengerSet().size());
            }

            @DisplayName("Then adding passenger multiple times")
            @Test
            public void testEconomyAddPassengerMultipleTimes() {
                int count = 0;
                for (; ; ) {
                    Assertions.assertTrue(economy.addPassenger(usualPassengerSaytam));
                    Assertions.assertTrue(economy.addPassenger(vipPassengerShashi));
                    count++;
                    if (5 == count)
                        break;
                }
                Assertions.assertEquals(2, economy.getPassengerSet().size());
            }
        }

        @DisplayName("When there is a VIP Passenger")
        @Nested
        class VIPPassenger {

            @DisplayName("Then adding passenger")
            @Test
            public void testEconomyAddPassenger() {
                Assertions.assertTrue(economy.addPassenger(vipPassengerShashi));
                Assertions.assertEquals(1, economy.getPassengerSet().size());
            }

            @DisplayName("Then removing passenger")
            @Test
            public void testEconomyRemovePassenger() {
                economy.addPassenger(usualPassengerSaytam);
                economy.addPassenger(vipPassengerShashi);
                Assertions.assertEquals(2, economy.getPassengerSet().size());
                Assertions.assertTrue(economy.removePassenger(usualPassengerSaytam));
                Assertions.assertFalse(economy.removePassenger(vipPassengerShashi));
                Assertions.assertEquals(1, economy.getPassengerSet().size());
            }

        }
    }

    @DisplayName("Given there is a Business Flight")
    @Nested
    public class TestBusiness {

        @DisplayName("Validate flight Name")
        @Test
        public void testFlightName() {
            Assertions.assertEquals(BUSINESS, business.getFlightType());
            System.out.println(business);
        }

        @DisplayName("When there is a Usual Passenger")
        @Nested
        public class TestUsualPassenger {

            @DisplayName("Then adding passenger")
            @Test
            public void testBusinessAddPassenger() {
                Assertions.assertFalse(business.addPassenger(usualPassengerSaytam));
                Assertions.assertEquals(0, business.getPassengerSet().size());
            }

            @DisplayName("Then removing passenger")
            @Test
            public void testBusinessRemovePassenger() {
                business.addPassenger(usualPassengerSaytam);
                Assertions.assertEquals(0, business.getPassengerSet().size());
                Assertions.assertFalse(business.removePassenger(usualPassengerSaytam));
                Assertions.assertEquals(0, business.getPassengerSet().size());
            }

        }

        @DisplayName("When there is a VIP Passenger")
        @Nested
        public class TestVIPPassenger {

            @DisplayName("Then adding passenger")
            @Test
            public void testBusinessAddPassenger() {
                Assertions.assertTrue(business.addPassenger(vipPassengerShashi));
                Assertions.assertEquals(1, business.getPassengerSet().size());
            }

            @DisplayName("Then removing passenger")
            @Test
            public void testBusinessRemovePassenger() {
                business.addPassenger(vipPassengerShashi);
                Assertions.assertEquals(1, business.getPassengerSet().size());
                Assertions.assertFalse(business.removePassenger(vipPassengerShashi));
                Assertions.assertEquals(1, business.getPassengerSet().size());
            }
        }
    }


    @DisplayName("Given there is a Premium Flight")
    @Nested
    public class TestPremiumFlight {

        @DisplayName("Validate flight Name")
        @Test
        public void testFlightName() {
            Assertions.assertEquals(PREMIUM, premium.getFlightType());
            System.out.println(premium);
        }

        @DisplayName("When there is a Usual Passenger")
        @Nested
        public class TestUsualPassenger {
            @DisplayName("Then adding passenger")
            @Test
            public void testBusinessAddPassenger() {
                Assertions.assertFalse(premium.addPassenger(usualPassengerSaytam));
                Assertions.assertEquals(0, premium.getPassengerSet().size());
            }

            @DisplayName("Then removing passenger")
            @Test
            public void testBusinessRemovePassenger() {
                premium.addPassenger(usualPassengerSaytam);
                Assertions.assertEquals(0, premium.getPassengerSet().size());
            }
        }

        @DisplayName("When there is a VIP Passenger")
        @Nested
        public class TestVIPPassenger {
            @DisplayName("Then adding passenger")
            @Test
            public void testBusinessAddPassenger() {
                Assertions.assertTrue(premium.addPassenger(vipPassengerShashi));
                Assertions.assertEquals(1, premium.getPassengerSet().size());
            }

            @DisplayName("Then removing passenger")
            @Test
            public void testBusinessRemovePassenger() {
                premium.addPassenger(vipPassengerShashi);
                Assertions.assertEquals(1, premium.getPassengerSet().size());
                Assertions.assertTrue(premium.removePassenger(vipPassengerShashi));
                Assertions.assertEquals(0, premium.getPassengerSet().size());
            }

        }

    }

}
