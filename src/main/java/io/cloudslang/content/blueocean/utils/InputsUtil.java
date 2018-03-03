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
package io.cloudslang.content.blueocean.utils;

import io.cloudslang.content.blueocean.entities.builders.CommonInputs;
import io.cloudslang.content.blueocean.entities.builders.InputsWrapper;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import static io.cloudslang.content.blueocean.entities.constants.Constants.Headers.RESPONSE_HEADERS;
import static io.cloudslang.content.blueocean.entities.constants.Constants.Miscellaneous.*;
import static io.cloudslang.content.blueocean.factory.UriFactory.getUri;
import static io.cloudslang.content.blueocean.validators.Validators.getValidUrl;
import static java.util.Arrays.stream;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.join;

public class InputsUtil {
    private InputsUtil() {
        // prevent instantiation
    }

    public static String getParamsString(Map<String, String> paramsMap, String separator, String suffix, boolean deleteLastChar) {
        if (paramsMap.isEmpty()) {
            return EMPTY;
        }

        StringBuilder sb = new StringBuilder();
        paramsMap
                .forEach((key, value) -> sb.append(key).append(separator).append(value).append(suffix));

        return deleteLastChar ? sb.deleteCharAt(sb.length() - 1).toString() : sb.toString();
    }

    public static String extractToken(Map<String, String> response, String headerName) {
        String[] headersArray = response.get(RESPONSE_HEADERS).split(NEW_LINE);

        Map<String, String> headers = new HashMap<>();
        stream(headersArray)
                .forEach(header -> headers.put(header.split(COLON + BLANK_CHAR)[0], header.split(COLON + BLANK_CHAR)[1]));

        return headers.get(headerName);
    }

    public static boolean applyContentType(String method) {
        return stream(new String[]{HttpPost.METHOD_NAME, HttpPut.METHOD_NAME, HttpPatch.METHOD_NAME})
                .anyMatch(filter -> filter.contains(method));
    }

    @SuppressWarnings("unchecked")
    public static String buildUrl(InputsWrapper wrapper) throws MalformedURLException {
        CommonInputs commonInputs = wrapper.getCommonInputs();

        return getValidUrl(join(commonInputs.getProtocol(), PROTOCOL_HOSTNAME_SEPARATOR, commonInputs.getEndpoint(),
                COLON, commonInputs.getPort(), getUri(wrapper)));
    }
}
