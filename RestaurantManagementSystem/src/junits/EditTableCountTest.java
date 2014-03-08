package junits;


import org.junit.Assert;
import org.junit.Test;

import controller.RestaurantController;

public class EditTableCountTest {

	@Test
	public void positiveTableCount() {
		RestaurantController controller = new RestaurantController();
		Assert.assertTrue(controller.editTableCount(10));
	}
	
	@Test
	public void zeroTableCount() {
		RestaurantController controller = new RestaurantController();
		Assert.assertTrue(controller.editTableCount(0));
	}
	
	@Test
	public void negativeTableCount() {
		RestaurantController controller = new RestaurantController();
		Assert.assertFalse(controller.editTableCount(-10));
	}

}
