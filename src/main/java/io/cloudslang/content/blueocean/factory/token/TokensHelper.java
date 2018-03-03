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
package io.cloudslang.content.blueocean.factory.token;

import io.cloudslang.content.blueocean.entities.builders.InputsWrapper;

import java.util.HashMap;
import java.util.Map;

import static io.cloudslang.content.blueocean.entities.constants.Constants.Miscellaneous.AMPERSAND;
import static io.cloudslang.content.blueocean.entities.constants.Constants.Miscellaneous.EQUAL;
import static io.cloudslang.content.blueocean.utils.InputsUtil.getParamsString;

class TokensHelper {
    private static final String EXPIRY_TIME_IN_MINS = "expiryTimeInMins";

    private TokensHelper() {
        // prevent instantiation
    }

    static String getTokensParamsString(InputsWrapper wrapper) {
        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put(EXPIRY_TIME_IN_MINS, wrapper.getTokenInputs().getExpiryTimeInMins());

        return getParamsString(paramsMap, EQUAL, AMPERSAND, true);
    }
}
