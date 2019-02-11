package com.app.equipe.controiai2.auxiliares;

/**
 * Created by Matt on 21/03/2017.
 */

public class VerifNotificações {
    private String msg1,msg2,msg3,msg4,msg5,msg6,msg7,msg8,msg9,msg10,msg11,msg12,msg13;
    private int aux;
    public VerifNotificações(){
        msg1 = "Olá, a equipe Constrói Aí? agradece pelo seu cadastro, esperamos te ajudar nas suas proximas construçes. Qualquer duvida entre em contato, vamos ter o prazer de te ajudar e de ouvir sua opiniao";
        msg2 = "Parabéns, você ganhou seu primeiro seguidor. Você pode pedir a ele uma avaliação";
        msg3 = "Parabéns, você recebeu sua primeira chamada de trabalho. Que tal fazer uma ligação para essa pessoa?";
        msg4 = "Parabéns, você já tem "+aux+" avaliações, continue assim e você vai ficar muito famoso na construção civil";
        msg5 = "Parabéns, você já tem "+aux+" seguidores, parabéns. Isso mostra que você esta fazendo um bom trabalho.";
        msg6 = "Parabéns, você já tem "+aux+" chamadas de trabalho. Muito bom traballho, esperamos continuar te ajudando nas suas proximas construções ";
        msg7 = "Olá, adicione fotos de seus antigos trabalhos, por que isso vai te ajudar a receber mais mensagens e ligações";
        msg8 = "Olá, crie uma descrição detalhada no seu perfil, por que isso vai te ajudar a receber mais mensagens e ligações";
        msg9 = "Olá, parabéns você recebeu sua primeira avaliação, continue melhorando.";
    }
    public String getMsg(int code,int aux){
        this.aux = aux;
        switch (code){
            case 1:
                return msg1;
            case 2:
                return msg2;
            case 3:
                return msg3;
            case 4:
                return msg4;
            case 5:
                return msg5;
            case 6:
                return msg6;
            case 7:
                return msg7;
            case 8:
                return msg8;
            case 9:
                return msg9;
        }
        return null;
    }

}
