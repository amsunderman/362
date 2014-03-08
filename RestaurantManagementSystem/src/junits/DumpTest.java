package junits;

import org.junit.Test;

import controller.RestaurantController;

public class DumpTest {

	@Test
	public void test() {
		RestaurantController controller = new RestaurantController();
		controller.editTableCount(10);
		controller.changeTableServer(1, "Joe");
		controller.dumpToFile();
	}

}
