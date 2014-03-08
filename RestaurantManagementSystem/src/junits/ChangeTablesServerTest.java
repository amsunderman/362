package junits;

import org.junit.Assert;
import org.junit.Test;

import controller.RestaurantController;

public class ChangeTablesServerTest {

	@Test
	public void mainSuccess() {
		RestaurantController controller = new RestaurantController();
		Assert.assertTrue(controller.changeTableServer(1, "Joe"));
	}
	
	@Test
	public void tableDoesntExistNegative() {
		RestaurantController controller = new RestaurantController();
		Assert.assertFalse(controller.changeTableServer(-1, "Joe"));
	}
	
	@Test
	public void tableDoesntExist() {
		RestaurantController controller = new RestaurantController();
		Assert.assertFalse(controller.changeTableServer(99999999, "Joe"));
	}
	
	@Test
	public void serverDoesntExist() {
		RestaurantController controller = new RestaurantController();
		Assert.assertFalse(controller.changeTableServer(99999999, "BillyBobJoeMan"));
	}

}
