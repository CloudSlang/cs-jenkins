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

import io.cloudslang.content.constants.BooleanValues;
import io.cloudslang.content.httpclient.HttpClientInputs;

import static io.cloudslang.content.blueocean.validators.Validators.areBothValuesPresent;
import static io.cloudslang.content.blueocean.validators.Validators.getValidOrDefaultValue;
import static io.cloudslang.content.blueocean.validators.Validators.isValidHost;
import static io.cloudslang.content.blueocean.validators.Validators.isValidPort;
import static io.cloudslang.content.utils.NumberUtilities.isValidInt;
import static java.lang.Boolean.parseBoolean;
import static java.lang.Integer.parseInt;
import static java.lang.String.valueOf;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

public class HttpClientInputsWrapper {
    private static final HttpClientInputs httpClientInputs = new HttpClientInputs();

    private final String username;
    private final String password;
    private final String trustAllRoots;
    private final String x509HostnameVerifier;
    private final String trustKeystore;
    private final String trustPassword;
    private final String keystore;
    private final String keystorePassword;
    private final String connectTimeout;
    private final String socketTimeout;
    private final String useCookies;
    private final String keepAlive;

    private HttpClientInputsWrapper(Builder builder) {
        httpClientInputs.setQueryParamsAreURLEncoded(BooleanValues.FALSE);
        httpClientInputs.setRequestCharacterSet(UTF_8.toString());

        this.username = builder.username;
        this.password = builder.password;
        this.trustAllRoots = builder.trustAllRoots;
        this.x509HostnameVerifier = builder.x509HostnameVerifier;
        this.trustKeystore = builder.trustKeystore;
        this.trustPassword = builder.trustPassword;
        this.keystore = builder.keystore;
        this.keystorePassword = builder.keystorePassword;
        this.connectTimeout = builder.connectTimeout;
        this.socketTimeout = builder.socketTimeout;
        this.useCookies = builder.useCookies;
        this.keepAlive = builder.keepAlive;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getTrustAllRoots() {
        return trustAllRoots;
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

    public String getConnectTimeout() {
        return connectTimeout;
    }

    public String getSocketTimeout() {
        return socketTimeout;
    }

    public String getUseCookies() {
        return useCookies;
    }

    public String getKeepAlive() {
        return keepAlive;
    }

    public HttpClientInputs getHttpClientInputs(String methodName, String proxyHost, String proxyPort, String proxyUsername,
                                                String proxyPassword) {
        httpClientInputs.setMethod(methodName);

        if (isNotBlank(proxyUsername)) {
            httpClientInputs.setProxyUsername(proxyUsername);
        }

        if (areBothValuesPresent(proxyUsername, proxyPassword)) {
            httpClientInputs.setProxyPassword(proxyPassword);
        }

        if (areBothValuesPresent(proxyHost, proxyPort) && isValidHost(proxyHost) && isValidPort(proxyPort)) {
            httpClientInputs.setProxyHost(proxyHost);
            httpClientInputs.setProxyPort(proxyPort);
        }

        return httpClientInputs;
    }

    public static class Builder {
        private String username;
        private String password;
        private String trustAllRoots;
        private String x509HostnameVerifier;
        private String trustKeystore;
        private String trustPassword;
        private String keystore;
        private String keystorePassword;
        private String connectTimeout;
        private String socketTimeout;
        private String useCookies;
        private String keepAlive;

        public HttpClientInputsWrapper build() {
            return new HttpClientInputsWrapper(this);
        }

        public Builder withUsername(String inputValue) {
            this.username = inputValue;
            httpClientInputs.setUsername(username);

            return this;
        }

        public Builder withPassword(String inputValue) {
            this.password = inputValue;
            httpClientInputs.setPassword(password);

            return this;
        }

        public Builder withTrustAllRoots(String inputValue) {
            this.trustAllRoots = valueOf(parseBoolean(inputValue));
            httpClientInputs.setTrustAllRoots(trustAllRoots);

            return this;
        }

        public Builder withKeepAlive(String inputValue) {
            this.keepAlive = valueOf(!Boolean.valueOf(inputValue));
            httpClientInputs.setKeepAlive(keepAlive);

            return this;
        }

        public Builder withUseCookies(String inputValue) {
            this.useCookies = valueOf(inputValue);
            httpClientInputs.setUseCookies(useCookies);

            return this;
        }

        public Builder withConnectTimeout(String inputValue) {
            this.connectTimeout = valueOf(isValidInt(inputValue, 0, Integer.MAX_VALUE) ? parseInt(inputValue) : 0);
            httpClientInputs.setConnectTimeout(connectTimeout);

            return this;
        }

        public Builder withSocketTimeout(String inputValue) {
            this.socketTimeout = valueOf(isValidInt(inputValue, 0, Integer.MAX_VALUE) ? parseInt(inputValue) : 0);
            httpClientInputs.setSocketTimeout(socketTimeout);

            return this;
        }

        public Builder withX509HostnameVerifier(String inputValue) {
            this.x509HostnameVerifier = getValidOrDefaultValue(inputValue, "allow_all", new String[]{"allow_all", "browser_compatible", "strict"});
            httpClientInputs.setX509HostnameVerifier(x509HostnameVerifier);

            return this;
        }

        public Builder withTrustKeystore(String inputValue) {
            this.trustKeystore = inputValue;
            if (isNotBlank(trustKeystore)) {
                httpClientInputs.setTrustKeystore(trustKeystore);
            }

            return this;
        }

        public Builder withTrustPassword(String inputValue) {
            this.trustPassword = inputValue;
            if (isNotBlank(trustPassword)) {
                httpClientInputs.setTrustPassword(trustPassword);
            }

            return this;
        }

        public Builder withKeystore(String inputValue) {
            this.keystore = inputValue;
            if (isNotBlank(keystore)) {
                httpClientInputs.setKeystore(keystore);
            }

            return this;
        }

        public Builder withKeystorePassword(String inputValue) {
            this.keystorePassword = inputValue;
            if (isNotBlank(keystorePassword)) {
                httpClientInputs.setKeystorePassword(keystorePassword);
            }

            return this;
        }
    }
}
