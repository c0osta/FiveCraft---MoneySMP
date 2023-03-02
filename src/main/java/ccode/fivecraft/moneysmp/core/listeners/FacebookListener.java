package ccode.fivecraft.moneysmp.core.listeners;
/*/
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.restfb.*;
import com.restfb.json.JsonObject;
import com.restfb.types.Page;
import org.json.simple.JSONObject;
import com.restfb.FacebookCallback;
import com.restfb.FacebookClient.AccessToken;
import com.restfb.exception.FacebookException;
import com.restfb.types.PageMessage;
import com.restfb.types.PageMessageItem;
import com.restfb.types.send.IdMessageRecipient;
import com.restfb.types.send.Message;
import com.restfb.types.send.SendResponse;

import org.bukkit.entity.Player;
import org.bukkit.Server;

public class FacebookListener
{

    private final FacebookClient facebookClient;
    private final String fanpageId;
    private final String rewardMessage;
    private final int rewardAmount;
    private final Server server;

    private Map<String, Long> claimedRewards = new HashMap<>();

    public FacebookListener(String accessToken, String fanpageId, String rewardMessage, int rewardAmount, Server server) {
        this.facebookClient = new DefaultFacebookClient(accessToken, Version.VERSION_3_3);
        this.fanpageId = fanpageId;
        this.rewardMessage = rewardMessage;
        this.rewardAmount = rewardAmount;
        this.server = server;
    }

    public void startListening() {
        facebookClient.fetchConnection(fanpageId + "/conversations", PageMessageInbox.class)
                .forEach(pageMessageInbox -> pageMessageInbox.getData()
                        .forEach(pageMessage -> {
                            if (pageMessage.getMessages() != null) {
                                pageMessage.getMessages().getData().stream()
                                        .filter(message -> !claimedRewards.containsKey(message.getFrom().getId()))
                                        .forEach(message -> {
                                            String recipientId = message.getFrom().getId();
                                            String messageText = message.getMessage();

                                            // Sprawdzanie, czy gracz jest online
                                            Player player = server.getPlayerExact(recipientId);
                                            if (player == null) {
                                                sendFacebookMessage(recipientId, "Przepraszamy, ale jesteś offline. Nagroda nie została przyznana.");
                                                return;
                                            }

                                            if (messageText.equalsIgnoreCase(rewardMessage)) {
                                                // Przyznawanie nagrody
                                                claimedRewards.put(recipientId, System.currentTimeMillis());
                                                player.sendMessage("Gratulacje! Otrzymałeś nagrodę za polubienie naszego fanpage'a!");

                                                // Wysyłanie wiadomości zwrotnej
                                                sendFacebookMessage(recipientId, "Dziękujemy za polubienie naszego fanpage'a. Otrzymujesz " + rewardAmount + " monet.");
                                            }
                                        });
                            }
                        }));
    }

    private void sendFacebookMessage(String recipientId, String message) {
        IdMessageRecipient recipient = new IdMessageRecipient(recipientId);
        Message messageObj = new Message(message);
        try {
            JsonObject response = facebookClient.publish(recipientId + "/messages", JsonObject.class,
                    Parameter.with("recipient", recipient), Parameter.with("message", messageObj));
            if (response.getBoolean("success")) {
                System.out.println("Wiadomość została wysłana na Facebooka.");
            }
        } catch (FacebookException e) {
            e.printStackTrace();
        }
    }







    private boolean isFan(String senderId) {
        try {
            // pobranie informacji o fanie strony
            Page fanpage = facebookClient.fetchObject(fanpageId, Page.class,
                    Parameter.with("fields", "fan_count,fan_status"),
                    Parameter.with("access_token", facebookClient.getAccessToken()));
            if (fanpage != null && fanpage.getFanStatus() != null) {
                return fanpage.getFanStatus().equals("fan");
            }
        } catch (FacebookException e) {
            e.printStackTrace();
        }
        return false;
    }
}/*/
