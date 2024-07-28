package org.lordalex.murdermysterylcp.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.lordalex.murdermysterylcp.MurderMysteryLCP;
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
                GameUtil.stop();
            } else if (args[0].equalsIgnoreCase("kick")) {
                if (args.length > 1 && args[1] != null) {
                    Bukkit.getPlayer(args[1]).kickPlayer("Вы были кикнуты управляющим");
                }
            } else if (args[0].equalsIgnoreCase("list") && sender instanceof Player) {
                Player p = (Player) sender;
                p.sendMessage("Онлайн игроков на сервере: " + Bukkit.getServer().getWorld("world").getPlayers().size());
                for (Player all : Bukkit.getServer().getWorld("world").getPlayers()) {
                    p.sendMessage(all.getName());
                }
            } else if (args[0].equalsIgnoreCase("flag")) {
                if (args.length > 1) {
                    System.out.println("FLAG");
                }
                else if(sender instanceof Player){
                    Player p = (Player) sender;
                    printFlagInfo(p);
                }
            }
            return true;
        }
        return true;
    }

    private static void printCommandInfo(Player p) {
        p.sendMessage(ColorUtil.getMessage("&e---------- &dУправление игрой&f (&e/game&f)&e ----------"));
        p.sendMessage(ColorUtil.getMessage("&e/game&7 start&f: запустить игру"));
        p.sendMessage(ColorUtil.getMessage("&e/game&7 stop&f: остановить игру"));
        p.sendMessage(ColorUtil.getMessage("&e/game&7 kick <ник игрока>&f: выкинуть игрока в лобби"));
        p.sendMessage(ColorUtil.getMessage("&e/game&7 list&f: список игроков на сервере"));
        p.sendMessage(ColorUtil.getMessage("&e/game&7 flag&f: управление настройками сервера"));
    }

    private static void printFlagInfo(Player p){
        p.sendMessage(ColorUtil.getMessage("&e---------- &dНастройки сервера&f (&e/game flag&f)&e ----------"));
        p.sendMessage(ColorUtil.getMessage("&dgame-duration&f (&7Длительность игры в минутах&f): " + MurderMysteryLCP.game.getGAME_DURATION()));
        p.sendMessage(ColorUtil.getMessage("&dgold-rate&f (&7Частота появления золота в секунду&f): " + MurderMysteryLCP.game.getGOLD_RATE()));
        p.sendMessage(ColorUtil.getMessage("&dmax-gold&f (&7Максимальное количество золота на карте&f): " + MurderMysteryLCP.game.getMAX_GOLD()));
        p.sendMessage(ColorUtil.getMessage("&dbow-recharge&f (&7Время перезарядки лука после выстрела в секундах&f): " + MurderMysteryLCP.game.getBOW_RECHARGE()));
        p.sendMessage(ColorUtil.getMessage("&dshot-cost&f (&7Цена выстрела в золоте&f): " + MurderMysteryLCP.game.getSHOT_COST()));
        p.sendMessage(ColorUtil.getMessage("&dsword-recharge&f (&7Время перезарядки меча после броска в секундах&f): " + MurderMysteryLCP.game.getSWORD_RECHARGE()));
        p.sendMessage(ColorUtil.getMessage("&dgive-weapon-delay&f (&7Задержка перед выдачей меча маньякам в секундах&f): " + MurderMysteryLCP.game.getGIVE_WEAPON_DELAY()));
        p.sendMessage(ColorUtil.getMessage("&dmurder-count&f (&7Количество маньяков&f): " + MurderMysteryLCP.game.getMURDER_COUNT()));
        p.sendMessage(ColorUtil.getMessage("&dmurder-speed&f (&7Скорость маньяка&f): " + MurderMysteryLCP.game.getMURDER_SPEED()));
        p.sendMessage(ColorUtil.getMessage("&ddetective-speed&f (&7Скорость детектива&f): " + MurderMysteryLCP.game.getDETECTIVE_SPEED()));
        p.sendMessage(ColorUtil.getMessage("&dinnocent-speed&f (&7Скорость невинных&f): " + MurderMysteryLCP.game.getINNOCENT_SPEED()));
    }
}
