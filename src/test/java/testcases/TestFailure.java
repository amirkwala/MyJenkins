package testcases;


import org.testng.Assert;
import org.testng.annotations.Test;

public class TestFailure {

	@Test
	public void mylogin() {
		Assert.fail("dologin failed");
	}
}
