package com.mddb.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by mainbord on 30.09.17.
 */
/*@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "DEVICE")*/
public class Ram  implements Serializable {
/*
    @Column(name = "RAM_SIZE")
    private Integer size; //mb 1024

    @Column(name = "RAM_TYPE")
    private String type; //LPDDR2

    @Column(name = "RAM_CLOCK")
    private Integer clock; //MHZ
    *//*Оперативная память (ОЗУ, RAM): всего 2048 Мб, двухканальная LPDDR2, частота 500 МГц, доступно ~1800 Мб, свободно ~1300 Мб*/
}
