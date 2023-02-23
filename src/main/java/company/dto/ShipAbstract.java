package company.dto;

import java.util.ArrayList;
import java.util.List;

public abstract class ShipAbstract extends Coordinate {
    public int pieces;
    public String type;
    public List<Coordinate> coordinates = new ArrayList<>();
}
