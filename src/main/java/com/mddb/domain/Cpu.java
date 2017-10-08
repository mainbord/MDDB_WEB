package com.mddb.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by mainbord on 26.09.17.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cpu {
    private Integer numberOfCores;
    private String arcitechure;
    private Integer maxFrequencyPerCore; //MHZ
    private String instruction;
    private Integer manufacturingMethod; //nm
/*    - двухъядерный
- архитектура krait 300
- максимальная частота каждого ядра - 1.7 ГГц
- поддерживаемый набор инструкций - ARMv7
- тех.процесс 28 нм*/
}
