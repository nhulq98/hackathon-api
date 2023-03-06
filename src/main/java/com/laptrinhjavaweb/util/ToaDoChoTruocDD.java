package com.laptrinhjavaweb.util;

import company.dto.Coordinate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ToaDoChoTruocDD {
    public static List<Coordinate> Hor_toaDoGocDuoiBenTrai_KoSatBien(){
        return Stream.of(new Coordinate(1, 0), new Coordinate(2, 0)).collect(Collectors.toList());
    }

    public static List<Coordinate> Hor_toaDoGocDuoiBenTrai_SatBien(){
        return Stream.of(new Coordinate(0, 0), new Coordinate(1, 0)).collect(Collectors.toList());
    }

    public static List<Coordinate> Ver_toaDoGocDuoiBenTrai_KoSatBien(){
        return Stream.of(new Coordinate(0, 1), new Coordinate(0, 2)).collect(Collectors.toList());
    }

    public static List<Coordinate> Ver_toaDoGocDuoiBenTrai_SatBien(){
        return Stream.of(new Coordinate(0, 0), new Coordinate(0, 1)).collect(Collectors.toList());
    }

    public static List<Coordinate> Hor_toaDoGocDuoiBenPhai_KoSatBien(){
        return Stream.of(new Coordinate(1, 0), new Coordinate(2, 0)).collect(Collectors.toList());
    }

    public static Coordinate toaDoGocDuoiBenPhai_DD1 = new Coordinate(18, 0);
    public static Coordinate toaDoGocDuoiBenPhai_DD2 = new Coordinate(17, 0);

    public static Coordinate toaDoGocTrenBenPhai_DD1 = new Coordinate(18, 7);
    public static Coordinate toaDoGocTrenBenPhai_DD2 = new Coordinate(17, 7);

    public static Coordinate toaDoGocTrenBenTrai_DD1 = new Coordinate(0, 5);
    public static Coordinate toaDoGocTrenBenTrai_DD2 = new Coordinate(0, 5);
}
