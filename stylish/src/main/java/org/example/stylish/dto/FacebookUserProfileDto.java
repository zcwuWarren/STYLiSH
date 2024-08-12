package org.example.stylish.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FacebookUserProfileDto {
    private String name;
    private String email;
    // use nested object to handle picture
    private Picture picture;
    private String id;

    @Data
    public class Picture {
        private Data data;

        @lombok.Data
        public class Data {
            private int height;
            private boolean is_silhouette;
            private String url;
            private int width;
        }
    }
}
