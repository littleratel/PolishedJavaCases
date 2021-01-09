package cn.work;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Resource {
    private String name;
    private int id;

    public static Builder Builder(){
        return new Builder();
    }

    public static class Builder {
        private String name;
        private int id;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Resource build() {
            return new ResourceInner(this.name, this.id);
        }
    }

    @Data
    @AllArgsConstructor
    private static class ResourceInner extends Resource {
        private String name;
        private int id;

        @Override
        public void setName(String name) {
        }

        @Override
        public void setId(int id) {
        }
    }
}
