package com.denizenscript.ddiscordbot.events;

import com.denizenscript.ddiscordbot.DiscordScriptEvent;
import discord4j.core.event.domain.guild.MemberLeaveEvent;
import net.aufdemrand.denizencore.objects.Element;
import net.aufdemrand.denizencore.objects.dObject;
import net.aufdemrand.denizencore.scripts.containers.ScriptContainer;
import net.aufdemrand.denizencore.utilities.CoreUtilities;

public class DiscordUserLeavesScriptEvent extends DiscordScriptEvent {

    public static DiscordUserLeavesScriptEvent instance;

    // <--[event]
    // @Events
    // discord user leaves
    //
    // @Regex ^on discord user leaves$
    // @Switch for <bot>
    //
    // @Triggers when a Discord user leaves a guild.
    //
    // @Plugin dDiscordBot
    //
    // @Context
    // <context.bot> returns the Denizen ID of the bot.
    // <context.self> returns the bots own Discord user ID.
    // <context.group> returns the group ID.
    // <context.group_name> returns the group name.
    // <context.user_id> returns the user's internal ID.
    // <context.user_name> returns the user's name.
    // -->

    public MemberLeaveEvent getEvent() {
        return (MemberLeaveEvent) event;
    }

    @Override
    public boolean couldMatch(ScriptContainer scriptContainer, String s) {
        return CoreUtilities.toLowerCase(s).startsWith("discord user leaves");
    }

    @Override
    public dObject getContext(String name) {
        if (name.equals("group")) {
            return new Element(getEvent().getGuildId().asLong());
        }
        else if (name.equals("group_name")) {
            return new Element(getEvent().getGuild().block().getName());
        }
        else if (name.equals("user_id")) {
            return new Element(getEvent().getMember().get().getId().asLong());
        }
        else if (name.equals("user_name")) {
            return new Element(getEvent().getMember().get().getUsername());
        }
        return super.getContext(name);
    }

    @Override
    public String getName() {
        return "DiscordUserLeaves";
    }
}
