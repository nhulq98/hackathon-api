package company;

import company.dto.*;
import company.request.GameInviteRQ;
import company.response.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BattleShips2 extends ShipAbstract {
    public static int numRows = 20;
    public static int numCols = 8;
    public static String HORIZON = "HORIZON";
    public static String VERTICAL = "VERTICAL";
    public static int playerShips;
    public static int computerShips;
    public static String[][] grid = new String[numRows][numCols];
    public static List<List<Coordinate>> occupied = new ArrayList<>();

    public static GameStartRS placeShips(GameInviteRQ ships){
        occupied = new ArrayList<>();
        GameStartRS response = new GameStartRS();

        // đem các tàu dễ ra bỏ vào đầu mảng để sắp trước
        GameInviteRQ ships2 = new GameInviteRQ();
        ships.ships.forEach(item ->{
            if(item.type.equals("DD") || item.type.equals("CA") || item.type.equals("BB")){
                ships2.ships.add(0, item);
            }else{
                ships2.ships.add(item);
            }
        });

        // Generate Coordinate
        ships2.ships.forEach(item -> {

            List<Coordinate> coordinates;
            Destroyer destroyerInfo = new Destroyer();
            Cruiser cruiserInfo = new Cruiser();
            OilRig oilRigInfo = new OilRig();
            BattleShip battleShipInfo = new BattleShip();
            Carrier carrierInfo = new Carrier();

            switch (item.type){
                case "DD":{
                    int lenOfShip = destroyerInfo.pieces;

                    for(int i = 0; i < item.quantity; i++){
                        DestroyerRS destroyerRS = new DestroyerRS();
                        destroyerRS.coordinates = findCoordinateByShip(lenOfShip, item.type);

                        //Step3: add Coordinate to resp
                        response.ships.add(destroyerRS);

                        //step4: add to occupied
                        occupied.add(destroyerRS.coordinates);
                    }
                    break;
                }

                case "CA": {
                    int lenOfShip = cruiserInfo.pieces;

                    for(int i = 0; i < item.quantity; i++){
                        CruiserRS cruiserRS = new CruiserRS();

                        cruiserRS.coordinates = findCoordinateByShip(lenOfShip, item.type);

                        //Step3: add Coordinate to resp
                        response.ships.add(cruiserRS);

                        //step4: add to occupied
                        occupied.add(cruiserRS.coordinates);
                    }

                    break;
                }

                case "OR": {
                    boolean reRandom;

                    List<Coordinate> remainCoordinates;

                    for(int z = 0; z < item.quantity; z++){

                        remainCoordinates = new ArrayList<>();
                        OilRigRS oilRigRS = new OilRigRS();
                        String direction = HORIZON;// TÌm điểm tiếp theo. Khởi tạo là chiều ngang
                        Coordinate rootFisrtCoordinate;
                        Coordinate rootSecondCoordinate;

                        do {
                            reRandom = false;
                            coordinates = new ArrayList<>();

                            //Step1: Random find rootCoordinate
                            rootFisrtCoordinate = findRootCoordinate();

                            //Step2: find root second
                            rootSecondCoordinate = findRootSecondForOR(rootFisrtCoordinate, direction);

                            //Step3: Find all remain Coordinates
                            if(rootSecondCoordinate == null){
                                continue;
                            }

                            remainCoordinates = findAllCoordinateRemainForOR(rootFisrtCoordinate, rootSecondCoordinate, direction);

                            if(remainCoordinates == null) {
                                reRandom = true; // re find
                            }

                        }while(reRandom == true);

                        coordinates.add(rootFisrtCoordinate);
                        coordinates.add(rootSecondCoordinate);
                        coordinates.addAll(remainCoordinates);

                        oilRigRS.coordinates = coordinates;

                        //Step3: add Coordinate to resp
                        response.ships.add(oilRigRS);

                        //step4: add to occupied
                        occupied.add(oilRigRS.coordinates);
                    }

                    break;
                }

                case "BB":{
                    // khởi tạo tàu chiến
                    int lenOfShip = battleShipInfo.pieces;

                    for(int i = 0; i < item.quantity; i++){
                        BattleShipRS battleShipRS = new BattleShipRS();

                        // get Coordinate
                        battleShipRS.coordinates =  findCoordinateByShip(lenOfShip, item.type);

                        //Step3: add Coordinate to resp
                        response.ships.add(battleShipRS);

                        //step4: add to occupied
                        occupied.add(battleShipRS.coordinates);
                    }
                    break;
                }

                case "CV": {
                    int lenOfShip = carrierInfo.pieces;

                    for(int i = 0; i < item.quantity; i++){

                        CarrierRS carrierRS = new CarrierRS();
                        String direction = HORIZON;
                        boolean reRandom = false;
                        Coordinate rootCoordinate;
                        List<Coordinate> threeCoordinates;
                        Coordinate roofCoordinate = new Coordinate();

                        do {
                            coordinates = new ArrayList<>();

                            // Step1: find root
                            rootCoordinate = findRootCoordinate();

                            // Step2: Tìm 3 điểm kế tiếp
                            threeCoordinates = findThreeCoordinates(rootCoordinate, direction, lenOfShip);

                            // Step Final: Find final coordinate
                            if(threeCoordinates.size() == 3){
                                roofCoordinate = findRoofCoordinate(threeCoordinates, direction);
                                if(roofCoordinate == null){
                                    reRandom = true;
                                }
                            }else{
                                threeCoordinates = new ArrayList<>();
                                reRandom = false;
                            }

                            if(reRandom == false){
                                coordinates.add(rootCoordinate);
                                coordinates.addAll(threeCoordinates);
                                coordinates.add(roofCoordinate);
                            }

                        }while(coordinates.size() != 5);

                        carrierRS.coordinates = coordinates;

                        //Step3: add Coordinate to resp
                        response.ships.add(carrierRS);

                        //step4: add to occupied
                        occupied.add(carrierRS.coordinates);
                    }

                    break;
                }
                default:
            }

        });

        return response;
    }


    public static int[][] missedGuesses = new int[numRows][numCols];

    public static Coordinate randomCoordinate(){
        int xMin = 0, xMax = 19, yMin = 0, yMax = 7;
        int xRand = 0;
        int yRand = 0;
        do{
            xRand = (int) ((Math.random()) * (xMax - xMin + 1)) + xMin;
            yRand = (int) ((Math.random()) * (yMax - yMin + 1)) + yMin;
        }while (!isCoordinateValid(xRand, yRand));

        return new Coordinate(xRand, yRand);
    }

    public static List<Coordinate> findCoordinateByShip(int lenOfShip, String shipType){
        List<Coordinate> coordinates;
        boolean turnAround = false; // Find coordinates finish
        Coordinate rootCoordinate;
        int sizeCondition = 0;
        do {
            coordinates = new ArrayList<>();

            //Step1: Random
            rootCoordinate = randomCoordinate();

            //Step2: find others Coordinate

            // t?m theo chi?u ngang --> t?ng x
            for (int y = 1; y < lenOfShip; y++) {
                int x1 = rootCoordinate.x;
                int y1 = rootCoordinate.y + y;
                if (isCoordinateValid(x1, y1)) {
                    Coordinate coordinateHorizon = new Coordinate(x1, y1);
                    coordinates.add(coordinateHorizon);

                }else{
                    coordinates = new ArrayList<>();
                    turnAround = true;
                    break;
                }
            }
            if (turnAround == true) { // Tìm theo chiều dọc
                for (int x = 1; x < lenOfShip; x++) {
                    int x1 = rootCoordinate.x + x;
                    int y1 = rootCoordinate.y;
                    if (isCoordinateValid(x1, y1)) {
                        Coordinate coordinateVertical = new Coordinate(x1, y1);
                        coordinates.add(coordinateVertical);
                    }else{
                        coordinates = new ArrayList<>();
                    }
                }
            }

            coordinates.add(rootCoordinate);

            if(shipType.equals("DD")){
                sizeCondition = 2;
            }
            if(shipType.equals("CA")){
                sizeCondition = 3;
            }
            if(shipType.equals("BB") || shipType.equals("OR")){
                sizeCondition = 4;
            }
            if(shipType.equals("CV")){
                sizeCondition = 5;
            }

        }while(coordinates.size() != sizeCondition);

        return coordinates;
    }


    public static Coordinate findRootCoordinate(){
        return randomCoordinate();
    }

    public static List<Coordinate> findThreeCoordinates(Coordinate rootCoordinate, String direction, int lenOfShip){
        List<Coordinate> coordinates = new ArrayList<>();

        //Step2: Tìm 3 điểm kế tiếp
        if(direction.equals(HORIZON)){
            // Tìm theo chiều ngang --> tăng x
            for (int x = 1; x < lenOfShip - 1; x++) {
                int x1 = rootCoordinate.x + x;
                int y1 = rootCoordinate.y;

                if (isCoordinateValid(x1, y1)) {
                    Coordinate coordinateHorizon = new Coordinate(x1, y1);
                    coordinates.add(coordinateHorizon);
                }else{
                    coordinates = new ArrayList<>();
                    direction = VERTICAL;
                    break;
                }
            }
        }

        if (direction.equals(VERTICAL)) { // Tìm theo chiều dọc --> tăng y
            for (int y = 1; y < lenOfShip - 1; y++) {
                int x1 = rootCoordinate.x;
                int y1 = rootCoordinate.y + y;

                if (isCoordinateValid(x1, y1)) {
                    Coordinate coordinateVertical = new Coordinate(x1, y1);
                    coordinates.add(coordinateVertical);
                }else{
                    coordinates = new ArrayList<>();
                    break;
                }
            }
        }

        return coordinates;
    }

    public static Coordinate findRoofCoordinate(List<Coordinate> threeCoordinates, String direction){
        Coordinate coordinate = Optional.ofNullable(threeCoordinates.get(1)).orElseThrow(()-> new NullPointerException("Find roof of CA battleship failed"));
        boolean turnAround = false;
        if(direction.equals(HORIZON)){
            int x = coordinate.x;
            int y = coordinate.y + 1;
            if(isCoordinateValid(x, y)){
                return new Coordinate(x, y);
            }else{
                turnAround = true;
            }

            if(turnAround == true){
                int x1 = coordinate.x;
                int y1 = coordinate.y - 1;
                if(isCoordinateValid(x1, y1)){
                    return new Coordinate(x1, y1);

                }else{
                    turnAround = false; // reset
                    direction = VERTICAL;
                }
            }
        }


        if(direction.equals(VERTICAL)){
            int x = coordinate.x + 1;
            int y = coordinate.y;
            if(isCoordinateValid(x, y)){
                return new Coordinate(x, y);

            }else{
                turnAround = true;
            }

            if(turnAround == true){
                int x1 = coordinate.x - 1;
                int y1 = coordinate.y;
                if(isCoordinateValid(x1, y1)){
                    return new Coordinate(x1, y1);
                }
            }
        }

        return null;
    }

    public static Coordinate findRootSecondForOR(Coordinate rootCoordinate, String direction){

        // t?m theo chi?u ngang --> t?ng x
        if(direction == HORIZON){
            if (isCoordinateValid(rootCoordinate.x + 1, rootCoordinate.y)) {
                return new Coordinate(rootCoordinate.x + 1, rootCoordinate.y);
            }else if (isCoordinateValid(rootCoordinate.x - 1, rootCoordinate.y)) {
                return new Coordinate(rootCoordinate.x - 1, rootCoordinate.y);
            }else{
                direction = VERTICAL; // Đổi hướng tìm
            }
        }

        if(direction == VERTICAL){ // tìm điểm tiếp theo theo chiều dọc
            if (isCoordinateValid(rootCoordinate.x, rootCoordinate.y + 1)) {
                return new Coordinate(rootCoordinate.x , rootCoordinate.y + 1);
            } else if (isCoordinateValid(rootCoordinate.x, rootCoordinate.y - 1)) {
                return new Coordinate(rootCoordinate.x, rootCoordinate.y - 1);
            }
        }

        return null;
    }

    public static List<Coordinate> findAllCoordinateRemainForOR(Coordinate rootFirstCoordinate, Coordinate rootSecondCoordinate, String direction){

        // Continue find all coordinates remain
        boolean turnAround = false;
        List<Coordinate> rootCoordinates = new ArrayList<>();
        rootCoordinates.add(rootFirstCoordinate);
        rootCoordinates.add(rootSecondCoordinate);

        List<Coordinate> remainCoordinates = new ArrayList<>();

        // Find continue
        if(direction.equals(HORIZON)){
            for(int i = 0; i < rootCoordinates.size(); i++){
                Coordinate coordinate = rootCoordinates.get(i);
                if(turnAround == false){
                    int x = coordinate.x;
                    int y = coordinate.y + 1;
                    if(isCoordinateValid(x, y)){
                        turnAround = false;
                        remainCoordinates.add(new Coordinate(x, y));
                        continue;
                    }else{
                        turnAround = true;
                        remainCoordinates = new ArrayList<>(); // find 2 coordinates begin
                        i = 0;
                    }
                }

                if(turnAround == true){
                    int x1 = coordinate.x;
                    int y1 = coordinate.y - 1;
                    if(isCoordinateValid(x1, y1)){
                        remainCoordinates.add(new Coordinate(x1, y1));
                        continue;
                    }else{ // re-begin find Coordinate
                        return null;
                    }
                }
            }
        }

        if(direction.equals(VERTICAL)) { // VERTICAL
            for(int i = 0; i < rootCoordinates.size(); i++){
                Coordinate coordinate = rootCoordinates.get(i);
                int x = coordinate.x + 1;
                int y = coordinate.y;
                if(isCoordinateValid(x, y)){
                    turnAround = false;
                    remainCoordinates.add(new Coordinate(x, y));
                    continue;
                }else{
                    turnAround = true;
                    remainCoordinates = new ArrayList<>();// find 2 coordinates begin
                    i = 0;
                }

                if(turnAround == true){
                    int x1 = coordinate.x - 1;
                    int y1 = coordinate.y;
                    if(isCoordinateValid(x1, y1)){
                        remainCoordinates.add(new Coordinate(x1, y1));
                        continue;
                    }else{ // re-random
                        return null;
                    }
                }
            }
        }

        if(remainCoordinates.size() != 2){
            return null;
        }

        return remainCoordinates;
    }

    public static void createOceanMap(){
        //First section of Ocean Map
        System.out.print("  ");
        for(int i = 0; i < numCols; i++)
            System.out.print(i);
        System.out.println();

        //Middle section of Ocean Map
        for(int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = " ";
                if (j == 0)
                    System.out.print(i + "|" + grid[i][j]);
                else if (j == grid[i].length - 1)
                    System.out.print(grid[i][j] + "|" + i);
                else
                    System.out.print(grid[i][j]);
            }
            System.out.println();
        }

        //Last section of Ocean Map
        System.out.print("  ");
        for(int i = 0; i < numCols; i++)
            System.out.print(i);
        System.out.println();
    }

    public static boolean isCoordinateValid(int x, int y){
        if (!is_overlap_other_ship(x, y) && !is_outside_board(x, y)) {
            return true;
        }
        return false;
    }

    public static boolean is_outside_board(int x, int y){
        if((x >= 0 && x < numRows) && (y >= 0 && y < numCols)){
            return false;
        }
        System.out.println("You can't place ships outside the " + numRows + " by " + numCols + " grid");
        return true;
    }

    public static Boolean is_overlap_other_ship(int x, int y){
        for(int i = 0; i < occupied.size(); i++){
            List<Coordinate> coordinates = occupied.get(i);
            for (int j = 0; j < coordinates.size(); j++) {
                if(coordinates.get(j).x == x && coordinates.get(j).y == y){
                    System.out.println("You can't place two or more ships on the same location (x,y) -> (" + x + ","+ y + ")");
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean get_near_positions(int x, int y){
        return true;
    }


//   not near any ship
//    public static boolean is_near_other_ship(int x, int y){
////        """
////        A position is nice when it does not near any ships.
////        :param x, y: A position need to check
////        :return:
////        """
//        nears_position = BoardHelper.get_near_positions(, width, height)
//        if BoardHelper.is_double_occupied(nears_position, allocates):
//        return True
//        return False
//    }

    public static void deployPlayerShips(GameStartRS gameStartRS){
        System.out.println("\nDeploy your ships:");

        // Get all coordinates and fill to map

        //1: get list ships
        List<ShipAbstractRS> ships = gameStartRS.ships;

        //step2: get all coordinates của ship
        for (int i = 0; i < ships.size(); i++) {
            List<Coordinate> coordinates = ships.get(i).coordinates;

            for (int j = 0; j < coordinates.size(); j++) {
                int x = coordinates.get(j).x;
                int y = coordinates.get(j).y;

                grid[x][y] = "@";
            }
        }
        printOceanMap();
    }

    public static void printOceanMap(){
        System.out.println();
        //First section of Ocean Map
        System.out.print("  ");
        for(int i = 0; i < numCols; i++)
            System.out.print(i);
        System.out.println();

        //Middle section of Ocean Map
        for(int x = 0; x < grid.length; x++) {
            System.out.print(x + "|");

            for (int y = 0; y < grid[x].length; y++){
                System.out.print(grid[x][y]);
            }

            System.out.println("|" + x);
        }

        //Last section of Ocean Map
        System.out.print("  ");
        for(int i = 0; i < numCols; i++)
            System.out.print(i);
        System.out.println();
    }
}
