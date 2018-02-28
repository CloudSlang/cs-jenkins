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
package io.cloudslang.content.blueocean.validators;

import static io.cloudslang.content.blueocean.entities.constants.Constants.Patterns.HOST_PATTERN;
import static java.lang.String.format;
import static java.util.Arrays.stream;
import static java.util.Optional.ofNullable;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

public class Validators {
    private Validators() {
        // prevent instantiation
    }

    public static boolean isValidHost(String input) {
        return isNotBlank(input) && HOST_PATTERN.matcher(input).matches();
    }

    public static String getValidOrDefaultValue(String input, String defaultValue, String[] validValues) {
        return isNotBlank(input) && stream(validValues).anyMatch(filter -> filter.contains(input)) ? input : defaultValue;
    }

    public static String getValidHost(String input) {
        return ofNullable(input)
                .filter(f -> isValidHost(input))
                .orElseThrow(() -> new RuntimeException(format("Invalid endpoint: [%s] supplied. Please provide a valid endpoint!", input)));
    }
}
