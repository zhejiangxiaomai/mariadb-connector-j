/*
 *
 * MariaDB Client for Java
 *
 * Copyright (c) 2012-2014 Monty Program Ab.
 * Copyright (c) 2015-2017 MariaDB Ab.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along
 * with this library; if not, write to Monty Program Ab info@montyprogram.com.
 *
 * This particular MariaDB Client for Java file is work
 * derived from a Drizzle-JDBC. Drizzle-JDBC file which is covered by subject to
 * the following copyright and notice provisions:
 *
 * Copyright (c) 2009-2011, Marcus Eriksson
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of the driver nor the names of its contributors may not be
 * used to endorse or promote products derived from this software without specific
 * prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS  AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 * NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY
 * OF SUCH DAMAGE.
 *
 */

package org.mariadb.jdbc.internal.util;

import org.mariadb.jdbc.internal.util.constant.HaMode;

import java.lang.reflect.Field;
import java.util.Properties;

public enum DefaultOptions {

    /**
     * Database user name.
     */
    USER("user", "1.0.0"),
    /**
     * Password of database user.
     */
    PASSWORD("password", "1.0.0"),

    /**
     * The connect timeout value, in milliseconds, or zero for no timeout.
     * Default: 30000 (30 seconds) (was 0 before 2.1.2)
     */
    CONNECT_TIMEOUT("connectTimeout", 30_000, 0, "1.1.8"),

    /**
     * On Windows, specify named pipe name to connect to mysqld.exe.
     */
    PIPE("pipe", "1.1.3"),

    /**
     * Allows to connect to database via Unix domain socket, if server allows it. The value is the path of Unix domain socket, i.e "socket"
     * database parameter.
     */
    LOCAL_SOCKET("localSocket", "1.1.4"),

    /**
     * Allowed to connect database via shared memory, if server allows it. The value is base name of the shared memory.
     */
    SHARED_MEMORY("sharedMemory", "1.1.4"),

    /**
     * Sets corresponding option on the connection socket.
     */
    TCP_NO_DELAY("tcpNoDelay", Boolean.TRUE, "1.0.0"),

    /**
     * Sets corresponding option on the connection socket.
     */
    TCP_ABORTIVE_CLOSE("tcpAbortiveClose", Boolean.FALSE, "1.1.1"),

    /**
     * Hostname or IP address to bind the connection socket to a local (UNIX domain) socket.
     */
    LOCAL_SOCKET_ADDRESS("localSocketAddress", "1.1.8"),

    /**
     * Defined the network socket timeout (SO_TIMEOUT) in milliseconds.
     * 0 (default) disable this timeout
     */
    SOCKET_TIMEOUT("socketTimeout", new Integer[]{10000, null, null, null, null, null}, 0, "1.1.8"),

    /**
     * Session timeout is defined by the wait_timeout server variable.
     * Setting interactiveClient to true will tell server to use the interactive_timeout server variable
     */
    INTERACTIVE_CLIENT("interactiveClient", Boolean.FALSE, "1.1.8"),

    /**
     * If set to 'true', exception thrown during query execution contain query string.
     */
    DUMP_QUERY_ON_EXCEPTION("dumpQueriesOnException", Boolean.TRUE, "1.1.0"),

    /**
     * Metadata ResultSetMetaData.getTableName() return the physical table name.
     * "useOldAliasMetadataBehavior" permit to activate the legacy code that send the table alias if set.
     */
    USE_OLD_ALIAS_METADATA_BEHAVIOR("useOldAliasMetadataBehavior", Boolean.FALSE, "1.1.9"),

    /**
     * If set to 'false', exception thrown during LOCAL INFILE if no InputStream has already been set.
     */
    ALLOW_LOCAL_INFILE("allowLocalInfile", Boolean.TRUE, "1.2.1"),

    /**
     * var=value pairs separated by comma, mysql session variables, set upon establishing successful connection.
     */
    SESSION_VARIABLES("sessionVariables", "1.1.0"),

    /**
     * The database precised in url will be created if doesn't exist.
     * (legacy alias "createDB")
     */
    CREATE_DATABASE_IF_NOT_EXISTS("createDatabaseIfNotExist", Boolean.FALSE, "1.1.8"),

    /**
     * Defined the server time zone.
     * to use only if jre server as a different time implementation of the server.
     * (best to have the same server time zone when possible)
     */
    SERVER_TIMEZONE("serverTimezone", "1.1.8"),
    /**
     * DatabaseMetaData use current catalog if null.
     */
    NULL_CATALOG_MEANS_CURRENT("nullCatalogMeansCurrent", Boolean.TRUE, "1.1.8"),

    /**
     * Datatype mapping flag, handle MySQL Tiny as BIT(boolean).
     */
    TINY_INT_IS_BIT("tinyInt1isBit", Boolean.TRUE, "1.0.0"),

    /**
     * Year is date type, rather than numerical.
     */
    YEAR_IS_DATE_TYPE("yearIsDateType", Boolean.TRUE, "1.0.0"),

    /**
     * Force SSL on connection.
     * (legacy alias "useSSL")
     */
    USE_SSL("useSsl", Boolean.FALSE, "1.1.0"),

    /**
     * allow compression in MySQL Protocol.
     */
    USER_COMPRESSION("useCompression", Boolean.FALSE, "1.0.0"),

    /**
     * Allows multiple statements in single executeQuery.
     */
    ALLOW_MULTI_QUERIES("allowMultiQueries", Boolean.FALSE, "1.0.0"),

    /**
     * rewrite batchedStatement to have only one server call.
     */
    REWRITE_BATCHED_STATEMENTS("rewriteBatchedStatements", Boolean.FALSE, "1.1.8"),

    /**
     * Sets corresponding option on the connection socket.
     * Defaults to true since 1.6.0 (false before)
     */
    TCP_KEEP_ALIVE("tcpKeepAlive", Boolean.TRUE, "1.0.0"),

    /**
     * set buffer size for TCP buffer (SO_RCVBUF).
     */
    TCP_RCV_BUF("tcpRcvBuf", (Integer) null, 0, "1.0.0"),

    /**
     * set buffer size for TCP buffer (SO_SNDBUF).
     */
    TCP_SND_BUF("tcpSndBuf", (Integer) null, 0, "1.0.0"),

    /**
     * to use custom socket factory, set it to full name of the class that implements javax.net.SocketFactory.
     */
    SOCKET_FACTORY("socketFactory", "1.0.0"),
    PIN_GLOBAL_TX_TO_PHYSICAL_CONNECTION("pinGlobalTxToPhysicalConnection", Boolean.FALSE, "1.1.8"),

    /**
     * When using SSL, do not check server's certificate.
     */
    TRUST_SERVER_CERTIFICATE("trustServerCertificate", Boolean.FALSE, "1.1.1"),

    /**
     * Server's certificate in DER form, or server's CA certificate. Can be used in one of 3 forms, sslServerCert=/path/to/cert.pem
     * (full path to certificate), sslServerCert=classpath:relative/cert.pem (relative to current classpath), or as verbatim DER-encoded certificate
     * string "-----BEGIN CERTIFICATE-----".
     */
    SERVER_SSL_CERT("serverSslCert", "1.1.3"),

    /**
     * Correctly handle subsecond precision in timestamps (feature available with MariaDB 5.3 and later).
     * May confuse 3rd party components (Hibernated).
     */
    USE_FRACTIONAL_SECONDS("useFractionalSeconds", Boolean.TRUE, "1.0.0"),

    /**
     * Driver must recreateConnection after a failover.
     */
    AUTO_RECONNECT("autoReconnect", Boolean.FALSE, "1.2.0"),

    /**
     * After a master failover and no other master found, back on a read-only host ( throw exception if not).
     */
    FAIL_ON_READ_ONLY("failOnReadOnly", Boolean.FALSE, "1.2.0"),

    /**
     * When using loadbalancing, the number of times the driver should cycle through available hosts, attempting to connect.
     * Between cycles, the driver will pause for 250ms if no servers are available.
     */
    RETRY_ALL_DOWN("retriesAllDown", 120, 0, "1.2.0"),

    /**
     * When using failover, the number of times the driver should cycle silently through available hosts, attempting to connect.
     * Between cycles, the driver will pause for 250ms if no servers are available.
     * if set to 0, there will be no silent reconnection
     */
    FAILOVER_LOOP_RETRIES("failoverLoopRetries", 120, 0, "1.2.0"),


    /**
     * When in multiple hosts, after this time in second without used, verification that the connections haven't been lost.
     * When 0, no verification will be done. Defaults to 0 (120 before 1.5.8 version)
     */
    VALID_CONNECTION_TIMEOUT("validConnectionTimeout", 0, 0, "1.2.0"),

    /**
     * time in second a server is blacklisted after a connection failure.  default to 50s
     */
    LOAD_BALANCE_BLACKLIST_TIMEOUT("loadBalanceBlacklistTimeout", 50, 0, "1.2.0"),

    /**
     * enable/disable prepare Statement cache, default true.
     */
    CACHEPREPSTMTS("cachePrepStmts", Boolean.TRUE, "1.3.0"),

    /**
     * This sets the number of prepared statements that the driver will cache per VM if "cachePrepStmts" is enabled.
     * default to 250.
     */
    PREPSTMTCACHESIZE("prepStmtCacheSize", 250, 0, "1.3.0"),

    /**
     * This is the maximum length of a prepared SQL statement that the driver will cache  if "cachePrepStmts" is enabled.
     * default to 2048.
     */
    PREPSTMTCACHESQLLIMIT("prepStmtCacheSqlLimit", 2048, 0, "1.3.0"),

    /**
     * when in high availability, and switching to a read-only host, assure that this host is in read-only mode by
     * setting session read-only.
     * default to false
     */
    ASSUREREADONLY("assureReadOnly", Boolean.FALSE, "1.3.0"),


    /**
     * if true (default) store date/timestamps according to client time zone.
     * if false, store all date/timestamps in DB according to server time zone, and time information (that is a time difference), doesn't take
     * timezone in account.
     */
    USELEGACYDATETIMECODE("useLegacyDatetimeCode", Boolean.TRUE, "1.3.0"),

    /**
     * maximize Mysql compatibility.
     * when using jdbc setDate(), will store date in client timezone, not in server timezone when useLegacyDatetimeCode = false.
     * default to false.
     */
    MAXIMIZEMYSQLCOMPATIBILITY("maximizeMysqlCompatibility", Boolean.FALSE, "1.3.0"),

    /**
     * useServerPrepStmts must prepared statements be prepared on server side, or just faked on client side.
     * if rewriteBatchedStatements is set to true, this options will be set to false.
     * default to false.
     */
    USESERVERPREPSTMTS("useServerPrepStmts", Boolean.FALSE, "1.3.0"),

    /**
     * File path of the trustStore file (similar to java System property "javax.net.ssl.trustStore").
     * Use the specified keystore for trusted root certificates.
     * When set, overrides serverSslCert.
     * <p>
     * (legacy alias trustCertificateKeyStoreUrl)
     */
    TRUSTSTORE("trustStore", "1.3.0"),

    /**
     * Password for the trusted root certificate file (similar to java System property "javax.net.ssl.trustStorePassword").
     * <p>
     * (legacy alias trustCertificateKeyStorePassword)
     */
    TRUST_CERTIFICATE_KEYSTORE_PASSWORD("trustStorePassword", "1.3.0"),

    /**
     * File path of the keyStore file that contain client private key store and associate certificates
     * (similar to java System property "javax.net.ssl.keyStore", but ensure that only the private key's entries are used).
     * (legacy alias clientCertificateKeyStoreUrl)
     */
    KEYSTORE("keyStore", "1.3.0"),

    /**
     * Password for the client certificate keystore  (similar to java System property "javax.net.ssl.keyStorePassword").
     * (legacy alias clientCertificateKeyStorePassword)
     */
    KEYSTORE_PASSWORD("keyStorePassword", "1.3.0"),

    /**
     * Password for the private key contain in client certificate keystore.
     * needed only in case private key password differ from keyStore password.
     */
    PRIVATE_KEYS_PASSWORD("keyPassword", "1.5.3"),

    /**
     * Force TLS/SSL protocol to a specific set of TLS versions (comma separated list)
     * example : "TLSv1, TLSv1.1, TLSv1.2"
     */
    ENABLED_SSL_PROTOCOL_SUITES("enabledSslProtocolSuites", "1.5.0"),

    /**
     * Force TLS/SSL cipher. (comma separated list)
     * example : "TLS_DHE_RSA_WITH_AES_256_GCM_SHA384, TLS_DHE_DSS_WITH_AES_256_GCM_SHA384"
     */
    ENABLED_SSL_CIPHER_SUITES("enabledSslCipherSuites", "1.5.0"),

    /**
     * When executing batch queries, must batch continue on error.
     * default to true.
     */
    CONTINUE_BATCH_ON_ERROR("continueBatchOnError", Boolean.TRUE, "1.4.0"),

    /**
     * Truncation error ("Data truncated for column '%' at row %", "Out of range value for column '%' at row %") will be thrown as error,
     * and not as warning.
     */
    JDBCOMPLIANTRUNCATION("jdbcCompliantTruncation", Boolean.TRUE, "1.4.0"),

    /**
     * enable/disable callable Statement cache, default true.
     */
    CACHE_CALLABLE_STMTS("cacheCallableStmts", Boolean.TRUE, "1.4.0"),

    /**
     * This sets the number of callable statements that the driver will cache per VM if "cacheCallableStmts" is enabled.
     * default to 150.
     */
    CALLABLE_STMT_CACHE_SIZE("callableStmtCacheSize", 150, 0, "1.4.0"),

    /**
     * Indicate to server some client information in a key;value pair.
     * for example connectionAttributes=key1:value1,key2,value2.
     * Those information can be retrieved on server within tables mysql.session_connect_attrs and mysql.session_account_connect_attrs.
     * This can permit from server an identification of client.
     */
    CONNECTION_ATTRIBUTES("connectionAttributes", "1.4.0"),

    /**
     * PreparedStatement.executeBatch() will send many QUERY before reading result packets.
     * default to true.
     * (null is considered true. this permit to know if option was explicitly set)
     */
    USE_BATCH_MULTI_SEND("useBatchMultiSend", (Boolean) null, "1.5.0"),

    /**
     * When using useBatchMultiSend, indicate maximum query that can be send at a time.
     * default to 100
     */
    USE_BATCH_MULTI_SEND_NUMBER("useBatchMultiSendNumber", 100, 1, "1.5.0"),

    /**
     * Enable log information. require Slf4j version &gt; 1.4 dependency.
     * log informations :
     * - info : query log
     * default to false.
     */
    LOGGING("log", Boolean.FALSE, "1.5.0"),

    /**
     * log query execution time.
     * (legacy alias alias profileSQL)
     */
    PROFILESQL("profileSql", Boolean.FALSE, "1.5.0"),

    /**
     * Max query log size.
     * default to 1024.
     */
    MAX_QUERY_LOG_SIZE("maxQuerySizeToLog", 1024, 0, "1.5.0"),

    /**
     * Will log query with execution time superior to this value (if defined )
     * default to null.
     */
    SLOW_QUERY_TIME("slowQueryThresholdNanos", (Long) null, 0L, "1.5.0"),

    /**
     * Indicate password encoding charset. If not set, driver use platform's default charset.
     * default to null.
     */
    PASSWORD_CHARACTER_ENCODING("passwordCharacterEncoding", "1.5.9"),

    /**
     * Fast connection creation (recommended if not using authentication plugins)
     * default to true.
     * (null is considered true. this permit to know if option was explicitly set)
     */
    PIPELINE_AUTH("usePipelineAuth", (Boolean) null, "1.6.0"),

    /**
     * Driver will save the last 16 MySQL packet exchanges (limited to first 1000 bytes).
     * Hexadecimal value of those packet will be added to stacktrace when an IOException occur.
     * This options has no performance incidence (&lt; 1 microseconds per query) but driver will then take 16kb more memory.
     */
    ENABLE_PACKET_DEBUG("enablePacketDebug", Boolean.FALSE, "1.6.0"),

    /**
     * When using ssl, driver check hostname against the server's identity as presented in the server's Certificate
     * (checking alternative names or certificate CN) to prevent man-in-the-middle attack.
     * This option permit to deactivate this validation.
     */
    SSL_HOSTNAME_VERIFICATION("disableSslHostnameVerification", Boolean.FALSE, "2.1.0"),
        
    /**
     *  Enable redirection.
     *  This option let user to decide if use redirection feature.
     */
    DISABLE_REDIRECTION("disableRedirect",Boolean.FALSE,"2.1.0"),
    
    /**
     * Use dedicated COM_STMT_BULK_EXECUTE protocol for batch insert when possible.
     * (batch without Statement.RETURN_GENERATED_KEYS and streams) to have faster batch.
     * (significant only if server MariaDB &ge; 10.2.7)
     */
    USE_BULK_PROTOCOL("useBulkStmts", Boolean.TRUE, "2.1.0"),

    /**
     * Set default autocommit value.
     * Default: true
     */
    AUTOCOMMIT("autocommit", Boolean.TRUE, "2.2.0"),

    /**
     * Enable pool.
     * This option is usefull only if not using a DataSource object, but only connection.
     */
    POOL("pool", Boolean.FALSE, "2.2.0"),

    /**
     * Pool name that will permit to identify thread.
     * default : auto-generated as MariaDb-pool-&le;pool-index&ge;
     */
    POOL_NAME("poolName", "2.2.0"),

    /**
     * The maximum number of physical connections that
     * the pool should contain.
     * default: 8
     */
    MAX_POOL_SIZE("maxPoolSize", 8, 1, "2.2.0"),

    /**
     * The number of physical connections the pool should keep available at all times.
     * default: maxPoolSize value
     */
    MIN_POOL_SIZE("minPoolSize", (Integer) null, 0, "2.2.0"),

    /**
     * The maximum amount of time in seconds that a connection can stay in pool when not used.
     * This value must always be below @wait_timeout value - 45s
     *
     * Default: 600 (10 minutes). minimum value is 60 seconds.
     */
    MAX_IDLE_TIME("maxIdleTime", 600, Options.MIN_VALUE__MAX_IDLE_TIME, "2.2.0"),

    /**
     *
     * When asking a connection to pool, Pool will validate connection state.
     * "poolValidMinDelay" permit to disable this validation if connection has been borrowed recently avoiding useless
     * verification in case of frequent reuse of connection
     *
     * 0 meaning validation is done each time connection is asked.
     *
     * Default: 1000 (in milliseconds)
     */
    POOL_VALID_MIN_DELAY("poolValidMinDelay", 1000, 0, "2.2.0"),

    /**
     * Indicate that global variable aren't changed by application, permitting to pool faster connection.
     * Default: false
     */
    STATIC_GLOBAL("staticGlobal", Boolean.FALSE, "2.2.0"),

    /**
     * Register JMX monitoring pools.
     * Default: true
     */
    REGISTER_POOL_JMX("registerJmxPool", Boolean.TRUE, "2.2.0"),

    /**
     * Use COM_RESET_CONNECTION when resetting connection for pools when server permit it (MariaDB &gt; 10.2.4, MySQL &gt; 5.7.3)
     * This permit to reset session and user variables.
     *
     * Default: false
     */
    USE_RESET_CONNECTION("useResetConnection", Boolean.FALSE, "2.2.0"),

    /**
     * On master/slave configuration, permit to connect Connection defaulting to a slave
     * when master is down.
     */
    ALLOW_MASTER_DOWN("allowMasterDownConnection", Boolean.FALSE, "2.2.0"),

    /**
     * Usually, Connection.isValid just send an empty packet to server, and server send a small response to ensure
     * connectivity. When this option is set, connector will ensure server that "wsrep_local_state" correspond to
     * allowed values (separated by comma)
     *
     * example "4,5". Recommended value "4" when using galera cluster without proxy like maxscale
     */
    GALERA_ALLOWED_STATE("galeraAllowedState", "2.2.5");


    private final String optionName;
    private final Object objType;
    private final Object defaultValue;
    private final Object minValue;
    private final Object maxValue;
    private final String implementationVersion;

    DefaultOptions(final String optionName, final String implementationVersion) {
        this.optionName = optionName;
        this.implementationVersion = implementationVersion;
        objType = String.class;
        defaultValue = null;
        minValue = null;
        maxValue = null;
    }

    DefaultOptions(final String optionName, final String defaultValue, final String implementationVersion) {
        this.optionName = optionName;
        this.implementationVersion = implementationVersion;
        objType = String.class;
        this.defaultValue = defaultValue;
        minValue = null;
        maxValue = null;
    }

    DefaultOptions(final String optionName, final Boolean defaultValue, final String implementationVersion) {
        this.optionName = optionName;
        this.objType = Boolean.class;
        this.defaultValue = defaultValue;
        this.implementationVersion = implementationVersion;
        minValue = null;
        maxValue = null;
    }

    DefaultOptions(final String optionName, final Integer defaultValue, final Integer minValue, final String implementationVersion) {
        this.optionName = optionName;
        this.objType = Integer.class;
        this.defaultValue = defaultValue;
        this.minValue = minValue;
        this.maxValue = Integer.MAX_VALUE;
        this.implementationVersion = implementationVersion;
    }

    DefaultOptions(final String optionName, final Long defaultValue, final Long minValue, final String implementationVersion) {
        this.optionName = optionName;
        this.objType = Long.class;
        this.defaultValue = defaultValue;
        this.minValue = minValue;
        this.maxValue = Long.MAX_VALUE;
        this.implementationVersion = implementationVersion;
    }


    DefaultOptions(final String optionName, final Integer[] defaultValue, final Integer minValue, final String implementationVersion) {
        this.optionName = optionName;
        this.objType = Integer.class;
        this.defaultValue = defaultValue;
        this.minValue = minValue;
        this.maxValue = Integer.MAX_VALUE;
        this.implementationVersion = implementationVersion;
    }

    public String getOptionName() {
        return optionName;
    }

    public static Options defaultValues(final HaMode haMode) {
        return parse(haMode, "", new Properties());
    }

    /**
     * Generate an Options object with default value corresponding to High Availability mode.
     *
     * @param haMode    current high Availability mode
     * @param pool      is for pool
     * @return Options object initialized
     */
    public static Options defaultValues(HaMode haMode, boolean pool) {
        Properties properties = new Properties();
        properties.setProperty("pool", String.valueOf(pool));
        Options options = parse(haMode, "", properties);
        optionCoherenceValidation(options);
        return options;
    }

    /**
     * Parse additional properties.
     *
     * @param haMode        current haMode.
     * @param urlParameters options defined in url
     * @param options       initial options
     */
    public static void parse(final HaMode haMode, final String urlParameters, final Options options) {
        Properties prop = new Properties();
        parse(haMode, urlParameters, prop, options);
        optionCoherenceValidation(options);
    }

    private static Options parse(final HaMode haMode, final String urlParameters, final Properties properties) {
        Options options = parse(haMode, urlParameters, properties, null);
        optionCoherenceValidation(options);
        return options;
    }

    /**
     * Parse additional properties .
     *
     * @param haMode        current haMode.
     * @param urlParameters options defined in url
     * @param properties    options defined by properties
     * @param options       initial options
     * @return options
     */
    public static Options parse(final HaMode haMode, final String urlParameters, final Properties properties, final Options options) {
        if (urlParameters != null && !urlParameters.isEmpty()) {
            String[] parameters = urlParameters.split("&");
            for (String parameter : parameters) {
                int pos = parameter.indexOf('=');
                if (pos == -1) {
                    if (!properties.containsKey(parameter)) {
                        properties.setProperty(parameter, "");
                    }
                } else {
                    if (!properties.containsKey(parameter.substring(0, pos))) {
                        properties.setProperty(parameter.substring(0, pos), parameter.substring(pos + 1));
                    }

                }
            }
        }
        return parse(haMode, properties, options);
    }

    private static Options parse(final HaMode haMode, final Properties properties, final Options paramOptions) {
        final Options options = paramOptions != null ? paramOptions : new Options();

        try {
            //Option object is already initialized to default values.
            //loop on properties,
            // - check DefaultOption to check that property value correspond to type (and range)
            // - set values
            for (final String key : properties.stringPropertyNames()) {
                final String propertyValue = properties.getProperty(key);
                final DefaultOptions o = OptionUtils.OPTIONS_MAP.get(key);
                if (o != null && propertyValue != null) {
                    final Field field = Options.class.getField(o.optionName);
                    if (o.objType.equals(String.class)) {
                        field.set(options, propertyValue);
                    } else if (o.objType.equals(Boolean.class)) {
                        switch (propertyValue.toLowerCase()) {
                            case "":
                            case "1":
                            case "true":
                                field.set(options, Boolean.TRUE);
                                break;

                            case "0":
                            case "false":
                                field.set(options, Boolean.FALSE);
                                break;

                            default:
                                throw new IllegalArgumentException("Optional parameter " + o.optionName
                                        + " must be boolean (true/false or 0/1) was \"" + propertyValue + "\"");
                        }
                    } else if (o.objType.equals(Integer.class)) {
                        try {
                            final Integer value = Integer.parseInt(propertyValue);
                            assert o.minValue != null;
                            assert o.maxValue != null;
                            if (value.compareTo((Integer) o.minValue) < 0 || value.compareTo((Integer) o.maxValue) > 0) {
                                throw new IllegalArgumentException("Optional parameter " + o.optionName
                                        + " must be greater or equal to " + o.minValue
                                        + (((Integer) o.maxValue != Integer.MAX_VALUE) ? " and smaller than " + o.maxValue : " ")
                                        + ", was \"" + propertyValue + "\"");
                            }
                            field.set(options, value);
                        } catch (NumberFormatException n) {
                            throw new IllegalArgumentException("Optional parameter " + o.optionName
                                    + " must be Integer, was \"" + propertyValue + "\"");
                        }
                    } else if (o.objType.equals(Long.class)) {
                        try {
                            final Long value = Long.parseLong(propertyValue);
                            assert o.minValue != null;
                            assert o.maxValue != null;
                            if (value.compareTo((Long) o.minValue) < 0 || value.compareTo((Long) o.maxValue) > 0) {
                                throw new IllegalArgumentException("Optional parameter " + o.optionName
                                        + " must be greater or equal to " + o.minValue
                                        + (((Long) o.maxValue != Long.MAX_VALUE) ? " and smaller than " + o.maxValue : " ")
                                        + ", was \"" + propertyValue + "\"");
                            }
                            field.set(options, value);
                        } catch (NumberFormatException n) {
                            throw new IllegalArgumentException("Optional parameter " + o.optionName
                                    + " must be Long, was \"" + propertyValue + "\"");
                        }
                    }
                }
            }

            //special case : field with multiple default according to HA_MODE
            if (options.socketTimeout == null) {
                options.socketTimeout = ((Integer[]) SOCKET_TIMEOUT.defaultValue)[haMode.ordinal()];
            }

        } catch (NoSuchFieldException | IllegalAccessException n) {
            n.printStackTrace();
        } catch (SecurityException s) {
            //only for jws, so never thrown
            throw new IllegalArgumentException("Security too restrictive : " + s.getMessage());
        }

        return options;
    }

    /**
     * Option initialisation end : set option value to a coherent state.
     * @param options options
     */
    public static void optionCoherenceValidation(final Options options) {

        //disable use server prepare id using client rewrite
        if (options.rewriteBatchedStatements) {
            options.useServerPrepStmts = false;
        }

        //pipe cannot use read and write socket simultaneously
        if (options.pipe != null) {
            options.useBatchMultiSend = false;
            options.usePipelineAuth = false;
        }

        //if min pool size default to maximum pool size if not set
        if (options.pool) {
            options.minPoolSize = options.minPoolSize == null ? options.maxPoolSize : Math.min(options.minPoolSize, options.maxPoolSize);
        }

    }

    /**
     * Generate parameter String equivalent to options.
     *
     * @param options   options
     * @param haMode    high availability Mode
     * @param sb        String builder
     */
    public static void propertyString(final Options options, final HaMode haMode, final StringBuilder sb) {
        try {
            boolean first = true;
            for (DefaultOptions o : DefaultOptions.values()) {
                final Object value = Options.class.getField(o.optionName).get(options);

                if (value != null && !value.equals(o.defaultValue)) {
                    if (first) {
                        first = false;
                        sb.append('?');
                    } else {
                        sb.append('&');
                    }
                    sb.append(o.optionName)
                            .append('=');
                    if (o.objType.equals(String.class)) {
                        sb.append((String) value);
                    } else if (o.objType.equals(Boolean.class)) {
                        sb.append(((Boolean) value).toString());
                    } else if (o.objType.equals(Integer.class)) {
                        sb.append((Integer) value);
                    } else if (o.objType.equals(Long.class)) {
                        sb.append((Long) value);
                    }
                }
            }
        } catch (NoSuchFieldException | IllegalAccessException n) {
            n.printStackTrace();
        }
    }
}
