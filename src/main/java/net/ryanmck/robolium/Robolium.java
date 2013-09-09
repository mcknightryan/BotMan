package net.ryanmck.robolium;

import net.ryanmck.robolium.command.CommandHandler;
import net.ryanmck.robolium.config.ConfigHandler;
import org.pircbotx.PircBotX;
import org.pircbotx.exception.IrcException;
import org.pircbotx.exception.NickAlreadyInUseException;
import java.io.*;

public class Robolium {
    private static PircBotX bot;
    private static CommandHandler commandHandler;
    private static ConfigHandler configHandler;

    public static PircBotX getBot() {
        return bot;
    } //end getBot

    public static CommandHandler getCommandHandler() {
        return commandHandler;
    } //end getCommandHandler

    public static ConfigHandler getConfigHandler() {
        return configHandler;
    } // end getConfigHandler

    public static void main(String[] args) throws Exception {
        bot = new PircBotX();
        commandHandler = new CommandHandler();
        configHandler = new ConfigHandler();
        configHandler.readConfig();

        bot.getListenerManager().addListener(commandHandler);

        bot.setName(configHandler.getNick());
        bot.setLogin("Robolium");
        bot.setVerbose(true);
        try {
            bot.connect(configHandler.getServer());
        } catch (IrcException ex) {
            System.out.println("[ERROR] Could not join server");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("[ERROR] Server could not be found");
            ex.printStackTrace();
        } //end try/catch
        bot.joinChannel(configHandler.getChannel());
    } //end main
} //end class