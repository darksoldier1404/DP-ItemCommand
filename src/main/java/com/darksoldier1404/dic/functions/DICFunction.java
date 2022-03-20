package com.darksoldier1404.dic.functions;

import com.darksoldier1404.dic.ItemCommand;
import com.darksoldier1404.dppc.utils.NBT;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.Iterator;

@SuppressWarnings("all")
public class DICFunction {
    private static final String prefix = ItemCommand.prefix;

    public static void addLeftCommand(Player p, String[] args) {
        if (isHoldItem(p)) {
            String cmd = getArgs(args, 1);
            p.setItemInHand(NBT.setStringTag(p.getItemInHand(), "left_cmd", cmd));
            p.sendMessage(prefix + "§a좌클릭 커맨드 등록 : " + cmd);
        } else {
            p.sendMessage(prefix + "§c아이템을 손에 들어주세요.");
        }
    }

    public static void addRightCommand(Player p, String[] args) {
        if (isHoldItem(p)) {
            String cmd = getArgs(args, 1);
            p.setItemInHand(NBT.setStringTag(p.getItemInHand(), "right_cmd", cmd));
            p.sendMessage(prefix + "§a우클릭 커맨드 등록 : " + cmd);
        } else {
            p.sendMessage(prefix + "§c아이템을 손에 들어주세요.");
        }
    }

    public static void addShiftSwapCommand(Player p, String[] args) {
        if (isHoldItem(p)) {
            String cmd = getArgs(args, 1);
            p.setItemInHand(NBT.setStringTag(p.getItemInHand(), "shift_f_cmd", cmd));
            p.sendMessage(prefix + "§a쉬프트 스왑 커맨드 등록 : " + cmd);
        } else {
            p.sendMessage(prefix + "§c아이템을 손에 들어주세요.");
        }
    }

    public static void removeLeftCommand(Player p) {
        if (isHoldItem(p)) {
            p.setItemInHand(NBT.removeTag(p.getItemInHand(), "left_cmd"));
            p.sendMessage(prefix + "§a좌클릭 커맨드가 삭제 되었습니다.");
        } else {
            p.sendMessage(prefix + "§c아이템을 손에 들어주세요.");
        }
    }

    public static void removeRightCommand(Player p) {
        if (isHoldItem(p)) {
            p.setItemInHand(NBT.removeTag(p.getItemInHand(), "right_cmd"));
            p.sendMessage(prefix + "§a우클릭 커맨드가 삭제 되었습니다.");
        } else {
            p.sendMessage(prefix + "§c아이템을 손에 들어주세요.");
        }
    }

    public static void removeShiftSwapCommand(Player p) {
        if (isHoldItem(p)) {
            p.setItemInHand(NBT.removeTag(p.getItemInHand(), "shift_f_cmd"));
            p.sendMessage(prefix + "§a쉬프트 스왑 커맨드가 삭제 되었습니다.");
        } else {
            p.sendMessage(prefix + "§c아이템을 손에 들어주세요.");
        }
    }

    public static void makeAdminLeftCommand(Player p) {
        if (isHoldItem(p)) {
            if (NBT.hasTagKey(p.getItemInHand(), "left_cmd")) {
                if (NBT.hasTagKey(p.getItemInHand(), "left_cmd_isAdmin")) {
                    p.setItemInHand(NBT.removeTag(p.getItemInHand(), "left_cmd_isAdmin"));
                    p.sendMessage(prefix + "§a좌클릭 커맨드가 유저 권한으로 실행 됩니다.");
                } else {
                    p.setItemInHand(NBT.setStringTag(p.getItemInHand(), "left_cmd_isAdmin", "true"));
                    p.sendMessage(prefix + "§a좌클릭 커맨드가 관리자 권한으로 실행 됩니다.");
                }
            } else {
                p.sendMessage(prefix + "§c좌클릭 커맨드가 없습니다.");
            }
        } else {
            p.sendMessage(prefix + "§c아이템을 손에 들어주세요.");
        }
    }

    public static void makeAdminRightCommand(Player p) {
        if (isHoldItem(p)) {
            if (NBT.hasTagKey(p.getItemInHand(), "right_cmd")) {
                if (NBT.hasTagKey(p.getItemInHand(), "right_cmd_isAdmin")) {
                    p.setItemInHand(NBT.removeTag(p.getItemInHand(), "right_cmd_isAdmin"));
                    p.sendMessage(prefix + "§a우클릭 커맨드가 유저 권한으로 실행 됩니다.");
                } else {
                    p.setItemInHand(NBT.setStringTag(p.getItemInHand(), "right_cmd_isAdmin", "true"));
                    p.sendMessage(prefix + "§a우클릭 커맨드가 관리자 권한으로 실행 됩니다.");
                }
            } else {
                p.sendMessage(prefix + "§c우클릭 커맨드가 없습니다.");
            }
        } else {
            p.sendMessage(prefix + "§c아이템을 손에 들어주세요.");
        }
    }

    public static void makeAdminSwapCommand(Player p) {
        if (isHoldItem(p)) {
            if (NBT.hasTagKey(p.getItemInHand(), "shift_f_cmd")) {
                if (NBT.hasTagKey(p.getItemInHand(), "shift_f_cmd_isAdmin")) {
                    p.setItemInHand(NBT.removeTag(p.getItemInHand(), "shift_f_cmd_isAdmin"));
                    p.sendMessage(prefix + "§a쉬프트+F 커맨드가 유저 권한으로 실행 됩니다.");
                } else {
                    p.setItemInHand(NBT.setStringTag(p.getItemInHand(), "shift_f_cmd_isAdmin", "true"));
                    p.sendMessage(prefix + "§a쉬프트+F 커맨드가 관리자 권한으로 실행 됩니다.");
                }
            } else {
                p.sendMessage(prefix + "§c우클릭 커맨드가 없습니다.");
            }
        } else {
            p.sendMessage(prefix + "§c아이템을 손에 들어주세요.");
        }
    }

    public static void makeConsuming(Player p) {
        if(isHoldItem(p)) {
            if(NBT.hasTagKey(p.getItemInHand(), "isConsume")) {
                p.setItemInHand(NBT.removeTag(p.getItemInHand(), "isConsume"));
                p.sendMessage(prefix + "§a무한정 사용 가능한 아이템으로 변경되었습니다.");
            }else{
                p.setItemInHand(NBT.setStringTag(p.getItemInHand(), "isConsume", "true"));
                p.sendMessage(prefix + "§a1회만 사용 가능한 아이템으로 변경되었습니다.");
            }
        }else{
            p.sendMessage(prefix + "§c아이템을 손에 들어주세요.");
        }
    }

    public static boolean isHoldItem(Player p) {
        return p.getItemInHand().getType() != Material.AIR;
    }

    public static String getArgs(String[] args, int index) {
        StringBuilder s = new StringBuilder();
        args = Arrays.copyOfRange(args, index, args.length);
        Iterator<String> i = Arrays.stream(args).iterator();
        while (i.hasNext()) {
            s.append(i.next()).append(" ");
        }
        if (s.charAt(s.length() - 1) == ' ') {
            s.deleteCharAt(s.length() - 1);
        }
        return s.toString();
    }

    public static void makeCooldown(Player p, float cooldown) {
        if(isHoldItem(p)) {
            if(NBT.hasTagKey(p.getItemInHand(), "cooldown") || cooldown == 0) {
                p.setItemInHand(NBT.removeTag(p.getItemInHand(), "cooldown"));
                p.sendMessage(prefix + "§a쿨다운을 삭제하였습니다.");
            }else{
                p.setItemInHand(NBT.setFloatTag(p.getItemInHand(), "cooldown", cooldown));
                p.sendMessage(prefix + "§a쿨다운을 " + cooldown + "초로 설정하였습니다.");
            }
        }else{
            p.sendMessage(prefix + "§c아이템을 손에 들어주세요.");
        }
    }

    public static boolean isCooldown(Player p, String cmd) {
        return ItemCommand.cooldown.contains(p.getUniqueId()+cmd);
    }

    public static void cooldown(Player p, String cmd, float cooldown) {
        ItemCommand.cooldown.add(p.getUniqueId()+cmd);
        Bukkit.getScheduler().runTaskLater(ItemCommand.getInstance(), () -> {
            ItemCommand.cooldown.remove(p.getUniqueId()+cmd);
        }, (long) (20L*cooldown));
    }
}
