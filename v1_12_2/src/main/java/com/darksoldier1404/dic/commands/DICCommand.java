package com.darksoldier1404.dic.commands;

import com.darksoldier1404.dic.ItemCommand;
import com.darksoldier1404.dic.functions.DICFunction;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

@SuppressWarnings("all")
public class DICCommand implements CommandExecutor, TabCompleter {
    private final String prefix = ItemCommand.prefix;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(!sender.isOp()) {
            sender.sendMessage(prefix + "§c관리자 전용 명령어 입니다.");
            return false;
        }
        if(!(sender instanceof Player)) {
            sender.sendMessage(prefix + "§a플레이어만 사용 가능합니다.");
            return false;
        }
        Player p = (Player) sender;
        if(args.length == 0) {
            p.sendMessage(prefix + "/dic L <command> - 좌클릭시 실행하는 커맨드를 설정합니다.");
            p.sendMessage(prefix + "/dic R <command> - 우클릭시 실행하는 커맨드를 설정합니다.");
            p.sendMessage(prefix + "/dic F <command> - 쉬프트 + F 시 실행하는 커맨드를 설정합니다.");
            p.sendMessage(prefix + "/dic A <L, R, F> - 해당 액션의 커맨드를 관리자 권한으로 실행되게 만듭니다. (토글)");
            p.sendMessage(prefix + "/dic D <L, R, F> - 해당 액션의 커맨드를 삭제합니다.");
            p.sendMessage(prefix + "/dic C - 사용시 소모되게 만들거나 소모되지 않게 만듭니다.");
            p.sendMessage(prefix + "/dic CD <sec> - 커맨드당 사용 쿨타임을 설정합니다.");
            return false;
        }
        if(args[0].equalsIgnoreCase("L")) {
            if(args.length == 1) {
                p.sendMessage(prefix + "커맨드를 추가해주세요.");
                return false;
            }
            DICFunction.addLeftCommand(p, args);
            return false;
        }
        if(args[0].equalsIgnoreCase("R")) {
            if(args.length == 1) {
                p.sendMessage(prefix + "커맨드를 추가해주세요.");
                return false;
            }
            DICFunction.addRightCommand(p, args);
            return false;
        }
        if(args[0].equalsIgnoreCase("F")) {
            if(args.length == 1) {
                p.sendMessage(prefix + "커맨드를 추가해주세요.");
                return false;
            }
            DICFunction.addShiftSwapCommand(p, args);
            return false;
        }
        if(args[0].equalsIgnoreCase("A")) {
            if(args.length == 1) {
                p.sendMessage(prefix + "액션을 선택해주세요.");
                return false;
            }
            if(args.length == 2) {
                if(args[1].equalsIgnoreCase("L")) {
                    DICFunction.makeAdminLeftCommand(p);
                    return false;
                }
                if(args[1].equalsIgnoreCase("R")) {
                    DICFunction.makeAdminRightCommand(p);
                    return false;
                }
                if(args[1].equalsIgnoreCase("F")) {
                    DICFunction.makeAdminSwapCommand(p);
                    return false;
                }
                p.sendMessage(prefix + "액션을 선택해주세요.");
                return false;
            }
        }
        if(args[0].equalsIgnoreCase("D")) {
            if(args.length == 1) {
                p.sendMessage(prefix + "액션을 선택해주세요.");
                return false;
            }
            if(args.length == 2) {
                if(args[1].equalsIgnoreCase("L")) {
                    DICFunction.removeLeftCommand(p);
                    return false;
                }
                if(args[1].equalsIgnoreCase("R")) {
                    DICFunction.removeRightCommand(p);
                    return false;
                }
                if(args[1].equalsIgnoreCase("F")) {
                    DICFunction.removeShiftSwapCommand(p);
                    return false;
                }
                p.sendMessage(prefix + "액션을 선택해주세요.");
                return false;
            }
        }
        if(args[0].equalsIgnoreCase("C")) {
            DICFunction.makeConsuming(p);
            return false;
        }
        if(args[0].equalsIgnoreCase("CD")) {
            if(args.length == 1) {
                p.sendMessage(prefix + "쿨다운을 입력해주세요.");
                return false;
            }
            if(args.length == 2) {
                try{
                    float cooldown = Float.parseFloat(args[1]);
                    DICFunction.makeCooldown(p, cooldown);
                    return false;
                }catch (Exception e){
                    p.sendMessage(prefix + "숫자만 입력해주세요!");
                    return false;
                }
            }
        }
        return false;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(args.length == 1) {
            return Arrays.asList("L", "R", "F", "A", "D", "C", "CD");
        }
        if(args.length == 2) {
            if(args[0].equalsIgnoreCase("A") || args[0].equalsIgnoreCase("D")) {
                return Arrays.asList("L", "R", "F");
            }
        }
        return null;
    }
}
