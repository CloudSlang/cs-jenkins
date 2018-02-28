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
package blueocean.utils;

import org.junit.Test;

import static io.cloudslang.content.blueocean.utils.InputsUtil.areBothValuesPresent;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class InputsUtilTest {
    @Test
    public void shouldReturnFalse() {
        assertFalse(areBothValuesPresent(null, "  "));
    }

    @Test
    public void shouldReturnFalse2() {
        assertFalse(areBothValuesPresent("", "some_value"));
    }

    @Test
    public void shouldReturnTrue() {
        assertTrue(areBothValuesPresent("some_value", "another_value"));
    }
}
