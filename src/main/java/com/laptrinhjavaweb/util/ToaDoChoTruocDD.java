package com.laptrinhjavaweb.util;

import company.dto.Coordinate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ToaDoChoTruocDD {
    public static List<Coordinate> Ver_toaDoGocDuoiBenTrai_KoSatBien(){
        return Stream.of(new Coordinate(1, 1), new Coordinate(1, 2)).collect(Collectors.toList());
    }

    public static List<Coordinate> Hor_toaDoGocDuoiBenTrai_KoSatBien(){
        return Stream.of(new Coordinate(1, 1), new Coordinate(2, 1)).collect(Collectors.toList());
    }

    public static List<Coordinate> Hor_toaDoGocDuoiBenPhai_KoSatBien(){
        return Stream.of(new Coordinate(17, 1), new Coordinate(18, 1)).collect(Collectors.toList());
    }

    public static List<Coordinate> Ver_toaDoGocDuoiBenPhai_KoSatBien(){
        return Stream.of(new Coordinate(18, 1), new Coordinate(18, 2)).collect(Collectors.toList());
    }


    public static List<Coordinate> Hor_toaDoGocDuoiBenTrai_SatBien(){
        return Stream.of(new Coordinate(1, 0), new Coordinate(2, 0)).collect(Collectors.toList());
    }

    public static List<Coordinate> Ver_toaDoGocDuoiBenTrai_SatBien(){
        return Stream.of(new Coordinate(0, 1), new Coordinate(0, 2)).collect(Collectors.toList());
    }
    public static List<Coordinate> Hor_toaDoGocDuoiBenPhai_SatBien(){
        return Stream.of(new Coordinate(17, 0), new Coordinate(18, 0)).collect(Collectors.toList());
    }

    public static List<Coordinate> Ver_toaDoGocDuoiBenPhai_SatBien(){
        return Stream.of(new Coordinate(19, 1), new Coordinate(19, 2)).collect(Collectors.toList());
    }

    public static List<List<Coordinate>> ListToaDoChoTruocDD(){
        List<List<Coordinate>> dsToaDo = new ArrayList<>();

        dsToaDo.add(Hor_toaDoGocDuoiBenPhai_KoSatBien());
        dsToaDo.add(Ver_toaDoGocDuoiBenPhai_KoSatBien());

        dsToaDo.add(Hor_toaDoGocDuoiBenTrai_KoSatBien());
        dsToaDo.add(Ver_toaDoGocDuoiBenTrai_KoSatBien());

//        dsToaDo.add(Hor_toaDoGocDuoiBenTrai_SatBien());
//        dsToaDo.add(Ver_toaDoGocDuoiBenTrai_SatBien());
//
//        dsToaDo.add(Hor_toaDoGocDuoiBenPhai_SatBien());
//        dsToaDo.add(Ver_toaDoGocDuoiBenPhai_SatBien());

        return dsToaDo;
    }
}
