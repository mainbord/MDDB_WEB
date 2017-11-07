package com.mddb.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by mainbord on 26.09.17.
 */
/*@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "DEVICE")*/
public class Cpu implements Serializable{
/*
    @Column(name = "CPU_NUMBER_OF_CORES")
    private Integer numberOfCores;

    @Column(name = "CPU_ARCHITECTURE")
    private String architecture;

    @Column(name = "CPU_MAX_FREQUENCY_PER_CORE")
    private Integer maxFrequencyPerCore; //MHZ

    @Column(name = "CPU_INSTRUCTION")
    private String instruction;

    @Column(name = "CPU_MANUFACTURING_METHOD")
    private Integer manufacturingMethod; //nm
*//*    - двухъядерный
- архитектура krait 300
- максимальная частота каждого ядра - 1.7 ГГц
- поддерживаемый набор инструкций - ARMv7
- тех.процесс 28 нм*/
}
