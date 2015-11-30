package com.golenko.gameshop.dao;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ GameDAOTest.class, MySqlConnectionTest.class })
public class AllTests {

}
