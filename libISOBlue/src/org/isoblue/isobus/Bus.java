/*
 * Author: Alex Layton <awlayton@purdue.edu>
 *
 * Copyright (c) 2013 Purdue University
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package org.isoblue.isobus;

import java.util.Collection;

public abstract class Bus {

	public enum BusType {
		ENGINE, IMPLEMENT,
	}

	private ISOBUSNetwork mNetwork;
	private BusType mType;
	private Collection<ISOBUSSocket> mSocks;

	public Bus(ISOBUSNetwork network, BusType type) {
		mNetwork = network;
		mType = type;

		mSocks = initSocks();
	}

	protected abstract Collection<ISOBUSSocket> initSocks();

	protected void passMessageIn(ISOBUSSocket socket, Message message) {
		socket.receive(message);
	}

	protected abstract void passMessageOut(Message message)
			throws InterruptedException;

	protected void attach(ISOBUSSocket sock) throws InterruptedException {
		mSocks.add(sock);
	}

	protected void detach(ISOBUSSocket sock) {
		mSocks.remove(sock);
	}

	public ISOBUSNetwork getNetwork() {
		return mNetwork;
	}

	public BusType getType() {
		return mType;
	}
}
