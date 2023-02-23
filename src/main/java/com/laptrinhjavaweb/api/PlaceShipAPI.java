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
        GameStartRS gameStartRS = placeShips(gameInviteRQ);
        return gameStartRS;
    }
}
