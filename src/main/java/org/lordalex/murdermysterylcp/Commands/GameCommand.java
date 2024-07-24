package org.lordalex.murdermysterylcp.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.lordalex.murdermysterylcp.MurderMysteryLCP;
import org.lordalex.murdermysterylcp.Config.GameState;
import org.lordalex.murdermysterylcp.Utils.ColorUtil;
import org.lordalex.murdermysterylcp.Utils.GameUtil;

public class GameCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (args == null || args.length < 1) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                printCommandInfo(p);
                return true;
            }
        }
        if (sender.isOp()) {
            if (args[0].equalsIgnoreCase("start")) {
                GameUtil.start();
            } else if (args[0].equalsIgnoreCase("stop")) {
                if (MurderMysteryLCP.game.getState() == GameState.GAME) {
                    GameUtil.stop();
                } else if (sender instanceof Player) {
                    Player p = (Player) sender;
                    p.sendMessage(ColorUtil.getMessage("&cИгра ещё не запущена"));
                }
            }
        }
        return true;
    }

    private static void printCommandInfo(Player p) {
        p.sendMessage(ColorUtil.getMessage("&e---------- &dУправление игрой&f (&e/game&f)&e ----------"));
        p.sendMessage(ColorUtil.getMessage("&e/game&7 start&f: запустить игру"));
        p.sendMessage(ColorUtil.getMessage("&e/game&7 stop&f: остановить игру"));
        p.sendMessage(ColorUtil.getMessage("&e/game&7 flag&f: управление настройками сервера"));
        //p.sendMessage(ColorUtil.getMessage("&e/game&7 kick <ник игрока>&f: выкинуть игрока в лобби"));
        //p.sendMessage(ColorUtil.getMessage("&e/game&7 list&f: список игроков на сервере"));
        //p.sendMessage(ColorUtil.getMessage("&e/game&7 sf <ник игрока>&f: принудительно перекинуть игрока на сервер"));
    }
}
