package models;

import models.*;
import org.junit.*;
import static org.junit.Assert.*;
import play.test.WithApplication;
import static play.test.Helpers.*;

public class ModelsTest extends WithApplication {
    @Before
    public void setUp() {
        start(fakeApplication(inMemoryDatabase()));
    }

    @Test
    public void createAndRetrieveUser() {
        new UserAccount("bob@gmail.com", "Bob", "secret").save();
        UserAccount bob = UserAccount.find.where().eq("email", "bob@gmail.com").findUnique();
        assertNotNull(bob);
        assertEquals("Bob", bob.name);
    }

    @Test
    public void tryAuthenticateUser() {
        new UserAccount("bob@gmail.com", "Bob", "secret").save();

        assertNotNull(UserAccount.authenticate("bob@gmail.com", "secret"));
        assertNull(UserAccount.authenticate("bob@gmail.com", "badpassword"));
        assertNull(UserAccount.authenticate("tom@gmail.com", "secret"));
    }

    @Test
    public void createAndRetrieveSeller() {
        Seller seller = new Seller("Travaller's Coffee");
        //System.out.println("before: " + seller.id);
        seller.save();
        //System.out.println("after: " + seller.id);
        Seller travellers = Seller.find.where().eq("name", "Travaller's Coffee").findUnique();
        assertNotNull(travellers);

        Seller travellersById = Seller.find.where().eq("id", seller.id).findUnique();
        assertNotNull(travellersById);
        assertEquals(seller.id, travellersById.id);
    }

    @Test
    public void createAndRetrieveSellPoint() {
        Seller seller = new Seller("Travaller's Coffee");
        seller.save();

        SellPoint pointOne = SellPoint.create(seller, "На Речном");
        //System.out.println("pointOne id: " + pointOne.id);
        //System.out.println("seller id: " + pointOne.seller.name);

        SellPoint pointTwo = SellPoint.create(seller, "На Горском");
        //System.out.println("pointOne id: " + pointTwo.id);
        //System.out.println("seller id: " + pointTwo.seller.name);

        //System.out.println("seller points: " + seller.sellPoints);

        assertEquals(2, seller.sellPoints.size());
    }
}