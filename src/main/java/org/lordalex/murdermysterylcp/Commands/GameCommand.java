package org.lordalex.murdermysterylcp.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

public class MMCommand  implements CommandExecutor{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(args == null || args.length < 1){
            if(sender instanceof Player){
                Player p = (Player) sender;
                printCommandInfo(p);
                return true;
            }
        }
        if(sender.isOp()) {
            if (args[0].equalsIgnoreCase("start")) {
                GameUtil.start();
            } else if (args[0].equalsIgnoreCase("stop")) {
                if (TheBridgeLCP.game.getState() == GameState.STARTING) {
                    GameUtil.interrupt();
                } else if (TheBridgeLCP.game.getState() == GameState.GAME) {
//                    int max1 = Integer.MIN_VALUE;
//                    int max2 = Integer.MIN_VALUE;
//                    for(TBTeam team : TheBridgeLCP.teams){
//                        if(team.getPoints() > max1){
//                            max1 = team.getPoints();
//                        }
//                        else if(team.getPoints() == max2){
//
//                        }
//                    }
                    GameUtil.finish(null);
                } else {
                    if (sender instanceof Player) {
                        Player p = (Player) sender;
                        p.sendMessage(ColorUtil.getMessage("&cИгра ещё не запущена"));
                    }
                }
            }
        }
        return true;
    }
}
