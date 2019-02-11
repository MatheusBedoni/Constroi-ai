package com.app.equipe.controiai2.Service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.app.equipe.controiai2.ProfissionalChamadas;
import com.app.equipe.controiai2.ProfissionalPerfil;
import com.app.equipe.controiai2.R;

import com.app.equipe.controiai2.auxiliares.VerifNotificações;
import com.app.equipe.controiai2.domain.Profissional;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.GregorianCalendar;

/**
 * Created by Matt on 20/12/2016.
 */

public class ServicoTest extends Service  implements ValueEventListener,Firebase.CompletionListener{
    private Profissional profissional;
    private VerifNotificações verifNotificações;
    //
    private String mensagem;
    private int codigo;
    private int dataInt;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onCreate(){
        super.onCreate();
        profissional = new Profissional();
        verifNotificações = new VerifNotificações();
        //
        GregorianCalendar gc=new GregorianCalendar();
        String mes,dia;
        int mesint = 0,diferença = 0;
        if (gc.get(gc.MONTH) >= 0 && gc.get(gc.MONTH) < 9) {
            mesint = gc.get(gc.MONTH) + 1;
            mes = "0" + mesint;
        } else {
            mesint = gc.get(gc.MONTH) + 1;
            mes = "" + mesint;
            Log.i("LOG", "MONTH: " + (gc.get(gc.MONTH) + 1));
        }
        if (gc.get(gc.DAY_OF_MONTH) >= 1 && gc.get(gc.DAY_OF_MONTH) < 10) {
            dia = "0" + gc.get(gc.DAY_OF_MONTH);
        } else {
            dia = "" + gc.get(gc.DAY_OF_MONTH);
        }
        String data = "" + gc.get(gc.YEAR) + "" + mes + "" + dia;
        dataInt = Integer.parseInt(data);
        dataInt *= -1;
    }
    //
    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        try{
            profissional.contextDataDB(this);
        }catch (Exception e){}
        //
        return(super.onStartCommand(intent, flags, startId));
    }
    //
    @Override
    public void onComplete(FirebaseError firebaseError, Firebase firebase) {}
    //
    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
            try {
                Profissional u = dataSnapshot.getValue(Profissional.class);
                profissional = u;
                int i = 0;
                // NOTIFICAÇÕES DO APLICATIVO
                // NOTIFICAÇÕES DE OUTROS USUARIOS
                if(profissional.getVip() == -13){
                    gerarNotificacaoCon();
                }
                if(profissional.getInicioVip() == -10){
                    gerarNotificacaoRenovar();
                }
                if (profissional.getPedidoAvaliacao() > 0) {
                    gerarNotificacaoAva();}
                if (profissional.getChamadas_novas() != profissional.getChamadas_velhas()) {
                        gerarNotificacao();
                } else if (profissional.getFavoritos() != profissional.getNovoFavoritos()) {
                    profissional.setFavoritos(profissional.getNovoFavoritos());
                    atualizar();
                    gerarNotificacaoFavoritos();
                } else if (profissional.getNovoVotoComentario() != profissional.getVotoComentario()) {
                    gerarNotificacaoComentario();
                }
            }catch (Exception e){
            }
    }
    //
    public void gerarNotificacaoCon(){
        //
        Intent intent = new Intent(getBaseContext(),ProfissionalPerfil.class);
        NotificationManager nm = (NotificationManager) getBaseContext().getSystemService(Context.NOTIFICATION_SERVICE);
        PendingIntent p = PendingIntent.getActivity(getBaseContext(), 0, intent, 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getBaseContext());
        //
        builder.setTicker("Ganhe mais visalizações tenha seu perfil em destaque, você será o profissional mais visto pelos contratantes e suas chances de conseguir serviços aumentarão.");
        builder.setContentTitle("Receba mais visualizações no Constrói Aí?");
        builder.setContentText("Ganhe mais visalizações tenha seu perfil em destaque, você será o profissional mais visto pelos contratantes e suas chances de conseguir serviços aumentarão.");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(getBaseContext().getResources(), R.mipmap.ic_launcher));
        builder.setContentIntent(p);
        //
        Notification n = builder.build();
        n.vibrate = new long[]{150, 300, 150, 600};
        n.flags = Notification.FLAG_AUTO_CANCEL;
        nm.notify(R.mipmap.ic_launcher, n);
        //
        profissional.setVip(-12);
        atualizar();
        //
        try{
            Uri som = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone toque = RingtoneManager.getRingtone(getBaseContext(), som);
            toque.play();
        }
        //
        catch(Exception e){}
    }
    public void gerarNotificacaoRenovar(){
        //
        Intent intent = new Intent(getBaseContext(),ProfissionalPerfil.class);
        NotificationManager nm = (NotificationManager) getBaseContext().getSystemService(Context.NOTIFICATION_SERVICE);
        PendingIntent p = PendingIntent.getActivity(getBaseContext(), 0, intent, 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getBaseContext());
        //
        builder.setTicker("Seja VIP novamente para ter seu perfil no topo denovo, você será o profissional mais visto pelos contratantes e suas chances de conseguir serviços aumentarão.");
        builder.setContentTitle("Seu periodo VIP já acabou");
        builder.setContentText("Seja VIP novamente para ter seu perfil no topo denovo, você será o profissional mais visto pelos contratantes e suas chances de conseguir serviços aumentarão.");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(getBaseContext().getResources(), R.mipmap.ic_launcher));
        builder.setContentIntent(p);
        //
        Notification n = builder.build();
        n.vibrate = new long[]{150, 300, 150, 600};
        n.flags = Notification.FLAG_AUTO_CANCEL;
        nm.notify(R.mipmap.ic_launcher, n);
        //
        profissional.setVip(-12);
        atualizar();
        //
        try{
            Uri som = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone toque = RingtoneManager.getRingtone(getBaseContext(), som);
            toque.play();
        }
        //
        catch(Exception e){}
    }

    //
    public void gerarNotificacaoMsg(){

    }
    //

    public void gerarNotificacaoAva(){
        //

    }
    //
    public void gerarNotificacaoComentario(){
        //

        Intent intent = new Intent(getBaseContext(), ProfissionalPerfil.class);

        //
        NotificationManager nm = (NotificationManager) getBaseContext().getSystemService(Context.NOTIFICATION_SERVICE);
        PendingIntent p = PendingIntent.getActivity(getBaseContext(), 0, intent, 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getBaseContext());
        //
        builder.setTicker("Voce recebeu uma nova avaliação");
        builder.setContentTitle("Avaliação");
        builder.setContentText("Voce recebeu uma nova avaliação");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(getBaseContext().getResources(), R.mipmap.ic_launcher));
        builder.setContentIntent(p);
        //
        Notification n = builder.build();
        n.vibrate = new long[]{150, 300, 150, 600};
        n.flags = Notification.FLAG_AUTO_CANCEL;
        nm.notify(R.mipmap.ic_launcher, n);
        //
//        profissional.setVotoComentario(profissional.getNovoVotoComentario());
//        atualizar();
        //
        try{
            Uri som = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone toque = RingtoneManager.getRingtone(getBaseContext(), som);
            toque.play();
        }
        catch(Exception e){}
    }
    //
    public void gerarNotificacaoFavoritos(){
        //

//        NotificationManager nm = (NotificationManager) getBaseContext().getSystemService(Context.NOTIFICATION_SERVICE);
//        PendingIntent p = PendingIntent.getActivity(getBaseContext(), 0, intent, 0);
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(getBaseContext());
//        //
//        builder.setTicker("Voce ganhou um novo seguidor");
//        builder.setContentTitle("Seguidores");
//        builder.setContentText("Voce ganhou um novo seguidor");
//        builder.setSmallIcon(R.mipmap.ic_launcher);
//        builder.setLargeIcon(BitmapFactory.decodeResource(getBaseContext().getResources(), R.mipmap.ic_launcher));
//        builder.setContentIntent(p);
//        //
//        Notification n = builder.build();
//        n.vibrate = new long[]{150, 300, 150, 600};
//        n.flags = Notification.FLAG_AUTO_CANCEL;
//        nm.notify(R.mipmap.ic_launcher, n);
//        //
//
//        profissional.setFavoritos(profissional.getNovoFavoritos());
//        atualizar();
//        //
//        try{
//            Uri som = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//            Ringtone toque = RingtoneManager.getRingtone(getBaseContext(), som);
//            toque.play();
//        }
//        catch(Exception e){}
    }
    //
    public void gerarNotificacao(){
        //
        Intent intent = new Intent(getBaseContext(),ProfissionalChamadas.class);
        NotificationManager nm = (NotificationManager) getBaseContext().getSystemService(Context.NOTIFICATION_SERVICE);
        PendingIntent p = PendingIntent.getActivity(getBaseContext(), 0, intent, 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getBaseContext());
        //
        builder.setTicker("Voce recebeu novas chamadas de trabalho");
        builder.setContentTitle("Chamada de Trabalho");
        builder.setContentText("Voce recebeu novas chamadas de trabalho");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(getBaseContext().getResources(), R.mipmap.ic_launcher));
        builder.setContentIntent(p);
        //
        Notification n = builder.build();
        n.vibrate = new long[]{150, 300, 150, 600};
        n.flags = Notification.FLAG_AUTO_CANCEL;
        nm.notify(R.mipmap.ic_launcher, n);
        //
        profissional.setChamadas_velhas(profissional.getChamadas_novas());
        atualizar();
        //
        try{
            Uri som = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone toque = RingtoneManager.getRingtone(getBaseContext(), som);
            toque.play();
        }
        catch(Exception e){}
    }

    //
    public void atualizar(){
        profissional.updateDB(this);
        profissional.updateTodosDB(this);
        profissional.updateTodosProfDB(this);
    }
    //
    @Override
    public void onCancelled(FirebaseError firebaseError) {}
}
