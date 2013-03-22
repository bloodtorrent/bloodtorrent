package org.bloodtorrent.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Location implements Serializable {
    @NonNull
    private String address;
    @NonNull
    private String city;
    @NonNull
    private String state;
}
