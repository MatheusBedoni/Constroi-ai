package com.app.equipe.controiai2.auxiliares;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.app.equipe.controiai2.Service.ServicoTest;


/**
 * Created by matheus on 16/10/16.
 */

public class BroadcastReceiverAux extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        context.startService(new Intent(context, ServicoTest.class));
    }
}
