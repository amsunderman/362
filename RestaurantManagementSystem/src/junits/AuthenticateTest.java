package junits;

import org.junit.Assert;
import org.junit.Test;

import controller.RestaurantController;

public class AuthenticateTest {

	@Test
	public void correctPasscode() {
		RestaurantController controller = new RestaurantController();
		Assert.assertTrue(controller.authenticate("password"));
	}
	
	@Test
	public void wrongPasscode() {
		RestaurantController controller = new RestaurantController();
		Assert.assertFalse(controller.authenticate("passcode"));
	}

}
