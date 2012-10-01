/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.google.jplurk_oauth.data;

import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author SkyforceShen
 */
public class UserChannel extends Data {

    @Override
    public String toString() {
        return json.toString();
    }

    public UserChannel(JSONObject json) {
         super(json);
    }

    public String getChannelName() throws JSONException {
        return get("channel_name");
    }

    public String getCometServer() throws JSONException {
        return get("comet_server");
    }

}