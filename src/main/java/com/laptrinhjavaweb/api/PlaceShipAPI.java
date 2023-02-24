package com.laptrinhjavaweb.api;

import company.request.GameInviteRQ;
import company.response.GameStartRS;
import org.springframework.web.bind.annotation.*;

import static company.BattleShips2.placeShips;

@RestController
@RequestMapping("/api")
public class PlaceShipAPI {

    @PostMapping("/place-ship")
    public GameStartRS getCoordinates(@RequestBody GameInviteRQ gameInviteRQ) {
        System.out.println("----Ông nội MINH đang gọi API lén----");
        GameStartRS gameStartRS;
        try{
            gameStartRS = placeShips(gameInviteRQ);

            System.out.println("----Đã bị ổng lấy data. Hãy gọi 113----");

            return gameStartRS;
        }catch (Exception e){
            e.printStackTrace();
            return new GameStartRS();
        }
    }
}
