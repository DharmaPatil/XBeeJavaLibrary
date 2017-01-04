/**
 * Copyright (c) 2016-2017 Digi International Inc.,
 * All rights not expressly granted are reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 *
 * Digi International Inc. 11001 Bren Road East, Minnetonka, MN 55343
 * =======================================================================
 */
package com.digi.xbee.api;

import java.net.Inet4Address;

import org.junit.Test;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;

import com.digi.xbee.api.connection.serial.SerialPortRxTx;
import com.digi.xbee.api.models.IPProtocol;

public class SendBroadcastIPDataTest {
	
	// Constants.
	private static final String DATA = "data";
	
	private static final int PORT = 12345;
	
	/**
	 * Test method for {@link com.digi.xbee.api.IPDevice#sendBroadcastIPData(int, IPProtocol, byte[])}.
	 * 
	 * <p>Verify that broadcast IP data can be sent successfully.</p>
	 */
	@Test
	public void testSendBroadcastIPDataSuccess() throws Exception {
		// Instantiate an IPDevice object with a mocked interface.
		IPDevice ipDevice = PowerMockito.spy(new IPDevice(Mockito.mock(SerialPortRxTx.class)));
		
		// Do nothing when the sendNetowrkData(Inet4Address, int, IPProtocol, boolean, byte[]) method is called.
		Mockito.doNothing().when(ipDevice).sendIPData(Mockito.any(Inet4Address.class), Mockito.any(int.class), Mockito.any(IPProtocol.class), Mockito.anyBoolean(), Mockito.any(byte[].class));
				
		ipDevice.sendBroadcastIPData(PORT, DATA.getBytes());
		
		// Verify that the method sendIPData(Inet4Address, int, IPProtocol, boolean, byte[]) was 
		// called with a BROADCAST_ADDRESS and UDP protocol.
		Mockito.verify(ipDevice, Mockito.times(1)).sendIPData(Mockito.eq((Inet4Address) Inet4Address.getByName(IPDevice.BROADCAST_IP)), 
				Mockito.eq(PORT), Mockito.eq(IPProtocol.UDP), Mockito.eq(false), Mockito.eq(DATA.getBytes()));
	}
}