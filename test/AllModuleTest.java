// Copyright (c) 2018. Kristopher J Sewell, All Rights Reserved.
// File: AllModuleTest.java  Module: graph_tiefighter
// Net_ID: kjs170430

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectClasses({
        InputFileTest.class,
        OutputFileTest.class,
        PilotTest.class,
        PilotSortTests.class,
        HelpersTest.class
})

public final class AllModuleTest {
}
