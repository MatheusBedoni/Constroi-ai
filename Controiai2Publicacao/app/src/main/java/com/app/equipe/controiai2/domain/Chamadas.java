package com.app.equipe.controiai2.domain;


import com.app.equipe.controiai2.util.LibraryClass;
import com.firebase.client.Firebase;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by matheus on 27/09/16.
 */
public class Chamadas {
    private String nome;
    private String tel;
    private String problema;
    private String id;
    private String tipo;
    private String url;
    private String data;
    private int aceito;


    public Chamadas(String nome,String tel,String problema,String url){
        this.nome = nome;
        this.problema = problema;
        this.tel = tel;
        this.url = url;
        this.aceito = 0;
        this.data = " ";
    }
    public Chamadas(){
        this.aceito = 0;
        this.data = " ";
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getProblema() {
        return problema;
    }

    public int getAceito() {
        return aceito;
    }

    public void setAceito(int aceito) {
        this.aceito = aceito;
    }

    public void setProblema(String problema) {
        this.problema = problema;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setIdInMap(Map<String,Object> map){
        if(getId() != null){
            map.put("id",getId());
        }else{
        }
    }
    public void setNameInMap(Map<String,Object> map){
        if(getNome() != null){
            map.put("nome",getNome());
        }else{
        }
    }
    public void setTelefoneInMap(Map<String,Object> map){
        if(getTel() != null){
            map.put("tel",getTel());
        }else{
        }
    }
    public void setProblemaInMap(Map<String,Object> map){
        if(getProblema() != null){
            map.put("problema",getProblema());
        }else{
        }
    }
    public void setTipoInMap(Map<String,Object> map){
        if(getProblema() != null){
            map.put("tipo", getTipo());
        }
    }
    public void setUrlInMap(Map<String,Object> map){
        if(getUrl() != null){
            map.put("url",getUrl());
        }else{
        }
    }
    public void setAceitoInMap(Map<String,Object>map){
        if(getNome() != null)
            map.put("aceito",getAceito());
    }
    public void setDataInMap(Map<String,Object>map){
        if(getNome() != null)
            map.put("data",getData());
    }

    public void update(String user, Firebase.CompletionListener... completionListener){
        Firebase firebase = LibraryClass.getFirebase();
        firebase = firebase.child("chamadas").child(user).child(getId());
        Map<String,Object> map = new HashMap<>();
        setIdInMap(map);
        setTipoInMap(map);
        setProblemaInMap(map);
        setAceitoInMap(map);
        setNameInMap(map);
        setTelefoneInMap(map);
        setUrlInMap(map);
        setDataInMap(map);
        if(map.isEmpty()){
            return;
        }
        if(completionListener != null && completionListener[0] != null){
            firebase.updateChildren(map,completionListener[0]);
        }else{
            firebase.updateChildren(map);
        }
    }

    public void saveChamadasDB(String id,String user){
        Firebase firebase = LibraryClass.getFirebase();
        firebase = firebase.child("orcamento").child(id);
        firebase.setValue(this);
    }
    public void removeDB( String id,String user){
        Firebase firebase = LibraryClass.getFirebase();
        firebase = firebase.child("orcamento").child(id);
        firebase.removeValue();
    }
}
