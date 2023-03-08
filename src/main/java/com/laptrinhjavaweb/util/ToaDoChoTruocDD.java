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

    public static List<Coordinate> Hor_toaDoGocTrenBenPhai_KoSatBien(){
        return Stream.of(new Coordinate(17, 6), new Coordinate(18, 6)).collect(Collectors.toList());
    }

    public static List<Coordinate> Ver_toaDoGocTrenBenPhai_KoSatBien(){
        return Stream.of(new Coordinate(18, 6), new Coordinate(18, 5)).collect(Collectors.toList());
    }

    public static List<Coordinate> Hor_toaDoGocTrenBenPhai_SatBien(){
        return Stream.of(new Coordinate(18, 6), new Coordinate(19, 6)).collect(Collectors.toList());
    }

    public static List<Coordinate> Ver_toaDoGocTrenBenPhai_SatBien(){
        return Stream.of(new Coordinate(18, 7), new Coordinate(18, 6)).collect(Collectors.toList());
    }

    public static List<List<Coordinate>> ListToaDoChoTruocDD(){
        List<List<Coordinate>> dsToaDo = new ArrayList<>();

        //ct 1
        dsToaDo.add(Ver_toaDoGocTrenBenPhai_KoSatBien());
        dsToaDo.add(Ver_toaDoGocDuoiBenTrai_SatBien());

        //ct2
//        dsToaDo.add(Hor_toaDoGocTrenBenPhai_KoSatBien());
//        dsToaDo.add(Hor_toaDoGocDuoiBenTrai_SatBien());

        //ct3
//        dsToaDo.add(Ver_toaDoGocDuoiBenTrai_SatBien());
//        dsToaDo.add(Hor_toaDoGocDuoiBenPhai_SatBien());

        return dsToaDo;
    }
}
