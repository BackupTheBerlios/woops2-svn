package test.isimarket;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses({
	test.isimarket.client.TestSuite.class,
	test.isimarket.servants.TestSuite.class
})
public class TestSuite {
	// None.
}
