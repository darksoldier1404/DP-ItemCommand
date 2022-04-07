package com.darksoldier1404.dic.functions;

import com.darksoldier1404.dic.ItemCommand;
import com.darksoldier1404.dppc.utils.NBT;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.Iterator;

import static com.darksoldier1404.dic.ItemCommand.prefix;

@SuppressWarnings("all")
public class DICFunction {
    private static final ItemCommand plugin = ItemCommand.getInstance();

    public static void addLeftCommand(Player p, String[] args) {
        if (isHoldItem(p)) {
            String cmd = getArgs(args, 1);
            p.setItemInHand(NBT.setStringTag(p.getItemInHand(), "left_cmd", cmd));
            p.sendMessage(prefix + plugin.lang.getWithArgs("func_left_cmd_registered", cmd));
        } else {
            p.sendMessage(prefix + plugin.lang.get("func_item_required"));
        }
    }

    public static void addRightCommand(Player p, String[] args) {
        if (isHoldItem(p)) {
            String cmd = getArgs(args, 1);
            p.setItemInHand(NBT.setStringTag(p.getItemInHand(), "right_cmd", cmd));
            p.sendMessage(prefix + plugin.lang.getWithArgs("func_right_cmd_registered", cmd));
        } else {
            p.sendMessage(prefix + plugin.lang.get("func_item_required"));
        }
    }

    public static void addShiftSwapCommand(Player p, String[] args) {
        if (isHoldItem(p)) {
            String cmd = getArgs(args, 1);
            p.setItemInHand(NBT.setStringTag(p.getItemInHand(), "shift_f_cmd", cmd));
            p.sendMessage(prefix + plugin.lang.getWithArgs("func_shift_swap_cmd_registered", cmd));
        } else {
            p.sendMessage(prefix + plugin.lang.get("func_item_required"));
        }
    }

    public static void removeLeftCommand(Player p) {
        if (isHoldItem(p)) {
            p.setItemInHand(NBT.removeTag(p.getItemInHand(), "left_cmd"));
            p.sendMessage(prefix + plugin.lang.get("func_left_cmd_deleted"));
        } else {
            p.sendMessage(prefix + plugin.lang.get("func_item_required"));
        }
    }

    public static void removeRightCommand(Player p) {
        if (isHoldItem(p)) {
            p.setItemInHand(NBT.removeTag(p.getItemInHand(), "right_cmd"));
            p.sendMessage(prefix + plugin.lang.get("func_right_cmd_deleted"));
        } else {
            p.sendMessage(prefix + plugin.lang.get("func_item_required"));
        }
    }

    public static void removeShiftSwapCommand(Player p) {
        if (isHoldItem(p)) {
            p.setItemInHand(NBT.removeTag(p.getItemInHand(), "shift_f_cmd"));
            p.sendMessage(prefix + plugin.lang.get("func_shift_swap_cmd_deleted"));
        } else {
            p.sendMessage(prefix + plugin.lang.get("func_item_required"));
        }
    }

    public static void makeAdminLeftCommand(Player p) {
        if (isHoldItem(p)) {
            if (NBT.hasTagKey(p.getItemInHand(), "left_cmd")) {
                if (NBT.hasTagKey(p.getItemInHand(), "left_cmd_isAdmin")) {
                    p.setItemInHand(NBT.removeTag(p.getItemInHand(), "left_cmd_isAdmin"));
                    p.sendMessage(prefix + plugin.lang.get("func_left_cmd_make_admin"));
                } else {
                    p.setItemInHand(NBT.setStringTag(p.getItemInHand(), "left_cmd_isAdmin", "true"));
                    p.sendMessage(prefix + plugin.lang.get("func_left_cmd_make_admin"));
                }
            } else {
                p.sendMessage(prefix + plugin.lang.get("func_left_cmd_is_not_registered"));
            }
        } else {
            p.sendMessage(prefix + plugin.lang.get("func_item_required"));
        }
    }

    public static void makeAdminRightCommand(Player p) {
        if (isHoldItem(p)) {
            if (NBT.hasTagKey(p.getItemInHand(), "right_cmd")) {
                if (NBT.hasTagKey(p.getItemInHand(), "right_cmd_isAdmin")) {
                    p.setItemInHand(NBT.removeTag(p.getItemInHand(), "right_cmd_isAdmin"));
                    p.sendMessage(prefix + plugin.lang.get("func_right_cmd_make_admin"));
                } else {
                    p.setItemInHand(NBT.setStringTag(p.getItemInHand(), "right_cmd_isAdmin", "true"));
                    p.sendMessage(prefix + plugin.lang.get("func_right_cmd_make_admin"));
                }
            } else {
                p.sendMessage(prefix + plugin.lang.get("func_right_cmd_is_not_registered"));
            }
        } else {
            p.sendMessage(prefix + plugin.lang.get("func_item_required"));
        }
    }

    public static void makeAdminSwapCommand(Player p) {
        if (isHoldItem(p)) {
            if (NBT.hasTagKey(p.getItemInHand(), "shift_f_cmd")) {
                if (NBT.hasTagKey(p.getItemInHand(), "shift_f_cmd_isAdmin")) {
                    p.setItemInHand(NBT.removeTag(p.getItemInHand(), "shift_f_cmd_isAdmin"));
                    p.sendMessage(prefix + plugin.lang.get("func_shift_swap_cmd_make_admin"));
                } else {
                    p.setItemInHand(NBT.setStringTag(p.getItemInHand(), "shift_f_cmd_isAdmin", "true"));
                    p.sendMessage(prefix + plugin.lang.get("func_shift_swap_cmd_make_admin"));
                }
            } else {
                p.sendMessage(prefix + plugin.lang.get("func_shift_swap_cmd_is_not_registered"));
            }
        } else {
            p.sendMessage(prefix + plugin.lang.get("func_item_required"));
        }
    }

    public static void makeConsuming(Player p) {
        if(isHoldItem(p)) {
            if(NBT.hasTagKey(p.getItemInHand(), "isConsume")) {
                p.setItemInHand(NBT.removeTag(p.getItemInHand(), "isConsume"));
                p.sendMessage(prefix + plugin.lang.get("func_make_not_consume_item"));
            }else{
                p.setItemInHand(NBT.setStringTag(p.getItemInHand(), "isConsume", "true"));
                p.sendMessage(prefix + plugin.lang.get("func_make_consume_item"));
            }
        }else{
            p.sendMessage(prefix + plugin.lang.get("func_item_required"));
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
                p.sendMessage(prefix + plugin.lang.get("func_cooldown_deleted"));
            }else{
                p.setItemInHand(NBT.setFloatTag(p.getItemInHand(), "cooldown", cooldown));
                p.sendMessage(prefix + plugin.lang.getWithArgs("func_cooldown_set", String.valueOf(cooldown)));
            }
        }else{
            p.sendMessage(prefix + plugin.lang.get("func_item_required"));
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
