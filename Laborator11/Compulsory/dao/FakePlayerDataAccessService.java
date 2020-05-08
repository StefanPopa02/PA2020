//package com.popastefan.dao;
//
//import com.popastefan.model.Player;
//import org.springframework.stereotype.Repository;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//@Repository("fakeDao")
//public class FakePlayerDataAccessService implements PlayerDao  {
//
//    private static List<Player> DB = new ArrayList<>();
//
//    @Override
//    public int insertPlayer(UUID id, Player player) {
//        DB.add(new Player(id, player.getName()));
//        return 1;
//    }
//
//    @Override
//    public List<Player> selectAllPlayers() {
//        return DB;
//    }
//
//    @Override
//    public int deletePlayerById(UUID id) {
//        Optional<Player> chosenPlayer = selectPlayerById(id);
//        if (chosenPlayer.isEmpty()) {
//            return 0;
//        }
//        DB.remove(chosenPlayer.get());
//        return 1;
//    }
//
//    @Override
//    public int updatePlayerById(UUID id, Player update) {
//        return selectPlayerById(id)
//                .map(player -> {
//                    int indexOfPlayerToUpdate = DB.indexOf(player);
//                    if (indexOfPlayerToUpdate >= 0) {
//                        DB.set(indexOfPlayerToUpdate, new Player(id, update.getName()));
//                        return 1;
//                    }
//                    return 0;
//                }).orElse(0);
//    }
//
//    @Override
//    public Optional<Player> selectPlayerById(UUID id) {
//        return DB.stream()
//                .filter(player -> player.getId().equals(id))
//                .findFirst();
//    }
//}
