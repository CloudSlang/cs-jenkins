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

import static org.apache.commons.lang3.Validate.notNull;

public class UserInputs {
    private final String user;

    private UserInputs(Builder builder) {
        this.user = builder.user;
    }

    public String getUser() {
        return user;
    }

    public static class Builder {
        private String user;

        public UserInputs build() {
            return new UserInputs(this);
        }

        public Builder withUser(String input) {
            notNull(input);
            user = input;
            return this;
        }
    }
}
