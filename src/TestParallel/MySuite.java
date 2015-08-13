package TestParallel;

// Test case

@RunWith(ConcurrentSuite.class)
@Suite.SuiteClasses({ATest.class, ATest2.class, ATest3.class})
public class MySuite {
  
}
