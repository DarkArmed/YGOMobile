/*
 * ====================================================================
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */
package org.apache.http.HC4.impl.nio.pool;

import java.io.IOException;

import org.apache.http.HC4.HttpHost;
import org.apache.http.HC4.annotation.ThreadSafe;
import org.apache.http.HC4.nio.NHttpClientConnection;
import org.apache.http.HC4.pool.PoolEntry;

/**
 * A basic {@link PoolEntry} implementation that represents an entry
 * in a pool of non-blocking {@link NHttpClientConnection}s identified by
 * an {@link HttpHost} instance.
 *
 * @see HttpHost
 * @since 4.2
 */
@ThreadSafe
public class BasicNIOPoolEntry extends PoolEntry<HttpHost, NHttpClientConnection> {

    private volatile int socketTimeout;

    public BasicNIOPoolEntry(final String id, final HttpHost route, final NHttpClientConnection conn) {
        super(id, route, conn);
    }

    @Override
    public void close() {
        try {
            getConnection().close();
        } catch (final IOException ignore) {
        }
    }

    @Override
    public boolean isClosed() {
        return !getConnection().isOpen();
    }

    int getSocketTimeout() {
        return socketTimeout;
    }

    void setSocketTimeout(final int socketTimeout) {
        this.socketTimeout = socketTimeout;
    }

}
