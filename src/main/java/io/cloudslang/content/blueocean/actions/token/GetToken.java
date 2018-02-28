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
package io.cloudslang.content.blueocean.actions.token;

import com.hp.oo.sdk.content.annotations.Action;
import com.hp.oo.sdk.content.annotations.Output;
import com.hp.oo.sdk.content.annotations.Param;
import com.hp.oo.sdk.content.annotations.Response;
import com.hp.oo.sdk.content.plugin.ActionMetadata.MatchType;
import com.hp.oo.sdk.content.plugin.ActionMetadata.ResponseType;
import io.cloudslang.content.blueocean.entities.builders.CommonInputs;
import io.cloudslang.content.blueocean.entities.builders.InputsWrapper;
import io.cloudslang.content.blueocean.service.BlueOceanService;
import io.cloudslang.content.constants.ReturnCodes;

import java.util.Map;

import static io.cloudslang.content.blueocean.entities.Api.TOKEN;
import static io.cloudslang.content.blueocean.entities.constants.Constants.Actions.GET_TOKEN;
import static io.cloudslang.content.blueocean.entities.constants.Constants.Headers.X_BLUE_OCEAN_JWT;
import static io.cloudslang.content.blueocean.entities.constants.Inputs.StandardInputs.ENDPOINT;
import static io.cloudslang.content.blueocean.entities.constants.Inputs.StandardInputs.PORT;
import static io.cloudslang.content.blueocean.entities.constants.Inputs.StandardInputs.PROTOCOL;
import static io.cloudslang.content.blueocean.utils.InputsUtil.extractToken;
import static io.cloudslang.content.constants.OutputNames.EXCEPTION;
import static io.cloudslang.content.constants.OutputNames.RETURN_CODE;
import static io.cloudslang.content.constants.OutputNames.RETURN_RESULT;
import static io.cloudslang.content.constants.ResponseNames.FAILURE;
import static io.cloudslang.content.constants.ResponseNames.SUCCESS;
import static io.cloudslang.content.httpclient.HttpClientInputs.CONNECT_TIMEOUT;
import static io.cloudslang.content.httpclient.HttpClientInputs.KEEP_ALIVE;
import static io.cloudslang.content.httpclient.HttpClientInputs.KEYSTORE;
import static io.cloudslang.content.httpclient.HttpClientInputs.KEYSTORE_PASSWORD;
import static io.cloudslang.content.httpclient.HttpClientInputs.PASSWORD;
import static io.cloudslang.content.httpclient.HttpClientInputs.PROXY_HOST;
import static io.cloudslang.content.httpclient.HttpClientInputs.PROXY_PASSWORD;
import static io.cloudslang.content.httpclient.HttpClientInputs.PROXY_PORT;
import static io.cloudslang.content.httpclient.HttpClientInputs.PROXY_USERNAME;
import static io.cloudslang.content.httpclient.HttpClientInputs.SOCKET_TIMEOUT;
import static io.cloudslang.content.httpclient.HttpClientInputs.TRUST_ALL_ROOTS;
import static io.cloudslang.content.httpclient.HttpClientInputs.TRUST_KEYSTORE;
import static io.cloudslang.content.httpclient.HttpClientInputs.TRUST_PASSWORD;
import static io.cloudslang.content.httpclient.HttpClientInputs.USERNAME;
import static io.cloudslang.content.httpclient.HttpClientInputs.USE_COOKIES;
import static io.cloudslang.content.httpclient.HttpClientInputs.X509_HOSTNAME_VERIFIER;
import static io.cloudslang.content.utils.OutputUtilities.getFailureResultsMap;
import static org.apache.http.client.methods.HttpGet.METHOD_NAME;

public class GetToken {
    /**
     * Retrieves JWT Token for specified user.
     * JWT token is generated for the user in session. In Jenkins there is always a user in context, that is if there is
     * no logged in user then the generated token will carry the claim for anonymous user.
     * Default expiry time of token is 30 minutes.
     * JWT token is return as X-BLUEOCEAN-JWT HTTP header.
     * https://github.com/jenkinsci/blueocean-plugin/blob/master/blueocean-jwt/README.md#JWT-Token-API
     *
     * @param protocol             Optional - protocol used in request. If no valid value provided then "http" will be supplied.
     *                             Valid values: "http", "https"
     *                             Default value: "http"
     * @param endpoint             Endpoint to which request will be sent.
     *                             Example: "somewhere.jenkins.com"
     * @param port                 Optional - port used in request. If no value value provided then "8080" will be supplied.
     *                             Default value: "8080"
     * @param username             Username used in basic authentication.
     * @param password             Password associated with "username" input to be used in basic authentication.
     * @param proxyHost            Optional - proxy server used to connect to Couchbase API. If empty no proxy will be used.
     * @param proxyPort            Optional - proxy server port. You must either specify values for both proxyHost and
     *                             proxyPort inputs or leave them both empty.
     * @param proxyUsername        Optional - proxy server user name.
     * @param proxyPassword        Optional - proxy server password associated with the proxyUsername input value.
     * @param trustAllRoots        Optional - specifies whether to enable weak security over SSL/TSL. A certificate is
     *                             trusted even if no trusted certification authority issued it.
     *                             Valid values: "true", "false"
     *                             Default value: "true"
     * @param x509HostnameVerifier Optional - specifies the way the server hostname must match a domain name in the subject's
     *                             Common Name (CN) or subjectAltName field of the X.509 certificate. Set this to "allow_all"
     *                             to skip any checking. For the value "browser_compatible" the hostname verifier works
     *                             the same way as Curl and Firefox. The hostname must match either the first CN, or any
     *                             of the subject-alts. A wildcard can occur in the CN, and in any of the subject-alts.
     *                             The only difference between "browser_compatible" and "strict" is that a wildcard (such
     *                             as "*.foo.com") with "browser_compatible" matches all subdomains, including "a.b.foo.com".
     *                             If no valid value provided then "allow_all" will be supplied.
     *                             Valid values: "strict", "browser_compatible", "allow_all"
     *                             Default value: "allow_all"
     * @param trustKeystore        Optional - pathname of the Java TrustStore file. This contains certificates from other
     *                             parties that you expect to communicate with, or from Certificate Authorities that you
     *                             trust to identify other parties. If the protocol (specified by the "url") is not "https"
     *                             or if trustAllRoots is "true" this input is ignored.
     *                             Default value: ../java/lib/security/cacerts
     *                             Format: Java KeyStore (JKS)
     * @param trustPassword        Optional - password associated with the TrustStore file. If trustAllRoots is "false"
     *                             and trustKeystore is empty, trustPassword default will be supplied.
     *                             Default value: "changeit"
     * @param keystore             Optional - pathname of the Java KeyStore file. You only need this if the server requires
     *                             client authentication. If the protocol (specified by the "url") is not "https" or if
     *                             trustAllRoots is "true" this input is ignored.
     *                             Format: Java KeyStore (JKS)
     *                             Default value: ../java/lib/security/cacerts.
     * @param keystorePassword     Optional - password associated with the KeyStore file. If trustAllRoots is "false" and
     *                             keystore is empty, keystorePassword default will be supplied.
     *                             Default value: "changeit"
     * @param connectTimeout       Optional - time to wait for a connection to be established, in seconds. A timeout value
     *                             of "0" represents an infinite timeout.
     *                             Default value: "0"
     * @param socketTimeout        Optional - timeout for waiting for data (a maximum period inactivity between two
     *                             consecutive data packets), in seconds. A socketTimeout value of "0" represents an
     *                             infinite timeout.
     *                             Default value: "0"
     * @param useCookies           Optional - specifies whether to enable cookie tracking or not. Cookies are stored between
     *                             consecutive calls in a serializable session object therefore they will be available on
     *                             a branch level. If you specify a non-boolean value, the default value is used.
     *                             Valid values: "true", "false"
     *                             Default value: "true"
     * @param keepAlive            Optional - specifies whether to create a shared connection that will be used in subsequent
     *                             calls. If keepAlive is "false", the already open connection will be used and after
     *                             execution it will close it.
     *                             Valid values: "true", "false"
     *                             Default value: "true"
     * @return A map with strings as keys and strings as values that contains: outcome of the action (or failure message
     * and the exception if there is one), returnCode of the operation and the ID of the request
     */
    @Action(name = "Get Token",
            outputs = {
                    @Output(RETURN_CODE),
                    @Output(RETURN_RESULT),
                    @Output(EXCEPTION)
            },
            responses = {
                    @Response(text = SUCCESS, field = RETURN_CODE, value = ReturnCodes.SUCCESS,
                            matchType = MatchType.COMPARE_EQUAL, responseType = ResponseType.RESOLVED),
                    @Response(text = FAILURE, field = RETURN_CODE, value = ReturnCodes.FAILURE,
                            matchType = MatchType.COMPARE_EQUAL, responseType = ResponseType.ERROR, isOnFail = true)
            })
    public Map<String, String> getToken(@Param(value = PROTOCOL) String protocol,
                                        @Param(value = ENDPOINT, required = true) String endpoint,
                                        @Param(value = PORT) String port,
                                        @Param(value = USERNAME, required = true) String username,
                                        @Param(value = PASSWORD, required = true, encrypted = true) String password,
                                        @Param(value = PROXY_HOST) String proxyHost,
                                        @Param(value = PROXY_PORT) String proxyPort,
                                        @Param(value = PROXY_USERNAME) String proxyUsername,
                                        @Param(value = PROXY_PASSWORD, encrypted = true) String proxyPassword,
                                        @Param(value = TRUST_ALL_ROOTS) String trustAllRoots,
                                        @Param(value = X509_HOSTNAME_VERIFIER) String x509HostnameVerifier,
                                        @Param(value = TRUST_KEYSTORE) String trustKeystore,
                                        @Param(value = TRUST_PASSWORD, encrypted = true) String trustPassword,
                                        @Param(value = KEYSTORE) String keystore,
                                        @Param(value = KEYSTORE_PASSWORD, encrypted = true) String keystorePassword,
                                        @Param(value = CONNECT_TIMEOUT) String connectTimeout,
                                        @Param(value = SOCKET_TIMEOUT) String socketTimeout,
                                        @Param(value = USE_COOKIES) String useCookies,
                                        @Param(value = KEEP_ALIVE) String keepAlive) {
        try {
            final CommonInputs commonInputs = new CommonInputs.Builder()
                    .withProtocol(protocol)
                    .withEndpoint(endpoint)
                    .withPort(port)
                    .withUsername(username)
                    .withPassword(password)
                    .withProxyHost(proxyHost)
                    .withProxyPort(proxyPort)
                    .withProxyUsername(proxyUsername)
                    .withProxyPassword(proxyPassword)
                    .withTrustAllRoots(trustAllRoots)
                    .withX509HostnameVerifier(x509HostnameVerifier)
                    .withTrustKeystore(trustKeystore)
                    .withTrustPassword(trustPassword)
                    .withKeystore(keystore)
                    .withKeystorePassword(keystorePassword)
                    .withConnectTimeout(connectTimeout)
                    .withSocketTimeout(socketTimeout)
                    .withUseCookies(useCookies)
                    .withKeepAlive(keepAlive)
                    .build();

            final InputsWrapper wrapper = new InputsWrapper.Builder()
                    .withCommonInputs(commonInputs)
                    .withMethod(METHOD_NAME)
                    .withAction(GET_TOKEN)
                    .withApi(TOKEN)
                    .build();

            Map<String, String> results = new BlueOceanService().execute(wrapper);
            results.put(X_BLUE_OCEAN_JWT, extractToken(results, X_BLUE_OCEAN_JWT));

            return results;
        } catch (Exception exception) {
            return getFailureResultsMap(exception);
        }
    }
}