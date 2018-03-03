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


import io.cloudslang.content.httpclient.HttpClientInputs;

public class InputsWrapper {
    private final HttpClientInputs httpClientInputs;
    private final CommonInputs commonInputs;
    private final TokenInputs tokenInputs;
    private final UserInputs userInputs;

    private final String token;

    private InputsWrapper(Builder builder) {
        this.httpClientInputs = builder.httpClientInputs;
        this.commonInputs = builder.commonInputs;
        this.tokenInputs = builder.tokenInputs;
        this.userInputs = builder.userInputs;

        this.token = builder.token;
    }

    public HttpClientInputs getHttpClientInputs() {
        return httpClientInputs;
    }

    public CommonInputs getCommonInputs() {
        return commonInputs;
    }

    public TokenInputs getTokenInputs() {
        return tokenInputs;
    }

    public UserInputs getUserInputs() {
        return userInputs;
    }

    public String getToken() {
        return token;
    }

    public static class Builder {
        private HttpClientInputs httpClientInputs;
        private CommonInputs commonInputs;
        private TokenInputs tokenInputs;
        private UserInputs userInputs;

        private String token;

        public InputsWrapper build() {
            return new InputsWrapper(this);
        }

        public Builder withHttpClientInputs(HttpClientInputs httpClientInputs) {
            this.httpClientInputs = httpClientInputs;
            return this;
        }

        public Builder withCommonInputs(CommonInputs commonInputs) {
            this.commonInputs = commonInputs;
            return this;
        }

        public Builder withTokenInputs(TokenInputs tokenInputs) {
            this.tokenInputs = tokenInputs;
            return this;
        }

        public Builder withUserInputs(UserInputs userInputs) {
            this.userInputs = userInputs;
            return this;
        }

        public Builder withToken(String input) {
            token = input;
            return this;
        }
    }
}
