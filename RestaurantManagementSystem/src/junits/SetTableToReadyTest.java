package junits;


import org.junit.Assert;
import org.junit.Test;

import controller.RestaurantController;

public class SetTableToReadyTest {

	@Test
	public void positiveTableCount() {
		RestaurantController controller = new RestaurantController();
		Assert.assertTrue(controller.setTableToReady(5));
	}
	
	@Test
	public void tooHighTableCount() {
		RestaurantController controller = new RestaurantController();
		Assert.assertFalse(controller.setTableToReady(999999));
	}
	
	@Test
	public void negativeTableCount() {
		RestaurantController controller = new RestaurantController();
		Assert.assertFalse(controller.setTableToReady(-5));
	}

}
