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

import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

public class InputsUtil {
    private InputsUtil() {
        // prevent instantiation
    }

    public static boolean areBothValuesPresent(String value1, String value2) {
        return isNotBlank(value1) && isNotBlank(value2);
    }

    public static String getParamsString(Map<String, String> paramsMap, String separator, String suffix, boolean deleteLastChar) {
        if (paramsMap.isEmpty()) {
            return EMPTY;
        }

        StringBuilder sb = new StringBuilder();
        paramsMap.forEach((key, value) -> sb.append(key).append(separator).append(value).append(suffix));

        return deleteLastChar ? sb.deleteCharAt(sb.length() - 1).toString() : sb.toString();
    }

    public static String extractToken(Map<String, String> response, String headerName) {
        String[] headersArray = response.get("responseHeaders").split("\r\n");

        Map<String, String> headers = new HashMap<>();
        for (int i = 1; i < headersArray.length - 1; i++) {
            headers.put(headersArray[i].split(": ")[0], headersArray[i].split(": ")[1]);
        }

        return headers.get(headerName);
    }
}
