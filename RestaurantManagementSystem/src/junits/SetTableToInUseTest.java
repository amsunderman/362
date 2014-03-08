package junits;


import org.junit.Assert;
import org.junit.Test;

import controller.RestaurantController;

public class SetTableToInUseTest {

	@Test
	public void positiveTableCount() {
		RestaurantController controller = new RestaurantController();
		Assert.assertTrue(controller.setTableToInUse(5, "Joe"));
	}
	
	@Test
	public void tooHighTableCount() {
		RestaurantController controller = new RestaurantController();
		Assert.assertFalse(controller.setTableToInUse(999999, "Joe"));
	}
	
	@Test
	public void negativeTableCount() {
		RestaurantController controller = new RestaurantController();
		Assert.assertFalse(controller.setTableToInUse(-10, "Joe"));
	}

}
