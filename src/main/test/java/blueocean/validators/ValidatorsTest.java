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
package blueocean.validators;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static blueocean.helpers.TestsUtil.setExpectedExceptions;
import static io.cloudslang.content.blueocean.validators.Validators.getValidHost;
import static io.cloudslang.content.blueocean.validators.Validators.getValidOrDefaultValue;
import static io.cloudslang.content.blueocean.validators.Validators.isValidHost;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ValidatorsTest {
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void shouldReturnFalseWhenValidHostIsNull() {
        assertFalse(isValidHost(null));
    }

    @Test
    public void shouldReturnFalseWhenValidHostIsEmptyChars() {
        assertFalse(isValidHost(" "));
    }

    @Test
    public void shouldReturnFalseWhenValidHostIsInvalid() {
        assertFalse(isValidHost("intended;InvalidHost"));
    }

    @Test
    public void shouldReturnTrueWhenValidHost() {
        assertTrue(isValidHost("somePotentialValidHost"));
    }

    @Test
    public void shouldReturnHttpString() {
        assertEquals(getValidOrDefaultValue("some crappy input here", "http", new String[]{"http", "https"}), "http");
    }

    @Test
    public void shouldReturnHttpsString() {
        assertEquals(getValidOrDefaultValue("https", "http", new String[]{"http", "https"}), "https");
    }

    @Test
    public void shouldReturnAllowAllString() {
        assertEquals(getValidOrDefaultValue("another crappy input here", "allow_all", new String[]{"allow_all", "browser_compatible", "strict"}), "allow_all");
    }

    @Test
    public void shouldReturnStrictString() {
        assertEquals(getValidOrDefaultValue("strict", "allow_all", new String[]{"allow_all", "browser_compatible", "strict"}), "strict");
    }

    @Test
    public void shouldThrowExceptionWhenGetValidHostIsNull() {
        setExpectedExceptions(RuntimeException.class, exception, "Invalid endpoint: [null] supplied. Please provide a valid endpoint!");

        getValidHost(null);
    }

    @Test
    public void shouldThrowExceptionWhenGetValidHostIsEmpty() {
        setExpectedExceptions(RuntimeException.class, exception, "Invalid endpoint: [] supplied. Please provide a valid endpoint!");

        getValidHost("");
    }

    @Test
    public void shouldReturnValidHost() {
        assertEquals(getValidHost("valid.domain.example.com"), "valid.domain.example.com");
    }
}
