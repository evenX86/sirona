/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.commons.monitoring;

import junit.framework.TestCase;

import org.apache.commons.monitoring.impl.RepositoryBase;

/**
 * @author <a href="mailto:nicolas@apache.org">Nicolas De Loof</a>
 */
public class MonitoringTest
    extends TestCase
{
    public void testMonitoring()
        throws Exception
    {
        Monitoring.setRepository( new RepositoryBase() );

        StopWatch stopWatch1 = Monitoring.start( "MonitoringTest.testMonitoring", "test", "utils" );
        StopWatch stopWatch2 = Monitoring.start( "MonitoringTest.testMonitoring", "test", "utils" );
        stopWatch2.stop();

        Monitor monitor = Monitoring.getMonitor( "MonitoringTest.testMonitoring", "test", "utils" );
        Gauge concurrency = monitor.getGauge( Monitor.CONCURRENCY );
        assertEquals( 1, concurrency.get() );
        assertEquals( 2, concurrency.getMax() );

        stopWatch1.stop();
        assertEquals( 0, concurrency.get() );
    }
}
