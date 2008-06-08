package test.isimarket.servants;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses( {
	test.isimarket.servants.actiontypeservant.TestSuite.class,
	test.isimarket.servants.administrationservant.TestSuite.class,
	test.isimarket.servants.alarmservant.TestSuite.class,
	test.isimarket.servants.eventservant.TestSuite.class,
	test.isimarket.servants.walletservant.TestSuite.class
})
public class TestSuite {
	// None.
}
