package net.countercraft.movecraft.combat.commands;

import net.countercraft.movecraft.combat.MovecraftCombat;
import net.countercraft.movecraft.combat.localisation.I18nSupport;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

import static net.countercraft.movecraft.util.ChatUtils.MOVECRAFT_COMMAND_PREFIX;

public class TracerSettingCommand implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if(!command.getName().equalsIgnoreCase("tracersetting"))
            return false;

        if(!(commandSender instanceof Player)) {
            commandSender.sendMessage(MOVECRAFT_COMMAND_PREFIX + I18nSupport.getInternationalisedString("Command - Must Be Player"));
            return true;
        }
        Player player = (Player) commandSender;

        if(args.length == 0) {
            commandSender.sendMessage(MOVECRAFT_COMMAND_PREFIX + I18nSupport.getInternationalisedString("Command - Current Setting") + ": " + MovecraftCombat.getInstance().getPlayerManager().getSetting(player));
            return true;
        }
        if(args.length != 1) {
            commandSender.sendMessage(MOVECRAFT_COMMAND_PREFIX + I18nSupport.getInternationalisedString("Command - Specify Setting"));
            return true;
        }

        String setting = args[0].toUpperCase();
        if(!setting.equals("OFF") && !setting.equals("LOW") && !setting.equals("MEDIUM") && !setting.equals("HIGH")) {
            commandSender.sendMessage(MOVECRAFT_COMMAND_PREFIX + I18nSupport.getInternationalisedString("Command - Specify Valid Setting"));
            return true;
        }

        MovecraftCombat.getInstance().getPlayerManager().setSetting(player, setting);
        commandSender.sendMessage(MOVECRAFT_COMMAND_PREFIX + I18nSupport.getInternationalisedString("Command - Tracer Set") + ": " + setting);
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String s, String[] strings) {
        final List<String> tabCompletions = new ArrayList<>();
        if (strings.length <= 1) {
            tabCompletions.add("OFF");
            tabCompletions.add("MEDIUM");
            tabCompletions.add("HIGH");
            tabCompletions.add("LOW");
        }
        if (strings.length == 0) {
            return tabCompletions;
        }
        final List<String> completions = new ArrayList<>();
        for (String completion : tabCompletions) {
            if (!completion.startsWith(strings[strings.length - 1].toUpperCase())) {
                continue;
            }
            completions.add(completion);
        }
        return completions;
    }
}
