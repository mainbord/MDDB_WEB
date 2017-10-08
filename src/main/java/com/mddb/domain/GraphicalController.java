package com.mddb.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by mainbord on 30.09.17.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GraphicalController {
    private String name;
    private Integer cpuClock; // MHZ
}
