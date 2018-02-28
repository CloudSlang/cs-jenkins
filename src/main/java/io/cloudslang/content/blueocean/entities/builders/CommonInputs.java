/*
 * (c) Copyright 2017 Micro Focus, L.P.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License v2.0 which accompany this distribution.
 *
 * The Apache License is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.cloudslang.content.blueocean.entities.builders;

import static io.cloudslang.content.blueocean.validators.Validators.getValidHost;
import static io.cloudslang.content.blueocean.validators.Validators.getValidOrDefaultValue;
import static io.cloudslang.content.blueocean.validators.Validators.isValidHost;
import static io.cloudslang.content.utils.NumberUtilities.isValidInt;
import static io.cloudslang.content.utils.OtherUtilities.isValidIpPort;
import static java.lang.Boolean.valueOf;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.apache.commons.lang3.Validate.notNull;

public class CommonInputs {
    private final String protocol;
    private final String endpoint;
    private final String port;
    private final String username;
    private final String password;
    private final String proxyHost;
    private final String proxyPort;
    private final String proxyUsername;
    private final String proxyPassword;
    private final String x509HostnameVerifier;
    private final String trustKeystore;
    private final String trustPassword;
    private final String keystore;
    private final String keystorePassword;

    private final Integer connectTimeout;
    private final Integer socketTimeout;

    private final Boolean trustAllRoots;
    private final Boolean useCookies;
    private final Boolean keepAlive;

    private CommonInputs(Builder builder) {
        this.protocol = builder.protocol;
        this.endpoint = builder.endpoint;
        this.port = builder.port;
        this.username = builder.username;
        this.password = builder.password;
        this.proxyHost = builder.proxyHost;
        this.proxyPort = builder.proxyPort;
        this.proxyUsername = builder.proxyUsername;
        this.proxyPassword = builder.proxyPassword;
        this.x509HostnameVerifier = builder.x509HostnameVerifier;
        this.trustKeystore = builder.trustKeystore;
        this.trustPassword = builder.trustPassword;
        this.keystore = builder.keystore;
        this.keystorePassword = builder.keystorePassword;

        this.connectTimeout = builder.connectTimeout;
        this.socketTimeout = builder.socketTimeout;

        this.trustAllRoots = builder.trustAllRoots;
        this.useCookies = builder.useCookies;
        this.keepAlive = builder.keepAlive;
    }

    public String getProtocol() {
        return protocol;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public String getPort() {
        return port;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getProxyHost() {
        return proxyHost;
    }

    public String getProxyPort() {
        return proxyPort;
    }

    public String getProxyUsername() {
        return proxyUsername;
    }

    public String getProxyPassword() {
        return proxyPassword;
    }

    public String getX509HostnameVerifier() {
        return x509HostnameVerifier;
    }

    public String getTrustKeystore() {
        return trustKeystore;
    }

    public String getTrustPassword() {
        return trustPassword;
    }

    public String getKeystore() {
        return keystore;
    }

    public String getKeystorePassword() {
        return keystorePassword;
    }

    public Integer getConnectTimeout() {
        return connectTimeout;
    }

    public Integer getSocketTimeout() {
        return socketTimeout;
    }

    public Boolean getTrustAllRoots() {
        return trustAllRoots;
    }

    public Boolean getUseCookies() {
        return useCookies;
    }

    public Boolean getKeepAlive() {
        return keepAlive;
    }

    public static class Builder {
        private String protocol;
        private String endpoint;
        private String port;
        private String username;
        private String password;
        private String proxyHost;
        private String proxyPort;
        private String proxyUsername;
        private String proxyPassword;
        private String x509HostnameVerifier;
        private String trustKeystore;
        private String trustPassword;
        private String keystore;
        private String keystorePassword;

        private Integer connectTimeout;
        private Integer socketTimeout;

        private Boolean trustAllRoots;
        private Boolean useCookies;
        private Boolean keepAlive;

        public CommonInputs build() {
            return new CommonInputs(this);
        }

        public CommonInputs.Builder withProtocol(String input) {
            protocol = getValidOrDefaultValue(input, "http", new String[]{"http", "https"});
            return this;
        }

        public CommonInputs.Builder withEndpoint(String input) {
            notNull(input);
            endpoint = getValidHost(input);
            return this;
        }

        public CommonInputs.Builder withPort(String input) {
            port = isNotBlank(input) && isValidIpPort(input) ? input : "8080";
            return this;
        }

        public CommonInputs.Builder withUsername(String input) {
            notNull(input);
            username = input;
            return this;
        }

        public CommonInputs.Builder withPassword(String input) {
            notNull(input);
            password = input;
            return this;
        }

        public CommonInputs.Builder withProxyHost(String input) {
            proxyHost = isValidHost(input) ? input : EMPTY;
            return this;
        }

        public CommonInputs.Builder withProxyPort(String input) {
            proxyPort = isNotBlank(input) && isValidIpPort(input) ? input : EMPTY;
            return this;
        }

        public CommonInputs.Builder withProxyUsername(String input) {
            proxyUsername = input;
            return this;
        }

        public CommonInputs.Builder withProxyPassword(String input) {
            proxyPassword = input;
            return this;
        }

        public CommonInputs.Builder withX509HostnameVerifier(String input) {
            x509HostnameVerifier = getValidOrDefaultValue(input, "allow_all", new String[]{"allow_all", "browser_compatible", "strict"});
            return this;
        }

        public CommonInputs.Builder withTrustKeystore(String input) {
            trustKeystore = input;
            return this;
        }

        public CommonInputs.Builder withTrustPassword(String input) {
            trustPassword = input;
            return this;
        }

        public CommonInputs.Builder withKeystore(String input) {
            keystore = input;
            return this;
        }

        public CommonInputs.Builder withKeystorePassword(String input) {
            keystorePassword = input;
            return this;
        }

        public CommonInputs.Builder withConnectTimeout(String input) {
            connectTimeout = isValidInt(input, 0, Integer.MAX_VALUE) ? Integer.parseInt(input) : 0;
            return this;
        }

        public CommonInputs.Builder withSocketTimeout(String input) {
            socketTimeout = isValidInt(input, 0, Integer.MAX_VALUE) ? Integer.parseInt(input) : 0;
            return this;
        }

        public CommonInputs.Builder withTrustAllRoots(String input) {
            trustAllRoots = valueOf(input);
            return this;
        }

        public CommonInputs.Builder withUseCookies(String input) {
            useCookies = !valueOf(input);
            return this;
        }

        public CommonInputs.Builder withKeepAlive(String input) {
            keepAlive = !valueOf(input);
            return this;
        }
    }
}
