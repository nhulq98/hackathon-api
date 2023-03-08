package com.laptrinhjavaweb.util;

import company.dto.Coordinate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ToaDoChoTruocCA {

    public static List<Coordinate> Hor_toaDoBenDuoi_SatBien(){
        return Stream.of(new Coordinate(8, 0), new Coordinate(9, 0), new Coordinate(10, 0)).collect(Collectors.toList());
    }

    public static List<Coordinate> Hor_toaDoBenTren_SatBien(){
        return Stream.of(new Coordinate(8, 7), new Coordinate(9, 7), new Coordinate(10, 7)).collect(Collectors.toList());
    }

    public static List<Coordinate> Hor_toaDoBenTrai_SatBien(){
        return Stream.of(new Coordinate(0, 4), new Coordinate(0, 3), new Coordinate(0, 2)).collect(Collectors.toList());
    }

    public static List<Coordinate> Hor_toaDoBenPhai_SatBien(){
        return Stream.of(new Coordinate(19, 4), new Coordinate(19, 3), new Coordinate(19, 2)).collect(Collectors.toList());
    }


    public static List<List<Coordinate>> ListToaDoChoTruocCA(){
        List<List<Coordinate>> dsToaDo = new ArrayList<>();

        //ct1
//        dsToaDo.add(Hor_toaDoBenDuoi_SatBien());
//        dsToaDo.add(Hor_toaDoBenTren_SatBien());
//        dsToaDo.add(Hor_toaDoBenPhai_SatBien());
//        dsToaDo.add(Hor_toaDoBenTrai_SatBien());


        //ct2




        return dsToaDo;
    }
}
